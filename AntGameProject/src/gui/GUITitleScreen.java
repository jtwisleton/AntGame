package gui;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Class to represent a title screen for the GUI.
 * 
 * @author team18
 */
public class GUITitleScreen extends BasicGameState {
    
    private Image titleScreen;
    private boolean firstVisit;
    private float screenScale;
    
    /**
     * Construct a new GUITitleScreen object.
     * 
     * @param screenScale Scale of the screen.
     */
    public GUITitleScreen(float screenScale){
        this.screenScale = screenScale;
    }

    /**
     * Get the ID of this state.
     *
     * @return This state's ID.
     */
    @Override
    public int getID() {
        return 1;
    }
    
    /**
     * Initialize the GUITitleScreen.
     *
     * @param gc Game container holding the GUI.
     * @param sbg Game object.
     * @throws SlickException if problem initialising mouse over areas.
     */
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        titleScreen = new Image("resources/title_screen.png");
        firstVisit = true;
    }

    /**
     * Update the game logic.
     *
     * @param gc Game container holding the GUI.
     * @param sbg Game object.
     * @param i Delta (unused).
     * @throws SlickException if problem entering states.
     */
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {   
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException ex) {
            Logger.getLogger(GUITeamScreen.class.getName()).log(Level.SEVERE, null, ex);
        }

        if(firstVisit){
            firstVisit = false;
        } else {
            sbg.enterState(2); 
        }
    }
    
    /**
     * Render the games screen.
     * 
     * @param gc Game container holding the GUI.
     * @param sbg Game object.
     * @param grphcs The graphics context.
     * @throws SlickException if problem when drawing images.
     */
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        grphcs.scale(screenScale, screenScale);
        grphcs.drawImage(titleScreen, 0, 0);
    }

}
