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
    private HashMap<Colour, Marker> markers; 
    private final Terrain cellTerrain;
    private Ant antOnTile;
    
    public BoardTile(int foodInTile, Terrain cellTerrain){
        this.foodInTile = foodInTile;
        this.cellTerrain = cellTerrain;
        
        markers = new HashMap<>();
        for(Colour col: Colour.values()){
            markers.put(col, Marker.EMPTY);
        }
    }
    
    public void setAntOnTile(Ant antOnTile){
        this.antOnTile = antOnTile;
    }
    
    public Ant getAntOnTile(){
        return antOnTile;
    }
    
    public void setMarker(Colour  colourOfMarker, Marker markerToSet){
        markers.put(colourOfMarker, markerToSet);
    }
    
    public Marker getMarker(Colour colourOfMarkerToGet){
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
}