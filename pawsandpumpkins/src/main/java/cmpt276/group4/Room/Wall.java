package cmpt276.group4.Room;

import cmpt276.group4.CharacterAvaliablePosition;
import cmpt276.group4.Position;
import cmpt276.group4.GameMap.RecordUsedPlace;
import cmpt276.group4.WindowAndInput.GamePanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import cmpt276.group4.Logic.GameConfig;
import cmpt276.group4.Logic.WindowConfig;

import javax.imageio.ImageIO;


public class Wall extends Obstacle {
    private GameConfig gc;
    public List<Position> wallPositions;
    BufferedImage wallImage;

    /**
     * Constructor to initialize a wall with the specified type, amount, and position.
     *
     * @param amount   The number of wall obstacles.
     * @param position The position of the wall.
     */
    public Wall(Position position) {
        super( position);
        this.position = position;
        initialWallImage();
    }

    /**
     * Generates the image name for the given position and wall positions.
     *
     * @param p             The position of the wall.
     * @param wallPositions The list of wall positions.
     * @return The image name for the wall based on its surrounding walls.
     */
    public String getImageNameForPosition(Position p,List<Position> wallPositions) {
        boolean north = false, south = false, east = false, west = false;
        gc = GameConfig.getGameConfigInstance();
        wallPositions = gc.getWallPositions();

        for (Position wallPosition : wallPositions) {
            if (wallPosition.equals(new Position(p.getX_axis(), p.getY_axis() - WindowConfig.tileSize))) {
                north = true;
            }
            if (wallPosition.equals(new Position(p.getX_axis(), p.getY_axis() + WindowConfig.tileSize))) {
                south = true;
            }
            if (wallPosition.equals(new Position(p.getX_axis() + WindowConfig.tileSize, p.getY_axis()))) {
                east = true;
            }
            if (wallPosition.equals(new Position(p.getX_axis() - WindowConfig.tileSize, p.getY_axis()))) {
                west = true;
            }
        }

        String imageName = "";
        if (north) imageName += "north_";
        if (east) imageName += "east_";
        if (south) imageName += "south_";
        if (west) imageName += "west_";

        imageName += ".png";

        return imageName;
    }
    
    /**
     * Initializes the wall image based on the generated image name for the position.
     */
    public void initialWallImage(){
        try {
            wallImage = ImageIO.read(new File(System.getProperty("user.dir") + "/res/Walls/" 
            + getImageNameForPosition(position, wallPositions)));
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    /**
     * Draws the wall on the graphics context.
     *
     * @param g2 The Graphics2D context on which to draw the wall.
     */
    @Override
    public void draw(Graphics2D g2){
        if(wallImage != null)
            g2.drawImage(wallImage, position.getX_axis(), position.getY_axis(), WindowConfig.tileSize , WindowConfig.tileSize, null);
    }

    
}
