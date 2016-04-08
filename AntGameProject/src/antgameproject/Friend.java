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
public class Friend implements Condition {

    @Override
    public boolean testCondition(Pos sensePosition, Colour antColour, Board gameBoard) {
        if(gameBoard.antInPosition(sensePosition)){
            return gameBoard.antAt(sensePosition).getAntColour() == antColour;
        }
        return false;
    }
    
}
