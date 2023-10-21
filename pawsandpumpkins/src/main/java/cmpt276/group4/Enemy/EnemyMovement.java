package cmpt276.group4.Enemy;

import cmpt276.group4.Position;
import cmpt276.group4.Movement;

public class EnemyMovement implements Movement {
    @Override
    public boolean moveTo(Position position) {
        System.out.println("Moving to position: " + position.getX_axis() + ", " + position.getY_axis());
        return true;
    }

    @Override
    public boolean moveable(Position position) {
        System.out.println("Checking if position is moveable: " + position.getX_axis() + ", " + position.getY_axis());
        return true;
    }
}
