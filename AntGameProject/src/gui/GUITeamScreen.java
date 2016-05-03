package gui;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Class to represent a team screen.
 * 
 * @author team18
 */
public class GUITeamScreen extends BasicGameState {
    
    private Image teamLogo;
    private float screenScale;

    /**
     * Construct a new GUITeamScreen object
     * 
     * @param screenScale Scale of the screen.
     */
    public GUITeamScreen(float screenScale){
        this.screenScale = screenScale;
    }
    
    /**
     * Get the ID of this state.
     *
     * @return This state's ID.
     */
    @Override
    public int getID() {
        return 0;
    }

    /**
     * Initialize the GUITeamScreen.
     *
     * @param gc Game container holding the GUI.
     * @param sbg Game object.
     * @throws SlickException if problem initialising mouse over areas.
     */
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        teamLogo = new Image("resources/team_18_game.png");
    }

    /**
     * Update the game logic.
     *
     * @param container Game container holding the GUI.
     * @param sbg Game object.
     * @param i Delta (unused).
     * @throws SlickException if problem entering states.
     */
    @Override
    public void update(GameContainer container, StateBasedGame sbg, int i) throws SlickException {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(GUITeamScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        sbg.enterState(1);
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
        grphcs.setBackground(new Color(33, 252, 172));
        grphcs.drawImage(teamLogo, 0, 0); 
    }
}
