package cmpt276.group4.Room;

import cmpt276.group4.CharacterAvaliablePosition;
import cmpt276.group4.Position;

public class Obstacle implements CharacterAvaliablePosition {

    private boolean playerAccess;
    private boolean enemyTraversability;
    private Position[] positions;

    // Constructor to initialize an obstacle with player access, enemy traversability, and positions
    public Obstacle(Position[] positions) {
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







}

