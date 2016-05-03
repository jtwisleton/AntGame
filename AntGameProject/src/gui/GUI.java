/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import antgameproject.AntGameTournament;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author wilki
 */
public class GUI extends StateBasedGame {

    private AntGameTournament tournament;
    
    public GUI(String name, AntGameTournament tournament) {
        super(name);
        this.tournament = tournament;
    }
	   
    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        float scale = getScale(container);
        System.out.println(scale);
        this.addState(new GUITeamScreen(scale));
        this.addState(new GUITitleScreen(scale));
        this.addState(new GUIMenuScreen(tournament, scale));
        this.addState(new GUITournamentOptions(tournament, scale));
        this.addState(new GUISingleGameOptions(tournament, scale));
        this.addState(new GUITournamentDisplay(tournament, scale));
        this.addState(new GUISingleGameDisplay(tournament, scale));
    }

    private float getScale(GameContainer container) {
        System.out.println(container.getWidth());
        if(container.getWidth() == 1920){
            return 1;
        } else if(container.getWidth() == 1440){
            return 0.75f;
        } else {
            return 0.5f;
        }
    }
    
}
