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
public class Board {
    private BoardTile[][] board;
    private List<Ant> antsOnBoard; 
    private HashMap<Colour, Terrain> colourToBaseMatch;
    
    public Board(BoardTile[][] board){
        this.board = board;
        antsOnBoard = new ArrayList<>();
        
        colourToBaseMatch = new HashMap<>();
        colourToBaseMatch.put(Colour.BLACK, Terrain.BLACKBASE);
        colourToBaseMatch.put(Colour.RED, Terrain.REDBASE);
        
        printBoardToASCII();    // needs to be removed once proram complete
        addAnts();
        printBoardToASCII();    // needs to be removed once program complete
    }
    
    private void addAnts(){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j].getCellTerrain() != Terrain.ROCK && 
                        board[i][j].getCellTerrain() != Terrain.GRASS){
                    for(Colour key: colourToBaseMatch.keySet()){
                        if(colourToBaseMatch.get(key) == board[i][j].getCellTerrain()){
                            Colour antColour = key;
                            int antId = antsOnBoard.size();
                            Pos antPosition = new Pos(i, j);
                            Ant antToAdd = new Ant(antColour, antId, antPosition);
                            antsOnBoard.add(antToAdd);
                            board[i][j].setAntOnTile(antToAdd);
                        }
                    }
                }
            }
        }
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
        return antsOnBoard.get(id).getAntIsAlive();
    }
    
    public Pos getAntPosition(int id){
        return antsOnBoard.get(id).getBoardPosition(); 
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
        return board[anthillPos.getPosY()][anthillPos.getPosX()].getCellTerrain()
                == colourToBaseMatch.get(anthillColour);
    }
    
    public void setMarker(Pos markerPos, Colour markerCol, int mark){
        board[markerPos.getPosY()][markerPos.getPosX()].setMarker(markerCol, mark); 
    }
    
    public void clearMarker(Pos markerPos, Colour markerCol, int mark){
        if(board[markerPos.getPosY()][markerPos.getPosX()].getMarker(markerCol) != null){
            int markInTile = board[markerPos.getPosY()][markerPos.getPosX()].getMarker(markerCol);
            if(markInTile == mark){
                board[markerPos.getPosY()][markerPos.getPosX()].setMarker(markerCol, null);
            }
        }    
    }
    
    public boolean checkMarker(Pos markerPos, Colour markerCol, int mark){
        if(board[markerPos.getPosY()][markerPos.getPosX()].getMarker(markerCol) == null){
            return false;
        }
        return board[markerPos.getPosY()][markerPos.getPosX()].getMarker(markerCol) == mark;
    }
    
    public boolean checkAnyMarker(Pos markerPos, Colour markerCol){
        return board[markerPos.getPosY()][markerPos.getPosX()].getMarker(markerCol) != null;

    }
    
    public int getNumberOfAnts(){
        return antsOnBoard.size();
    }
    
    public Terrain getTerrainAtPosition(Pos positionOfTerrain){
        return board[positionOfTerrain.getPosY()][positionOfTerrain.getPosX()].getCellTerrain();
    }
    
    public void printBoardToASCII(){
        for(int i = 0; i < board.length; i++){
            if(i % 2 != 0){
                System.out.print(" ");
            }
            for(int j = 0; j < board[0].length; j++){
                System.out.print(board[i][j]);
            }
            System.out.print("\n");
        }
    }
    
}
