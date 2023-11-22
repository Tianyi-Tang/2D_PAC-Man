package cmpt276.group4.Enemy;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import GameMap.RecordUsedPlace;
import cmpt276.group4.GameManager;
import cmpt276.group4.Position;
import cmpt276.group4.Logic.WindowConfig;
import cmpt276.group4.Player.PlayerMovement;
import cmpt276.group4.WindowAndInput.GamePanel;

/**
 * Represents a Ghost enemy in the game.
 * This class handles the movement and behavior of a ghost-type enemy.
 */
public class Ghost implements Enemy {
    private EnemyMovement enemyMovement;
    private Position playerPosition;
    private Position enemyPosition;
    private EnemyType enemyType;
    private BufferedImage ghost_basic, ghost_advanced;
    private BufferedImage currentImage = null;
    private RecordUsedPlace record;

    /**
     * Constructs a Ghost enemy with specified type.
     * 
     * @param type The type of the ghost (BASIC or ADVANCED).
     */
    public Ghost(EnemyType type) {
        getPlayerPosition();
        record = RecordUsedPlace.getInstance();
        enemyPosition = new Position(0, 0);
        enemyType = type;
        getEnemyImage();
        this.enemyMovement = new EnemyMovement();

        Position potentialPosition = new Position(0, 0);
        potentialPosition.equal(playerPosition);
        do {
            potentialPosition = record.getRandomSafePosition();
        } while (record.isPlayerNearBy(8 * WindowConfig.tileSize, potentialPosition));

        this.enemyPosition.setPosition(potentialPosition);
        record.addEnemy(this);
    }

    /**
     * Moves the ghost to its next position based on the player's location.
     * If the player is nearby, the ghost moves towards the player.
     * Otherwise, it moves to a random position.
     */
    public void ghostMoveNextPosition() {
        // Get player's current position
        catchPlayer();
        getPlayerPosition();

        // Check if the player is around
        if (record.isPlayerNearBy(4 * WindowConfig.tileSize, enemyPosition)) {
            // If the player is around, move towards the player
            setToClosestPlayerPosition();
        } else {
            // If the player is not around, move to a random position
            moveToRandomPosition();
        }
        //emyMovement.moveTo(enemyPosition);
    }

    /**
     * Retrieves and updates the player's current position from the record of used
     * places.
     */
    private void getPlayerPosition() {
        RecordUsedPlace record = RecordUsedPlace.getInstance();
        playerPosition = record.getPlayerPosition();
    }

    /**
     * Sets the movement behavior for this enemy.
     *
     * @param eMovement The enemy movement behavior to be set.
     */
    public void setPlayerMovement(EnemyMovement eMovement) {
        enemyMovement = eMovement;
    }

    /**
     * Gets the current movement behavior of this enemy.
     *
     * @return The current EnemyMovement behavior.
     */

    public EnemyMovement getPlayerMovement() {
        return enemyMovement;
    }

    /**
     * Moves the enemy to the closest position towards the player, based on
     * priority.
     * If the enemy is already at the player's position, it remains there.
     */

    private void setToClosestPlayerPosition() {

        if (enemyPosition.equal(playerPosition)) {

            enemyPosition.setPosition(playerPosition);
            return;
        }
        List<Position> availableDirections = getPriorityPositions();

        Position highestPriorityAvailablePosition = null;
        for (Position position : availableDirections) {
            if (enemyMovement.isPositionAvailable(position)) {
                highestPriorityAvailablePosition = position;
                break;
            }
        }

        if (highestPriorityAvailablePosition != null) {
            enemyPosition.setPosition(highestPriorityAvailablePosition);
        } else {

        }
    }

    /**
     * Generates a list of potential positions to move to, prioritized by proximity
     * to the player.
     *
     * @return A list of prioritized positions for movement.
     */

    private List<Position> getPriorityPositions() {
        int deltaX = playerPosition.getX_axis() - enemyPosition.getX_axis();
        int deltaY = playerPosition.getY_axis() - enemyPosition.getY_axis();
        Position newPositionRight = new Position(enemyPosition.getX_axis() + WindowConfig.tileSize,
                enemyPosition.getY_axis());
        Position newPositionLeft = new Position(enemyPosition.getX_axis() - WindowConfig.tileSize,
                enemyPosition.getY_axis());
        Position newPositionUp = new Position(enemyPosition.getX_axis(),
                enemyPosition.getY_axis() + WindowConfig.tileSize);
        Position newPositionDown = new Position(enemyPosition.getX_axis(),
                enemyPosition.getY_axis() - WindowConfig.tileSize);
        List<Position> priorityList = new ArrayList<>();

        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            if (deltaX > 0) {
                if (deltaY > 0) {
                    priorityList
                            .addAll(Arrays.asList(newPositionRight, newPositionLeft, newPositionUp, newPositionDown));
                } else {
                    priorityList
                            .addAll(Arrays.asList(newPositionRight, newPositionLeft, newPositionDown, newPositionUp));
                }
            } else {
                if (deltaY > 0) {
                    priorityList
                            .addAll(Arrays.asList(newPositionLeft, newPositionRight, newPositionUp, newPositionDown));
                } else {
                    priorityList
                            .addAll(Arrays.asList(newPositionLeft, newPositionRight, newPositionDown, newPositionUp));
                }
            }
        } else {
            if (deltaY > 0) {
                if (deltaX > 0) {
                    priorityList
                            .addAll(Arrays.asList(newPositionUp, newPositionDown, newPositionRight, newPositionLeft));
                } else {
                    priorityList
                            .addAll(Arrays.asList(newPositionUp, newPositionDown, newPositionLeft, newPositionRight));
                }
            } else {
                if (deltaX > 0) {
                    priorityList
                            .addAll(Arrays.asList(newPositionDown, newPositionUp, newPositionRight, newPositionLeft));
                } else {
                    priorityList
                            .addAll(Arrays.asList(newPositionDown, newPositionUp, newPositionLeft, newPositionRight));
                }
            }
        }
        return priorityList;
    }

    private void moveToRandomPosition() {
        Random random = new Random();

        // Create a list to store the available directions
        List<Integer> availableDirections = new ArrayList<>(Arrays.asList(0, 1, 2, 3));

        while (!availableDirections.isEmpty()) {
            // Generate a random index for the available directions
            int randomIndex = random.nextInt(availableDirections.size());

            // Get the direction at the random index
            int direction = availableDirections.get(randomIndex);

            int newX = enemyPosition.getX_axis();
            int newY = enemyPosition.getY_axis();

            // Update the new position based on the selected direction
            switch (direction) {
                case 0:
                    // Move up
                    newY = newY - WindowConfig.tileSize;
                    break;
                case 1:
                    // Move down
                    newY = newY + WindowConfig.tileSize;
                    break;
                case 2:
                    // Move left
                    newX = newX - WindowConfig.tileSize;
                    break;
                case 3:
                    // Move right
                    newX = newX + WindowConfig.tileSize;
                    break;
            }
            Position newPosition = new Position(newX, newY);

            if (enemyMovement.isPositionAvailable(newPosition)) {
                enemyPosition.setX_axis(newX);
                enemyPosition.setY_axis(newY);
                break; // Exit the loop if a movable direction is found
            } else {
                availableDirections.remove(randomIndex);
            }
        }

    }

    /**
     * Loads images for different types of ghosts.
     */
    private void getEnemyImage() {
        try {
            String directory = System.getProperty("user.dir");
            ghost_basic = ImageIO.read(new File(directory + "/res/Enemy/ghost_basic.png"));
            ghost_advanced = ImageIO.read(new File(directory + "/res/Enemy/ghost_advanced.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Position getPosition() {
        return enemyPosition;
    }

    @Override
    public void catchPlayer() {
        getPlayerPosition();
        if (playerPosition.equal(enemyPosition)) {
            GameManager.getInstance().enemyCatachPlayer(movable);
        } 
    }

    @Override
    public Position getEnemyPosition() {
        return enemyPosition;
    }

    @Override
    public void setEnemyPosition(Position newPosition) {
        enemyPosition.setPosition(newPosition);
    }

    @Override
    public boolean isMovable() {
        return movable;
    }

    /**
     * Draws the ghost on the game panel.
     * 
     * @param g2 The Graphics2D object used for drawing.
     */
    @Override
    public void draw(Graphics2D g2) {
        switch (enemyType) {
            case GHOST_BASIC:
                currentImage = ghost_basic;
                break;

            default:
                currentImage = ghost_advanced;
                break;
        }
        g2.drawImage(currentImage, enemyPosition.getX_axis(), enemyPosition.getY_axis(), WindowConfig.tileSize,
            WindowConfig.tileSize, null);
    }

    @Override
    public boolean getMovable() {
        return movable;
    }

}
