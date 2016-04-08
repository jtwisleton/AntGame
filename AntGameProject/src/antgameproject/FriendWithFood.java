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
public class FriendWithFood implements Condition {

    @Override
    public boolean testCondition(Pos sensePosition, Colour antColour, Board gameBoard) {
        if(gameBoard.antInPosition(sensePosition)){
            Ant antInSensePos = gameBoard.antAt(sensePosition);
            return (antInSensePos.getAntColour() == antColour) &&
                                (antInSensePos.getCarryingFood());
        }
        return false;
    }
    
}
