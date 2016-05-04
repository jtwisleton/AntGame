package gui;

/**
 * Class to represent a camera on top of the game board.
 *
 * @author Team18
 */
public class GUICamera {

    private int xPos;
    private int yPos;
    private float zoom;
    private float boardZoom;
    private int height;
    private int width;
    private float controlZoom;

    /**
     * Construct a new GUICamera object.
     *
     * @param height Height of the camera's view.
     * @param width Width of the camera's view.
     * @param zoom Amount to zoom in on the board.
     * @param controlZoom Amount to zoom in on the menu.
     * @param xOffset X-axis offset of the board.
     * @param yOffset Y-axis offset of the board.
     */
    public GUICamera(int height, int width, float zoom, float controlZoom, int xOffset, int yOffset) {
        // Initiliase all variables
        this.height = height;
        this.width = width;
        this.boardZoom = zoom;
        this.controlZoom = controlZoom;
        xPos = xOffset;
        yPos = yOffset;
        this.zoom = boardZoom;
    }

    /**
     * Get the x-position of the camera.
     *
     * @return The x-position.
     */
    public int getXPos() {
        return (int) (xPos + (width / 2 * controlZoom));
    }

    /**
     * Get the y-position of the camera.
     *
     * @return The y-position.
     */
    public int getYPos() {
        return (int) (yPos + (height / 2 * controlZoom));
    }

    /**
     * Get the camera's zoom level.
     *
     * @return The camera's zoom.
     */
    public float getZoom() {
        return zoom;
    }

    /**
     * Set the x-position of the camera.
     *
     * @param xPos X-position to move the camera to.
     */
    public void setXPos(int xPos) {
        this.xPos = xPos;
    }

    /**
     * Set the y-position of the camera.
     *
     * @param yPos Y-position to move the camera to.
     */
    public void setYPos(int yPos) {
        this.yPos = yPos;
    }

    /**
     * Decrement the x-position of the camera.
     */
    public void decrementXPos() {
        // Limit the x-position change to a minimum of 1
        if (10 * controlZoom < 1) {
            xPos -= 1;
        } else {
            xPos -= 10 * controlZoom;
        }

    }

    /**
     * Increment the x-position of the camera.
     */
    public void incrementXPos() {
        // Limit the x-position change to a minimum of 1
        if (10 * controlZoom < 1) {
            xPos += 1;
        } else {
            xPos += 10 * controlZoom;
        }

    }

    /**
     * Decrement the y-position of the camera.
     */
    public void decrementYPos() {
        // Limit the y-position change to a minimum of 1
        if (10 * controlZoom < 1) {
            yPos -= 1;
        } else {
            yPos -= 10 * controlZoom;
        }
    }

    /**
     * Increment the y-position of the camera.
     */
    public void incrementYPos() {
        // Limit the y-position change to a minimum of 1
        if (10 * controlZoom < 1) {
            yPos += 1;
        } else {
            yPos += 10 * controlZoom;
        }
    }

    /**
     * Increment the zoom level of the camera.
     */
    public void incrementZoom() {
        // Board zoom level cannot be above 16
        if (boardZoom < 16) {
            boardZoom = boardZoom * 2;
            controlZoom = controlZoom / 2;
        }
    }

    /**
     * Decrement the zoom level of the camera.
     */
    public void decrementZoom() {
        // Board zoom level cannot be less than 0.25
        if (boardZoom > 0.25) {
            boardZoom = boardZoom / 2;
            controlZoom = controlZoom * 2;
        }
    }

    /**
     * Set the zoom level of the menu.
     */
    public void setMenuZoom() {
        zoom = controlZoom;
    }

    /**
     * Set the zoom level of the board.
     */
    public void setBoardZoom() {
        zoom = boardZoom;
    }

}
