/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antgameproject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author jamestwisleton
 */
public class AntWorldChecker {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        /*
         Read the world from a file into a String.
         */
        String fn = "src//antgameproject//testWorld.txt";
        String brainString = "";
        BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(fn)));
        try {
            String line;
            while ((line = br.readLine()) != null) {
                brainString += line;
                brainString += '\n';
                System.out.println(brainString);
            }
        } finally {
            br.close();
        }

    }
}
