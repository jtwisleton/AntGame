package antgameproject;

import gui.GUI;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Team_Name
 */
public class AntGameProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SlickException{
	AntGameTournament tournament = new AntGameTournament();
        AppGameContainer app = new AppGameContainer(new GUI("Ant game", tournament));
	app.setDisplayMode(1920, 1080, false);
        app.setTargetFrameRate(60);
	app.start();
    }
    
    
}
