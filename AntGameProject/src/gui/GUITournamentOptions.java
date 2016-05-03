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
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
 */
public class GUITournamentOptions extends BasicGameState {

    private AntGameTournament tournament;
    private Font headerFont;
    private Font fileFont;
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
    private Image startTournamentUnavailable;
    private Image currentStartTournament;
    private Image mainMenu;
    private Image mainMenuHover;
    private Image currentMainMenu;
    private MouseOverArea up1MO;
    private MouseOverArea down1MO;
    private MouseOverArea up2MO;
    private MouseOverArea down2MO;
    private MouseOverArea loadAntBrainMO;
    private MouseOverArea loadAntWorldMO;
    private MouseOverArea genAntWorldMO;
    private MouseOverArea startTournMO;
    private MouseOverArea mainMenuMO;
    private int topOfAntBrainList;
    private int topOfAntWorldList;
    private int bottomOfAntBrainList;
    private int bottomOfAntWorldList;
    private List antBrainList;
    private List antWorldList;
    private final float screenScale;
    
    public GUITournamentOptions(AntGameTournament tournament, float screenScale){
        this.tournament = tournament;
        this.screenScale = screenScale;
    }
    
    @Override
    public int getID() {
        return 4;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        loadResources();
        
        curGenWorld = genAntWorld;
        currentUp1 = up;
        currentUp2 = up;
        currentDown1 = down;
        currentDown2 = down;
        currentLoadAntBrain = loadAntBrain;
        currentLoadAntWorld = loadAntWorld;
        currentStartTournament = startTournamentUnavailable;
        currentMainMenu = mainMenu;
        
        createMouseOverAreas(gc);
     
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
                if(antBrainToLoad != null){
                    try {
                        tournament.loadAntBrain(antBrainToLoad.getAbsolutePath(), antBrainToLoad.getName());
                        antBrainList = tournament.getListOfAntBrains();
                    } catch (AntBrainLoader.AntBrainLoaderException ex) {
                        showError(ex.getMessage(), "Ant brain error");
                    } catch (IOException ex) {
                        showError(ex.getMessage(), "Error loading ant brain");
                    }
                    bottomOfAntBrainList = setListBottom(antBrainList, topOfAntBrainList);
                }
            }
        } else if(loadAntWorldMO.isMouseOver()){
            currentLoadAntWorld = loadAntWorldHover;
            if(gc.getInput().isMouseButtonDown(0)){
                File antWorldToLoad = fileLoader();
                if(antWorldToLoad != null){
                    try {
                        tournament.loadAntWorld(antWorldToLoad.getAbsolutePath(), antWorldToLoad.getName());
                        antWorldList = tournament.getListOfAntWorlds();
                    } catch (AntWorldLoader.AntWorldLoaderException ex) {
                        showError(ex.getMessage(), "Ant world error");
                    } catch (IOException ex) {
                        showError(ex.getMessage(), "Error loading ant world");
                    }
                    bottomOfAntWorldList = setListBottom(antWorldList, topOfAntWorldList);
                }
            }
        } else if(genAntWorldMO.isMouseOver()){
            curGenWorld = genAntWorldHover;
            if(gc.getInput().isMouseButtonDown(0)){
                try {
                    tournament.generateAntWorld();
                    TimeUnit.MILLISECONDS.sleep(250);
                    antWorldList = tournament.getListOfAntWorlds();
                    bottomOfAntWorldList = setListBottom(antWorldList, topOfAntWorldList);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GUITournamentOptions.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if(mainMenuMO.isMouseOver()){
            currentMainMenu = mainMenuHover;
            if(gc.getInput().isMouseButtonDown(0)){
                try {
                    TimeUnit.MILLISECONDS.sleep(250);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GUISingleGameOptions.class.getName()).log(Level.SEVERE, null, ex);
                }
                sbg.enterState(2);
            }
        } else if(startTournMO.isMouseOver()){
            if(tournament.getListOfAntBrains().size() >= 2 && tournament.getListOfAntWorlds().size() >= 1){
                currentStartTournament = startTournamentHover;
                if(gc.getInput().isMouseButtonDown(0)){
                    tournament.createTournament();
                    sbg.getState(6).init(gc, sbg);
                    sbg.enterState(6);
                }
            }
        } else if(up1MO.isMouseOver()){
            currentUp1 = upHover;
            if(gc.getInput().isMouseButtonDown(0)){
                if(tournament.getListOfAntBrains().size() > 10 && topOfAntBrainList > 0){
                    topOfAntBrainList--;
                    try {
                        TimeUnit.MILLISECONDS.sleep(250);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GUITournamentOptions.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                bottomOfAntBrainList = setListBottom(antBrainList, topOfAntBrainList);
            }
        } else if(down1MO.isMouseOver()){
            currentDown1 = downHover;
            if(gc.getInput().isMouseButtonDown(0)){
                if(tournament.getListOfAntBrains().size() > 10 && topOfAntBrainList+10 < tournament.getListOfAntBrains().size()){
                    topOfAntBrainList++;
                    try {
                        TimeUnit.MILLISECONDS.sleep(250);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GUITournamentOptions.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                bottomOfAntBrainList = setListBottom(antBrainList, topOfAntBrainList);
            }
        } else if(up2MO.isMouseOver()){
            currentUp2 = upHover;
            if(gc.getInput().isMouseButtonDown(0)){
                if(tournament.getListOfAntWorlds().size() > 10 && topOfAntWorldList > 0){
                    topOfAntWorldList--;
                    try {
                        TimeUnit.MILLISECONDS.sleep(250);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GUITournamentOptions.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                bottomOfAntWorldList = setListBottom(antWorldList, topOfAntWorldList);
            }
        } else if(down2MO.isMouseOver()){
            currentDown2 = downHover;
            if(gc.getInput().isMouseButtonDown(0)){
                if(tournament.getListOfAntWorlds().size() > 10 && topOfAntWorldList+10 < tournament.getListOfAntWorlds().size()){
                    topOfAntWorldList++;
                    try {
                        TimeUnit.MILLISECONDS.sleep(250);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GUITournamentOptions.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                bottomOfAntWorldList = setListBottom(antWorldList, topOfAntWorldList);
            }
        } else {
            currentUp1 = up;
            currentUp2 = up;
            currentDown1 = down;
            currentDown2 = down;
            currentLoadAntBrain = loadAntBrain;
            currentStartTournament = updateStart();
            curGenWorld = genAntWorld;
            currentLoadAntWorld = loadAntWorld;
            currentMainMenu = mainMenu;
        }
    
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        grphcs.scale(screenScale, screenScale);
        
        grphcs.setLineWidth(3);
        
        grphcs.drawImage(pageTitle, 1920/2 - pageTitle.getWidth()/2, 20);
        grphcs.drawImage(currentLoadAntBrain, 50, 330);
        grphcs.drawImage(currentLoadAntWorld, 50, 430);
        grphcs.drawImage(curGenWorld, 50, 530);
        grphcs.drawImage(currentMainMenu, 50, 630);
        grphcs.drawImage(currentStartTournament, 50, 730);
        
        grphcs.drawRoundRect(genAntWorld.getWidth()+100, 330, 520, 700, 10);
        grphcs.drawLine(genAntWorld.getWidth()+100, 390, genAntWorld.getWidth()+620, 390);
        headerFont.drawString(genAntWorld.getWidth()+120, 330, "Ant Brains");
        grphcs.drawImage(currentUp1, genAntWorld.getWidth()+630, 600);
        grphcs.drawImage(currentDown1, genAntWorld.getWidth()+630, 680);
        
        grphcs.drawRoundRect(1280, 330, 520, 700, 10);
        grphcs.drawLine(1280, 390, 1280+520, 390);
        headerFont.drawString(1280+20, 330, "Ant Worlds");
        grphcs.drawImage(currentUp2, 1280+530, 600);
        grphcs.drawImage(currentDown2, 1280+530, 680);
        
        antBrainList = tournament.getListOfAntBrains();
        antWorldList = tournament.getListOfAntWorlds();
        
        if (antBrainList.isEmpty() && antWorldList.isEmpty()) {
            topOfAntBrainList = 0;
            topOfAntWorldList = 0;
            bottomOfAntBrainList = 0;
            bottomOfAntWorldList = 0;
        }
        
        //System.out.println(topOfAntBrainList + " " + bottomOfAntBrainList + " " + antBrainList.size());
        for(int i = topOfAntBrainList; i < bottomOfAntBrainList; i++){
            String filename = removeExtension(antBrainList.get(i).toString());
            fileFont.drawString(genAntWorld.getWidth()+120, 400 + (i-topOfAntBrainList) * 60, filename);
        }
        //System.out.println(topOfAntWorldList + " " + bottomOfAntWorldList + " " + antWorldList.size());
        for(int i = topOfAntWorldList; i < bottomOfAntWorldList; i++){
            String filename = removeExtension(antWorldList.get(i).toString());
            fileFont.drawString(1300, 400 + (i-topOfAntWorldList) * 60, filename);
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
    
    private int setListBottom(List listToSetBottomOf, int listTopPos){
        int listBottom = 0;
        if(listToSetBottomOf.size() > 0){
            if(listToSetBottomOf.size() < 11){
                listBottom = listToSetBottomOf.size();
            } else {
                listBottom = listTopPos+10;
            }
        }
       return listBottom;
    }
    
    private void showError(String errorMessage, String errorType){
        JOptionPane.showMessageDialog(new JFrame(), errorMessage,
        errorType, JOptionPane.ERROR_MESSAGE);
    }
    
    private Image updateStart() {
        if (tournament.getListOfAntBrains().size() >= 2 && tournament.getListOfAntWorlds().size() >= 1) {
            return startTournament;
        } else {
            return startTournamentUnavailable;
        }
    }
    
    private String removeExtension(String filename) {
        if (filename.indexOf(".") > 0) {
            filename = filename.substring(0, filename.lastIndexOf("."));
        }
        return filename;
    }

    private void createMouseOverAreas(GameContainer gc) {
        up1MO = new MouseOverArea(gc, up, (int)((genAntWorld.getWidth()+630)* screenScale), (int)(600*screenScale),
                (int)(up.getWidth()*screenScale), (int)(up.getHeight()*screenScale));
        down1MO = new MouseOverArea(gc, down, (int)((genAntWorld.getWidth()+630)* screenScale), (int)(680*screenScale),
                (int)(down.getWidth()*screenScale), (int)(down.getHeight()*screenScale));
        up2MO = new MouseOverArea(gc, up, (int)((1280+530)*screenScale), (int)(600*screenScale),
                (int)(up.getWidth()*screenScale), (int)(up.getHeight()*screenScale));
        down2MO = new MouseOverArea(gc, down, (int)((1280+530)*screenScale), (int)(680*screenScale),
                (int)(down.getWidth()*screenScale), (int)(down.getHeight()*screenScale));
        loadAntBrainMO = new MouseOverArea(gc, loadAntBrain, (int)(50*screenScale), (int)(330*screenScale),
                (int)(loadAntBrain.getWidth()*screenScale), (int)(loadAntBrain.getHeight()*screenScale));
        loadAntWorldMO = new MouseOverArea(gc, loadAntWorld, (int)(50*screenScale), (int)(430*screenScale),
                (int)(loadAntWorld.getWidth()*screenScale), (int)(loadAntWorld.getHeight()*screenScale));       
        genAntWorldMO = new MouseOverArea(gc, genAntWorld, (int)(50*screenScale), (int)(530*screenScale),
                (int)(genAntWorld.getWidth()*screenScale), (int)(genAntWorld.getHeight()*screenScale));
        mainMenuMO = new MouseOverArea(gc, mainMenu, (int)(50*screenScale), (int)(630*screenScale),
                (int)(mainMenu.getWidth()*screenScale), (int)(mainMenu.getHeight()*screenScale));
        startTournMO = new MouseOverArea(gc, startTournament, (int)(50*screenScale), (int)(730*screenScale),
                (int)(startTournament.getWidth()*screenScale), (int)(startTournament.getHeight()*screenScale));
    }

    private void loadResources() throws SlickException {
        headerFont = new AngelCodeFont("resources/hugeFont.fnt", "resources/hugeFont_0.png");
        fileFont = new AngelCodeFont("resources/fontAlt.fnt", "resources/fontAlt_0.png");
        pageTitle = new Image("resources/tournament_setup.png");
        genAntWorld = new Image("resources/generateAntWorld.png");
        genAntWorldHover = new Image("resources/generateAntWorldHover.png");
        up = new Image("resources/up.png");
        upHover = new Image("resources/upHover.png");
        down = new Image("resources/down.png");
        downHover = new Image("resources/downHover.png");
        loadAntBrain = new Image("resources/loadAntBrain.png");
        loadAntBrainHover = new Image("resources/loadAntBrainHover.png");
        loadAntWorld = new Image("resources/loadAntWorldCenter.png");
        loadAntWorldHover = new Image("resources/loadAntWorldCenterHover.png");
        startTournament = new Image("resources/startTourn.png");
        startTournamentHover = new Image("resources/startTournHover.png");
        startTournamentUnavailable = new Image("resources/startTournUnavailable.png");
        mainMenu = new Image("resources/mainMenu.png");
        mainMenuHover = new Image("resources/mainMenuHover.png");
    }
}
