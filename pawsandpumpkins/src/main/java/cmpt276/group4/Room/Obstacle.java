package cmpt276.group4.Room;

import cmpt276.group4.Position;

public class Obstacle extends Tile{
    protected Position position;
    // Constructor to initialize an obstacle with player access, enemy traversability, and positions
    public Obstacle(Position position, int type) {
        super(position);
        if (type == 1) {
            Wall wall = new Wall(position, type);

        }
        else{
            Tombstone tombstone = new Tombstone(position,type);
        }

    }

    

}

