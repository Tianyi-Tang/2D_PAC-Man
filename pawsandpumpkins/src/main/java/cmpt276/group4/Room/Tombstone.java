package cmpt276.group4.Room;
import java.awt.Graphics2D;
import cmpt276.group4.WindowAndInput.GamePanel;

import cmpt276.group4.Position;

public class Tombstone extends Obstacle {

    public Tombstone(Position position, int type) {
        super(position, type);
        setPlayerAccess(false);
        setEnemyAccess(true);
    }

    @Override
    public void draw(Graphics2D g2){
        if(tileImage != null)
            g2.drawImage(tileImage, position.getX_axis(), position.getY_axis(), GamePanel.tileSize , GamePanel.tileSize, null);
        //g2.fillRect(location.getX_axis(), location.getY_axis(), GamePanel.tileSize , GamePanel.tileSize);
    }

}