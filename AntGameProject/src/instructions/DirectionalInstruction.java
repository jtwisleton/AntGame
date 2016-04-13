/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instructions;

import antgameproject.Pos;

/**
 *
 * @author wilki
 */
public abstract class DirectionalInstruction {
    
    protected Pos getAdjacentCell(Pos cellPosition, int adjacentDirection){
        if(adjacentDirection == 0){
            return new Pos(cellPosition.getPosX()+1, cellPosition.getPosY());
        } else if(adjacentDirection == 3){
            return new Pos(cellPosition.getPosX()-1, cellPosition.getPosY());
        } else if(adjacentDirection == 1){
            if(cellPosition.getPosY() % 2 == 0){
                return new Pos(cellPosition.getPosX(), cellPosition.getPosY()+1);
            } else {
                return new Pos(cellPosition.getPosX()+1, cellPosition.getPosY()+1);
            }   
        } else if(adjacentDirection == 2){
            if(cellPosition.getPosY() % 2 == 0){
                return new Pos(cellPosition.getPosX()-1, cellPosition.getPosY()+1);
            } else {
                return new Pos(cellPosition.getPosX(), cellPosition.getPosY()+1);
            }
        } else if(adjacentDirection == 4){
            if(cellPosition.getPosY() % 2 == 0){
                return new Pos(cellPosition.getPosX()-1, cellPosition.getPosY()-1);
            } else {
                return new Pos(cellPosition.getPosX(), cellPosition.getPosY()-1);
            }
        } else {
            if(cellPosition.getPosY() % 2 == 0){
                return new Pos(cellPosition.getPosX(), cellPosition.getPosY()-1);
            } else {
                return new Pos(cellPosition.getPosX()+1, cellPosition.getPosY()-1);
            }
        }
    }
        
        //function adjacent_cell(p:pos, d:dir):pos =
        //let (x,y) = p in
        //switch d of
        //case 0: (x+1, y)
        //case 1: if evenyes then (x, y+1) else (x+1, y+1)
        //case 2: if evenyes then (x-1, y+1) else (x, y+1)
        //case 3: (x-1, y)
        //case 4: if evenyes then (x-1, y-1) else (x, y-1)
        //case 5: if evenyes then (x, y-1) else (x+1, y-1)
        
    
   
    protected int turn(TurnDirection directionToTurn, int currentFacingDirection){
        if(directionToTurn == TurnDirection.RIGHT){
            return (currentFacingDirection + 1) % 6;
        } else {
            if((currentFacingDirection - 1) < 0){
                return 5;
            } else {
                return (currentFacingDirection - 1) % 6;
            }    
        } 
    }
        
}
