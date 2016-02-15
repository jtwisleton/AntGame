package antgameproject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author James Twisleton
 */
public class AntWorldChecker {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        /*
         Read the world from a file into a String.
         */
        String fn = "src//antgameproject//testWorld.txt";
        String worldString = "";
        BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(fn)));
        try {
            String line;
            while ((line = br.readLine()) != null) {
                worldString += line;
            }
        } finally {
            br.close();
        }

        /*
         Check that first and second lines are digits
         */
        String[] lineArray = worldString.split("\\n");
        String firstLine = lineArray[0];
        String secondLine = lineArray[1];

        Boolean isFirstLineNumber = firstLine.matches("\\d+");
        Boolean isSecondLineNumber = secondLine.matches("\\d+");

        if (!isFirstLineNumber || !isSecondLineNumber) {
            System.out.println("Invalid world: first and/or second lines are not digits");
        }

        /*
         Check that the given dimension values match the world
         */
        int xDimension = Integer.parseInt(lineArray[0]);
        int yDimension = Integer.parseInt(lineArray[1]);

        /*
         Iterate through worldString, delimiting by line break, analysing
         each line to make sure it is valid.
         */
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];

        }

    }
}
