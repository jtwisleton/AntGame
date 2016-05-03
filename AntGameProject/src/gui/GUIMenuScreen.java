/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import antgameproject.AntGameTournament;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author wilki
 */
public class GUIMenuScreen extends BasicGameState{

    private AntGameTournament tournament;
    private Image title;
    private Image singlePButton;
    private Image tournamentButton;
    private Image exitButton;
    private Image currentSP;
    private Image currentExit;
    private Image currentTourn;
    private Image exitButtonHover;
    private Image singleGButtonHover;
    private Image tournamentButtonHover;
    private int buttonPosX;
    private int buttonStartPosY;
    private int offset;
    private MouseOverArea singleGameMO;
    private MouseOverArea tournamentGameMO;
    private MouseOverArea exitMO;
    private float screenScale;

    
    public GUIMenuScreen(AntGameTournament tournament, float screenScale){
        this.screenScale = screenScale;
        this.tournament = tournament;
    }
    
    @Override
    public int getID() {
        return 2;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        title = new Image("resources/menu_screen1.png");
        singlePButton = new Image("resources/spB.png");
        tournamentButton = new Image("resources/tournB.png");
        exitButton = new Image("resources/exitB.png");
        exitButtonHover = new Image("resources/exitBAlt.png");
        singleGButtonHover = new Image("resources/spBAlt.png");
        tournamentButtonHover = new Image("resources/tournBAlt.png");
        currentSP = singlePButton;
        currentTourn = tournamentButton;
        currentExit = exitButton;
        buttonPosX = 960 - 480;
        buttonStartPosY = 500;
        offset = 190;
        //singleGameMO = new MouseOverArea(gc, singlePButton, buttonPosX, buttonStartPosY);

        singleGameMO = new MouseOverArea(gc, singlePButton, (int)(buttonPosX*screenScale), (int)(buttonStartPosY*screenScale), 
                (int)(singlePButton.getWidth()*screenScale), (int)(singlePButton.getHeight()*screenScale));
        tournamentGameMO = new MouseOverArea(gc, tournamentButton, (int)(buttonPosX*screenScale), (int)((buttonStartPosY + offset)*screenScale),
                (int)(tournamentButton.getWidth()*screenScale), (int)(tournamentButton.getHeight()*screenScale));
        exitMO = new MouseOverArea(gc, exitButton, (int)(buttonPosX*screenScale), (int)((buttonStartPosY + 2 * offset)*screenScale),
                (int)(exitButton.getWidth()*screenScale), (int)(exitButton.getHeight()*screenScale));
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        //gc.setDefaultMouseCursor();
        tournament.reset(); //probably remove
        if(singleGameMO.isMouseOver()){
            currentSP = singleGButtonHover;
            if(gc.getInput().isMouseButtonDown(0)){
                sbg.enterState(5);
            }
        } else if(tournamentGameMO.isMouseOver()) {
            currentTourn = tournamentButtonHover;
            if(gc.getInput().isMouseButtonDown(0)){
                sbg.enterState(4);
            }
        }else if(exitMO.isMouseOver()) {
            currentExit = exitButtonHover;
            if(gc.getInput().isMouseButtonDown(0)){
                System.exit(0);
            } 
        } else {
            currentSP = singlePButton;
            currentTourn = tournamentButton;
            currentExit = exitButton;
        }
       
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        grphcs.scale(screenScale, screenScale);
        grphcs.drawImage(title, 0, 0);
        grphcs.drawImage(currentSP, buttonPosX, buttonStartPosY);
        grphcs.drawImage(currentTourn, buttonPosX, buttonStartPosY + offset);
        grphcs.drawImage(currentExit, buttonPosX, buttonStartPosY + 2 * offset);
    }

}
