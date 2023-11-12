package cmpt276.group4.Enemy;

import java.awt.Graphics2D;
import cmpt276.group4.Position;

/**
 * Represents the common behavior of enemies in the game.
 * This interface defines methods for enemy movement, position handling,
 * and drawing the enemy on the screen.
 */
public interface Enemy {

    /**
     * Defines the action to be taken when an enemy catches the player.
     */
    void catchPlayer();
    boolean movable = true;
    Position enemyPosition = new Position(0, 0);

    /**
     * Gets the current position of the enemy.
     * 
     * @return The current position of the enemy.
     */
    Position getEnemyPosition();

    /**
     * Sets a new position for the enemy.
     * 
     * @param newPosition The new position to be set for the enemy.
     */
    void setEnemyPosition(Position newPosition);

    /**
     * Checks if the enemy is movable.
     * 
     * @return {@code true} if the enemy is movable, {@code false} otherwise.
     */
    boolean isMovable();

    /**
     * Draws the enemy on the provided graphics context.
     * 
     * @param g2 The Graphics2D context on which the enemy is drawn.
     */
    void draw(Graphics2D g2);

    /**
     * Gets the position of the enemy.
     * 
     * @return The position of the enemy.
     */
    Position getPosition();
}
