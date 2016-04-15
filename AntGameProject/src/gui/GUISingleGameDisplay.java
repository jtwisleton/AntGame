/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import antgameproject.AntGameTournament;
import antgameproject.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author wilki
 */
public class GUISingleGameDisplay extends BasicGameState {

    private AntGameTournament tournament;
    private int divide;
    private int steps;
    private Game game;
    
    public GUISingleGameDisplay(AntGameTournament tournament){
        this.tournament = tournament;
    }
    
    @Override
    public int getID() {
        return 7;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
            divide = 1400;
            steps = 14;
            game = tournament.getCurrentGame();
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        grphcs.setLineWidth(4);
        grphcs.drawString("Single Game Display", 500, 350);
        grphcs.drawLine(divide, 0, divide, 1080);
        
        grphcs.drawRoundRect(divide + 20, 250, 480, 350, 10);
        
        grphcs.drawRoundRect(divide + 20, 620, 480, 350, 10);
    }

    
    
}
