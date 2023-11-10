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
    public Tombstone(Position position) {
        super(position);
        initialWallImage();
        setPlayerAccess();
    }

    @Override
    public Position getPosition() {
        return position;
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