package cmpt276.group4.Enemy;

import cmpt276.group4.Position;

interface Enemy {
    void catchPlayer();
    boolean movable = true;
    Position enemyPosition = new Position(0, 0);
}
