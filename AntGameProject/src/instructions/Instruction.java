/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instructions;

import antgameproject.Ant;
import antgameproject.Board;

/**
 *
 * @author wilki
 */
public interface Instruction {
    public void execute(Board gameBoard, Ant currentAnt);
}
