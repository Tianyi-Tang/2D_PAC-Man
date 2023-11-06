package cmpt276.group4.Enemy;

import cmpt276.group4.Position;
import cmpt276.group4.RecordUsedPlace;
import cmpt276.group4.Movement;
import cmpt276.group4.RecordUsedPlace;

public class EnemyMovement implements Movement {
    @Override
    public boolean moveTo(Position position) {
        //Call image controller to enemy image to new position
        System.out.println("Moving Enemy to position: " + position.getX_axis() + ", " + position.getY_axis());
        
        return true;
    }

    @Override
    public boolean isPositionAvailable(Position position) {
        RecordUsedPlace record = RecordUsedPlace.getInstance();
        System.out.println("Enemymovement/isPositionAvaiale "+ record.enemyMovable(position));
        return record.enemyMovable(position)&&record.isNotSpiderPosition(position);
    }
}
