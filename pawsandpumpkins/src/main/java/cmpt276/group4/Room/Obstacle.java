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
    public Position position;
    private boolean playerAvaliable = false;
    private boolean takenPlace = true;
    private Position playerPosition;
    BufferedImage wallImage;
    RecordUsedPlace record;

    // Constructor to initialize an obstacle with player access, enemy
    // traversability, and positions
    public Obstacle(Obstacletype type, int amount, Position position) {
        record = RecordUsedPlace.getInstance();;
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

    @Override
    public void draw(Graphics2D g2) {

    }

}
