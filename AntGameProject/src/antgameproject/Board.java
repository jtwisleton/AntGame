
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
    private HashMap<Colour, Integer> numberOfAntsAlive;
    private HashMap<Colour, Integer> numberOfFoodInBase;    //think about implmentation of this?
    private String boardName;
    
    public Board(BoardTile[][] board, String boardName){
        this.board = board;
        this.boardName = boardName;
        antsOnBoard = new ArrayList<>();
        
        colourToBaseMatch = new HashMap<>();
        colourToBaseMatch.put(Colour.BLACK, Terrain.BLACKBASE);
        colourToBaseMatch.put(Colour.RED, Terrain.REDBASE);
        
        numberOfAntsAlive = new HashMap<>();
        numberOfFoodInBase = new HashMap<>();
        
        addAnts();
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
                            Pos antPosition = new Pos(j, i);
                            Ant antToAdd = new Ant(antColour, antId, antPosition);
                            antsOnBoard.add(antToAdd);
                            board[i][j].setAntOnTile(antToAdd);
                            
                            if(numberOfAntsAlive.get(key) == null){
                                numberOfAntsAlive.put(key, 1);
                            } else {
                                numberOfAntsAlive.put(key, numberOfAntsAlive.get(key) + 1);
                            }
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
        numberOfAntsAlive.put(ant.getAntColour(), numberOfAntsAlive.get(ant.getAntColour()) - 1);
    }
    
    public int numberOfFoodAt(Pos position){
        return board[position.getPosY()][position.getPosX()].getFoodInTile();
    }
    
    public void setFoodAt(Pos foodPosition, int amountOfFood){
        int oldAmountOfFood = board[foodPosition.getPosY()][foodPosition.getPosX()].getFoodInTile();
        int difference = oldAmountOfFood - amountOfFood;
        if(board[foodPosition.getPosY()][foodPosition.getPosX()].getCellTerrain() ==
                Terrain.BLACKBASE){
            int totalBlackFood = numberOfFoodInBase.get(Colour.BLACK);
            int newTotalFood = totalBlackFood + difference;
            numberOfFoodInBase.put(Colour.BLACK, newTotalFood);
        } else if(board[foodPosition.getPosY()][foodPosition.getPosX()].getCellTerrain() ==
                Terrain.REDBASE){
            int totalRedFood = numberOfFoodInBase.get(Colour.RED);
            int newTotalFood = totalRedFood + difference;
            numberOfFoodInBase.put(Colour.RED, newTotalFood);
        }
        board[foodPosition.getPosY()][foodPosition.getPosX()].setFoodInTile(amountOfFood);
    }
    
    public int getNumberOfAntsAlive(Colour colourOfTeam){
        return numberOfAntsAlive.get(colourOfTeam);
    }
    
    public int getNumberOfFoodInBase(Colour colourOfTeam){
        return numberOfFoodInBase.get(colourOfTeam);
    }
    
    public boolean anthillAt(Pos anthillPos, Colour anthillColour){
        return board[anthillPos.getPosY()][anthillPos.getPosX()].getCellTerrain()
                == colourToBaseMatch.get(anthillColour);
    }
    
    public void setMarker(Pos markerPos, Colour markerCol, int mark){
        board[markerPos.getPosY()][markerPos.getPosX()].setMarker(markerCol, mark); 
    }
    
    public void clearMarker(Pos markerPos, Colour markerCol, int mark){
        int index = board[markerPos.getPosY()][markerPos.getPosX()].getMarkers(markerCol).indexOf(mark);
        if(index >= 0){
            board[markerPos.getPosY()][markerPos.getPosX()].getMarkers(markerCol).remove(index);
        }  
    }
    
    public boolean checkMarker(Pos markerPos, Colour markerCol, int mark){
        int index = board[markerPos.getPosY()][markerPos.getPosX()].getMarkers(markerCol).indexOf(mark);
        if(index < 0){
            return false;
        }
        return true;
    }
    
    public boolean checkAnyMarker(Pos markerPos, Colour markerCol){
        return board[markerPos.getPosY()][markerPos.getPosX()].getMarkers(markerCol).size() > 0;
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
    
    public boolean foodInTile(Pos posToCheckForFood){
        return board[posToCheckForFood.getPosY()][posToCheckForFood.getPosX()].getFoodInTile() > 0;
    }
    
    @Override
    public String toString(){
        return boardName;
    }
    
}
