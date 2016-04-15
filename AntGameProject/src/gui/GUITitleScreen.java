/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.GUITeamScreen;
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
 *
 * @author wilki
 */
public class GUITitleScreen extends BasicGameState {
    
    private Image titleScreen;
    private boolean firstVisit;

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        titleScreen = new Image("resources/title_screen.png");
        firstVisit = true;
    }

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
    
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        grphcs.drawImage(titleScreen, 0, 0);
    }

}
