package cmpt276.group4.Room;

import cmpt276.group4.CharacterAvaliablePosition;
import cmpt276.group4.Position;
import cmpt276.group4.RecordUsedPlace;
import cmpt276.group4.WindowAndInput.GamePanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import cmpt276.group4.Logic.GameConfig;


import javax.imageio.ImageIO;


public class Wall extends Obstacle {
    private GameConfig gc;
    public List<Position> wallPositions;
    BufferedImage wallImage;
    public Wall(Obstacletype type, int amount, Position position) {
        super(type, amount, position);
        this.position = position;
        initialWallImage();
    }

    public String getImageNameForPosition(Position p,List<Position> wallPositions) {
        boolean north = false, south = false, east = false, west = false;
        gc = GameConfig.getGameConfigInstance();
        wallPositions = gc.getWallPositions();

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
    
    public void initialWallImage(){
        try {
            wallImage = ImageIO.read(new File(System.getProperty("user.dir") + "/res/Walls/" 
            + getImageNameForPosition(position, wallPositions)));
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    @Override
    public void draw(Graphics2D g2){
        if(wallImage != null)
            g2.drawImage(wallImage, position.getX_axis(), position.getY_axis(), GamePanel.tileSize , GamePanel.tileSize, null);

    }

    

}
