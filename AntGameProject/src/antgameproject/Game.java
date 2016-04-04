
package antgameproject;

/**
 *
 * @author wilki
 */
public class Game {
    private AntBrain playerOne;
    private AntBrain playerTwo;
    private Board gameBoard;
    private int step;
    private int restDuration;
    
    public Game(AntBrain playerOne, AntBrain playerTwo, Board gameBoard){
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.gameBoard = gameBoard;
        step = 0;
        restDuration = 14;
    }
    
    public void runGame(){
        while(step < 300000){
            for(int i = 0; i < gameBoard.numberOfAnts(); i++){
                takeAntWithIdMove(i);
            }
            step++;
        }
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

    }

}
