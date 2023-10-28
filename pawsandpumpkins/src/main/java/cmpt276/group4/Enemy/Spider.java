package cmpt276.group4.Enemy;

import cmpt276.group4.Position;
import cmpt276.group4.RecordUsedPlace;

public class Spider implements Enemy {

    private boolean movable = false;
    private Position enemyPosition = new Position(0, 0);

    @Override
    public void catchPlayer() {
        RecordUsedPlace record = RecordUsedPlace.getInstance();
        Position playerPosition = record.getPlayerPosition();
    
        if (playerPosition == enemyPosition) {
            System.out.println("Bagel stepped on spider!"); 
        }
    }

    @Override
    public Position getEnemyPosition() {
        return enemyPosition;
    }

    @Override
    public void setEnemyPosition(Position newPosition) {
        enemyPosition = newPosition;
    }

    @Override
    public boolean isMovable() {
        return movable;
    }
}
