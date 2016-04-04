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
public class Board {
    private BoardTile[][] board;
    private Ant[] antsOnBoard; 
    private HashMap<Colour, Terrain> colourToBaseMatch;
    
    public Board(BoardTile[][] board){
        this.board = board;
        // add ants?
        
        colourToBaseMatch = new HashMap<>();
        colourToBaseMatch.put(Colour.BLACK, Terrain.BLACKBASE);
        colourToBaseMatch.put(Colour.RED, Terrain.REDBASE);
    }
    
    public boolean antInPosition(Pos posToCheck){
        return board[posToCheck.getPosY()][posToCheck.getPosX()].getAntOnTile() != null;
    }
    
    public Ant antAt(Pos antPosition){
        return board[antPosition.getPosY()][antPosition.getPosX()].getAntOnTile(); 
    }
    
    public void setAntAt(Pos posToSetAnt, Ant antToSet){
        board[posToSetAnt.getPosY()][posToSetAnt.getPosX()].setAntOnTile(antToSet);
        antToSet.setBoardPosition(posToSetAnt);
    }
    
    public void clearAntAt(Pos posToClear){
        Ant ant = board[posToClear.getPosY()][posToClear.getPosX()].getAntOnTile();
        board[posToClear.getPosY()][posToClear.getPosX()].setAntOnTile(null);
        ant.setBoardPosition(null);
    }
    
    public boolean antIsAlive(int id){
        return antsOnBoard[id].getAntIsAlive();
    }
    
    public Pos getAntPosition(int id){
        return antsOnBoard[id].getBoardPosition(); 
    }
    
    public void killAntAt(Pos positionToKillAnt){
        Ant ant = board[positionToKillAnt.getPosY()][positionToKillAnt.getPosX()].getAntOnTile();
        ant.killAnt();
        clearAntAt(positionToKillAnt);
    }
    
    public int numberOfFoodAt(Pos position){
        return board[position.getPosY()][position.getPosX()].getFoodInTile();
    }
    
    public void setFoodAt(Pos foodPosition, int amountOfFood){
        board[foodPosition.getPosY()][foodPosition.getPosX()].setFoodInTile(amountOfFood);
    }
    
    public boolean anthillAt(Pos anthillPos, Colour anthillColour){
        for(Colour col: Colour.values()){
            if(col == anthillColour){
                return board[anthillPos.getPosY()][anthillPos.getPosX()].getCellTerrain() 
                    == colourToBaseMatch.get(anthillColour);
            }
        }
        return false;
    }
    
    public void setMarker(Pos markerPos, Colour markerCol, Marker mark){
        for(Colour col: Colour.values()){
            if(markerCol == col){
                board[markerPos.getPosY()][markerPos.getPosX()].setMarker(markerCol, mark);
            }
        }
    }
    
    public void clearMarker(Pos markerPos, Colour markerCol, Marker mark){
        for(Colour col: Colour.values()){
            if(markerCol == col){
                Marker markInTile = board[markerPos.getPosY()][markerPos.getPosX()].getMarker(col);
                if(markInTile == mark){
                    board[markerPos.getPosY()][markerPos.getPosX()].setMarker(col, Marker.EMPTY);
                }
            }
        }
    }
    
    public boolean checkMarker(Pos markerPos, Colour markerCol, Marker mark){
        for(Colour col: Colour.values()){
            if(markerCol == col){
                return board[markerPos.getPosY()][markerPos.getPosX()].getMarker(col) == mark;
            }
        }
        return false;
    }
    
    public boolean checkAnyMarker(Pos markerPos, Colour markerCol){
        for(Colour col: Colour.values()){
            if(markerCol == col){
                return board[markerPos.getPosY()][markerPos.getPosX()].getMarker(col) != Marker.EMPTY;
            }
        }
        return false;
    }
    
    public int getNumberOfAnts(){
        return antsOnBoard.length;
    }
    
    public Terrain getTerrainAtPosition(Pos positionOfTerrain){
        return board[positionOfTerrain.getPosY()][positionOfTerrain.getPosX()].getCellTerrain();
    }
    
    public int getFoodAtint(Pos tilePosition){
        return board[tilePosition.getPosY()][tilePosition.getPosX()].getFoodInTile();
    }
    
}
