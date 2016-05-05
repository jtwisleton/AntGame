package antgameproject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a board tile, which is a single cell on the game board.
 *
 * @author Team18
 */
public class BoardTile {

    private int foodInTile;
    private final HashMap<Colour, List<Integer>> markers;
    private final Terrain cellTerrain;
    private Ant antOnTile;

    /**
     * Constructor for a board tile.
     *
     * @param foodInTile the amount of food in the tile.
     * @param cellTerrain the terrain type of the tile.
     */
    public BoardTile(int foodInTile, Terrain cellTerrain) {
        assert foodInTile < 10;
        if (cellTerrain == Terrain.ROCK) {
            assert foodInTile == 0;
        }

        this.foodInTile = foodInTile;
        this.cellTerrain = cellTerrain;

        //create an empty list of colours.
        markers = new HashMap<>();
        for (Colour col : Colour.values()) {
            markers.put(col, new ArrayList<Integer>());
        }
    }

    /**
     * Sets the given ant in this tile.
     *
     * @param antOnTile ant to set on tile.
     */
    public void setAntOnTile(Ant antOnTile) {
        this.antOnTile = antOnTile;
    }

    /**
     * Return the ant on the tile.
     *
     * @return the ant on this tile.
     */
    public Ant getAntOnTile() {
        return antOnTile;
    }

    /**
     * Set the marker type given for the colour given.
     *
     * @param colourOfMarker colour of the marker to set.
     * @param markerToSet the type of the marker to set.
     */
    public void setMarker(Colour colourOfMarker, Integer markerToSet) {
        if (!markers.get(colourOfMarker).contains(markerToSet)) {
            markers.get(colourOfMarker).add(markerToSet);
        }
    }

    /**
     * Returns all the markers of a given colour for this board tile.
     *
     * @param colourOfMarkerToGet the colour of the markers to get.
     * @return a list of all the markers set on this tile for the given colour.
     */
    public List<Integer> getMarkers(Colour colourOfMarkerToGet) {
        Collections.sort(markers.get(colourOfMarkerToGet));
        return markers.get(colourOfMarkerToGet);
    }

    /**
     * Sets the amount of food on this tile.
     *
     * @param newAmountOfFood the new amount of food to set on this tile.
     */
    public void setFoodInTile(int newAmountOfFood) {
        foodInTile = newAmountOfFood;
    }

    /**
     * Returns the amount of food in this tile.
     *
     * @return the amount of food in this tile.
     */
    public int getFoodInTile() {
        return foodInTile;
    }

    /**
     * Returns the cell terrain for th given tile.
     *
     * @return the cell terrain of the given tile.
     */
    public Terrain getCellTerrain() {
        return cellTerrain;
    }

    @Override
    public String toString() {
        if (antOnTile != null) {
            return antOnTile.getAntColour().toString();
        } else if (foodInTile > 0) {
            return Integer.toString(foodInTile);
        } else {
            return cellTerrain.toString();
        }
    }

}
