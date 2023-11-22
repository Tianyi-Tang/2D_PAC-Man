package cmpt276.group4.Enemy;

import cmpt276.group4.Position;
import GameMap.RecordUsedPlace;
import cmpt276.group4.Movement;

/**
 * This class is responsible for managing the movement of enemies in the game.
 * It implements the {@link Movement} interface to handle enemy position updates and checks.
 */
public class EnemyMovement implements Movement {

    /**
     * Moves the enemy to the specified position.
     * This method updates the enemy's position and  handle updating the enemy's image in the game.
     *
     * @param position The new position to move the enemy to.
     * @return Returns true, indicating that the move operation was successful.
     */
    @Override
    public boolean moveTo(Position position) {
        return true;
    }

    /**
     * Checks if a specified position is available for the enemy to move to.
     * This method verifies if the given position is not currently occupied by another game character or obstacle.
     *
     * @param position The position to be checked for availability.
     * @return true if the position is available for movement, false otherwise.
     */
    @Override
    public boolean isPositionAvailable(Position position) {
        RecordUsedPlace record = RecordUsedPlace.getInstance();
        return record.characterMovable(position);
    }
}
