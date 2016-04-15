/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import antgameproject.AntBrainLoader;
import antgameproject.AntGameTournament;
import antgameproject.AntWorldLoader;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
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
public class GUITournamentOptions extends BasicGameState {

    private AntGameTournament tournament;
    private Font gameFont;
    private Image pageTitle;
    private Image genAntWorld;
    private Image genAntWorldHover;
    private Image curGenWorld;
    private Image up;
    private Image upHover;
    private Image down;
    private Image downHover;
    private Image currentUp1;
    private Image currentDown1;
    private Image currentUp2;
    private Image currentDown2;
    private Image loadAntBrain;
    private Image loadAntBrainHover;
    private Image currentLoadAntBrain;
    private Image loadAntWorld;
    private Image loadAntWorldHover;
    private Image currentLoadAntWorld;
    private Image startTournament;
    private Image startTournamentHover;
    private Image currentStartTournament;
    private MouseOverArea up1MO;
    private MouseOverArea down1MO;
    private MouseOverArea up2MO;
    private MouseOverArea down2MO;
    private MouseOverArea loadAntBrainMO;
    private MouseOverArea loadAntWorldMO;
    private MouseOverArea genAntWorldMO;
    private MouseOverArea startTournMO;
    private int topOfAntBrainList;
    private int topOfAntWorldList;
    private int bottomOfAntBrainList;
    private int bottomOfAntWorldList;
    private List antBrainList;
    private List antWorldList;
    
    
    public GUITournamentOptions(AntGameTournament tournament){
        this.tournament = tournament;
    }
    
    @Override
    public int getID() {
        return 4;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        gameFont = new AngelCodeFont("resources/hugeFont.fnt", "resources/hugeFont_0.png");
        pageTitle = new Image("resources/tournament_setup.png");
        genAntWorld = new Image("resources/generateAntWorld.png");
        genAntWorldHover = new Image("resources/generateAntWorldHover.png");
        curGenWorld = genAntWorld;
        up = new Image("resources/up.png");
        upHover = new Image("resources/upHover.png");
        down = new Image("resources/down.png");
        downHover = new Image("resources/downHover.png");
        currentUp1 = up;
        currentUp2 = up;
        currentDown1 = down;
        currentDown2 = down;
        loadAntBrain = new Image("resources/loadAntBrain.png");
        loadAntBrainHover = new Image("resources/loadAntBrainHover.png");;
        currentLoadAntBrain = loadAntBrain;
        loadAntWorld = new Image("resources/loadAntWorldCenter.png");
        loadAntWorldHover = new Image("resources/loadAntWorldCenterHover.png");;
        currentLoadAntWorld = loadAntWorld;
        startTournament = new Image("resources/startTourn.png");
        startTournamentHover = new Image("resources/startTournHover.png");
        currentStartTournament = startTournament;
        up1MO = new MouseOverArea(gc, up, genAntWorld.getWidth()+630, 600);
        down1MO = new MouseOverArea(gc, down, genAntWorld.getWidth()+630, 680);
        up2MO = new MouseOverArea(gc, up, 1280+530, 600);
        down2MO = new MouseOverArea(gc, down, 1280+530, 680);
        loadAntBrainMO = new MouseOverArea(gc, loadAntBrain, 50, 330);
        loadAntWorldMO = new MouseOverArea(gc, loadAntWorld, 50, 430);
        genAntWorldMO = new MouseOverArea(gc, genAntWorld, 50, 530);
        startTournMO = new MouseOverArea(gc, startTournament, 50, 630);
        topOfAntBrainList = 0;
        topOfAntWorldList = 0;
        antBrainList = tournament.getListOfAntBrains();
        antWorldList = tournament.getListOfAntWorlds();
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        if(loadAntBrainMO.isMouseOver()){
            currentLoadAntBrain = loadAntBrainHover;
            if(gc.getInput().isMouseButtonDown(0)){
                File antBrainToLoad = fileLoader();
                try {
                    tournament.loadAntBrain(antBrainToLoad.getAbsolutePath(), antBrainToLoad.getName());
                    antBrainList = tournament.getListOfAntBrains();
                } catch (AntBrainLoader.AntBrainLoaderException ex) {
                    // notify user
                } catch (IOException ex) {
                    // notify user
                }
            }
        } else if(loadAntWorldMO.isMouseOver()){
            currentLoadAntWorld = loadAntWorldHover;
            if(gc.getInput().isMouseButtonDown(0)){
                File antWorldToLoad = fileLoader();
                try {
                    tournament.loadAntWorld(antWorldToLoad.getAbsolutePath());
                    antWorldList = tournament.getListOfAntWorlds();
                } catch (AntWorldLoader.AntWorldLoaderException ex) {
                    // notify user
                    System.out.println(ex.getMessage());
                } catch (IOException ex) {
                    // notify user
                }
            }
        } else if(genAntWorldMO.isMouseOver()){
            curGenWorld = genAntWorldHover;
            if(gc.getInput().isMouseButtonDown(0)){
                // show gen world options?
            }
        } else if(startTournMO.isMouseOver()){
            currentStartTournament = startTournamentHover;
            if(gc.getInput().isMouseButtonDown(0)){
                if(tournament.getListOfAntBrains().size() >= 2 && tournament.getListOfAntWorlds().size() >= 1){
                    sbg.enterState(6);
                }
            }
        } else if(up1MO.isMouseOver()){
            currentUp1 = upHover;
            if(gc.getInput().isMouseButtonDown(0)){
                if(tournament.getListOfAntBrains().size() > 10){
                    topOfAntBrainList--;
                }
                bottomOfAntBrainList = setListBottom(antBrainList, topOfAntBrainList);
            }
        } else if (down1MO.isMouseOver()){
            currentDown1 = downHover;
            if(gc.getInput().isMouseButtonDown(0)){
                if(tournament.getListOfAntBrains().size() > 10){
                    topOfAntBrainList++;
                }
                bottomOfAntBrainList = setListBottom(antBrainList, topOfAntBrainList);
            }
        } else if(up2MO.isMouseOver()){
            currentUp2 = upHover;
            if(gc.getInput().isMouseButtonDown(0)){
                if(tournament.getListOfAntWorlds().size() > 10){
                    topOfAntBrainList--;
                }
                bottomOfAntWorldList = setListBottom(antWorldList, topOfAntWorldList);
            }
        } else if(down2MO.isMouseOver()){
            currentDown2 = downHover;
            if(gc.getInput().isMouseButtonDown(0)){
                if(tournament.getListOfAntWorlds().size() > 10){
                    topOfAntBrainList++;
                }
                bottomOfAntWorldList = setListBottom(antWorldList, topOfAntWorldList);
            }
        } else {
            currentUp1 = up;
            currentUp2 = up;
            currentDown1 = down;
            currentDown2 = down;
            currentLoadAntBrain = loadAntBrain;
            currentStartTournament = startTournament;
            curGenWorld = genAntWorld;
            currentLoadAntWorld = loadAntWorld;
        }
    
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        grphcs.setLineWidth(3);
        
        grphcs.drawImage(pageTitle, 1920/2 - pageTitle.getWidth()/2, 20);
        grphcs.drawImage(currentLoadAntBrain, 50, 330);
        grphcs.drawImage(currentLoadAntWorld, 50, 430);
        grphcs.drawImage(curGenWorld, 50, 530);
        grphcs.drawImage(currentStartTournament, 50, 630);
        
        grphcs.drawRoundRect(genAntWorld.getWidth()+100, 330, 520, 700, 10);
        grphcs.drawLine(genAntWorld.getWidth()+100, 390, genAntWorld.getWidth()+620, 390);
        gameFont.drawString(genAntWorld.getWidth()+120, 330, "Ant Brains");
        grphcs.drawImage(currentUp1, genAntWorld.getWidth()+630, 600);
        grphcs.drawImage(currentDown1, genAntWorld.getWidth()+630, 680);
        
        grphcs.drawRoundRect(1280, 330, 520, 700, 10);
        grphcs.drawLine(1280, 390, 1280+520, 390);
        gameFont.drawString(1280+20, 330, "Ant Worlds");
        grphcs.drawImage(currentUp2, 1280+530, 600);
        grphcs.drawImage(currentDown2, 1280+530, 680);
            
        for(int i = topOfAntBrainList; i < bottomOfAntBrainList; i++){
            gameFont.drawString(genAntWorld.getWidth()+120, 400 + i * 55, 
                    antBrainList.get(i).toString());
        }
        
        for(int i = topOfAntWorldList; i < bottomOfAntWorldList; i++){
            gameFont.drawString(1300, 400 + i * 55, 
                    antWorldList.get(i).toString());
        }
    }
    
    private File fileLoader(){
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            return selectedFile;
        }
        return null;
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
