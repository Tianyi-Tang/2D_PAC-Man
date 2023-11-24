package cmpt276.group4.Room;

import cmpt276.group4.CharacterAvaliablePosition;
import cmpt276.group4.Position;
import cmpt276.group4.GameMap.RecordUsedPlace;
import cmpt276.group4.WindowAndInput.GamePanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Obstacle implements CharacterAvaliablePosition {
    protected Position position;
    private boolean playerAvaliable = false;
    private boolean takenPlace = true;
    BufferedImage wallImage;
    RecordUsedPlace record;

    /**
     * Constructor to initialize an obstacle with player access, enemy
     * traversability, and positions.
     *
     * @param type     The type of obstacle.
     * @param amount   The number of obstacles.
     * @param position The position of the obstacle.
     */
    public Obstacle(Obstacletype type, int amount, Position position) {
        record = RecordUsedPlace.getInstance();
    }

    /**
     * Sets player access to the obstacle.
     */
    public void setPlayerAccess() {
        playerAvaliable = false;
    }

    /**
     * Gets the position of the obstacle.
     *
     * @return The position of the obstacle.
     */
    @Override
    public Position getPosition() {
        return position;
    }

    /**
     * Gets whether the player can access the obstacle.
     *
     * @return True if the player can access the obstacle, false otherwise.
     */
    @Override
    public boolean getPlayerAvaliable() {
        return playerAvaliable;
    }

    /**
     * Gets whether the position is already taken.
     *
     * @return True if the position is taken, false otherwise.
     */
    @Override
    public boolean getTakenPlace() {
        return takenPlace;
    }

    /**
     * Draws the obstacle on the graphics context.
     *
     * @param g2 The Graphics2D context on which to draw the obstacle.
     */
    @Override
    public void draw(Graphics2D g2) {

    }

}
