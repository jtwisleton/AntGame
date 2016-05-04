package antgameproject;

/**
 * @author Team18
 *
 * The Terrain enum is used to specify types of terrain for the board cells.
 */
public enum Terrain {

    GRASS("."), ROCK("#"), BLACKBASE("-"), REDBASE("+");

    private final String symbol;

    Terrain(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
