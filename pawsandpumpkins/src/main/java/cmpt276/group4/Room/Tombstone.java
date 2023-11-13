package cmpt276.group4.Room;

import cmpt276.group4.CharacterAvaliablePosition;
import cmpt276.group4.Position;
import cmpt276.group4.RecordUsedPlace;
import cmpt276.group4.WindowAndInput.GamePanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;


/**
 * The Tombstone class represents a tombstone obstacle in the game, extending the functionality
 * of the Obstacle class.
 */
public class Tombstone extends Obstacle {
    BufferedImage tombImage;

    /**
     * Constructor to initialize a tombstone with the specified type, amount, and position.
     *
     * @param type     The type of tombstone obstacle.
     * @param amount   The number of tombstone obstacles.
     * @param position The position of the tombstone.
     */
    public Tombstone(Obstacletype type, int amount, Position position ) {
        super(type, amount, position);
        setObstaclePosition();
        initialWallImage();
        setPlayerAccess();
    }

    /**
     * Gets the position of the tombstone.
     *
     * @return The position of the tombstone.
     */
    @Override
    public Position getPosition() {
        return position;
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
    
    /**
     * Initializes the tombstone image.
     */
    public void initialWallImage(){
        try {
            tombImage = ImageIO.read(new File(System.getProperty("user.dir") + "/res/Obstacle/obstacle1.png"));
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    /**
     * Draws the tombstone on the graphics context.
     *
     * @param g2 The Graphics2D context on which to draw the tombstone.
     */
    @Override
    public void draw(Graphics2D g2){
        if(tombImage != null)
            g2.drawImage(tombImage, position.getX_axis(), position.getY_axis(), GamePanel.tileSize , GamePanel.tileSize, null);

    }

}