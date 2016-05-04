package gui;

import antgameproject.AntGameTournament;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Class to represent a Graphical User Interface for the Ant Game.
 *
 * @author Team18
 */
public class GUI extends StateBasedGame {

    private AntGameTournament tournament;

    /**
     * Construct a new GUI object.
     *
     * @param name Name of the State Based Game.
     * @param tournament Ant game tournament being displayed.
     */
    public GUI(String name, AntGameTournament tournament) {
        super(name);
        this.tournament = tournament;
    }

    /**
     * Initialize the list of states.
     *
     * @param container Game container holding the GUI.
     * @throws SlickException if problem adding states.
     */
    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        // Get the size of the screen
        float scale = getScale(container);
        System.out.println(scale);

        // Add all the screens as states
        this.addState(new GUITeamScreen(scale));
        this.addState(new GUITitleScreen(scale));
        this.addState(new GUIMenuScreen(tournament, scale));
        this.addState(new GUITournamentOptions(tournament, scale));
        this.addState(new GUISingleGameOptions(tournament, scale));
        this.addState(new GUITournamentDisplay(tournament, scale));
        this.addState(new GUISingleGameDisplay(tournament, scale));
    }

    /**
     * Get the scale of the game container.
     *
     * @param container Game container for measuring.
     * @return The containers scale as a float.
     */
    private float getScale(GameContainer container) {
        System.out.println(container.getWidth());

        // Calculate the scale depending on the users resolution choice
        if (container.getWidth() == 1920) {
            return 1;
        } else if (container.getWidth() == 1440) {
            return 0.75f;
        } else {
            return 0.5f;
        }
    }

}
