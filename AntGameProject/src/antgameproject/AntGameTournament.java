package antgameproject;

import antgameproject.AntBrainLoader.AntBrainLoaderException;
import antgameproject.AntWorldLoader.AntWorldLoaderException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Class to represent a tournament of ant games.
 * 
 * @author Joseph Kimberley
 */
public class AntGameTournament {
 
    private List<Board> antWorlds;
    private List<AntBrain> antBrains;
    private List<Pair<AntBrain, AntBrain>> pairs;
    private HashMap<AntBrain, Integer> scores;
    private Game currentGame;
    private int topScore;

    /**
     * Static class to represent an AntBrain pairing.
     * 
     * @param <X> Type of the first entry.
     * @param <Y> Type of the second entry.
     */
    public static class Pair<X, Y> {
        public final AntBrain one;
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
    
    public AntGameTournament(){
        antWorlds = new ArrayList<>();
        antBrains = new ArrayList<>();
    }
    
    /**
     * Class constructor for a list of ant brain files and a list of ant world files.
     * 
     * @param antBrainFiles Collection of ant brain files.
     * @param antWorldFiles Collection of ant world files.
     */
    public AntGameTournament(List<String> antBrainFiles, List<String> antWorldFiles) {
        antBrains = loadAntBrains(antBrainFiles);
        antWorlds = loadAntWorlds(antWorldFiles);
        pairs = new ArrayList<>();
        
        // Initialise each ant brain's score to 0
        scores = new HashMap<>();
        for (AntBrain brain : antBrains) {
            scores.put(brain, 0);
        }
        topScore = 0;
    }
    
    /**
     * Constructor for a list of ant brain files and a number of ant worlds.
     * 
     * @param antBrainFiles Collection of ant brain files.
     * @param numAntWorlds Number of ant worlds to be generated.
     */
    public AntGameTournament(List<String> antBrainFiles, int numAntWorlds) {
        antBrains = loadAntBrains(antBrainFiles);
        for (int i = 0; i < numAntWorlds; i++) {
            // Generate ant world
            // Check ant world and add to array
        }
        pairs = new ArrayList<>();
        
        // Initialise each ant brain's score to 0
        scores = new HashMap<>();
        for (AntBrain brain : antBrains) {
            scores.put(brain, 0);
        }
        topScore = 0;
    }
    
    /**
     * Run the tournament simulation.
     */
    public void runTournament() {
        // Find every combination of ant brains (AB == BA)
        for (int i = 0; i < antBrains.size() - 1; i++) {
            for (int j = i + 1; j < antBrains.size(); j++) {
                pairs.add(new Pair(antBrains.get(i), antBrains.get(j)));
            }
        }
        
        // Run a game for each pair twice on each world
        for (Board antWorld : antWorlds) {
            for (Pair pair : pairs) {
                runGame(pair.one, pair.two, antWorld);
                runGame(pair.two, pair.one, antWorld);
            }
        }
        
        // Remove all ant brains that have less than the top score
        Iterator<AntBrain> it = antBrains.iterator();
        while (it.hasNext()) {
            AntBrain antBrain = it.next();
            if (scores.get(antBrain) < topScore) {
                it.remove();
            }
        }
        
        // If there are more than one ant brain with the top score, repeat tournament
        if (antBrains.size() > 1) {
            // Could reset scores to 0
            runTournament();
        }
        
        // Do something with winning ant brain: antBrains.get(0)
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
        currentGame = new Game(one, two, world);
        currentGame.run();
        
        // Update scores with 2 points for a win and 1 point for a draw
        int playerOneScore = currentGame.getPlayerOneScore();
        int playerTwoScore = currentGame.getPlayerTwoScore();
        
        if (playerOneScore > playerTwoScore) {
            scores.put(one, scores.get(one) + 2);
        } else if (playerTwoScore > playerOneScore) {
            scores.put(two, scores.get(two) + 2);
        } else {
            scores.put(one, scores.get(one) + 1);
            scores.put(two, scores.get(two) + 1);
        }
        
        // Maintain a record of the highest score
        if (scores.get(one) > topScore)  {
            topScore = scores.get(one);
        } else if (scores.get(two) > topScore) {
            topScore = scores.get(two);
        }
    }
    
    /**
     * Load a list of ant brains from a list of files.
     * 
     * @param antBrainFiles List of ant brain files.
     * @return A list of ant brains.
     */
    private List<AntBrain> loadAntBrains(List<String> antBrainFiles) {
        List<AntBrain> brains = new ArrayList<>();
        
        // For each file, try to load the ant brain and catch any exceptions
        for (String antBrainFile : antBrainFiles) {
            try {
                brains.add(AntBrainLoader.loadBrain(antBrainFile, " "));
            } catch (IOException | AntBrainLoaderException e) {
                System.out.println(e);
            }
        }
        
        return brains;
    }
    
    /**
     * Load a list of ant worlds from a list of ant world files.
     * 
     * @param antWorldFiles List of ant world files.
     * @return A list of ant worlds.
     */
    private List<Board> loadAntWorlds(List<String> antWorldFiles) {
        List<Board> worlds = new ArrayList<>();
        
        // For each file, try to load the ant world and catch any exceptions
        for (String antWorldFile : antWorldFiles) {
            try {
                worlds.add(AntWorldLoader.loadWorld(antWorldFile, true));
            } catch (IOException | AntWorldLoaderException e) {
                System.out.println(e);
            }
        }
        
        return worlds;
    }
    
    public void loadAntWorld(String antWorldFilePath) throws AntWorldLoaderException, IOException {
        antWorlds.add(AntWorldLoader.loadWorld(antWorldFilePath, false));    // change back to true as some point
    }
    
    public void loadAntBrain(String antBrainFilePath, String name) throws AntBrainLoaderException, IOException{
        antBrains.add(AntBrainLoader.loadBrain(antBrainFilePath, name));
    }
    
    public List getListOfAntWorlds(){
        return antWorlds;
    }
    
    public List getListOfAntBrains(){
        return antBrains;
    }
    
    public Game getCurrentGame(){
        return currentGame;
    }
    
}
