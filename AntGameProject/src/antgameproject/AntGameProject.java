package antgameproject;

import gui.GUI;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 * AntGameProject is the start up class for the whole project. It starts an instance
 * of a tournament and links this to the GUI. 
 */
public class AntGameProject {

    /**
     * Main class for the creation of the project.
     * @param args the command line arguments
     * @throws org.newdawn.slick.SlickException
     */
    public static void main(String[] args) throws SlickException{
	AntGameTournament tournament = new AntGameTournament();
        AppGameContainer app = new AppGameContainer(new GUI("Ant game", tournament));
        
        // get screen resolution
        Object[] options = {"1920 x 1080",
                            "1440 x 810",
                            "960 x 540"};
        int resSelection = JOptionPane.showOptionDialog(new JFrame(),
            "Please select a screen resolution",
            "Screen resolution",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);
        
        if(resSelection == 0){
            app.setDisplayMode(1920, 1080, false);
        } else if (resSelection == 1){
            app.setDisplayMode(1440, 810, false);
        } else if(resSelection == 2){
            app.setDisplayMode(960, 540, false);
        } else {
            System.exit(1);
        }
        
        app.setTargetFrameRate(60);
	app.start();
    }  
}
