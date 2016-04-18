/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antgameproject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        if(!markers.get(colourOfMarker).contains(markerToSet)){
           markers.get(colourOfMarker).add(markerToSet); 
        }
        
    }
    
    public List<Integer> getMarkers(Colour colourOfMarkerToGet){
        Collections.sort(markers.get(colourOfMarkerToGet));
        return markers.get(colourOfMarkerToGet);
        //return markers.get(colourOfMarkerToGet).sort(sortMarkers);
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
    
    class sortMarkers implements Comparator<Integer>  {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    
    }
}
