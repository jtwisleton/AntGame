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
 * Class to represent the menu screen of a GUI.
 *
 * @author Team18
 */
public class GUIMenuScreen extends BasicGameState {

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

    /**
     * Construct a new GUIMenuScreen object.
     *
     * @param tournament AntGameTournament being displayed.
     * @param screenScale Scale of the screen.
     */
    public GUIMenuScreen(AntGameTournament tournament, float screenScale) {
        this.screenScale = screenScale;
        this.tournament = tournament;
    }

    /**
     * Get the ID of this state.
     *
     * @return This state's ID.
     */
    @Override
    public int getID() {
        return 2;
    }

    /**
     * Initialize the GUIMenuScreen.
     *
     * @param gc Game container holding the GUI.
     * @param sbg Game object.
     * @throws SlickException if problem initialising mouse over areas.
     */
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        // Load button images
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

        // Set position variables
        buttonPosX = 960 - 480;
        buttonStartPosY = 500;
        offset = 190;

        // Initilaise mouse over areas
        singleGameMO = new MouseOverArea(gc, singlePButton, (int) (buttonPosX * screenScale), (int) (buttonStartPosY * screenScale),
                (int) (singlePButton.getWidth() * screenScale), (int) (singlePButton.getHeight() * screenScale));
        tournamentGameMO = new MouseOverArea(gc, tournamentButton, (int) (buttonPosX * screenScale), (int) ((buttonStartPosY + offset) * screenScale),
                (int) (tournamentButton.getWidth() * screenScale), (int) (tournamentButton.getHeight() * screenScale));
        exitMO = new MouseOverArea(gc, exitButton, (int) (buttonPosX * screenScale), (int) ((buttonStartPosY + 2 * offset) * screenScale),
                (int) (exitButton.getWidth() * screenScale), (int) (exitButton.getHeight() * screenScale));
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
        tournament.reset();

        // Check where the mouse is
        if (singleGameMO.isMouseOver()) {
            // Single game option
            currentSP = singleGButtonHover;
            if (gc.getInput().isMouseButtonDown(0)) {
                // Clicked button
                sbg.enterState(5);
            }

        } else if (tournamentGameMO.isMouseOver()) {
            // Tournament option
            currentTourn = tournamentButtonHover;
            if (gc.getInput().isMouseButtonDown(0)) {
                // Clicked button
                sbg.enterState(4);
            }

        } else if (exitMO.isMouseOver()) {
            // Exit option
            currentExit = exitButtonHover;
            if (gc.getInput().isMouseButtonDown(0)) {
                // Clicked button
                System.exit(0);
            }

        } else {
            // Not inside any mouse over areas.
            currentSP = singlePButton;
            currentTourn = tournamentButton;
            currentExit = exitButton;
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
        // Draw the images onto the screen
        grphcs.scale(screenScale, screenScale);
        grphcs.drawImage(title, 0, 0);
        grphcs.drawImage(currentSP, buttonPosX, buttonStartPosY);
        grphcs.drawImage(currentTourn, buttonPosX, buttonStartPosY + offset);
        grphcs.drawImage(currentExit, buttonPosX, buttonStartPosY + 2 * offset);
    }

}
