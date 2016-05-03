package antgameproject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author JTwisleton
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

    public Boolean tournamentReady(BoardTile[][] b) {

        return null;
    }

    public Boolean validAnthills(BoardTile[][] b) throws AntWorldLoaderException {
        int blackBaseCount = 0;
        int redBaseCount = 0;
        
        /*
        Check there are the right amount of ant hill board tiles, throw exception
        if not.
        */
        for (BoardTile[] b2 : b) {
            for (BoardTile b1 : b2) {
                if (b1.getCellTerrain() == Terrain.BLACKBASE) {
                    blackBaseCount++;
                }
                if (b1.getCellTerrain() == Terrain.REDBASE) {
                    redBaseCount++;
                }
            }        
        }
        
        if(blackBaseCount!=163||redBaseCount!=163){
            throw new AntWorldLoaderException("wrong amount of red or black ant hill board tiles.");
        }else{
            return true;
        }
        
        
        
        
    }

    public Boolean validFood(BoardTile[][] b) {
        return null;
    }

    public Boolean validRocks(BoardTile[][] b) {
        return null;
    }

    /*
     Calls the checkWorldSyntax method, then uses the lines ArrayList to create
     a board from the given world file if checkWorldSyntax returns true.
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

    /*
     Returns true is a world is syntactically correct, or throws an exception.
     Also loads given world into lines ArrayList.
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
