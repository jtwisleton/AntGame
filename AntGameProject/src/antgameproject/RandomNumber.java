
package antgameproject;

/**
 *
 * @author jw478
 */
public class RandomNumber {
    
    private int s4;
    
    public RandomNumber(int seed){
        s4 = seed; 
        for(int i = 0; i < 4; i++){
            s4 = (s4 * 22695477 + 1) & (int)(Math.pow(2, 32) - 1);
        }
    }
    
    public int generateNumber(int n){
        int x = (s4 / 65536) % 16384;
        s4 = (s4 * 22695477 + 1) & (int)(Math.pow(2, 32) - 1);
        return x % n;
    }

}
