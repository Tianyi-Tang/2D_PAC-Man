package cmpt276.group4;

/**
 * The Movement interface defines the essential methods required for moving game entities.
 * Implementing classes will provide specific movement behaviors.
 */
public interface Movement {
    
    /**
     * Checks if the specified position is available for movement.
     *
     * @param position The position to check for availability.
     * @return true if the position is available, false otherwise.
     */
    boolean isPositionAvailable(Position position);
}
