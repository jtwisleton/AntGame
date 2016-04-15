
package gui;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 *
 * @author wilki
 */
public class GUITeamScreen extends BasicGameState {
    
    private Image teamLogo;

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        teamLogo = new Image("resources/team_18_game.png");
    }

    @Override
    public void update(GameContainer container, StateBasedGame sbg, int i) throws SlickException {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(GUITeamScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        sbg.enterState(1);
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        grphcs.setBackground(new Color(33, 252, 172));
        grphcs.drawImage(teamLogo, 0, 0); 
    }
}
