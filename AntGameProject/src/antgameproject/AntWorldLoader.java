package antgameproject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Class that loads in a description of an AntWorld from a file, checking if it
 * is syntactically correct and conforms to the tournament world parameters.
 *
 * @author Team18
 */
public class AntWorldLoader {

    /*
     String representation of the world to be loaded in.
     */
    static ArrayList<String> lines;

    public static class AntWorldLoaderException extends Exception {

        public AntWorldLoaderException(String message) {
            super(message);
        }
    }

    /**
     * Calls boardHasValidAntHills, validFood and validRocks methods on given
     * board, checks it is the right height and width, and returns true if all
     * these conditions are met.
     *
     * @param boardToTest BoardTile array to check if tournament ready.
     * @return true if BoardTile world is valid, false if not.
     */
    private Boolean tournamentReady(BoardTile[][] boardToTest) {
        if (boardToTest.length == 150 && boardToTest[0].length == 150
                && boardHasValidAntHills(boardToTest) && validFood(boardToTest)
                && validRocks(boardToTest)) {
            return true;
        }
        return false;
    }

    /**
     * Returns true if validAntHillForm method returns true on the black and red
     * bases, false if not.
     *
     * @param boardToTest board to check if has valid anthills.
     * @return true if anthills valid, false if not.
     */
    private boolean boardHasValidAntHills(BoardTile[][] boardToTest) {
        Pos blackAntHillStart = findAntHillStart(boardToTest, Terrain.BLACKBASE);
        Pos redAntHillStart = findAntHillStart(boardToTest, Terrain.REDBASE);
        if (validAntHillForm(boardToTest, blackAntHillStart, Terrain.BLACKBASE)
                && validAntHillForm(boardToTest, redAntHillStart, Terrain.REDBASE)) {
            return true;
        }
        return false;
    }

    /**
     * Returns the first position of the given type of ant hill.
     *
     * @param boardToTestBaseOn board to find first anthill position on.
     * @param baseTerrain type of anthill to look for.
     * @return
     */
    private Pos findAntHillStart(BoardTile[][] boardToTestBaseOn, Terrain baseTerrain) {
        boolean found = false;
        int i = 0;
        Pos colourBasePosition = null;
        while (i < 150 && !found) {
            int j = 0;
            while (j < 150 && !found) {
                if (boardToTestBaseOn[i][j].getCellTerrain() == baseTerrain) {
                    colourBasePosition = new Pos(j, i);
                    found = true;
                }
                j++;
            }
            i++;
        }

        return colourBasePosition;
    }

    /**
     * Checks that given anthill type is of the right shape.
     *
     * @param boardToCheck board to check anthill are valid shape on.
     * @param positionToStartCheck first position of anthill to check.
     * @param baseTerrain type of anthill.
     * @return true if given anthill type is of right shape on world, false if
     * not.
     */
    private boolean validAntHillForm(BoardTile[][] boardToCheck, Pos positionToStartCheck,
            Terrain baseTerrain) {
        int startX = positionToStartCheck.getPosX();
        int startY = positionToStartCheck.getPosY();
        int currentRow = 0;
        int extraBlocks = 0;
        int currentColumn = 0;
        boolean errorFound = false;
        while (currentRow < 13 && !errorFound) {
            while (currentColumn < 7 + extraBlocks && !errorFound) {
                if (boardToCheck[startY + currentRow][startX + currentColumn].getCellTerrain()
                        != baseTerrain) {
                    errorFound = true;
                }
                int edgeNo = isEdgeCase(currentColumn, currentRow, 6 + extraBlocks, 12);
                if (isAnErrorAtEdge(boardToCheck, new Pos(startX + currentColumn, startY + currentRow), edgeNo)) {
                    errorFound = true;
                }
                currentColumn++;
            }

            if (currentRow < 6) {
                extraBlocks++;
                if ((currentRow + positionToStartCheck.getPosY()) % 2 != 0) {
                    currentColumn = 0;
                } else {
                    startX--;
                    currentColumn = 0;
                }
            } else {
                extraBlocks--;
                if ((currentRow + positionToStartCheck.getPosY()) % 2 != 0) {
                    startX++;
                    currentColumn = 0;
                } else {
                    currentColumn = 0;
                }
            }
            currentRow++;

        }
        return !errorFound;
    }

    /**
     * Checks if a given position is the edge of a food blob or ant hill, and if
     * so then what type of edge.
     *
     * @param currentColumn
     * @param currentRow
     * @param maxColumn
     * @param maxRow
     * @return
     */
    private int isEdgeCase(int currentColumn, int currentRow, int maxColumn, int maxRow) {
        int edgeCaseNo = 0;
        if (currentColumn == 0 && currentRow == 0) {
            edgeCaseNo = 1;
        } else if (currentColumn == maxColumn && currentRow == 0) {
            edgeCaseNo = 2;
        } else if (currentColumn == 0 && currentRow == maxRow) {
            edgeCaseNo = 3;
        } else if (currentColumn == maxColumn && currentRow == maxRow) {
            edgeCaseNo = 4;
        } else if (currentRow == 0) {
            edgeCaseNo = 5;
        } else if (currentRow == maxRow) {
            edgeCaseNo = 6;
        } else if (currentColumn == 0) {
            edgeCaseNo = 7;
        } else if (currentColumn == maxRow) {
            edgeCaseNo = 8;
        }
        return edgeCaseNo;
    }

    /**
     * Checks if a food or anthill is surrounded by grass with no food on it,
     * returns true if not, false if so.
     *
     * @param boardToCheck
     * @param pos
     * @param edgeNo
     * @return
     */
    private boolean isAnErrorAtEdge(BoardTile[][] boardToCheck, Pos pos, int edgeNo) {
        boolean edgeError = false;
        int posX = pos.getPosX();
        int posY = pos.getPosY();
        switch (edgeNo) {
            case 1:
                edgeError = !((boardToCheck[posY - 1][posX - 1].getCellTerrain() == Terrain.GRASS
                        && boardToCheck[posY - 1][posX - 1].getFoodInTile() == 0));
                break;
            case 2:
                edgeError = !((boardToCheck[posY - 1][posX + 1].getCellTerrain() == Terrain.GRASS
                        && boardToCheck[posY - 1][posX + 1].getFoodInTile() == 0));
                break;
            case 3:
                edgeError = !((boardToCheck[posY + 1][posX - 1].getCellTerrain() == Terrain.GRASS
                        && boardToCheck[posY + 1][posX - 1].getFoodInTile() == 0));
                break;
            case 4:
                edgeError = !((boardToCheck[posY + 1][posX + 1].getCellTerrain() == Terrain.GRASS
                        && boardToCheck[posY + 1][posX + 1].getFoodInTile() == 0));
                break;
            default:
                break;
        }

        if (edgeError) {
            return edgeError;
        }

        if (edgeNo == 5 || edgeNo == 1 || edgeNo == 2) {
            edgeError = !(boardToCheck[posY - 1][posX].getCellTerrain() == Terrain.GRASS
                    && boardToCheck[posY - 1][posX].getFoodInTile() == 0);
        } else if (edgeNo == 6 || edgeNo == 3 || edgeNo == 4) {
            edgeError = !(boardToCheck[posY + 1][posX].getCellTerrain() == Terrain.GRASS
                    && boardToCheck[posY + 1][posX].getFoodInTile() == 0);
        }

        if (edgeError) {
            return edgeError;
        }

        if (edgeNo == 7 || edgeNo == 1 || edgeNo == 3) {
            edgeError = !(boardToCheck[posY][posX - 1].getCellTerrain() == Terrain.GRASS
                    && boardToCheck[posY][posX - 1].getFoodInTile() == 0);
        } else if (edgeNo == 8 || edgeNo == 2 || edgeNo == 4) {
            edgeError = !(boardToCheck[posY][posX + 1].getCellTerrain() == Terrain.GRASS
                    && boardToCheck[posY][posX + 1].getFoodInTile() == 0);
        }

        return edgeError;
    }

    /**
     * Checks if a position is surrounded by grass, returns true if so false if
     * not.
     *
     * @param position
     * @param board
     * @return
     */
    private boolean checkSurroundedByGrass(Pos position, BoardTile[][] board) {
        int positionY = position.getPosY();
        int positionX = position.getPosX();
        for (int i = positionY - 1; i < positionY + 2; i++) {
            for (int j = positionX - 1; j < positionX + 2; j++) {
                if (!(board[i][j].getCellTerrain() == Terrain.GRASS
                        && board[i][j].getFoodInTile() == 0) || (positionY == i && positionX == j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if a world has valid food blocks on it, returns true if so false
     * if not.
     *
     * @param boardToFindFoodOn
     * @return
     */
    private Boolean validFood(BoardTile[][] boardToFindFoodOn) {
        int blobsOfFoodFound = 0;
        boardToFindFoodOn = new Board(boardToFindFoodOn, "").copy().getBoard();
        for (int i = 1; i < 149; i++) {
            for (int j = 1; j < 149; j++) {
                if (boardToFindFoodOn[i][j].getFoodInTile() > 0) {
                    if (i % 2 == 0) {
                        if (boardToFindFoodOn[i + 1][j + 4].getFoodInTile() > 0) {
                            int[] offset = {0, 1, 0, 1, 0};
                            testFoodBlock(new Pos(j, i), boardToFindFoodOn, offset);
                        } else if (boardToFindFoodOn[i + 1][j - 1].getFoodInTile() > 0) {
                            int[] offset = {-1, 0, -1, 0, 0};
                            testFoodBlock(new Pos(j, i), boardToFindFoodOn, offset);
                        }
                    } else {
                        if (boardToFindFoodOn[i + 1][j + 5].getFoodInTile() > 0) {
                            int[] offset = {1, 0, 1, 0, 0};
                            testFoodBlock(new Pos(j, i), boardToFindFoodOn, offset);
                        } else if (boardToFindFoodOn[i + 1][j].getFoodInTile() > 0) {
                            int[] offset = {0, -1, 0, -1, 0};
                            testFoodBlock(new Pos(j, i), boardToFindFoodOn, offset);
                        }
                    }
                    blobsOfFoodFound++;
                }
            }
        }
        for (int i = 0; i < 149; i++) {
            for (int j = 0; j < 149; j++) {
                if (boardToFindFoodOn[i][j].getFoodInTile() > 0) {
                    return false;
                }
            }
        }

        return blobsOfFoodFound == 11;
    }

    /**
     * Tests individual blocks of food to check they're valid.
     *
     * @param pos
     * @param boardToFindFoodOn
     * @param offsets
     * @return
     */
    private boolean testFoodBlock(Pos pos, BoardTile[][] boardToFindFoodOn, int[] offsets) {
        int posX = pos.getPosX();
        int posY = pos.getPosY();
        int i = 0;
        boolean errorFound = false;
        while (i < 5 && !errorFound) {
            int j = 0;
            while (j < 5 && !errorFound) {
                if (boardToFindFoodOn[posY + i][posX + j].getCellTerrain() != Terrain.GRASS
                        || boardToFindFoodOn[posY + i][posX + j].getFoodInTile() != 5) {
                    errorFound = true;
                }
                int edgeNo = isEdgeCase(i, j, 5, 5);
                if (isAnErrorAtEdge(boardToFindFoodOn, new Pos(posX + j, posY + i), edgeNo)) {
                    errorFound = true;
                }
                boardToFindFoodOn[posY + i][posX + j] = new BoardTile(0, Terrain.GRASS);
                j++;
            }
            posX += offsets[i];
            i++;
        }
        return errorFound;
    }

    /**
     * Checks if a world has valid rocks on it.
     *
     * @param boardToFindRocksOn
     * @return
     */
    private Boolean validRocks(BoardTile[][] boardToFindRocksOn) {
        int rockCount = 0;
        for (int i = 1; i < 149; i++) {
            for (int j = 1; j < 149; j++) {
                if (boardToFindRocksOn[i][j].getCellTerrain() == Terrain.ROCK) {
                    if (checkSurroundedByGrass(new Pos(j, i), boardToFindRocksOn)) {
                        return false;
                    }
                    rockCount++;
                }
            }
        }
        return rockCount == 14;
    }

    /**
     * Calls the checkWorldSyntax method, then uses the lines ArrayList to
     * create a board from the given world file if checkWorldSyntax returns
     * true.
     *
     * @param fileName
     * @param name
     * @param tournamentReady
     * @return
     * @throws antgameproject.AntWorldLoader.AntWorldLoaderException
     * @throws IOException
     */
    public Board loadWorld(String fileName, String name, boolean tournamentReady) throws AntWorldLoaderException, IOException {

        /*
         If the world is syntactically correct
         */
        if (checkWorldSyntax(fileName)) {

            /*
             Set the board size depending on the given world.
             */
            int ySize = lines.size() - 2;
            int xSize = lines.get(2).length();
            BoardTile[][] board = new BoardTile[ySize][xSize];

            /*
             Iterate over each line in the line ArrayList except the first 2
             (which declare the world size).
             */
            for (int i = 0; i < ySize; i++) {

                /*
                 Put iterator selected line in a String. 
                 */
                String currentLine = lines.get(i + 2);

                /*
                 Iterate over the characters in the iterator selected line, adding 
                 corresponding BoardTiles to the board array.
                 */
                for (int j = 0; j < xSize; j++) {
                    if (currentLine.charAt(j) == '#') {
                        board[i][j] = new BoardTile(0, Terrain.ROCK);
                    } else if (currentLine.charAt(j) == '.') {
                        board[i][j] = new BoardTile(0, Terrain.GRASS);
                    } else if (currentLine.charAt(j) == '+') {
                        board[i][j] = new BoardTile(0, Terrain.REDBASE);
                    } else if (currentLine.charAt(j) == '-') {
                        board[i][j] = new BoardTile(0, Terrain.BLACKBASE);
                    } else {

                        /*
                         If current character isn't #, ., +, or -, check if it's
                         a digit. If it's not, throw an exception.
                         */
                        String s = Character.toString(currentLine.charAt(j));
                        if (s.matches("\\d")) {

                            /*
                             If character is a digit, place a new GRASS BoardTile
                             on the board with food value set to the digit.
                             */
                            board[i][j] = new BoardTile(Integer.parseInt(s), Terrain.GRASS);

                        } else {
                            throw new AntWorldLoaderException("Unknown character found in world String ArrayList: " + s);
                        }
                    }
                }
            }

            /*
             If the tournamentReady Boolean is true, meaning the world needs to
             be tournament ready, run the tournamentReady method on the board
             and return it if it is tournament ready, throw an exception if not.
             */
            if (tournamentReady) {
                if (tournamentReady(board)) {
                    return new Board(board, "Board 1");
                } else {
                    throw new AntWorldLoaderException("Expected tournament ready board.");
                }
            } else {

                /*
                 If the board doesn't need to be tournament ready just return it.
                 */
                return new Board(board, name);
            }

        } else {
            throw new AntWorldLoaderException("checkWorldSyntax failed when called by loadWorld");
        }
    }

    /**
     * Returns true is a world is syntactically correct, or throws an exception.
     * Also loads given world into lines ArrayList.
     *
     * @param fileName
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @throws antgameproject.AntWorldLoader.AntWorldLoaderException
     */
    public Boolean checkWorldSyntax(String fileName) throws FileNotFoundException, IOException, AntWorldLoaderException {

        /*
         Read the world from a file into a String, removing all whitespace.
         */
        lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(fileName)))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line.replaceAll("\\s", ""));
            }
        }

        /*
         Check that first and second lines are digits, throw an exception if not.
         */
        String firstLine = lines.get(0);
        String secondLine = lines.get(1);
        Boolean isFirstLineNumber = firstLine.matches("\\d+");
        Boolean isSecondLineNumber = secondLine.matches("\\d+");
        if (!isFirstLineNumber || !isSecondLineNumber) {
            throw new AntWorldLoaderException("Invalid world: first and/or second lines are not digits.");
        }

        /*
         Check that the given dimension values match the world, if not then
         throw an exception (additional x dimension check for every line done
         later).
         */
        int xDimension = Integer.parseInt(lines.get(0));
        int yDimension = Integer.parseInt(lines.get(1));

        if (lines.get(2).length() != (xDimension)) {
            throw new AntWorldLoaderException("xDimension doesn't match size of world.");
        }

        if (yDimension != lines.size() - 2) {
            throw new AntWorldLoaderException("yDimension doesn't match size of the world.");
        }

        /*
         Check the third line and last lines are all hashtags, throw exception
         if not.
         */
        if (!lines.get(2).matches("#+") || !lines.get(lines.size() - 1).matches("#+")) {
            throw new AntWorldLoaderException("3rd or last line isn't made up of #.");
        }

        /*
         Iterate through worldString, analysing each line to make sure it is 
         syntactically valid, throw exception if not.
         */
        for (int i = 3; i < lines.size(); i++) {
            if (!lines.get(i).matches("#(\\d|\\.|\\-|\\+|#)+#")) {
                throw new AntWorldLoaderException("line in world doesn't match regex: " + lines.get(i));
            } else {

                /*
                 Check line is the right length.
                 */
                if (lines.get(i).length() != xDimension) {
                    throw new AntWorldLoaderException("line in world wrong length:" + lines.get(i));
                }
            }
        }
        return true;
    }
}
