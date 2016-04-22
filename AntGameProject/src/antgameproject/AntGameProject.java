package antgameproject;

import gui.GUI;
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
	app.setDisplayMode(1920, 1080, false);
        app.setTargetFrameRate(60);
	app.start();
    }
      
}
