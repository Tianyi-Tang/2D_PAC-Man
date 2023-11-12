package cmpt276.group4.Room;

import cmpt276.group4.CharacterAvaliablePosition;
import cmpt276.group4.Position;
import cmpt276.group4.RecordUsedPlace;
import cmpt276.group4.WindowAndInput.GamePanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Obstacle implements CharacterAvaliablePosition {
    protected Position position;
    private boolean playerAvaliable = false;
    private boolean takenPlace = true;
    private Position playerPosition;
    BufferedImage wallImage;
    RecordUsedPlace record;

    // Constructor to initialize an obstacle with player access, enemy
    // traversability, and positions
    public Obstacle(Obstacletype type, int amount, Position position) {
        // this.position = position;
        record = RecordUsedPlace.getInstance();
        initialWallImage();
        setObstaclePosition();
    }

    /**
     * Sets the position for an tombstone in the game.This method selects a random position from available positions and ensures it
     * is not too close to the player.
     */
    public void setObstaclePosition() {
        Position potentialPosition;
        do {
            // Get a random position from available positions
            potentialPosition = RecordUsedPlace.getInstance().getRandomFromAvailablePosition();
            // Repeat until the position is not too close to the player
        } while (record.isPlayerNearBy(2 * GamePanel.tileSize, potentialPosition));

        // Set the chosen position for the obstacle
        position = potentialPosition;
    }

    public void setPlayerAccess() {
        playerAvaliable = false;
    }

    public void setPosition() {
        position = RecordUsedPlace.getInstance().getRandomFromAvailablePosition();
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public boolean getPlayerAvaliable() {
        return playerAvaliable;
    }

    @Override
    public boolean getTakenPlace() {
        return takenPlace;
    }

    public void initialWallImage() {
        try {
            wallImage = ImageIO.read(new File(System.getProperty("user.dir") + "/res/Walls/mid_wall2.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D g2) {

    }

}
