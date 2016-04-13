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
        Board b = loadWorld(fn, false);
        b.printBoardToASCII();
    }

    public static Boolean tournamentReady(BoardTile[][] b) {
        for (int i = 3; i < b.length; i++) {

        }
        return null;
    }

    /*
     Calls the checkWorldSyntax method, then uses the lines ArrayList to create
     a board from the given world file if checkWorldSyntax returns true.
     */
    public static Board loadWorld(String fileName, boolean tournamentReady) throws AntWorldLoaderException, IOException {

        /*
         If the world is syntactically correct
         */
        if (checkWorldSyntax(fileName)) {

            /*
             Set the board size depending on the given world - as the boards have
             spaces between the elements horizontal size is given by the length 
             of each line divided by 2, plus one.
             */
            int ySize = lines.size() - 2;
            int xSize = (lines.get(2).length() / 2)+1;
            BoardTile[][] board = new BoardTile[ySize][xSize];

            /*
             Iterate over each line in the line ArrayList except the first 2
             (which declare the world size).
             */
            for (int i = 0; i < ySize; i++) {
                
                /*
                 Remove all the whitespace in iterator selected line and put the
                 result in a String. 
                 */
                String currentLine = lines.get(i + 2).replaceAll("\\s", "");

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
                    return new Board(board);
                } else {
                    throw new AntWorldLoaderException("Expected tournament ready board.");
                }
            } else {
                
                /*
                If the board doesn't need to be tournament ready just return it.
                */
                return new Board(board);
            }

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
        int yDimension = Integer.parseInt(lines.get(1));

        if (lines.get(2).length() != (xDimension * 2) - 1) {
            throw new AntWorldLoaderException("xDimension doesn't match size of world.");
        }

        if (yDimension != lines.size() - 2) {
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
