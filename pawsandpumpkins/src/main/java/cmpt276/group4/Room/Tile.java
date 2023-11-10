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

    public Tile(Position position){
        location = position;
        initalTileImage();
    }

    public void initalTileImage(){
        try {
            tileImage = ImageIO.read(new File(System.getProperty("user.dir") + "/res/Tiles/tile1.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public Position getPosition() {
        return location;
    }

    @Override
    public boolean getPlayerAvaliable() {
        return playerAvaliable;
    }

    @Override
    public boolean getTakenPlace() {
        return takenPlace;
    }
    
    @Override
    public void draw(Graphics2D g2){
        if(tileImage != null)
            g2.drawImage(tileImage, location.getX_axis(), location.getY_axis(), GamePanel.tileSize , GamePanel.tileSize, null);
        
        
    }

    public String getImageNameForPosition(Position p,List<Position> wallPositions) {
        boolean north = false, south = false, east = false, west = false;

        for (Position wallPosition : wallPositions) {
            if (wallPosition.equals(new Position(p.getX_axis(), p.getY_axis() - GamePanel.tileSize))) {
                north = true;
            }
            if (wallPosition.equals(new Position(p.getX_axis(), p.getY_axis() + GamePanel.tileSize))) {
                south = true;
            }
            if (wallPosition.equals(new Position(p.getX_axis() + GamePanel.tileSize, p.getY_axis()))) {
                east = true;
            }
            if (wallPosition.equals(new Position(p.getX_axis() - GamePanel.tileSize, p.getY_axis()))) {
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



    
}
