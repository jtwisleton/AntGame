/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import antgameproject.AntGameTournament;
import antgameproject.Board;
import antgameproject.BoardTile;
import antgameproject.Colour;
import antgameproject.Game;
import antgameproject.Pos;
import antgameproject.Terrain;
import java.util.HashMap;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author wilki
 */
public class GUISingleGameDisplay extends BasicGameState {

    private AntGameTournament tournament;
    private SpriteSheet tiles;
    private Image tileset;
    private int divide;
    private int steps;
    private float scale;
    //private Game game;
    private HashMap<String, Pos> spritePositions;
    Board gameBoard = createTestBoard();
    private Image exitButton;
    private Image exitButtonHover;
    private Image currentExitButton;
    private Image logo;
    private MouseOverArea exitMO;
    private Image speedUpButton;
    private Image speedUpButtonHover;
    private Image currentSpeedUp;
    private Image slowDownButton;
    private Image slowDownButtonHover;
    private Image currentSlowDownButton;
    private Image pauseButton;
    private Image pauseButtonHover;
    private Image currentPauseButton;
    private Image skipToEndButton;
    private Image skipToEndButtonHover;
    private Image currentSkipToEndButton;
    private MouseOverArea speedUpMO;
    private MouseOverArea slowDownMO;
    private MouseOverArea pauseMO;
    private MouseOverArea skipMO;
    
    public GUISingleGameDisplay(AntGameTournament tournament){
        this.tournament = tournament;
    }
    
    @Override
    public int getID() {
        return 7;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        scale = 1f;
        divide = 1400;
        steps = 14;
        //game = tournament.getCurrentGame();
        tileset = new Image("resources/sprite.png");
	tiles = new SpriteSheet(tileset, 64, 64);
        spritePositions = new HashMap<>();
        createSpritePositions();
        exitButton = new Image("resources/exitButton.png");
        exitButtonHover = new Image("resources/exitButtonHover.png");
        currentExitButton = exitButton;
        exitMO = new MouseOverArea(gc, exitButton, Math.round((divide+20)*scale), Math.round(985*scale));
        logo = new Image("resources/smallLogo.png");
        speedUpButton = new Image("resources/FFButton.png");
        speedUpButtonHover = new Image("resources/FFButtonHover.png");
        currentSpeedUp = speedUpButton;
        slowDownButton = new Image("resources/RWButton.png");
        slowDownButtonHover = new Image("resources/RWButtonHover.png");
        currentSlowDownButton = slowDownButton;
        pauseButton = new Image("resources/pauseButton.png");
        pauseButtonHover = new Image("resources/pauseButtonHover.png");
        currentPauseButton = pauseButton;
        skipToEndButton = new Image("resources/SFButton.png");
        skipToEndButtonHover = new Image("resources/SFButtonHover.png");
        currentSkipToEndButton = skipToEndButton;
        speedUpMO = new MouseOverArea(gc, speedUpButton, divide+227, 115);
        slowDownMO = new MouseOverArea(gc, slowDownButton, divide+113, 115);
        pauseMO = new MouseOverArea(gc, pauseButton, divide+170, 15);
        skipMO = new MouseOverArea(gc, skipToEndButton, divide+284, 15);
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        if(exitMO.isMouseOver()){
            currentExitButton = exitButtonHover;
            if(gc.getInput().isMouseButtonDown(0)){
                // reset tournament
                sbg.enterState(1);
            }
        } else {
            currentExitButton = exitButton;
        }
            
        if(speedUpMO.isMouseOver()){
            currentSpeedUp = speedUpButtonHover;
        } else {
            currentSpeedUp = speedUpButton;
        }
            
        if(slowDownMO.isMouseOver()){
            currentSlowDownButton = slowDownButtonHover; 
        } else {
            currentSlowDownButton = slowDownButton;
        }
            
        if(pauseMO.isMouseOver()){
            currentPauseButton = pauseButtonHover;
        } else {
            currentPauseButton = pauseButton;
        }
            
        if(skipMO.isMouseOver()){
            currentSkipToEndButton = skipToEndButtonHover;
        } else {
            currentSkipToEndButton = skipToEndButton;
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        grphcs.scale(scale, scale);
        grphcs.setLineWidth(4);
        grphcs.drawLine(divide, 0, divide, 1080);
        grphcs.drawRoundRect(divide + 20, 250, 480, 350, 10);
        grphcs.drawRoundRect(divide + 20, 620, 480, 350, 10);
        
        int redAnts = 200;
        int blueAnts = 190;
        int redFood = 150;
        int blueFood = 66;
        grphcs.setColor(new Color(231, 76, 60));
        grphcs.fillRect(divide+120, 550-redAnts, 100, redAnts);
        grphcs.fillRect(divide+120, 920-redFood, 100, redFood);
        grphcs.setColor(new Color(52, 73, 94));
        grphcs.fillRect(divide+270, 550-blueAnts, 100, blueAnts);
        grphcs.fillRect(divide+270, 920-blueFood, 100, blueFood);
        
        
        grphcs.setColor(Color.white);
        grphcs.drawLine(divide+70, 320, divide+70, 550);
        grphcs.drawLine(divide+70, 550, divide+430, 550);
        grphcs.drawLine(divide+70, 690, divide+70, 920);
        grphcs.drawLine(divide+70, 920, divide+430, 920);
        
        
        grphcs.drawImage(logo, 1800, 990);
        grphcs.drawImage(currentExitButton, divide+20, 985);
        
        grphcs.drawImage(currentPauseButton, divide+170, 15);
        grphcs.drawImage(currentSpeedUp, divide+227, 115);
        grphcs.drawImage(currentSlowDownButton, divide+113, 115);
        grphcs.drawImage(currentSkipToEndButton, divide+284, 15);

        grphcs.scale(0.5f, 0.5f);
        tiles.startUse();
        for(int i = 0; i < 150; i++){   //use board size
                for(int j = 0; j < 150; j++){   // use board size
                        Pos spritePosition = calculateSpritePosition(i,j);
                        if(i % 2 == 0){
                                tiles.getSubImage(spritePosition.getPosX(), spritePosition.getPosY()).drawEmbedded(50+16*j, 50+12*i, 16, 16);
                        }else{
                                tiles.getSubImage(spritePosition.getPosX(), spritePosition.getPosY()).drawEmbedded(50+8+16*j, 50+12f*i, 16, 16);
                        }
                }
        }	
        tiles.endUse();
        grphcs.scale(2, 2);
        //grphcs.scale(2, 2);
    }

    private Pos calculateSpritePosition(int x, int y){
        String key;
        //Board gameBoard = game.getGameBoard();
        Pos somePos = new Pos(x, y);
        if(gameBoard.antInPosition(somePos)){
            key  = gameBoard.getTerrainAtPosition(somePos).toString()+gameBoard.antAt(somePos).getAntColour()
                    +gameBoard.antAt(somePos).getFacingDirection()+gameBoard.foodInTile(somePos);
        } else {
            key = gameBoard.getTerrainAtPosition(somePos).toString()+gameBoard.foodInTile(somePos);
        }
        return spritePositions.get(key);
    }

    private void createSpritePositions() {
        int j = 0;
        boolean food = false;
 
        Terrain[] terrainVals = {Terrain.GRASS, Terrain.GRASS, Terrain.REDBASE, Terrain.REDBASE, 
            Terrain.BLACKBASE, Terrain.BLACKBASE};
        for(Terrain terrainType: terrainVals){
            for(int z=0; z<14; z++){
                String key;
                int antDirection = 0;
                if(z < 2){
                    key = terrainType.toString() + food;
                } else {
                    if(j % 2 == 0){
                        key = terrainType.toString() + Colour.BLACK + antDirection + food;
                    } else {
                        key = terrainType.toString() + Colour.RED + antDirection + food;
                    }    
                    
                    if(z % 2 != 0) { 
                        antDirection++; 
                    }        
                }
                spritePositions.put(key, new Pos(z, j));
                food = !food;
            }
            j++;
        }
        spritePositions.put(Terrain.ROCK.toString()+false, new Pos(0, 6));
    }

    private Board createTestBoard() {
        BoardTile[][] board = new BoardTile[150][150];
        for(int i = 0; i < 150; i++){
            for(int j = 0; j < 150; j++){
                if((i == 0) || (i == 149) || (j == 0) || (j == 149)){
                    board[i][j] = new BoardTile(0, Terrain.ROCK);
                } else {
                    board[i][j] = new BoardTile(0, Terrain.GRASS);
                }
            }
            board[5][5] = new BoardTile(5, Terrain.REDBASE);
            board[15][15] = new BoardTile(0, Terrain.BLACKBASE);
        }
        return new Board(board, "test board");
    }
    
    
}
