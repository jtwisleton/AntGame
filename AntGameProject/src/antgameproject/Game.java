
package antgameproject;

import instructions.Instruction;

/**
 *
 * @author wilki
 */
public class Game {
    private final AntBrain playerOne;
    private final AntBrain playerTwo;
    private final Board gameBoard;
    private int step;
    private int playerOneScore;
    private int playerTwoScore;
    private RandomNumber randomNumberGen;
    
    public Game(AntBrain playerOne, AntBrain playerTwo, Board gameBoard){
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.gameBoard = gameBoard;
        step = 0;
        randomNumberGen = new RandomNumber(12345);
    }
    
    public void run(){
        while(step < 300000){
            for(int i = 0; i < gameBoard.getNumberOfAnts(); i++){
                takeAntWithIdMove(i);
            }
            step++;
        }
        //updateScores();
    }
    
    private void takeAntWithIdMove(int antId){
        if(gameBoard.antIsAlive(antId)){
            Pos antPosition = gameBoard.getAntPosition(antId);
            Ant currentAnt = gameBoard.antAt(antPosition);
            if(currentAnt.antIsResting()){
                currentAnt.decreaseAntResting();
            } else {
                Instruction antsCurrentInstruction = getInstruction(currentAnt.getAntColour(), 
                        currentAnt.getCurrentBrainState());
                antsCurrentInstruction.execute(gameBoard, currentAnt, randomNumberGen);    
            }
        } 
    }
    
            
    private Instruction getInstruction(Colour antColour, int brainState){
        // player one is red and player two black (only works for two players)
        if(antColour == Colour.RED){
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
    
    public int getRedAntsAlive(){
        return gameBoard.getNumberOfAntsAlive(Colour.RED);
    }
    
    public int getBlackAntsAlive(){
        return gameBoard.getNumberOfAntsAlive(Colour.BLACK);
    }
    
    public Board getGameBoard(){
        return gameBoard;
    }

    public void runRounds(int numberOfStepsToRun) {
        for(int j = 0; j < numberOfStepsToRun; j++){
            if(step > 300000){
                break;
            }
            for(int i = 0; i < gameBoard.getNumberOfAnts(); i++){
                takeAntWithIdMove(i);
            }
            step++;
        }    
    }

}
