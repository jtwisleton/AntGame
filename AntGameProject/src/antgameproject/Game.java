
package antgameproject;

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
    
    public Game(AntBrain playerOne, AntBrain playerTwo, Board gameBoard){
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.gameBoard = gameBoard;
        step = 0;
    }
    
    public void runGame(){
        while(step < 300000){
            for(int i = 0; i < gameBoard.getNumberOfAnts(); i++){
                takeAntWithIdMove(i);
            }
            step++;
        }
        getScores();
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
                antsCurrentInstruction.execute(gameBoard, currentAnt);    
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
    
    private void getScores() {
        playerOneScore = 0;
        playerTwoScore = 0;
        
        for (int i = 0; i < 150; i++) {
            for (int j = 0; j < 150; j++) {
                Pos pos = new Pos(i, j);
                if (gameBoard.anthillAt(pos, Colour.RED)) {
                    playerOneScore += gameBoard.getFoodAtint(pos);
                } else if (gameBoard.anthillAt(pos, Colour.BLACK)) {
                    playerTwoScore += gameBoard.getFoodAtint(pos);
                }
            }
        }
    }
    
    public int getPlayerOneScore() {
        return playerOneScore;
    }
    
    public int getPlayerTwoScore() {
        return playerTwoScore;
    }

}
