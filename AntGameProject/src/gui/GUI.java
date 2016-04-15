/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import antgameproject.AntGameTournament;
import org.lwjgl.input.Cursor;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
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
        //container.setMouseCursor(new Image("resources/hand_cursor.png"), 0, 0);
        this.addState(new GUITeamScreen());
        this.addState(new GUITitleScreen());
        this.addState(new GUIMenuScreen());
        this.addState(new GUITournamentOptions(tournament));
        this.addState(new GUISingleGameOptions(tournament));
        this.addState(new GUITournamentDisplay(tournament));
        this.addState(new GUISingleGameDisplay(tournament));
    }
    
}
