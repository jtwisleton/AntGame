/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import antgameproject.AntBrain;
import antgameproject.AntGameTournament;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author wilki
 */
public class GUITournamentDisplay extends BasicGameState{

    private AntGameTournament tournament;
    private Font gameFont;
    private Image pageTitle;
    private Image playNextRound;
    private Image playNextRoundHover;
    private Image playNextRoundUnavailable;
    private Image currentPlayNextRound;
    private Image skipToEnd;
    private Image skipToEndHover;
    private Image skipToEndUnavailable;
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
    private boolean finished;
    private boolean gameOverMessageShown;
    private boolean scoresShown;
    private float screenScale;
    
    public GUITournamentDisplay(AntGameTournament tournament, float screenScale){
        this.tournament = tournament;
        this.screenScale = screenScale;
    }
    
    @Override
    public int getID() {
        return 6;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        loadResources();
        createMouseOverAreas(gc);
        
        currentPlayNextRound = playNextRound;
        currentSkipToEnd = skipToEnd;
        currentExit = exit;
        currentUp = up;
        currentDown = down;
        
        topOfAntBrainList = 0;
        finished = false;
        gameOverMessageShown = false;
        scoresShown = false;
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        antBrainList = tournament.getListOfAntBrains();
        if(playNextRoundMO.isMouseOver()){
            if (!finished) {
                currentPlayNextRound = playNextRoundHover;
                if (gc.getInput().isMouseButtonDown(0)) {
                    finished = tournament.runTournamentRound();
                    try {
                        TimeUnit.MILLISECONDS.sleep(250);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GUITournamentDisplay.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } else if(skipToEndMO.isMouseOver()){
            if (!finished) {
                currentSkipToEnd = skipToEndHover;
            }
        } else if(exitMO.isMouseOver()){
            currentExit = exitHover;
            if(gc.getInput().isMouseButtonDown(0)){
                tournament.reset(); //probably need to reset some variables here
                sbg.enterState(2);
            }
        } else if(upMO.isMouseOver()){
            currentUp = upHover;
            if(gc.getInput().isMouseButtonDown(0)){
                if(antBrainList.size() > 10 && topOfAntBrainList > 0){
                    topOfAntBrainList++;
                }
                bottomOfAntBrainList = setListBottom(antBrainList, bottomOfAntBrainList);
            }
        } else if(downMO.isMouseOver()){
            currentDown = downHover;
            if(gc.getInput().isMouseButtonDown(0)){
                if(antBrainList.size() > 10 && topOfAntBrainList + 10 < antBrainList.size()){
                    topOfAntBrainList--;
                }
                bottomOfAntBrainList = setListBottom(antBrainList, bottomOfAntBrainList);
            }
        } else {
            currentPlayNextRound = updatePlayNextRound();
            currentSkipToEnd = updateSkipToEnd();
            currentExit = exit;
            currentUp = up;
            currentDown = down;
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        grphcs.scale(screenScale, screenScale);
        grphcs.setLineWidth(3);
        
        grphcs.drawImage(pageTitle, 1920/2 - pageTitle.getWidth()/2, 20);
        grphcs.drawImage(currentPlayNextRound, 25, pageTitle.getHeight() + 40);
        grphcs.drawImage(currentSkipToEnd, 25, pageTitle.getHeight() + 140);
        grphcs.drawImage(currentExit, 25, pageTitle.getHeight() + 240);
        grphcs.drawImage(currentUp, 1850, 580);
        grphcs.drawImage(currentDown, 1850, 650);
        
        int topMargin = pageTitle.getHeight() + 40;
        gameFont.drawString(exit.getWidth()+60, topMargin+4, "Ant brain");
        gameFont.drawString(exit.getWidth()+800, topMargin+4, "S");
        gameFont.drawString(exit.getWidth()+890, topMargin+4, "P");
        gameFont.drawString(exit.getWidth()+973, topMargin+4, "W");
        gameFont.drawString(exit.getWidth()+1068, topMargin+4, "D");
        gameFont.drawString(exit.getWidth()+1159, topMargin+4, "L");
        gameFont.drawString(exit.getWidth()+1235, topMargin+4, "PF");
        gameFont.drawString(exit.getWidth()+1325, topMargin+4, "PA");
        grphcs.drawRoundRect(exit.getWidth()+50, pageTitle.getHeight() + 40, 1350, 800, 10);
        grphcs.drawLine(exit.getWidth()+50, pageTitle.getHeight() + 100, exit.getWidth()+1400, pageTitle.getHeight() + 100);
        for(int i = 0; i < 7; i++){
            int yLinePosition = exit.getWidth()+ 770 + i * 90;
            grphcs.drawLine(yLinePosition, topMargin, yLinePosition, topMargin+800);
        }
        
        //add printing of teams scores
        for(int i = 0 ; i < antBrainList.size(); i++){  //no size control at moment
            AntBrain currentAntBrain = antBrainList.get(i);
            topMargin += 60;
            gameFont.drawString(exit.getWidth()+60, topMargin+(60 * i), removeExtension(currentAntBrain.toString()));
            gameFont.drawString(exit.getWidth()+800, topMargin+(60 * i), Integer.toString(currentAntBrain.getPoints()));
            gameFont.drawString(exit.getWidth()+885, topMargin+(60 * i), Integer.toString(currentAntBrain.getGamesPlayedIn()));
            gameFont.drawString(exit.getWidth()+975, topMargin+(60 * i), Integer.toString(currentAntBrain.getGamesWon()));
            gameFont.drawString(exit.getWidth()+1068, topMargin+(60 * i), Integer.toString(currentAntBrain.getGamesDrawn()));
            gameFont.drawString(exit.getWidth()+1157, topMargin+(60 * i), Integer.toString(currentAntBrain.getGamesLost()));
            gameFont.drawString(exit.getWidth()+1239, topMargin+(60 * i), Integer.toString(currentAntBrain.getTotalFoodInBase()));
            gameFont.drawString(exit.getWidth()+1332, topMargin+(60 * i), Integer.toString(currentAntBrain.getTotalFoodInEnemyBase()));
            topMargin -= 60;
        }
        
        // Check if the game has finished & the winning message has not already been shown
        if ((finished) && (!gameOverMessageShown)) {

            if (scoresShown) {

                // Game finished, show winning screen
                int firstScore = antBrainList.get(0).getPoints();
                int secondScore = antBrainList.get(1).getPoints();

                if (firstScore == secondScore) {
                    // Draw
                    showMessage("It's a draw!", "Game Over!");
                } else {
                    // Win
                    showMessage("Ant Brain " + removeExtension(antBrainList.get(0).toString()) + " wins!", "Game Over!");
                }

                gameOverMessageShown = true;
            } else {
                scoresShown = true;
            }
        }
           
        // print the number of rounds played and warning
        gameFont.drawString(25, 550, "Warning running rounds\ntakes time and your\ncomputer may appear to\nhave froze.");
        gameFont.drawString(25, 1010, "Rounds played " + tournament.getTournamentRoundNumber() 
                + " / " + tournament.getListOfAntWorlds().size());
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
    
    private void showMessage(String message, String errorType){
        JOptionPane.showMessageDialog(new JFrame(), message,
        errorType, JOptionPane.PLAIN_MESSAGE);
    }
    
    private String removeExtension(String filename) {
        if (filename.indexOf(".") > 0) {
            filename = filename.substring(0, filename.lastIndexOf("."));
        }
        return filename;
    }
    
    private Image updatePlayNextRound() {
        if (finished) {
            return playNextRoundUnavailable;
        } else {
            return playNextRound;
        }
    }
    
    private Image updateSkipToEnd() {
        if (finished) {
            return skipToEndUnavailable;
        } else {
            return skipToEnd;
        }
    }

    private void loadResources() throws SlickException {
        gameFont = new AngelCodeFont("resources/moreAdded.fnt", "resources/moreAdded_0.png");
        pageTitle = new Image("resources/tournamentLogo.png");
        playNextRound = new Image("resources/playNextRound.png");
        playNextRoundHover = new Image("resources/playNextRoundHover.png");
        playNextRoundUnavailable = new Image("resources/playNextRoundUnavailable.png");
        skipToEnd = new Image("resources/skipToEnd.png");
        skipToEndHover = new Image("resources/skipToEndHover.png");
        skipToEndUnavailable  = new Image("resources/skipToEndUnavailable.png");
        exit = new Image("resources/exitalt.png");
        exitHover = new Image("resources/exitAltHover.png");
        up = new Image("resources/up.png");
        upHover = new Image("resources/upHover.png");
        down = new Image("resources/down.png");
        downHover = new Image("resources/downHover.png");
    }

    private void createMouseOverAreas(GameContainer gc) {
        playNextRoundMO = new MouseOverArea(gc, playNextRound, (int)(25*screenScale), (int)((pageTitle.getHeight() + 40)*screenScale),
                (int)(playNextRound.getWidth()*screenScale), (int)(playNextRound.getHeight()*screenScale));
        skipToEndMO = new MouseOverArea(gc, skipToEnd, (int)(25*screenScale), (int)((pageTitle.getHeight() + 140)*screenScale),
                (int)(skipToEnd.getWidth()*screenScale), (int)(playNextRound.getHeight()*screenScale));
        exitMO = new MouseOverArea(gc, exit, (int)(25*screenScale), (int)((pageTitle.getHeight() + 240)*screenScale),
                (int)(exit.getWidth()*screenScale), (int)(exit.getHeight()*screenScale));
        upMO = new MouseOverArea(gc, up, (int)(1850*screenScale), (int)(580*screenScale),
                (int)(up.getWidth()*screenScale), (int)(up.getHeight()*screenScale));
        downMO = new MouseOverArea(gc, down, (int)(1850*screenScale), (int)(650*screenScale),
                (int)(down.getWidth()*screenScale), (int)(down.getHeight()*screenScale));
    }

}
