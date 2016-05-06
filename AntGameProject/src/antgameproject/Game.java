package antgameproject;

import instructions.Instruction;

/**
 * The Game class represents a single game between two ant brains on a single
 * world.
 *
 * @author Team18
 */
public class Game {

    private final AntBrain playerOne;
    private final AntBrain playerTwo;
    private final Board gameBoard;
    private final RandomNumber randomNumberGen;
    private int step;

    /**
     * Constructor for the game class
     *
     * @param playerOne the AntBrain for player one.
     * @param playerTwo the AntBrain for player two.
     * @param gameBoard the Board to play the game on.
     * @param randomSeed Random seed to construct random generator.
     */
    public Game(AntBrain playerOne, AntBrain playerTwo, Board gameBoard, int randomSeed) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.gameBoard = gameBoard;
        step = 0;
        randomNumberGen = new RandomNumber(randomSeed);
    }

    /**
     * Runs the game for 300000 turns. During a turn each alive ant on the board
     * executes an instruction if it's not resting.
     */
    public void run() {
        while (step < 300000) {
            for (int i = 0; i < gameBoard.getNumberOfAnts(); i++) {
                takeAntWithIdMove(i);
            }
            step++;
        }
    }

    /*
     * Takes the move for the ant given by the id if it is alive and is not resting.
     */
    private void takeAntWithIdMove(int antId) {
        if (gameBoard.antIsAlive(antId)) {
            Pos antPosition = gameBoard.getAntPosition(antId);
            Ant currentAnt = gameBoard.antAt(antPosition);
            if (currentAnt.antIsResting()) {
                currentAnt.decreaseAntResting();
            } else {
                Instruction antsCurrentInstruction = getInstruction(currentAnt.getAntColour(),
                        currentAnt.getCurrentBrainState());
                antsCurrentInstruction.execute(gameBoard, currentAnt, randomNumberGen);
            }
        }
    }

    /*
     * returns the corrent instruction depending on the ant colour of the ant given as
     * an argument.
     */
    private Instruction getInstruction(Colour antColour, int brainState) {
        if (antColour == Colour.RED) {
            return playerOne.getInstruction(brainState);
        } else {
            return playerTwo.getInstruction(brainState);
        }
    }

    /**
     * Get the red ant's score.
     *
     * @return Red ant score.
     */
    public int getPlayerOneScore() {
        return gameBoard.getNumberOfFoodInBase(Colour.RED);
    }

    /**
     * Get the black ant's score.
     *
     * @return Black ant score.
     */
    public int getPlayerTwoScore() {
        return gameBoard.getNumberOfFoodInBase(Colour.BLACK);
    }

    /**
     * Gets the number of red ants alive in this game.
     *
     * @return the number of red ants alive.
     */
    public int getRedAntsAlive() {
        return gameBoard.getNumberOfAntsAlive(Colour.RED);
    }

    /**
     * Gets the number of black ants alive in this game.
     *
     * @return the number of black ants alive.
     */
    public int getBlackAntsAlive() {
        return gameBoard.getNumberOfAntsAlive(Colour.BLACK);
    }

    /**
     * Returns the game board the game is being played on.
     *
     * @return the game board
     */
    public Board getGameBoard() {
        return gameBoard;
    }

    /**
     * Runs the current game for the given number of steps while the game has
     * run in total less than 300000 steps.
     *
     * @param numberOfStepsToRun the number of steps to run the game for.
     * @return True if game finished, false otherwise.
     */
    public boolean runRounds(int numberOfStepsToRun) {
        for (int j = 0; j < numberOfStepsToRun; j++) {
            if (step > 300000) {
                return true;
            }
            for (int i = 0; i < gameBoard.getNumberOfAnts(); i++) {
                takeAntWithIdMove(i);
            }
            step++;
        }
        return false;
    }

}
