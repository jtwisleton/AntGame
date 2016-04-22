
package antgameproject;

/**
 * the Pos class is a wrapper class for a two dimensional position.
 */
public class Pos {
    private int posX;
    private int posY;
    
    /**
     * Constructor for the Pos class.
     * @param posX x value.
     * @param posY y value.
     */
    public Pos(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
    }
    
    /**
     * Get the x value of the position.
     * @return x value.
     */
    public int getPosX(){
        return posX;
    }
    
    /**
     * Get the y value of the position.
     * @return y value.
     */
    public int getPosY(){
        return posY;
    }
}
