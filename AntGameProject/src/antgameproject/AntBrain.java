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

    private List<Instruction> instructions = new ArrayList<>();
    private final String name;
    private int gamesPlayedIn;
    private int gamesWon;
    private int gamesDrawn;
    private int gamesLost;
    private int totalFoodInBase;
    private int totalFoodInEnemyBase;
    private int points;

    /**
     * Constructor for the AntBrain class.
     *
     * @param instructions list of Instructions representing the brain.
     * @param name name of the ant brain, used for identification.
     */
    public AntBrain(List<Instruction> instructions, String name) {
        this.instructions = instructions;
        this.name = name;

        // initialize all score fields to 0
        gamesPlayedIn = 0;
        gamesWon = 0;
        gamesDrawn = 0;
        gamesLost = 0;
        totalFoodInBase = 0;
        totalFoodInEnemyBase = 0;
        points = 0;
    }

    /**
     * Returns an instruction for the given state identified by an integer.
     *
     * @param state the identity of the required sate.
     * @return the instruction for the specified state.
     */
    public Instruction getInstruction(int state) {
        return instructions.get(state);
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
    public int getTotalFoodInBase() {
        return totalFoodInBase;
    }

    /**
     * Returns the cumulative amount of food oppositions of this ant has
     * finished with in there bases.
     *
     * @return total amount of food enemy ants have finished with in there
     * bases.
     */
    public int getTotalFoodInEnemyBase() {
        return totalFoodInEnemyBase;
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
     * @param totalFoodInBase
     */
    public void setTotalFoodInBase(int totalFoodInBase) {
        this.totalFoodInBase = totalFoodInBase;
    }

    /**
     * Sets the total amount of food enemies of this ant have finished with in
     * there bases in games against this ant.
     *
     * @param totalFoodInEnemyBase
     */
    public void setTotalFoodInEnemyBase(int totalFoodInEnemyBase) {
        this.totalFoodInEnemyBase = totalFoodInEnemyBase;
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
            return o.totalFoodInBase - this.getTotalFoodInBase();
        }
        return o.points - this.points;
    }
}
