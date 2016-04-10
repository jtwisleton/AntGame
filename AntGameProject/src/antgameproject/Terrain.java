
package antgameproject;

/**
 *
 * @author wilki
 */
public enum Terrain {
    GRASS("-"), ROCK("#"), BLACKBASE("+"), REDBASE("-");
    
    private final String symbol;
    
    Terrain(String symbol){
        this.symbol = symbol;
    }
    
    @Override
    public String toString(){
        return symbol;
    }
}
