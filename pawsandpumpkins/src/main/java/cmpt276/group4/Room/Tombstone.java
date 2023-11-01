package cmpt276.group4.Room;

import cmpt276.group4.Position;


public class Tombstone extends Obstacle {
    boolean playerAvaliable = false;
    boolean enemeyAvaliable = true;
    boolean getTakenPlace = true;
    public Tombstone(Position[] position) {
        super(position);
    }

    @Override
    public boolean getPlayerAvaliable() {
        return playerAvaliable;
    }

    @Override
    public boolean getEnemyAvaliable() {
        return enemeyAvaliable;
    }

   // @Override
   // public Position getPosition() {

    //}

    @Override
    public boolean getTakenPlace() {
        return getTakenPlace;
    }

}