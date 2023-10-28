package cmpt276.group4.Room;

import cmpt276.group4.CharacterAvaliablePosition;
import cmpt276.group4.Position;

public class Obstacle implements CharacterAvaliablePosition {

    private boolean playerAccess;
    private boolean enemyTraversability;
    private Position[] positions;

    // Constructor to initialize an obstacle with player access, enemy traversability, and positions
    public Obstacle(boolean playerAccess, boolean enemyTraversability, Position[] positions) {
        this.playerAccess = playerAccess;
        this.enemyTraversability = enemyTraversability;
        this.positions = positions;
    }
    
    @Override
    public boolean getPlayerAvaliable() {
        throw new UnsupportedOperationException("is player avaliable");
    }

    @Override
    public boolean getEnemyAvaliable() {
        throw new UnsupportedOperationException("is enemy avaliable");
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







}

