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

    public static void main(String[] args) throws FileNotFoundException, IOException, AntWorldLoaderException {
        String fn = "src//antgameproject//testWorld.txt";
        Board b = loadWorld(fn);
        //Pos p = new Pos(0,0);
    }

    public static Boolean tournamentReady(Board b) {
        return null;
    }

    /*
     Calls the checkWorldSyntax method, then uses the lines ArrayList to create
     a board from the given world file if checkWorldSyntax returns true.
     */
    public static Board loadWorld(String fileName) throws AntWorldLoaderException, IOException {

        /*
         If the world is syntactically correct
         */
        if (checkWorldSyntax(fileName)) {

            /*
             Set the board size depending on the given world - as the boards have
             spaces between the elements and a line might be offset, horizontal
             size is given by the length of each line divided by 2, plus one.
             */
            int ySize = lines.size();
            int xSize = (lines.get(2).length() / 2) + 1;
            BoardTile[][] board = new BoardTile[ySize][xSize];
            Boolean offset = false;

            /*
             Iterate over each line in the line ArrayList except the first 2
             (which declare the world size).
             */
            for (int i = 2; i < ySize; i++) {

                /*
                 Remove all the whitespace in iterator selected line and put the
                 result in a String. Then set offset boolean to true if line number 
                 is odd, false if not. This offset variable will determine where 
                 in the BoardTile array to place spawned BoardTiles - if offset is 
                 true, then the x coordinate that a BoardTile is placed into will 
                 be increased by one.
                 */
                String currentLine = lines.get(i).replaceAll("\\s", "");

                if (i % 2 != 0) {
                    offset = true;
                } else {
                    offset = false;
                }

                /*
                Set first or last x position on board to be rock depending on
                offset.
                */
                if (offset) {
                    board[i][0] = new BoardTile(0, Terrain.ROCK);
                }else{
                    board[i][xSize-1] = new BoardTile(0, Terrain.ROCK);
                }

                /*
                 Iterate over the characters in the iterator selected line, adding 
                 corresponding BoardTiles to the board array.
                 */
                for (int j = 0; j < xSize - 1; j++) {

                    if (currentLine.charAt(j) == '#') {
                        if (offset) {
                            board[i][j + 1] = new BoardTile(0, Terrain.ROCK);
                        } else {
                            board[i][j] = new BoardTile(0, Terrain.ROCK);
                        }
                    } else if (currentLine.charAt(j) == '.') {
                        if (offset) {
                            board[i][j + 1] = new BoardTile(0, Terrain.GRASS);
                        } else {
                            board[i][j] = new BoardTile(0, Terrain.GRASS);
                        }
                    } else if (currentLine.charAt(j) == '+') {
                        if (offset) {
                            board[i][j + 1] = new BoardTile(0, Terrain.REDBASE);
                        } else {
                            board[i][j] = new BoardTile(0, Terrain.REDBASE);
                        }

                    } else if (currentLine.charAt(j) == '-') {
                        if (offset) {
                            board[i][j + 1] = new BoardTile(0, Terrain.BLACKBASE);
                        } else {
                            board[i][j] = new BoardTile(0, Terrain.BLACKBASE);
                        }
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
                            if (offset) {
                                board[i][j + 1] = new BoardTile(Integer.parseInt(s), Terrain.GRASS);
                            } else {
                                board[i][j] = new BoardTile(Integer.parseInt(s), Terrain.GRASS);
                            }
                        } else {
                            throw new AntWorldLoaderException("Unknown character found in world String ArrayList: " + s);
                        }
                    }
                }
            }
            return new Board(board);
        } else {
            throw new AntWorldLoaderException("checkWorldSyntax failed when called by loadWorld");
        }
    }
    /*
     Returns true is a world is syntactically correct, or throws an exception.
     Also loads given world into lines ArrayList.
     */

    public static Boolean checkWorldSyntax(String fileName) throws FileNotFoundException, IOException, AntWorldLoaderException {

        /*
         Read the world from a file into a String.
         */
        lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(fileName)))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
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
        int yDimension = Integer.parseInt(lines.get(0));

        if (lines.get(2).length() != (xDimension * 2) - 1) {
            throw new AntWorldLoaderException("xDimension doesn't match size of world.");
        } else if (yDimension != lines.size() - 2) {
            throw new AntWorldLoaderException("yDimension doesn't match size of the world.");
        }

        /*
         Check the third line and last lines are all hashtags and spaces, 
         throw exception if not.
         */
        if (!lines.get(2).matches("(#|\\s)+") || !lines.get(lines.size() - 1).matches("(#|\\s)+")) {
            throw new AntWorldLoaderException("3rd or last line isn't made up of # and spaces");
        }

        /*
         Iterate through worldString, analysing each line to make sure it is 
         syntactically valid, throw exception if not.
         */
        for (int i = 3; i < lines.size(); i++) {
            if (!lines.get(i).matches("\\s?#(\\d|\\s|\\.|\\-|\\+|#)+#")) {
                throw new AntWorldLoaderException("line in world doesn't match regex");
            } else {

                /*
                 If row should/shouldn't be offset isn't/is, throw exception.
                 */
                if (i % 2 != 0) {
                    if (lines.get(i).charAt(0) != ' ') {
                        throw new AntWorldLoaderException("Expected line " + i + " to be offset, it wasn't.");
                    } else {

                        /*
                         If row is wrong length, throw exception
                         */
                        if (lines.get(i).length() != (xDimension * 2)) {
                            throw new AntWorldLoaderException("Line " + i + " wrong length.");
                        }
                    }
                } else {
                    if (lines.get(i).charAt(0) != '#') {
                        throw new AntWorldLoaderException("Expected line " + i + "to not be offset, it was");
                    } else {

                        /*
                         If row is wrong length, throw exception.
                         */
                        if (lines.get(i).length() != (xDimension * 2) - 1) {
                            throw new AntWorldLoaderException("Line " + i + " wrong length");
                        }
                    }
                }
            }
        }
        return true;
    }
}
