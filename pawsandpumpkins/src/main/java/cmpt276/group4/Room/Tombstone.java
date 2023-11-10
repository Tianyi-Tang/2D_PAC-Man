package cmpt276.group4.Room;
import java.awt.Graphics2D;
import cmpt276.group4.WindowAndInput.GamePanel;

import cmpt276.group4.Position;

public class Tombstone extends Obstacle {

    public Tombstone(Position position, int type) {
        super(position, type);
        //setPlayerAccess(false);
        //setEnemyAccess(true);
    }

    //@Override
    //public boolean getPlayerAvaliable() {
        //return playerAvaliable;
    //}


    @Override
    public Position getPosition() {
        return position;
    }

    //@Override
    //public boolean getTakenPlace() {
        //return takenPlace;
    //}

}