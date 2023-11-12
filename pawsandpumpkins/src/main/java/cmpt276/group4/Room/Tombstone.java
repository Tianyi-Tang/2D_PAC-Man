package cmpt276.group4.Room;

import cmpt276.group4.CharacterAvaliablePosition;
import cmpt276.group4.Position;
import cmpt276.group4.RecordUsedPlace;
import cmpt276.group4.WindowAndInput.GamePanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Tombstone extends Obstacle {
    BufferedImage tombImage;
    public Tombstone(Obstacletype type, int amount, Position position ) {
        super(type, amount, position);
        setObstaclePosition();
        initialWallImage();
        setPlayerAccess();
    }

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
    

    public void initialWallImage(){
        try {
            tombImage = ImageIO.read(new File(System.getProperty("user.dir") + "/res/Obstacle/obstacle1.png"));
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    @Override
    public void draw(Graphics2D g2){
        if(tombImage != null)
            g2.drawImage(tombImage, position.getX_axis(), position.getY_axis(), GamePanel.tileSize , GamePanel.tileSize, null);

    }

}