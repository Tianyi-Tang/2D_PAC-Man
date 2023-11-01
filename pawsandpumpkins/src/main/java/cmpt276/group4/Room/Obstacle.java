package cmpt276.group4.Room;

import java.awt.Graphics2D;

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

    // Getter and setter methods for playerAccess
    public boolean isPlayerAccess() {
        return playerAccess;
    }

    public void setPlayerAccess(boolean playerAccess) {
        this.playerAccess = playerAccess;
    }

    // Getter and setter methods for enemyTraversability
    public boolean isEnemyTraversability() {
        return enemyTraversability;
    }

    public void setEnemyTraversability(boolean enemyTraversability) {
        this.enemyTraversability = enemyTraversability;
    }

    // Getter and setter methods for positions
    public Position[] getPositions() {
        return positions;
    }

    public void setPositions(Position[] positions) {
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

    @Override
    public void draw(Graphics2D g2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }







}

