package gui;

import antgameproject.AntGameTournament;
import antgameproject.Board;
import antgameproject.BoardTile;
import antgameproject.Colour;
import antgameproject.Game;
import antgameproject.Pos;
import antgameproject.Terrain;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Class to represent a single game display.
 *
 * @author team18
 */
public class GUISingleGameDisplay extends BasicGameState {

    private Font gameFont;
    private final AntGameTournament tournament;
    private SpriteSheet tiles;
    private Image tileset;
    private int divide;
    private int steps;
    private float scale;
    private Game game;
    private HashMap<String, Pos> spritePositions;
    private Board gameBoard;
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
    private Image playButton;
    private Image playButtonHover;
    private Image currentPauseButton;
    private Image currentPauseButtonHover;
    private Image currentButton;
    private Image skipToEndButton;
    private Image skipToEndButtonHover;
    private Image currentSkipToEndButton;
    private MouseOverArea speedUpMO;
    private MouseOverArea slowDownMO;
    private MouseOverArea pauseMO;
    private MouseOverArea skipMO;
    private BoardTile[][] board;
    private int oldSteps;
    private int numRedBaseFood;
    private int numBlackBaseFood;
    private int redAntsAlive;
    private int blackAntsAlive;
    private boolean gameOverMessageShown;
    private boolean exiting;
    private boolean firstRender;
    private boolean mouseHasBeenInCenter;
    private GUICamera cam;
    private final float screenScale;
    private int aliveBarScale;
    private int foodBarScale;

    /**
     * Construct a new GUISingleGameDisplay object.
     *
     * @param tournament AntGameTournament being displayed.
     * @param screenScale Scale of the screen.
     */
    public GUISingleGameDisplay(AntGameTournament tournament, float screenScale) {
        this.tournament = tournament;
        this.screenScale = screenScale;
    }

    /**
     * Get the ID of this state.
     *
     * @return This state's ID.
     */
    @Override
    public int getID() {
        return 7;
    }

    /**
     * Initialize the GUISingleGameDisplay.
     *
     * @param gc Game container holding the GUI.
     * @param sbg Game object.
     * @throws SlickException if problem initialising mouse over areas.
     */
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        // Initialise variables and load resources
        scale = 1f;
        divide = 1400;
        steps = 14;
        loadResources();
        createMouseOverAreas(gc);

        // Set buttons to their current state
        currentExitButton = exitButton;
        currentSpeedUp = speedUpButton;
        currentSlowDownButton = slowDownButton;
        currentPauseButton = pauseButton;
        currentPauseButtonHover = pauseButtonHover;
        currentButton = currentPauseButton;
        currentSkipToEndButton = skipToEndButton;

        // Boolean variables indicate stage of display
        firstRender = true;
        gameOverMessageShown = false;
        mouseHasBeenInCenter = false;
        exiting = false;

    }

    /**
     * Update the game logic.
     *
     * @param gc Game container holding the GUI.
     * @param sbg Game object.
     * @param i Delta (unused).
     * @throws SlickException if problem entering states.
     */
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        // If first time rendering this display
        if (firstRender) {
            // Create the display elements
            createCamera();
            game = tournament.getCurrentGame();
            gameBoard = game.getGameBoard();
            board = gameBoard.getBoard();
            aliveBarScale = 230 / game.getBlackAntsAlive();
            foodBarScale = 20;
            firstRender = false;
        }

        // Check if the mouse is inside any of the mouse over areas
        checkMouseOverAreas(gc, sbg);

        // If the user is exiting the single game display
        if (!exiting) {
            game = tournament.getCurrentGame();

            // Update food bar scale
            if (game.getPlayerOneScore() * foodBarScale >= 230 || game.getPlayerTwoScore() * foodBarScale >= 230) {
                foodBarScale = foodBarScale / 2;
            }

            // Check if the game has finished
            checkIfGameFinished(game);
            
            // Move the camera
            moveCamera(gc);

        }
    }

    /**
     * Check if the mouse is inside any of the mouse over areas.
     *
     * @param gc Game container holding the GUI.
     * @param sbg Game object.
     */
    private void checkMouseOverAreas(GameContainer gc, StateBasedGame sbg) {
        // Mouse over exit button
        if (exitMO.isMouseOver()) {
            currentExitButton = exitButtonHover;
            if (gc.getInput().isMouseButtonDown(0)) {
                // Clicked exit
                tournament.reset();
                sbg.enterState(2);
                exiting = true;
            }
        } else {
            currentExitButton = exitButton;
        }

        // Mouse over speed up button
        if (speedUpMO.isMouseOver()) {
            currentSpeedUp = speedUpButtonHover;
            if (gc.getInput().isMouseButtonDown(0)) {
                // Clicked speed up
                if (steps < 1000) {
                    // Increment the number of steps per refresh unless it's over 1000
                    steps++;
                }
            }
        } else {
            currentSpeedUp = speedUpButton;
        }

        // Mouse over slow down button
        if (slowDownMO.isMouseOver()) {
            currentSlowDownButton = slowDownButtonHover;
            if (gc.getInput().isMouseButtonDown(0)) {
                // Clicked slow down
                if (steps > 2) {
                    // Decrement number of steps per refresh unless less than 2
                    steps--;
                }
            }
        } else {
            currentSlowDownButton = slowDownButton;
        }

        // Mouse over play/pause button
        if (pauseMO.isMouseOver()) {
            currentButton = currentPauseButtonHover;
            if (gc.getInput().isMouseButtonDown(0)) {
                // Clicked play/pause button

                // Switch from play -> pause or pause -> play
                switchButtons();
                if (steps == 0) {
                    steps = oldSteps;
                } else {
                    oldSteps = steps;
                    steps = 0;
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(250);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GUISingleGameDisplay.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            currentButton = currentPauseButton;
        }

        // Mouse over skip button
        if (skipMO.isMouseOver()) {
            currentSkipToEndButton = skipToEndButtonHover;
            if (gc.getInput().isMouseButtonDown(0)) {
                // Clicked skip button
                // 300,000 steps will finish the game
                steps = 3000000;
            }
        } else {
            currentSkipToEndButton = skipToEndButton;
        }
    }

    /**
     * Check if the game has finished.
     * 
     * @param game Current game.
     */
    private void checkIfGameFinished(Game game) {
        // Check if the game has finished & the winning message has not already been shown
        if ((!gameOverMessageShown) && (game.runRounds(steps))) {
            // Game finished, show winning screen
            int p1Score = game.getPlayerOneScore();
            int p2Score = game.getPlayerTwoScore();

            if (p1Score > p2Score) {
                // Player one wins
                showMessage("Player one wins the game " + p1Score + " - " + p2Score + "!", "Game Over!");
            } else if (p2Score > p1Score) {
                // Player two wins
                showMessage("Player two wins the game " + p2Score + " - " + p1Score + "!", "Game Over!");
            } else {
                // Draw
                showMessage("It's a draw! " + p1Score + " - " + p2Score + "", "Game Over!");
            }

            gameOverMessageShown = true;
        }
    }

    /**
     * Move the game board camera.
     * 
     * @param gc Game container holding the GUI.
     */
    private void moveCamera(GameContainer gc) {
        // Check if zooming
        int mouseScroll = Mouse.getDWheel();
        if (mouseScroll > 0 || gc.getInput().isKeyPressed(Input.KEY_EQUALS)) {
            // Zooming in
            cam.incrementZoom();
        } else if (mouseScroll < 0 || gc.getInput().isKeyPressed(Input.KEY_MINUS)) {
            // Zooming out
            cam.decrementZoom();
        }

        // Check if panning
        if (Mouse.getX() < 200 * screenScale && Mouse.getX() > 0) {
            // Move board to right
            if (mouseHasBeenInCenter) {
                cam.incrementXPos();
            }
        } else if (Mouse.getX() > 1200 * screenScale && Mouse.getX() < 1400 * screenScale) {
            // Move board to left
            if (mouseHasBeenInCenter) {
                cam.decrementXPos();
            }
        } else if (Mouse.getY() > 0 && Mouse.getY() < 200 * screenScale && Mouse.getX() < 1400 * screenScale) {
            // Move board down
            if (mouseHasBeenInCenter) {
                cam.decrementYPos();
            }
        } else if (Mouse.getY() > 880 * screenScale && Mouse.getY() < 1080 * screenScale && Mouse.getX() < 1400 * screenScale) {
            // Move board up
            if (mouseHasBeenInCenter) {
                cam.incrementYPos();
            }
        } else {
            mouseHasBeenInCenter = true;
        }
    }

    /**
     * Render the games screen.
     * 
     * @param gc Game container holding the GUI.
     * @param sbg Game object.
     * @param grphcs The graphics context.
     * @throws SlickException if problem when drawing images.
     */
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        // Scale the images
        grphcs.scale(screenScale, screenScale);

        // Draw game board
        cam.setBoardZoom();
        grphcs.scale(cam.getZoom(), cam.getZoom());
        grphcs.translate(cam.getXPos(), cam.getYPos());
        tiles.startUse();
        
        // Iterate over each tile and draw
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                Pos spritePosition = calculateSpritePosition(j, i);
                if (i % 2 == 0) {
                    tiles.getSubImage(spritePosition.getPosX(), spritePosition.getPosY()).drawEmbedded(16 * j, 12 * i, 16, 16);
                } else {
                    tiles.getSubImage(spritePosition.getPosX(), spritePosition.getPosY()).drawEmbedded(8 + 16 * j, 12f * i, 16, 16);
                }
            }
        }
        tiles.endUse();
        cam.setMenuZoom();
        grphcs.translate(-cam.getXPos(), -cam.getYPos());
        grphcs.scale(cam.getZoom(), cam.getZoom());

        // Draw blocking rectangle so board doesn't run over controls
        grphcs.setColor(new Color(33, 252, 172));
        grphcs.fillRect(1400, 0, 520, 1080);

        // Draw graph frames
        grphcs.setColor(Color.white);
        grphcs.setLineWidth(4);
        grphcs.drawLine(divide, 0, divide, 1080);
        grphcs.drawRoundRect(divide + 20, 250, 480, 350, 10);
        grphcs.drawRoundRect(divide + 20, 620, 480, 350, 10);
        gameFont.drawString(divide + 40, 260, "Ants alive");
        gameFont.drawString(divide + 40, 630, "Food in base");

        grphcs.scale(0.5f, 0.5f);
        gameFont.drawString(2 * (divide + 25), 620, "" + 230 / aliveBarScale);
        gameFont.drawString(2 * (divide + 25), 1360, "" + 230 / foodBarScale);
        grphcs.scale(2, 2);

        // Calculate and draw graph bars
        grphcs.setColor(new Color(231, 76, 60));
        grphcs.fillRect(divide + 120, 550 - redAntsAlive * aliveBarScale, 100, redAntsAlive * aliveBarScale);
        grphcs.fillRect(divide + 120, 920 - numRedBaseFood * foodBarScale, 100, numRedBaseFood * foodBarScale);
        grphcs.setColor(new Color(52, 73, 94));
        grphcs.fillRect(divide + 270, 550 - blackAntsAlive * aliveBarScale, 100, blackAntsAlive * aliveBarScale);
        grphcs.fillRect(divide + 270, 920 - numBlackBaseFood * foodBarScale, 100, numBlackBaseFood * foodBarScale);

        // Draw graph axis
        grphcs.setColor(Color.white);
        grphcs.drawLine(divide + 70, 320, divide + 70, 550);
        grphcs.drawLine(divide + 70, 550, divide + 430, 550);
        grphcs.drawLine(divide + 70, 690, divide + 70, 920);
        grphcs.drawLine(divide + 70, 920, divide + 430, 920);

        grphcs.drawImage(logo, 1800, 990);
        grphcs.drawImage(currentExitButton, divide + 20, 985);

        // Draw control buttons
        grphcs.drawImage(currentButton, divide + 170, 15);
        grphcs.drawImage(currentSpeedUp, divide + 227, 115);
        grphcs.drawImage(currentSlowDownButton, divide + 113, 115);
        grphcs.drawImage(currentSkipToEndButton, divide + 284, 15);

    }

    /**
     * Calculate position of sprite.
     * 
     * @param x X-coordinate.
     * @param y Y-coordinate.
     * @return Position of sprite.
     */
    private Pos calculateSpritePosition(int x, int y) {
        String key;
        Pos somePos = new Pos(x, y);
        
        // If ant is in given position
        if (gameBoard.antInPosition(somePos)) {
            key = gameBoard.getTerrainAtPosition(somePos).toString() + gameBoard.antAt(somePos).getAntColour()
                    + gameBoard.antAt(somePos).getFacingDirection() + (gameBoard.numberOfFoodAt(somePos) > 0);
        } else {
            key = gameBoard.getTerrainAtPosition(somePos).toString() + (gameBoard.numberOfFoodAt(somePos) > 0);
        }
        
        // Return value in hash map given key
        return spritePositions.get(key);
    }

    /**
     * Create sprite dictionary.
     */
    private void createSpriteDictionary() {
        int j = 0;
        boolean food = false;

        // Types of terrain
        Terrain[] terrainVals = {Terrain.GRASS, Terrain.GRASS, Terrain.REDBASE, Terrain.REDBASE,
            Terrain.BLACKBASE, Terrain.BLACKBASE};
        
        // For each terrain, add entry to sprite dictionary
        for (Terrain terrainType : terrainVals) {
            int antDirection = 0;
            for (int z = 0; z < 14; z++) {
                String key;

                if (z < 2) {
                    key = terrainType.toString() + food;
                } else {
                    if (j % 2 == 0) {
                        key = terrainType.toString() + Colour.BLACK + antDirection + food;
                    } else {
                        key = terrainType.toString() + Colour.RED + antDirection + food;
                    }

                    if (z % 2 != 0) {
                        antDirection++;
                    }
                }
                spritePositions.put(key, new Pos(z, j));
                food = !food;
            }
            j++;
        }
        spritePositions.put(Terrain.ROCK.toString() + false, new Pos(0, 6));
    }

    /**
     * Switch play/pause buttons.
     */
    private void switchButtons() {
        if (currentPauseButton == pauseButton) {
            currentPauseButton = playButton;
            currentPauseButtonHover = playButtonHover;
        } else {
            currentPauseButton = pauseButton;
            currentPauseButtonHover = pauseButtonHover;
        }
    }

    /**
     * Show pop up message.
     * 
     * @param message String holding message contents.
     * @param errorType Type of error.
     */
    private void showMessage(String message, String errorType) {
        JOptionPane.showMessageDialog(new JFrame(), message, errorType, JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Create camera for board.
     */
    private void createCamera() {
        BoardTile[][] gameBoard = tournament.getCurrentGame().getGameBoard().getBoard();

        float zoomBoard = 1;
        float zoomControl = 1;
        int tilesWide = gameBoard.length * 16;
        int tilesTall = gameBoard[0].length * 12;
        
        // Check size of board
        if (tilesWide > 1400 || tilesTall > 1080) {
            while (tilesWide * zoomBoard > 1400 && tilesTall * zoomBoard > 1080) {
                // Increase zoom
                zoomBoard = zoomBoard / 2;
                zoomControl = zoomControl * 2;
            }
        } else {
            zoomBoard = 8;
            zoomControl = 0.125f;
            while (tilesWide * zoomBoard < 1400 && tilesTall * zoomBoard > 1080) {
                // Increase zoom
                zoomBoard = zoomBoard / 2;
                zoomControl = zoomControl * 2;
            }
            zoomBoard = zoomBoard / 2;
            zoomControl = zoomControl * 2;
        }

        // Create new camera
        cam = new GUICamera(1080, 1400, zoomBoard, zoomControl, -tilesWide / 2, -tilesTall / 2);
    }

    /**
     * Load resources.
     * 
     * @throws SlickException if problem loading images.
     */
    private void loadResources() throws SlickException {
        gameFont = new AngelCodeFont("resources/moreAdded.fnt", "resources/moreAdded_0.png");
        tileset = new Image("resources/sprite.png");
        exitButton = new Image("resources/exitButton.png");
        exitButtonHover = new Image("resources/exitButtonHover.png");
        logo = new Image("resources/smallLogo.png");
        speedUpButton = new Image("resources/FFButton.png");
        speedUpButtonHover = new Image("resources/FFButtonHover.png");
        slowDownButton = new Image("resources/RWButton.png");
        slowDownButtonHover = new Image("resources/RWButtonHover.png");
        pauseButton = new Image("resources/pauseButton.png");
        pauseButtonHover = new Image("resources/pauseButtonHover.png");
        playButton = new Image("resources/playButton.png");
        playButtonHover = new Image("resources/playButtonHover.png");
        skipToEndButton = new Image("resources/SFButton.png");
        skipToEndButtonHover = new Image("resources/SFButtonHover.png");
        tiles = new SpriteSheet(tileset, 64, 64);
        spritePositions = new HashMap<>();
        createSpriteDictionary();
    }

    /**
     * Create mouse over areas.
     * 
     * @param gc Game container holding the GUI.
     */
    private void createMouseOverAreas(GameContainer gc) {
        speedUpMO = new MouseOverArea(gc, speedUpButton, (int) ((divide + 227) * screenScale), (int) (115 * screenScale),
                (int) (speedUpButton.getWidth() * screenScale), (int) (speedUpButton.getHeight() * screenScale));
        slowDownMO = new MouseOverArea(gc, slowDownButton, (int) ((divide + 113) * screenScale), (int) (115 * screenScale),
                (int) (slowDownButton.getWidth() * screenScale), (int) (slowDownButton.getHeight() * screenScale));
        pauseMO = new MouseOverArea(gc, pauseButton, (int) ((divide + 170) * screenScale), (int) (15 * screenScale),
                (int) (pauseButton.getWidth() * screenScale), (int) (pauseButton.getHeight() * screenScale));
        skipMO = new MouseOverArea(gc, skipToEndButton, (int) ((divide + 284) * screenScale), (int) (15 * screenScale),
                (int) (skipToEndButton.getWidth() * screenScale), (int) (skipToEndButton.getHeight() * screenScale));
        exitMO = new MouseOverArea(gc, exitButton, (int) ((divide + 20) * screenScale), (int) (985 * screenScale),
                (int) (exitButton.getWidth() * screenScale), (int) (exitButton.getHeight() * screenScale));
    }

}
