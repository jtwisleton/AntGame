
package antgameproject;

/**
 * The RandomNumber class is a pseudo random number generator.
 */
public class RandomNumber {
    
    private int s4;
    
    /**
     * Constructor for the RandomNumber class which takes a seed.
     * @param seed seed for random number generation.
     */
    public RandomNumber(int seed){
        s4 = seed; 
        for(int i = 0; i < 4; i++){
            s4 = (s4 * 22695477 + 1) & (int)(Math.pow(2, 32) - 1);
        }
    }
    
    /**
     * Generates a random number in a range 0 to the upper value given, not 
     * inclusive of upper value.
     * @param upperValuespecifies the upper value for the random number generated.
     * @return a random number in the given range.
     */
    public int generateNumber(int upperValue){
        int x = (s4 / 65536) % 16384;
        s4 = (s4 * 22695477 + 1) & (int)(Math.pow(2, 32) - 1);
        return x % upperValue;
    }

}
