package cmpt276.group4.Room;

import cmpt276.group4.Position;

public class Wall extends Obstacle {

    public Wall(boolean playerAccess, boolean enemyTraversability, Position[] position) {
        super(playerAccess, enemyTraversability, position);
    }
}
