package antgameproject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wilki
 */
public interface Condition {
    
    public boolean testCondition(Pos sensePosition, Colour antColour, Board gameBoard);
    //FRIEND, FOE, FRIENDWITHFOOD, FOEWITHFOOD, FOOD, ROCK, MARKER, FOEMARKER,
    //HOME, FOEHOME;
}
