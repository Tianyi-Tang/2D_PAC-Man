package cmpt276.group4.Enemy;

/**
 * Defines the types of enemies present in the game.
 * This enum is used to distinguish between different enemy behaviors and characteristics.
 */
public enum EnemyType {
    /**
     * Represents an advanced ghost enemy.
     * This type of ghost may have ability to go through wall. Bot Available in current version
     */
    GHOST_ADVANCED,

    /**
     * Represents a basic ghost enemy.
     * This type of ghost chase after player if within the range.
     */
    GHOST_BASIC,

    /**
     * Represents a spider enemy.
     * This type of enemy deduct player's score.
     */
    SPIDER
}
