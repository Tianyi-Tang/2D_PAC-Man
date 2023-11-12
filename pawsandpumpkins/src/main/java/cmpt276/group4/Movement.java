package cmpt276.group4;

/**
 * The Movement interface defines the essential methods required for moving game entities.
 * Implementing classes will provide specific movement behaviors.
 */
public interface Movement {
    
    /**
     * Moves an entity to the specified position.
     *
     * @param position The position to move the entity to.
     * @return true if the movement is successful, false otherwise.
     */
    boolean moveTo(Position position);

    /**
     * Checks if the specified position is available for movement.
     *
     * @param position The position to check for availability.
     * @return true if the position is available, false otherwise.
     */
    boolean isPositionAvailable(Position position);
}
