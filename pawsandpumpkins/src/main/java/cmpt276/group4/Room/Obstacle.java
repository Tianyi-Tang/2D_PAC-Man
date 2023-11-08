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

    
    @Override
    public boolean getPlayerAvaliable() {
        throw new UnsupportedOperationException("is player avaliable");
    }

    @Override
    public Position getPosition() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPosition'");
    }

    @Override
    public boolean getTakenPlace() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTakenPlace'");
    }

    //@Override
    //public void draw(Graphics2D g2) {
    //    // TODO Auto-generated method stub
    //    throw new UnsupportedOperationException("Unimplemented method 'draw'");
    //}







}

