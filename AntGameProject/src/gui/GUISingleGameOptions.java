/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import antgameproject.AntBrainLoader;
import antgameproject.AntGameTournament;
import antgameproject.AntWorldLoader;
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
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
    private Image startUnavailable;
    private Image curStart;
    private Image mainMenu;
    private Image mainMenuHover;
    private Image curMainMenu;
    private Image tick;
    private Image selectTick;
    private Image selectTick2;
    private Image worldTick;
    private int rightMargin;
    private int leftMargin;
    private int offset;
    private int topButton;
    private MouseOverArea select1MO;
    private MouseOverArea select2MO;
    private MouseOverArea worldLoadMO;  
    private MouseOverArea worldGenMO;
    private MouseOverArea startMO;
    private MouseOverArea mainMenuMO;
    private File antBrainOne;
    private File antBrainTwo;
    private File antWorldFile;
    private final float screenScale;
    private boolean generatedAntWorld;
    
    public GUISingleGameOptions(AntGameTournament tournament, float screenScale){
        this.tournament = tournament;
        UIManager UI=new UIManager();
        UI.put("OptionPane.background",new ColorUIResource(33, 252, 172));
        UI.put("Panel.background",new ColorUIResource(33, 252, 172));
        this.screenScale = screenScale;
    }
    
    @Override
    public int getID() {
        return 5;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        loadResources();
        
        currentSelect = select;
        currentSelect2 = select;
        currentWorldGen = worldGen;
        curWorldLoad = worldLoad;
        curStart = startUnavailable;
        curMainMenu = mainMenu;
       
        rightMargin = 1920 - 350;
        leftMargin = 350;
        offset = 115;
        topButton = pageTitle.getHeight() + 80;

        createMouseOverAreas(gc);
        generatedAntWorld = false;
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        if(startMO.isMouseOver()){
            if(antBrainOne != null && antBrainTwo != null && (antWorldFile != null || generatedAntWorld)){
                curStart = startHover;
                if(gc.getInput().isMouseButtonDown(0)){
                    try {
                        antBrainOne = null;
                        antBrainTwo = null;
                        antWorldFile = null;
                        tournament.runGame();
                        TimeUnit.MILLISECONDS.sleep(250); 
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GUISingleGameOptions.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    sbg.enterState(7);
                }
            }
        } else if(mainMenuMO.isMouseOver()){
            curMainMenu = mainMenuHover;
            if(gc.getInput().isMouseButtonDown(0)){
                try {
                    tournament.reset(); //needed?
                    antBrainOne = null;
                    antBrainTwo = null;
                    antWorldFile = null;
                    TimeUnit.MILLISECONDS.sleep(250);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GUISingleGameOptions.class.getName()).log(Level.SEVERE, null, ex);
                }
                sbg.enterState(2);    
            }
        } else if(select1MO.isMouseOver()){
            currentSelect = selectHover;
            if(gc.getInput().isMouseButtonDown(0)){
                antBrainOne = fileLoader();
                if(antBrainOne != null){
                    try {
                        tournament.loadAntBrain(antBrainOne.getAbsolutePath(), "Ant brain one");
                        selectTick.setAlpha(1);
                    } catch (AntBrainLoader.AntBrainLoaderException ex) {
                        showError(ex.getMessage(), "Ant brain error");
                    } catch (IOException ex) {
                        showError(ex.getMessage(), "Error loading ant brain");
                    }
                } else {
                    selectTick.setAlpha(0);
                }
            }
        } else if(select2MO.isMouseOver()){
            currentSelect2 = selectHover;
            if(gc.getInput().isMouseButtonDown(0)){
                antBrainTwo = fileLoader();
                if(antBrainTwo != null){
                    try {
                        tournament.loadAntBrain(antBrainTwo.getAbsolutePath(), "Ant brain two");
                        selectTick2.setAlpha(1);
                    } catch (AntBrainLoader.AntBrainLoaderException ex) {
                        showError(ex.getMessage(), "Ant brain error");
                    } catch (IOException ex) {
                        showError(ex.getMessage(), "Error loading ant brain");
                    }
                } else {
                    selectTick2.setAlpha(0);
                }
            }
        } else if(worldLoadMO.isMouseOver()){
            curWorldLoad = worldLoadHover;
            if(gc.getInput().isMouseButtonDown(0)){
                antWorldFile = fileLoader();
                if(antWorldFile != null){
                    try {
                        tournament.loadAntWorld(antWorldFile.getAbsolutePath(), antWorldFile.getName());
                        worldTick.setAlpha(1);
                    } catch (AntWorldLoader.AntWorldLoaderException ex) {
                        showError(ex.getMessage(), "Ant world error");
                    } catch (IOException ex) {
                        showError(ex.getMessage(), "Error loading ant world");
                    }
                } else {
                    worldTick.setAlpha(0);
                }
            }
        } else if(worldGenMO.isMouseOver()){
            currentWorldGen = worldGenHover;
            if(gc.getInput().isMouseButtonDown(0)){
                if(antWorldFile == null && !generatedAntWorld){
                    tournament.generateAntWorld();
                    worldTick.setAlpha(1);
                    generatedAntWorld = true;
                }
            }
        } else {
            currentSelect = select;
            currentSelect2 = select;
            curStart = updateStart();
            curWorldLoad = worldLoad;
            currentWorldGen = worldGen;
            curMainMenu = mainMenu;
        }
        
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        grphcs.scale(screenScale, screenScale);
        grphcs.setLineWidth(3);
        grphcs.setFont(gameFont);
        
        grphcs.scale(2, 2);
        gameFont.drawString(leftMargin/2, 455/2, "Ant brain one");
        gameFont.drawString(leftMargin/2, 575/2, "Ant brain two");
        gameFont.drawString((leftMargin+560)/2, 695/2, "or");
        grphcs.scale(0.5f, 0.5f);
        
        grphcs.drawImage(pageTitle, 1920/2 - pageTitle.getWidth()/2, 20);
        grphcs.drawImage(currentSelect, rightMargin - currentSelect.getWidth(), topButton);
        grphcs.drawImage(currentSelect2, rightMargin - currentSelect.getWidth(), topButton + offset);
        grphcs.drawImage(curWorldLoad, leftMargin, topButton + 2 * offset);
        grphcs.drawImage(currentWorldGen, rightMargin - worldGen.getWidth(), topButton + 2 * offset);
        grphcs.drawImage(curMainMenu, rightMargin - mainMenu.getWidth(), topButton + 3 * offset);
        grphcs.drawImage(curStart, rightMargin - curStart.getWidth(), topButton + 4 * offset);
        
        grphcs.drawImage(selectTick, rightMargin + 20, topButton);
        grphcs.drawImage(selectTick2, rightMargin + 20, topButton + offset);
        grphcs.drawImage(worldTick, rightMargin + 20, topButton + 2 * offset);
        
        if (antBrainOne == null && antBrainTwo == null && (antWorldFile == null && !generatedAntWorld)) {
            selectTick.setAlpha(0);
            selectTick2.setAlpha(0);
            worldTick.setAlpha(0);
        }
    }
    
    private File fileLoader(){
        String osName = System.getProperty("os.name");
        File selectedFile = null;
        
        if (osName.equals("Mac OS X")) {
            FileDialog fd = new FileDialog(new Frame(), "Choose a file", FileDialog.LOAD);
            fd.setVisible(true);
            String filename = fd.getDirectory() + fd.getFile();
            try {
                selectedFile = new File(filename);
            } catch (Exception e) {
                // Cancelled
            }
            
        } else {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
            }
        }
        
        return selectedFile;
    }
    
    private void showError(String errorMessage, String errorType){
        JOptionPane.showMessageDialog(new JFrame(), errorMessage,
        errorType, JOptionPane.ERROR_MESSAGE);
    }
    
    private Image updateStart() {
        if (antBrainOne != null && antBrainTwo != null && (antWorldFile != null || generatedAntWorld)) {
            return start;
        } else {
            return startUnavailable;
        }
    }

    private void loadResources() throws SlickException {
        pageTitle = new Image("resources/game_setup_screen.png");
        gameFont = new AngelCodeFont("resources/moreAdded.fnt", "resources/moreAdded_0.png");
        select = new Image("resources/select.png");
        selectHover = new Image("resources/selectHover.png");
        worldGen = new Image("resources/generateAntWorld.png");
        worldGenHover = new Image("resources/generateAntWorldHover.png");
        worldLoad = new Image("resources/loadAntWorldCenter.png");
        worldLoadHover = new Image("resources/loadAntWorldCenterHover.png");
        start = new Image("resources/startGame.png");
        startHover = new Image("resources/startGameHover.png");
        startUnavailable = new Image("resources/startGameUnavailable.png");
        mainMenu = new Image("resources/mainMenu.png");
        mainMenuHover = new Image("resources/mainMenuHover.png");
        selectTick = new Image("resources/tick.png");
        selectTick2 = new Image("resources/tick.png");
        worldTick = new Image("resources/tick.png");
        selectTick.setAlpha(0);
        selectTick2.setAlpha(0);
        worldTick.setAlpha(0);
    }

    private void createMouseOverAreas(GameContainer gc) {
        select1MO = new MouseOverArea(gc, select, (int)((rightMargin - currentSelect.getWidth())*screenScale), (int)(topButton*screenScale),
                (int)(select.getWidth()*screenScale), (int)(select.getHeight()*screenScale));
        select2MO = new MouseOverArea(gc, select, (int)((rightMargin - currentSelect.getWidth())*screenScale), (int)((topButton + offset)*screenScale),
                (int)(select.getWidth()*screenScale), (int)(select.getHeight()*screenScale));
        startMO = new MouseOverArea(gc, start, (int)((rightMargin - curStart.getWidth())*screenScale), (int)((topButton + 4 * offset)*screenScale),
                (int)(start.getWidth()*screenScale), (int)(start.getHeight()*screenScale));
        worldLoadMO = new MouseOverArea(gc, worldLoad, (int)(leftMargin*screenScale), (int)((topButton + 2 * offset)*screenScale),
                (int)(worldLoad.getWidth()*screenScale), (int)(worldLoad.getHeight()*screenScale));
        worldGenMO = new MouseOverArea(gc, worldGen, (int)((rightMargin - worldGen.getWidth())*screenScale), (int)((topButton + 2 * offset)*screenScale),
                (int)(worldGen.getWidth()*screenScale), (int)(worldGen.getHeight()*screenScale));
        mainMenuMO = new MouseOverArea(gc, mainMenu, (int)((rightMargin - mainMenu.getWidth())*screenScale), (int)((topButton + 3 * offset)*screenScale),
                (int)(mainMenu.getWidth()*screenScale), (int)(mainMenu.getHeight()*screenScale));
    }
    
}
