package cmpt276.group4.Room;

import cmpt276.group4.CharacterAvaliablePosition;
import cmpt276.group4.Position;

public class Obstacle implements CharacterAvaliablePosition {

    // Should these be private???
    boolean playerAccess;
    boolean enemyTraversability;

    //Should this have [] for array
    Position position;

    public Obstacle() {
        this.playerAccess = true;
        this.enemyTraversability = true;
        this.position = new Position(5, 5);
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

