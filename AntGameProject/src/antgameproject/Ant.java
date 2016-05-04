package antgameproject;

/**
 * The Ant class represents an ant in the game.
 *
 * @author Team18
 */
public class Ant {

    private final Colour antColour;
    private int currentBrainState;
    private final int id;
    private int resting;
    private int facingDirection;
    private boolean carryingFood;
    private Pos boardPosition;
    private boolean antIsAlive;

    /**
     * Constructor for the Ant class.
     *
     * @param antColour defines the colour of the ant.
     * @param id is a unique identifier of the ant.
     * @param boardPosition is the position the ant is on the board.
     */
    public Ant(Colour antColour, int id, Pos boardPosition) {
        this.antColour = antColour;
        this.id = id;
        this.boardPosition = boardPosition;
        currentBrainState = 0;
        resting = 0;
        carryingFood = false;
        antIsAlive = true;
        facingDirection = 0;
    }

    /**
     * Tests if the ant is currently alive.
     *
     * @return true if the ant is alive and false otherwise.
     */
    public boolean getAntIsAlive() {
        return antIsAlive;
    }

    /**
     * Kills the ant so it is no longer used in the game.
     */
    public void killAnt() {
        antIsAlive = false;
    }

    /**
     * Returns the current board position of the ant.
     *
     * @return the current position of the ant.
     */
    public Pos getBoardPosition() {
        return boardPosition;
    }

    /**
     * Sets the ant to a new board position.
     *
     * @param newPosition the new position to set for the ant.
     */
    public void setBoardPosition(Pos newPosition) {
        this.boardPosition = newPosition;
    }

    /**
     * Returns if this ant is currently resting.
     *
     * @return true if the ant is resting and false otherwise.
     */
    public boolean antIsResting() {
        return resting > 0;
    }

    /**
     * Decrements the ants resting period by one.
     */
    public void decreaseAntResting() {
        resting--;
    }

    /**
     * Returns the ants colour.
     *
     * @return the colour of the ant.
     */
    public Colour getAntColour() {
        return antColour;
    }

    /**
     * Used to get the current brain state of the ant.
     *
     * @return current brain state.
     */
    public int getCurrentBrainState() {
        return currentBrainState;
    }

    /**
     * Used to set a new brain state for the ant.
     *
     * @param brainState the new brain state to set.
     */
    public void setBrainState(int brainState) {
        currentBrainState = brainState;
    }

    /**
     * Returns if the ant is currently carrying food. An ant can carry at a
     * maximum one bit of food.
     *
     * @return true if the ant is carrying food and false otherwise.
     */
    public boolean getCarryingFood() {
        return carryingFood;
    }

    /**
     * Sets if the ant is carrying food or not.
     *
     * @param carryingFood true if the ant is carrying food and false if it is
     * not.
     */
    public void setCarryingFood(boolean carryingFood) {
        this.carryingFood = carryingFood;
    }

    /**
     * Set the facing direction of the ant.
     *
     * @param facingDirection the new direction the ant is facing.
     */
    public void setFacingDirection(int facingDirection) {
        this.facingDirection = facingDirection;
    }

    /**
     * Returns the current direction the ant is facing.
     *
     * @return the direction the ant is facing.
     */
    public int getFacingDirection() {
        return facingDirection;
    }

    /**
     * Sets the amount of the time the ant rests after moving.
     *
     * @param timeToRest the period for the ant to rest.
     */
    public void setResting(int timeToRest) {
        resting = timeToRest;
    }

    /**
     * Returns the ants unique identification.
     *
     * @return the ants id.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the period of resting that the ant has. Currently used for
     * testing only.
     *
     * @return the amount of resting this ant still has before it can move.
     */
    public int getResting() {
        return resting;
    }
}
