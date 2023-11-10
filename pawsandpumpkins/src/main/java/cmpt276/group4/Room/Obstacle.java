package cmpt276.group4.Room;

import cmpt276.group4.CharacterAvaliablePosition;
import cmpt276.group4.Position;
import cmpt276.group4.RecordUsedPlace;
import cmpt276.group4.WindowAndInput.GamePanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Obstacle implements CharacterAvaliablePosition{
    public Position position;
    public int type;
    private boolean playerAvaliable = false;
    private boolean takenPlace = true;
    BufferedImage wallImage;
    // Constructor to initialize an obstacle with player access, enemy traversability, and positions
    public Obstacle(Position position, int type) {
        this.position = position;
        this.type = type;
        initialWallImage();
    }

    public int getObstacleType(){
        return type;
    }
    public void setPlayerAccess(){
        playerAvaliable = false;
    }
    //public void setEnemyAccess(){
    //    playerEnemy = false;
    //}


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

    public void initialWallImage(){
        try {
            wallImage = ImageIO.read(new File(System.getProperty("user.dir") + "/res/Walls/mid_wall2.png"));
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    @Override
    public void draw(Graphics2D g2){
        //if(getObstacleType() == 1){
        //    g2.drawImage(wallImage, position.getX_axis(), position.getY_axis(), GamePanel.tileSize , GamePanel.tileSize, null);
        //}
    }

        
    
    
}









