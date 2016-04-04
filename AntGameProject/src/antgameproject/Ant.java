/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antgameproject;

/**
 *
 * @author wilki
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
    
    public Ant(Colour antColour, int id, Pos boardPosition){
        this.antColour = antColour;
        currentBrainState = 0;
        this.id = id;
        resting = 0;
        // facing direction 0?
        carryingFood = false;
        this.boardPosition = boardPosition;
        antIsAlive = true;
        facingDirection = 0;
    }
    
    public boolean getAntIsAlive(){
        return antIsAlive;
    }
    
    public void killAnt(){
        antIsAlive = false;
    }
    
    public Pos getBoardPosition(){
        return boardPosition;
    }
    
    public void setBoardPosition(Pos newPosition){
        this.boardPosition = newPosition;
    }
    
    public boolean antIsResting(){
        return resting > 0;
    }
    
    public void decreaseAntResting(){
        resting--;
    }
    
    public Colour getAntColour(){
        return antColour;
    }
    
    public int getCurrentBrainState(){
        return currentBrainState;
    }
    
    public void setBrainState(int brainState){
        currentBrainState = brainState;
    }
    
    public boolean getCarryingFood(){
        return carryingFood;
    }
    
    public void setCarryingFood(boolean carryingFood){
        this.carryingFood = carryingFood;
    }
    
    // double check directions
    public void setTurnDirection(TurnDirection directionToTurn){
        if(directionToTurn == TurnDirection.RIGHT){
            facingDirection = (facingDirection + 1) % 6;
        } else {
            facingDirection = (facingDirection - 1) % 6;
        } 
    }
    
    public int getFacingDirection(){
        return facingDirection;
    }
    
    public void setResting(int timeToRest){
        resting = timeToRest;
    }
}
