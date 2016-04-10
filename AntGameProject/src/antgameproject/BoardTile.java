/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antgameproject;

import java.util.HashMap;

/**
 *
 * @author wilki
 */
public class BoardTile {
    private int foodInTile;
    private HashMap<Colour, Integer> markers; 
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
            markers.put(col, null);
        }
    }
    
    public void setAntOnTile(Ant antOnTile){
        this.antOnTile = antOnTile;
    }
    
    public Ant getAntOnTile(){
        return antOnTile;
    }
    
    public void setMarker(Colour  colourOfMarker, Integer markerToSet){
        markers.put(colourOfMarker, markerToSet);
    }
    
    public Integer getMarker(Colour colourOfMarkerToGet){
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
    
    public String toString(){
        if(antOnTile == null){
            return cellTerrain.toString();
        } else {
            return antOnTile.getAntColour().toString();
        }
    }
}
