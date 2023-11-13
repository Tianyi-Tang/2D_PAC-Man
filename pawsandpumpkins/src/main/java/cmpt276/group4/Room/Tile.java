package cmpt276.group4.Room;

import cmpt276.group4.CharacterAvaliablePosition;
import cmpt276.group4.Position;
import cmpt276.group4.WindowAndInput.GamePanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

public class Tile implements CharacterAvaliablePosition {
    private boolean playerAvaliable = true;
    //private boolean obstacle = false;
    private boolean takenPlace = false;
    private boolean enemyAvaliable = false;
    private Position location;
    BufferedImage tileImage;

    /**
     * Constructor to initialize a tile with the specified position.
     *
     * @param position The position of the tile.
     */
    public Tile(Position position){
        location = position;
        initalTileImage();
    }

    /**
     * Initializes the tile image.
     */
    public void initalTileImage(){
        try {
            tileImage = ImageIO.read(new File(System.getProperty("user.dir") + "/res/Tiles/tile1.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    /**
     * Gets the position of the tile.
     *
     * @return The position of the tile.
     */
    @Override
    public Position getPosition() {
        return location;
    }

    /**
     * Gets whether the player can access the tile.
     *
     * @return True if the player can access the tile, false otherwise.
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
     * Draws the tile on the graphics context.
     *
     * @param g2 The Graphics2D context on which to draw the tile.
     */
    @Override
    public void draw(Graphics2D g2){
        if(tileImage != null)
            g2.drawImage(tileImage, location.getX_axis(), location.getY_axis(), GamePanel.tileSize , GamePanel.tileSize, null);
    }


    
}
