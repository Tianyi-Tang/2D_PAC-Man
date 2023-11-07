package cmpt276.group4.Room;

import cmpt276.group4.Position;


public class Tombstone extends Obstacle {
    private boolean playerAvaliable = false;
    private boolean enemyAvaliable = true;
    private boolean takenPlace = true;
    private Position position;

    public Tombstone(Position location) {
        super(location);
    }

    @Override
    public boolean getPlayerAvaliable() {
        return playerAvaliable;
    }


    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public boolean getTakenPlace() {
        return takenPlace;
    }

}