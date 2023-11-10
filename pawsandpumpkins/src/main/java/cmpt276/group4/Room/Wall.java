package cmpt276.group4.Room;

import cmpt276.group4.CharacterAvaliablePosition;
import cmpt276.group4.Position;
import cmpt276.group4.RecordUsedPlace;
import cmpt276.group4.WindowAndInput.GamePanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;


public class Wall extends Obstacle {
    BufferedImage wallImage;
    public Wall(Position position, int type) {
        super(position, type);
        initialWallImage();
        setPlayerAccess();
    }

    public Wall CreateWall(Position position, int type) {
         Wall wall = new Wall(position, type);
         RecordUsedPlace.getInstance().addElementToMap(wall);
         return wall;
    }
    

    public void initialWallImage(){
        try {
            wallImage = ImageIO.read(new File(System.getProperty("user.dir") + "/res/Walls/mid_wall2.png"));
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
