package antgameproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Represents the board in the ant game.
 *
 * @author Team18
 */
public class Board {

    private final BoardTile[][] board;
    private List<Ant> antsOnBoard;
    private HashMap<Colour, Terrain> colourToBaseMatch;
    private HashMap<Colour, Integer> numberOfAntsAlive;
    private HashMap<Colour, Integer> numberOfFoodInBase;
    private final String boardName;

    /**
     * Constructor for the Board class.
     *
     * @param board the board that the board class is built around.
     * @param boardName name to identify the board by.
     */
    public Board(BoardTile[][] board, String boardName) {
        this.board = board;
        this.boardName = boardName;
        antsOnBoard = new ArrayList<>();

        colourToBaseMatch = new HashMap<>();
        colourToBaseMatch.put(Colour.BLACK, Terrain.BLACKBASE);
        colourToBaseMatch.put(Colour.RED, Terrain.REDBASE);

        numberOfAntsAlive = new HashMap<>();
        numberOfFoodInBase = new HashMap<>();
        for (Colour c : Colour.values()) {
            numberOfFoodInBase.put(c, 0);
        }

        addAnts();
    }

    /**
     * Adds ants onto the board and initializes the ants appropriately.
     */
    private void addAnts() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].getCellTerrain() != Terrain.ROCK
                        && board[i][j].getCellTerrain() != Terrain.GRASS) {
                    for (Colour key : colourToBaseMatch.keySet()) {
                        if (colourToBaseMatch.get(key) == board[i][j].getCellTerrain()) {
                            Colour antColour = key;
                            int antId = antsOnBoard.size();
                            Pos antPosition = new Pos(j, i);
                            Ant antToAdd = new Ant(antColour, antId, antPosition);
                            antsOnBoard.add(antToAdd);
                            board[i][j].setAntOnTile(antToAdd);

                            if (numberOfAntsAlive.get(key) == null) {
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

    /**
     * Checks if there is an ant in this board position.
     *
     * @param posToCheck the position to check for the ant.
     * @return true if an ant is in given in the board position and false
     * otherwise.
     */
    public boolean antInPosition(Pos posToCheck) {
        return board[posToCheck.getPosY()][posToCheck.getPosX()].getAntOnTile() != null;
    }

    /**
     * Returns the ant in a position.
     *
     * @param antPosition the position to return the ant from.
     * @return the ant in the given position.
     */
    public Ant antAt(Pos antPosition) {
        return board[antPosition.getPosY()][antPosition.getPosX()].getAntOnTile();
    }

    /**
     * Sets the specified ant to the specified position on the board.
     *
     * @param posToSetAnt position to set the ant.
     * @param antToSet ant to set the position of.
     */
    public void setAntAt(Pos posToSetAnt, Ant antToSet) {
        board[posToSetAnt.getPosY()][posToSetAnt.getPosX()].setAntOnTile(antToSet);
        antToSet.setBoardPosition(posToSetAnt);
    }

    /**
     * Clears the ant from the board at the given position.
     *
     * @param posToClear position to clear the ant from.
     */
    public void clearAntAt(Pos posToClear) {
        Ant ant = board[posToClear.getPosY()][posToClear.getPosX()].getAntOnTile();
        board[posToClear.getPosY()][posToClear.getPosX()].setAntOnTile(null);
        ant.setBoardPosition(null);
    }

    /**
     * Checks if an an ant with the given id is alive.
     *
     * @param id the id of the ant to check if it is alive or not.
     * @return true if the ant is alive and false if not.
     */
    public boolean antIsAlive(int id) {
        return antsOnBoard.get(id).getAntIsAlive();
    }

    /**
     * Returns the position of the ant with the given id.
     *
     * @param id the id of the ant to find the position of.
     * @return the position of the ant specified by the id.
     */
    public Pos getAntPosition(int id) {
        return antsOnBoard.get(id).getBoardPosition();
    }

    /**
     * Kills the ant on the board at the given ant position.
     *
     * @param positionToKillAnt the position of the ant to kill.
     */
    public void killAntAt(Pos positionToKillAnt) {
        Ant ant = board[positionToKillAnt.getPosY()][positionToKillAnt.getPosX()].getAntOnTile();
        ant.killAnt();
        clearAntAt(positionToKillAnt);
        numberOfAntsAlive.put(ant.getAntColour(), numberOfAntsAlive.get(ant.getAntColour()) - 1);
    }

    /**
     * Returns the amount of food at a given position.
     *
     * @param position the position to find the amount of food at.
     * @return the number of food at the given position.
     */
    public int numberOfFoodAt(Pos position) {
        return board[position.getPosY()][position.getPosX()].getFoodInTile();
    }

    /**
     * Sets the given amount of food at the given position.
     *
     * @param foodPosition the position to set the food.
     * @param amountOfFood the amount of food to set in the given position.
     */
    public void setFoodAt(Pos foodPosition, int amountOfFood) {
        int oldAmountOfFood = board[foodPosition.getPosY()][foodPosition.getPosX()].getFoodInTile();
        int difference = amountOfFood - oldAmountOfFood;
        if (board[foodPosition.getPosY()][foodPosition.getPosX()].getCellTerrain()
                == Terrain.BLACKBASE) {
            int totalBlackFood = numberOfFoodInBase.get(Colour.BLACK);
            int newTotalFood = totalBlackFood + difference;
            numberOfFoodInBase.put(Colour.BLACK, newTotalFood);
        } else if (board[foodPosition.getPosY()][foodPosition.getPosX()].getCellTerrain()
                == Terrain.REDBASE) {
            int totalRedFood = numberOfFoodInBase.get(Colour.RED);
            int newTotalFood = totalRedFood + difference;
            numberOfFoodInBase.put(Colour.RED, newTotalFood);
        }
        board[foodPosition.getPosY()][foodPosition.getPosX()].setFoodInTile(amountOfFood);
    }

    /**
     * returns the total number of ants alive for a given colour.
     *
     * @param colourOfTeam the colour of the team to find the number alive ants
     * of.
     * @return the number of alive ants for the given colour.
     */
    public int getNumberOfAntsAlive(Colour colourOfTeam) {
        return numberOfAntsAlive.get(colourOfTeam);
    }

    /**
     * Returns the total number of food in the base of the given colour.
     *
     * @param colourOfTeam colour of the team to get the amount of food in the
     * base.
     * @return the total food in the base of the given colour.
     */
    public int getNumberOfFoodInBase(Colour colourOfTeam) {
        return numberOfFoodInBase.get(colourOfTeam);
    }

    /**
     * Checks if an anthill of a given colour is at a given position.
     *
     * @param anthillPos the position to check for the ant hill.
     * @param anthillColour the colour to check for the ant hill.
     * @return true if an ant hill of the given colour is at the given position
     * and false otherwise.
     */
    public boolean anthillAt(Pos anthillPos, Colour anthillColour) {
        return board[anthillPos.getPosY()][anthillPos.getPosX()].getCellTerrain()
                == colourToBaseMatch.get(anthillColour);
    }

    /**
     * Sets the given marker in the given position, for the given colour.
     *
     * @param markerPos the position to set the marker.
     * @param markerCol the colour to set the marker for.
     * @param mark the mark to set.
     */
    public void setMarker(Pos markerPos, Colour markerCol, int mark) {
        board[markerPos.getPosY()][markerPos.getPosX()].setMarker(markerCol, mark);
    }

    /**
     * Clears a given marker for a given colour at the given position.
     *
     * @param markerPos the position to clear the marker.
     * @param markerCol the colour of the marker to clear.
     * @param mark the mark to clear.
     */
    public void clearMarker(Pos markerPos, Colour markerCol, int mark) {
        int index = board[markerPos.getPosY()][markerPos.getPosX()].getMarkers(markerCol).indexOf(mark);
        if (index >= 0) {
            board[markerPos.getPosY()][markerPos.getPosX()].getMarkers(markerCol).remove(index);
        }
    }

    /**
     * Checks if a given marker of the given colour is at the given position.
     *
     * @param markerPos the position to check for the marker.
     * @param markerCol the colour of the marker that is being checked.
     * @param mark the type of mark to check for.
     * @return true if the given marker for the given colour is found in the
     * given position and false otherwise.
     */
    public boolean checkMarker(Pos markerPos, Colour markerCol, int mark) {
        int index = board[markerPos.getPosY()][markerPos.getPosX()].getMarkers(markerCol).indexOf(mark);
        if (index < 0) {
            return false;
        }
        return true;
    }

    /**
     * Checks for any marker type at the given position for the given colour.
     *
     * @param markerPos the position to check for markers at.
     * @param markerCol the colour to check for markers.
     * @return true if any markers exists for the given colour at the given
     * position and false otherwise.
     */
    public boolean checkAnyMarker(Pos markerPos, Colour markerCol) {
        return board[markerPos.getPosY()][markerPos.getPosX()].getMarkers(markerCol).size() > 0;
    }

    /**
     * Returns the number of ants both dead and alive on the board.
     *
     * @return number of ants on the board.
     */
    public int getNumberOfAnts() {
        return antsOnBoard.size();
    }

    /**
     * Returns the terrain at a given position on the board.
     *
     * @param positionOfTerrain the position to return the terrain from.
     * @return the board terrain at the given position.
     */
    public Terrain getTerrainAtPosition(Pos positionOfTerrain) {
        return board[positionOfTerrain.getPosY()][positionOfTerrain.getPosX()].getCellTerrain();
    }

    /**
     * Prints the board to ASCII representation. Used for testing and bug
     * finding.
     */
    public void printBoardToASCII() {
        for (int i = 0; i < board.length; i++) {
            if (i % 2 != 0) {
                System.out.print(" ");
            }
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.print("\n");
        }
    }

    @Override
    public String toString() {
        return boardName;
    }

    /**
     * Returns the board representation.
     *
     * @return the board representation.
     */
    public BoardTile[][] getBoard() {
        return board;
    }

    /**
     * Create a copy of a game board.
     * 
     * @return Copy of the game board.
     */
    public Board copy() {
        BoardTile[][] copyBoard = new BoardTile[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                copyBoard[i][j] = new BoardTile(board[i][j].getFoodInTile(), board[i][j].getCellTerrain());
            }
        }
        return new Board(copyBoard, "copyBoard");
    }

}
