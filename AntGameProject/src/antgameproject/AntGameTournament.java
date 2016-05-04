package antgameproject;

import antgameproject.AntBrainLoader.AntBrainLoaderException;
import antgameproject.AntWorldLoader.AntWorldLoaderException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Class to represent a tournament of ant games.
 *
 * @author Team18
 */
public class AntGameTournament {

    private List<Board> antWorlds;
    private List<AntBrain> antBrains;
    private List<Pair<AntBrain, AntBrain>> pairs;
    private Game currentGame;
    private int boardToPlayIndex;
    private final int maxNumberOfAntBrains;
    private final int maxNumberOfAntWorlds;
    private Random seedGenerator;

    /**
     * Empty constructor for creating an ant game tournament.
     */
    public AntGameTournament() {
        antWorlds = new ArrayList<>();
        antBrains = new ArrayList<>();
        pairs = new ArrayList<>();
        boardToPlayIndex = 0;
        maxNumberOfAntBrains = 16;
        maxNumberOfAntWorlds = 16;
        seedGenerator = new Random();
    }

    /**
     * Run a game simulation.
     *
     * @param one First ant brain.
     * @param two Second ant brain.
     * @param world World to play game on.
     */
    private void runGame(AntBrain one, AntBrain two, Board world) {
        // Update current game and run it
        currentGame = new Game(one, two, world, seedGenerator.nextInt(1000));
        currentGame.run();

        // Update scores with 2 points for a win and 1 point for a draw
        int playerOneScore = currentGame.getPlayerOneScore();
        int playerTwoScore = currentGame.getPlayerTwoScore();
        one.incrementGamesPlayedIn();
        two.incrementGamesPlayedIn();
        one.setTotalFoodInBase(one.getTotalFoodInBase() + playerOneScore);
        one.setTotalFoodInEnemyBase(one.getTotalFoodInEnemyBase() + playerTwoScore);
        two.setTotalFoodInBase(two.getTotalFoodInBase() + playerTwoScore);
        two.setTotalFoodInEnemyBase(two.getTotalFoodInEnemyBase() + playerOneScore);
        if (playerOneScore > playerTwoScore) {
            one.incrementGamesWon();
            two.incrementGamesLost();
        } else if (playerTwoScore > playerOneScore) {
            one.incrementGamesLost();
            two.incrementGamesWon();
        } else {
            one.incrementGamesDrawn();
            two.incrementGamesDrawn();
        }

    }

    /**
     * Runs a single game. Two valid ant brains and one valid ant world must
     * have already been loaded to this tournament.
     */
    public void runGame() {
        if (antBrains.size() == 2 && antWorlds.size() == 1) {
            currentGame = new Game(antBrains.get(0), antBrains.get(1), antWorlds.get(0),
                    seedGenerator.nextInt(1000));
        }
    }

    /**
     * Called once all ant brains and worlds have been added to the tournament.
     * Sets up the ant brains into pairs to compete against each other.
     */
    public void createTournament() {
        for (int i = 0; i < antBrains.size() - 1; i++) {
            for (int j = i + 1; j < antBrains.size(); j++) {
                pairs.add(new Pair(antBrains.get(i), antBrains.get(j)));
            }
        }
    }

    /**
     * Runs one round of a tournament. A round means all teams play all enemies
     * on a board as both the red and the black colours.
     */
    public boolean runTournamentRound() {
        boolean finished = false;

        if (boardToPlayIndex < antWorlds.size()) {
            Board boardToPlayRoundOn = antWorlds.get(boardToPlayIndex);

            for (Pair pair : pairs) {
                Board boardToPlayRoundOneOn = boardToPlayRoundOn.copy();
                Board boardToPlayRoundTwoOn = boardToPlayRoundOn.copy();
                runGame(pair.one, pair.two, boardToPlayRoundOneOn);
                runGame(pair.two, pair.one, boardToPlayRoundTwoOn);
            }

            boardToPlayIndex++;
        }

        if (boardToPlayIndex == antWorlds.size()) {
            finished = true;
        }

        // sort the ant brain list so in order of points
        Collections.sort(antBrains);
        return finished;
    }

    /**
     * Loads an ant world from the given file path and gives it the specified
     * name.
     *
     * @param antWorldFilePath file path to load ant world from.
     * @param antWorldName name to give the ant world.
     * @param tournamentCorrect if the world loaded must be tournament correct
     * @throws AntWorldLoaderException
     * @throws IOException
     */
    public void loadAntWorld(String antWorldFilePath, String antWorldName, boolean tournamentCorrect)
            throws AntWorldLoaderException, IOException {
        if (antWorlds.size() < maxNumberOfAntWorlds) {
            AntWorldLoader awl = new AntWorldLoader();
            antWorlds.add(awl.loadWorld(antWorldFilePath, antWorldName, tournamentCorrect));    // change back to true as some point
        }
    }

    /**
     * Generates an ant brain and adds it to the list of worlds.
     * @throws IOException 
     */
    public void generateAntWorld() throws IOException {
        if (antWorlds.size() < maxNumberOfAntWorlds) {
            AntWorldGenerator awl = new AntWorldGenerator(30);
            antWorlds.add(awl.generateWorld());
        }
    }

    /**
     * Loads an ant brain from the given file path and gives it the specified
     * name.
     *
     * @param antBrainFilePath file path to load ant brain from.
     * @param name name to give the ant brain.
     * @throws AntBrainLoaderException
     * @throws IOException
     */
    public void loadAntBrain(String antBrainFilePath, String name)
            throws AntBrainLoaderException, IOException {
        if (antBrains.size() < maxNumberOfAntBrains) {
            antBrains.add(AntBrainLoader.loadBrain(antBrainFilePath, name));
        }
    }

    /**
     * Returns a list of all the ant worlds in this tournament.
     *
     * @return list of all ant worlds in the tournament.
     */
    public List getListOfAntWorlds() {
        return antWorlds;
    }

    /**
     * Returns a list of all the ant brains in this tournament.
     *
     * @return list of all ant brains in the tournament.
     */
    public List getListOfAntBrains() {
        return antBrains;
    }

    /**
     * Returns the current game that is being played.
     *
     * @return current game being played.
     */
    public Game getCurrentGame() {
        return currentGame;
    }

    /**
     * Resets the tournament.
     */
    public void reset() {
        antWorlds = new ArrayList<>();
        antBrains = new ArrayList<>();
        pairs = new ArrayList<>();
        currentGame = null;
        boardToPlayIndex = 0;
    }

    /**
     * Static class to represent an AntBrain pairing.
     *
     * @param <X> Type of the first entry.
     * @param <Y> Type of the second entry.
     */
    public class Pair<X, Y> {

        /**
         * The first ant brain in the pair.
         */
        public final AntBrain one;

        /**
         * The second ant brain in the pair.
         */
        public final AntBrain two;

        /**
         * Class constructor for a pair of ant brains.
         *
         * @param one First ant brain.
         * @param two Second ant brain.
         */
        public Pair(AntBrain one, AntBrain two) {
            this.one = one;
            this.two = two;
        }
    }

    /**
     * Returns the current round number of a game
     * @return the current round number
     */
    public int getTournamentRoundNumber() {
        return boardToPlayIndex;
    }

}
