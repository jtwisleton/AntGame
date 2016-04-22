
package antgameproject;

/**
 * Colour Enum used to specify ant colours.
 */
public enum Colour {

    RED("R"), BLACK("B");
    
    private String symbol;
    
    Colour(String symbol){
        this.symbol = symbol;
    }
    
    public String toString(){
        return symbol;
    }
}
