package antgameproject;

import instructions.Instruction;
import java.util.ArrayList;
import java.util.List;

/**
 * The AntBrain class represents an ant brain. It holds the instructions of that
 * ant brain but holds the score for that brain during a tournament.
 *
 * @author Team18
 */
public class AntBrain implements Comparable<AntBrain> {

    private List<Instruction> brain = new ArrayList<>();
    private final String name;
    private int gamesPlayedIn;
    private int gamesWon;
    private int gamesDrawn;
    private int gamesLost;
    private int totalFoodGatheredInAllGames;
    private int totalFoodGatheredByEnemies;
    private int points;

    /**
     * Constructor for the AntBrain class.
     *
     * @param brain the brain this ant brain is based on.
     * @param name name of the ant brain, used for identification.
     */
    public AntBrain(List<Instruction> brain, String name) {
        this.brain = brain;
        this.name = name;

        // initialize all score fields to 0
        gamesPlayedIn = 0;
        gamesWon = 0;
        gamesDrawn = 0;
        gamesLost = 0;
        totalFoodGatheredInAllGames = 0;
        totalFoodGatheredByEnemies = 0;
        points = 0;
    }

    /**
     * Returns an instruction for the given state identified by an integer.
     *
     * @param state the identity of the required sate.
     * @return the instruction for the specified state.
     */
    public Instruction getInstruction(int state) {
        return brain.get(state);
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Returns the number of games this ant brain has played.
     *
     * @return the number of games this brain has played.
     */
    public int getGamesPlayedIn() {
        return gamesPlayedIn;
    }

    /**
     * Returns the number of games this ant brain has won.
     *
     * @return the number of games ant brain won.
     */
    public int getGamesWon() {
        return gamesWon;
    }

    /**
     * Returns the number of games this ant has drawn.
     *
     * @return the number of games ant brain drew.
     */
    public int getGamesDrawn() {
        return gamesDrawn;
    }

    /**
     * Returns the number of games this ant brain lost.
     *
     * @return the number of games ant brain lost.
     */
    public int getGamesLost() {
        return gamesLost;
    }

    /**
     * Returns the cumulative amount of food this ant brain has finished with in
     * its base in all games.
     *
     * @return total amount of food this ant brain has finished with in its
     * base.
     */
    public int getTotalFoodGatheredInAllGames() {
        return totalFoodGatheredInAllGames;
    }

    /**
     * Returns the cumulative amount of food oppositions of this ant has
     * finished with in there bases.
     *
     * @return total amount of food enemy ants have finished with in there
     * bases.
     */
    public int getTotalFoodGatheredByEnemies() {
        return totalFoodGatheredByEnemies;
    }

    /**
     * Increments the number of games played by one.
     */
    public void incrementGamesPlayedIn() {
        gamesPlayedIn++;
    }

    /**
     * Increments the number of games lost by one.
     */
    public void incrementGamesWon() {
        gamesWon++;
        points += 2;
    }

    /**
     * Increments the number of games drawn by one.
     */
    public void incrementGamesDrawn() {
        gamesDrawn++;
    }

    /**
     * Increments the number of games lost by one.
     */
    public void incrementGamesLost() {
        gamesLost++;
        points += 1;
    }

    /**
     * Sets the total cumulative amount of food that this ant has finished with
     * in its base over all games.
     *
     * @param totalFoodInBase Total number of food in base.
     */
    public void setTotalFoodGatheredInAllGames(int totalFoodInBase) {
        this.totalFoodGatheredInAllGames = totalFoodInBase;
    }

    /**
     * Sets the total amount of food enemies of this ant have finished with in
     * there bases in games against this ant.
     *
     * @param totalFoodInEnemyBase Total number of food in the enemies base.
     */
    public void setTotalFoodGatheredByEnemies(int totalFoodInEnemyBase) {
        this.totalFoodGatheredByEnemies = totalFoodInEnemyBase;
    }

    /**
     * Returns the points that this ant brain currently has.
     *
     * @return number of points.
     */
    public int getPoints() {
        return points;
    }

    // comparable method so ant brains can be ordered for tournamanet.
    @Override
    public int compareTo(AntBrain o) {
        if (o.points == this.points) {
            return o.totalFoodGatheredInAllGames - this.getTotalFoodGatheredInAllGames();
        }
        return o.points - this.points;
    }
}
