/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antgameproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author wilki
 */
public class BoardTile {
    private int foodInTile;
    private HashMap<Colour, List<Integer>> markers; 
    private final Terrain cellTerrain;
    private Ant antOnTile;
    
    public BoardTile(int foodInTile, Terrain cellTerrain){
        assert foodInTile < 10;
        if(cellTerrain == Terrain.ROCK){
            assert foodInTile == 0;
        }
        
        this.foodInTile = foodInTile;
        this.cellTerrain = cellTerrain;
        
        markers = new HashMap<>();
        for(Colour col: Colour.values()){
            markers.put(col, new ArrayList<Integer>());
        }
    }
    
    public void setAntOnTile(Ant antOnTile){
        this.antOnTile = antOnTile;
    }
    
    public Ant getAntOnTile(){
        return antOnTile;
    }
    
    public void setMarker(Colour  colourOfMarker, Integer markerToSet){
        markers.get(colourOfMarker).add(markerToSet);
    }
    
    public List<Integer> getMarkers(Colour colourOfMarkerToGet){
        return markers.get(colourOfMarkerToGet);
    }
    
    public void setFoodInTile(int newAmountOfFood){
        foodInTile = newAmountOfFood;
    }
    
    public int getFoodInTile(){
        return foodInTile;
    }
    
    public Terrain getCellTerrain(){
        return cellTerrain;
    }
    
    @Override
    public String toString(){
        if(antOnTile != null){
            return antOnTile.getAntColour().toString();
        } else if(foodInTile > 0) {
            return Integer.toString(foodInTile);
        } else {
            return cellTerrain.toString();
        }
    }
}
