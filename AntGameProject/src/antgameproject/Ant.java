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
    private final Colour antColor;
    private int currentBrainState;
    private final int id;
    private int resting;
    private Direction facingDirection;
    private boolean hasFood;
    private Pos boardPosition;
    private boolean antIsAlive;
    
    public Ant(Colour antColor, int id, Pos boardPosition){
        this.antColor = antColor;
        currentBrainState = 0;
        this.id = id;
        resting = 0;
        // facing direction 0?
        hasFood = false;
        this.boardPosition = boardPosition;
        antIsAlive = true;
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
}
