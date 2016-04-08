package antgameproject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 *
 * @author James Twisleton
 */
public class AntWorldChecker {

    public static class AntWorldCheckerException extends Exception {

        public AntWorldCheckerException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException, AntWorldCheckerException {
        String fn = "src//antgameproject//testWorld.txt";
        Board b = loadWorld(fn);
    }

    public static Boolean tournamentReady(Board b) {
        return null;
    }

    public static Board loadWorld(String fileName) throws FileNotFoundException, IOException, AntWorldCheckerException {

        /*
         If the world is syntactically correct
         */
        if (checkWorldSyntax(fileName)) {

        }
        return null;
    }

    public static Boolean checkWorldSyntax(String fileName) throws FileNotFoundException, IOException, AntWorldCheckerException {

        /*
         Read the world from a file into a String.
         */
        ArrayList<String> lines = new ArrayList<>();
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
            throw new AntWorldCheckerException("Invalid world: first and/or second lines are not digits.");
        }

        /*
         Check that the given dimension values match the world, if not then
         throw an exception (additional x dimension check for every line done
         later).
         */
        int xDimension = Integer.parseInt(lines.get(0));
        int yDimension = Integer.parseInt(lines.get(0));

        if (lines.get(2).length() != (xDimension * 2) - 1) {
            throw new AntWorldCheckerException("xDimension doesn't match size of world.");
        } else if (yDimension != lines.size() - 2) {
            throw new AntWorldCheckerException("yDimension doesn't match size of the world.");
        }

        /*
         Check the third line and last lines are all hashtags and spaces, 
         throw exception if not.
         */
        if (!lines.get(2).matches("(#|\\s)+") || !lines.get(lines.size() - 1).matches("(#|\\s)+")) {
            throw new AntWorldCheckerException("3rd or last line isn't made up of # and spaces");
        }

        /*
         Iterate through worldString, analysing each line to make sure it is 
         syntactically valid, throw exception if not.
         */        
        for (int i = 3; i < lines.size() - 1; i++) {
            if (!lines.get(i).matches("\\s?#(\\d|\\s|\\.|\\-|\\+|#)+#")) {
                throw new AntWorldCheckerException("line in world doesn't match regex");
            } else {

                /*
                 If row should/shouldn't be offset isn't/is, throw exception.
                 */
                if (i % 2 != 0) {
                    if (lines.get(i).charAt(0) != ' ') {
                        throw new AntWorldCheckerException("Expected line " + i + " to be offset, it wasn't.");
                    } else {

                        /*
                         If row is wrong length, throw exception
                         */
                        if (lines.get(i).length() != (xDimension * 2)) {
                            throw new AntWorldCheckerException("Line " + i + " wrong length.");
                        }
                    }
                } else {
                    if (lines.get(i).charAt(0) != '#') {
                        throw new AntWorldCheckerException("Expected line " + i + "to not be offset, it was");
                    } else {

                        /*
                         If row is wrong length, throw exception.
                         */
                        if (lines.get(i).length() != (xDimension * 2) - 1) {
                            throw new AntWorldCheckerException("Line " + i + " wrong length");
                        }

                    }
                }
            }
        }        
        return true;
    }
}
