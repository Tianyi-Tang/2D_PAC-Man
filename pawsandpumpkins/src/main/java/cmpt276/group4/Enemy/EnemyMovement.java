package cmpt276.group4.Enemy;

import cmpt276.group4.Position;
import cmpt276.group4.RecordUsedPlace;
import cmpt276.group4.Movement;

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
        System.out.println("Enemymovement/isPositionAvaiale "+ record.characterMovable(position));
        return record.characterMovable(position);
        //return record.enemyMovable(position)&&record.isNotSpiderPosition(position);
    }
}
