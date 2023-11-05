package cmpt276.group4.Room;

import cmpt276.group4.Position;
import cmpt276.group4.WindowAndInput.GamePanel;
import cmpt276.group4.Position;

import java.awt.Color;
import java.awt.Graphics2D;



public class Tombstone extends Obstacle {
    private boolean playerAvaliable = false;
    private boolean enemyAvaliable = true;
    private boolean takenPlace = true;
    private Position location;

    

    public Tombstone(Position location) {
        super(location);
    }

    @Override
    public boolean getPlayerAvaliable() {
        return playerAvaliable;
    }

    @Override
    public boolean getEnemyAvaliable() {
        return enemyAvaliable;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public boolean getTakenPlace() {
        return takenPlace;
    }

    @Override
    public void draw(Graphics2D g2){
        g2.setColor(Color.red);
        g2.fillRect(location.getX_axis(), location.getY_axis(), GamePanel.tileSize , GamePanel.tileSize);
    }

}