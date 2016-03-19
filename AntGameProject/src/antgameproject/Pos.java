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
public class Pos {
    private int posX;
    private int posY;
    
    public Pos(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
    }
    
    public int getPosX(){
        return posX;
    }
    
    public int getPosY(){
        return posY;
    }
}
