package cmpt276.group4.Room;

import cmpt276.group4.CharacterAvaliablePosition;
import cmpt276.group4.Position;
import cmpt276.group4.WindowAndInput.GamePanel;


import java.awt.Graphics2D;

public class Tile implements CharacterAvaliablePosition {
    private boolean playerAvaliable = true;
    private boolean enemyAvialiable = true;
    private boolean takenPlace = true;
    private Position location;

    public Tile(Position position){
        location = position;
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
    public boolean getEnemyAvaliable() {
        return enemyAvialiable;
    }

    @Override
    public boolean getTakenPlace() {
        return takenPlace;
    }

    public void draw(Graphics2D g2){
        g2.fillRect(location.getX_axis(), location.getY_axis(), GamePanel.tileSize , GamePanel.tileSize);
    }
    
}
