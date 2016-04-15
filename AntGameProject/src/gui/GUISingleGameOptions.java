/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import antgameproject.AntGameTournament;
import java.io.File;
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
public class GUISingleGameOptions extends BasicGameState {
    
    private AntGameTournament tournament;
    private Font gameFont;
    private Image pageTitle;
    private Image select;
    private Image selectHover;
    private Image currentSelect;
    private Image currentSelect2;
    private Image worldGen;
    private Image worldGenHover;
    private Image currentWorldGen;
    private Image worldLoad;
    private Image worldLoadHover;
    private Image curWorldLoad;
    private Image start;
    private Image startHover;
    private Image curStart;
    private int rightMargin;
    private int leftMargin;
    private int offset;
    private int topButton;
    private MouseOverArea select1MO;
    private MouseOverArea select2MO;
    private MouseOverArea worldLoadMO;  
    private MouseOverArea worldGenMO;
    private MouseOverArea startMO;
    private File antBrainOne;
    private File antBrainTwo;
    private File antWorldFile;
    
    public GUISingleGameOptions(AntGameTournament tournament){
        this.tournament = tournament;
    }
    
    @Override
    public int getID() {
        return 5;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        pageTitle = new Image("resources/game_setup_screen.png");
        gameFont = new AngelCodeFont("resources/hugeFont.fnt", "resources/hugeFont_0.png");
        select = new Image("resources/select.png");
        selectHover = new Image("resources/selectHover.png");
        currentSelect = select;
        currentSelect2 = select;
        worldGen = new Image("resources/generateAntWorld.png");
        worldGenHover = new Image("resources/generateAntWorldHover.png");
        currentWorldGen = worldGen;
        worldLoad = new Image("resources/loadAntWorldCenter.png");
        worldLoadHover = new Image("resources/loadAntWorldCenterHover.png");
        curWorldLoad = worldLoad;
        start = new Image("resources/startGame.png");
        startHover = new Image("resources/startGameHover.png");
        curStart = start;
        
        rightMargin = 1920 - 350;
        leftMargin = 350;
        offset = 120;
        topButton = pageTitle.getHeight() + 100;
        
        select1MO = new MouseOverArea(gc, select, rightMargin - currentSelect.getWidth(), topButton);
        select2MO = new MouseOverArea(gc, select, rightMargin - currentSelect.getWidth(), topButton + offset);
        startMO = new MouseOverArea(gc, start, rightMargin - curStart.getWidth(), topButton + 3 * offset);
        worldLoadMO = new MouseOverArea(gc, worldLoad, leftMargin, topButton + 2 * offset);
        worldGenMO = new MouseOverArea(gc, worldGen, rightMargin - worldGen.getWidth(), topButton + 2 * offset);
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        if(startMO.isMouseOver()){
            curStart = startHover;
            if(gc.getInput().isMouseButtonDown(0)){
                // check for null then build a game
                sbg.enterState(7);
            }
        } else if(select1MO.isMouseOver()){
            currentSelect = selectHover;
            if(gc.getInput().isMouseButtonDown(0)){
                antBrainOne = fileLoader();
            }
        } else if(select2MO.isMouseOver()){
            currentSelect2 = selectHover;
            if(gc.getInput().isMouseButtonDown(0)){
                antBrainTwo = fileLoader();
            }
        } else if(worldLoadMO.isMouseOver()){
            curWorldLoad = worldLoadHover;
            if(gc.getInput().isMouseButtonDown(0)){
                antWorldFile = fileLoader();
            }
        } else if(worldGenMO.isMouseOver()){
            currentWorldGen = worldGenHover;
            if(gc.getInput().isMouseButtonDown(0)){
                //worldGenPage?
            }
        } else {
            currentSelect = select;
            currentSelect2 = select;
            curStart = start;
            curWorldLoad = worldLoad;
            currentWorldGen = worldGen;
        }
        
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        grphcs.setLineWidth(3);
        grphcs.setFont(gameFont);
        
        grphcs.scale(2, 2);
        gameFont.drawString(leftMargin/2, 475/2, "Ant brain one");
        gameFont.drawString(leftMargin/2, 600/2, "Ant brain two");
        gameFont.drawString((leftMargin+560)/2, 730/2, "or");
        
        grphcs.scale(0.5f, 0.5f);
        grphcs.drawImage(pageTitle, 1920/2 - pageTitle.getWidth()/2, 20);
        grphcs.drawImage(currentSelect, rightMargin - currentSelect.getWidth(), topButton);
        grphcs.drawImage(currentSelect2, rightMargin - currentSelect.getWidth(), topButton + offset);
        grphcs.drawImage(curWorldLoad, leftMargin, topButton + 2 * offset);
        grphcs.drawImage(currentWorldGen, rightMargin - worldGen.getWidth(), topButton + 2 * offset);
        grphcs.drawImage(curStart, rightMargin - curStart.getWidth(), topButton + 3 * offset);
        
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
    
}
