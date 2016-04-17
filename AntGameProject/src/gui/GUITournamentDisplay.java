/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import antgameproject.AntBrain;
import antgameproject.AntGameTournament;
import java.util.List;
import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Font;
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
public class GUITournamentDisplay extends BasicGameState{

    private AntGameTournament tournament;
    private Font gameFont;
    private Image pageTitle;
    private Image playNextRound;
    private Image playNextRoundHover;
    private Image currentPlayNextRound;
    private Image skipToEnd;
    private Image skipToEndHover;
    private Image currentSkipToEnd;
    private Image exit;
    private Image exitHover;
    private Image currentExit;
    private Image up;
    private Image upHover;
    private Image down;
    private Image downHover;
    private Image currentUp;
    private Image currentDown;
    private MouseOverArea playNextRoundMO;
    private MouseOverArea skipToEndMO;
    private MouseOverArea exitMO;
    private MouseOverArea upMO;
    private MouseOverArea downMO;
    private List<AntBrain> antBrainList;
    private int topOfAntBrainList;
    private int bottomOfAntBrainList;
    
    
    public GUITournamentDisplay(AntGameTournament tournament){
        this.tournament = tournament;
    }
    
    @Override
    public int getID() {
        return 6;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        gameFont = new AngelCodeFont("resources/hugeFont.fnt", "resources/hugeFont_0.png");
        pageTitle = new Image("resources/tournamentLogo.png");
        playNextRound = new Image("resources/playNextRound.png");
        playNextRoundHover = new Image("resources/playNextRoundHover.png");
        currentPlayNextRound = playNextRound;
        skipToEnd = new Image("resources/skipToEnd.png");
        skipToEndHover = new Image("resources/skipToEndHover.png");
        currentSkipToEnd = skipToEnd;
        exit = new Image("resources/exitalt.png");
        exitHover = new Image("resources/exitAltHover.png");
        currentExit = exit;
        up = new Image("resources/up.png");
        upHover = new Image("resources/upHover.png");
        down = new Image("resources/down.png");
        downHover = new Image("resources/downHover.png");
        currentUp = up;
        currentDown = down;
        
        playNextRoundMO = new MouseOverArea(gc, playNextRound, 25, pageTitle.getHeight() + 40);
        skipToEndMO = new MouseOverArea(gc, skipToEnd, 25, pageTitle.getHeight() + 140);
        exitMO = new MouseOverArea(gc, exit, 25, pageTitle.getHeight() + 240);
        upMO = new MouseOverArea(gc, up, 1850, 580);
        downMO = new MouseOverArea(gc, down, 1850, 650);
        
        antBrainList = tournament.getListOfAntBrains();
        topOfAntBrainList = 0;
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        if(playNextRoundMO.isMouseOver()){
            currentPlayNextRound = playNextRoundHover;
        } else if(skipToEndMO.isMouseOver()){
            currentSkipToEnd = skipToEndHover;
        } else if(exitMO.isMouseOver()){
            currentExit = exitHover;
            if(gc.getInput().isMouseButtonDown(0)){
                tournament.reset();
                sbg.getState(2);
                sbg.enterState(2);
            }
        } else if(upMO.isMouseOver()){
            currentUp = upHover;
            if(gc.getInput().isMouseButtonDown(0)){
                if(tournament.getListOfAntBrains().size() > 10){
                    topOfAntBrainList++;
                }
                bottomOfAntBrainList = setListBottom(antBrainList, bottomOfAntBrainList);
            }
        } else if(downMO.isMouseOver()){
            currentDown = downHover;
            if(gc.getInput().isMouseButtonDown(0)){
                if(tournament.getListOfAntBrains().size() > 10){
                    topOfAntBrainList--;
                }
                bottomOfAntBrainList = setListBottom(antBrainList, bottomOfAntBrainList);
            }
        } else {
            currentPlayNextRound = playNextRound;
            currentSkipToEnd = skipToEnd;
            currentExit = exit;
            currentUp = up;
            currentDown = down;
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        grphcs.setLineWidth(3);
        
        grphcs.drawImage(pageTitle, 1920/2 - pageTitle.getWidth()/2, 20);
        grphcs.drawImage(currentPlayNextRound, 25, pageTitle.getHeight() + 40);
        grphcs.drawImage(currentSkipToEnd, 25, pageTitle.getHeight() + 140);
        grphcs.drawImage(currentExit, 25, pageTitle.getHeight() + 240);
        grphcs.drawImage(currentUp, 1850, 580);
        grphcs.drawImage(currentDown, 1850, 650);
        
        int topMargin = pageTitle.getHeight() + 40;
        gameFont.drawString(exit.getWidth()+60, topMargin+5, "Ant brain");
        gameFont.drawString(exit.getWidth()+800, topMargin+5, "P");
        gameFont.drawString(exit.getWidth()+885, topMargin+5, "W");
        gameFont.drawString(exit.getWidth()+975, topMargin+5, "D");
        gameFont.drawString(exit.getWidth()+1068, topMargin+5, "L");
        gameFont.drawString(exit.getWidth()+1148, topMargin+5, "PF");
        gameFont.drawString(exit.getWidth()+1235, topMargin+5, "PA");
        gameFont.drawString(exit.getWidth()+1340, topMargin+5, "S");
        grphcs.drawRoundRect(exit.getWidth()+50, pageTitle.getHeight() + 40, 1350, 800, 10);
        grphcs.drawLine(exit.getWidth()+50, pageTitle.getHeight() + 100, exit.getWidth()+1400, pageTitle.getHeight() + 100);
        for(int i = 0; i < 7; i++){
            int yLinePosition = exit.getWidth()+ 770 + i * 90;
            grphcs.drawLine(yLinePosition, topMargin, yLinePosition, topMargin+800);
        }
        
        //add printing of teams scores
    }
    
    private int setListBottom(List listToSetBottomOf, int listTopPos){
        int listBottom = 0;
        if(listToSetBottomOf.size() > 0){
            if(listToSetBottomOf.size() < 10){
                listBottom = listToSetBottomOf.size();
            } else {
                listBottom = listTopPos+11;
            }
        }
       return listBottom;
    }

}
