package cmpt276.group4.Enemy;

import cmpt276.group4.Position;

public interface Enemy {
    void catchPlayer();
    boolean movable = true;
    Position enemyPosition = new Position(0, 0);

   
    Position getEnemyPosition();
    void setEnemyPosition(Position newPosition);
    boolean isMovable();
    void setMovable(boolean movable);

}
