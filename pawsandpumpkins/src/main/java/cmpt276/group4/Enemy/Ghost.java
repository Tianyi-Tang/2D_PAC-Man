package cmpt276.group4.Enemy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import cmpt276.group4.Position;
import cmpt276.group4.Player.Player;

public class Ghost implements Enemy {
    private EnemyMovement enemyMovement;
    private Position playerPosition;
    private Position enemyPosition;
    private boolean findPlayer;

    public Ghost() {
        this.enemyMovement = new EnemyMovement();
        // Initialize playerPosition and enemyPosition with placeholder values
        this.playerPosition = new Position(0, 0);
        this.enemyPosition = new Position(5, 5);
        this.findPlayer = false;
    }

    public void ghostNextPosition() {
        // Get the player's position
        getPlayerPosition();

        // Check if the player is around
        if (isPlayerAround()) {
            // If the player is around, move towards the player
            moveToClosestPlayerPosition();
        } else {
            // If the player is not around, move to a random position
            moveToRandomPosition();
        }
        enemyMovement.moveTo(enemyPosition);
    }

    private void getPlayerPosition() {
        // Get the player's position
        Player playerInstance = Player.getInstance();
        playerPosition = playerInstance.getPosition();
    }

    private boolean isPlayerAround() {
        // Calculate the absolute differences in x and y coordinates
        int deltaX = Math.abs(playerPosition.getX_axis() - enemyPosition.getX_axis());
        int deltaY = Math.abs(playerPosition.getY_axis() - enemyPosition.getY_axis());

        // Check if both differences are less than or equal to 4
        return deltaX <= 4 && deltaY <= 4;
    }

    private void moveToClosestPlayerPosition() {
        // Calculate the differences in x and y coordinates
        int deltaX = playerPosition.getX_axis() - enemyPosition.getX_axis();
        int deltaY = playerPosition.getY_axis() - enemyPosition.getY_axis();

        // Move towards the player along the axis with the greater absolute difference
        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            if (deltaX > 0) {
                // Check if the new position is movable
                Position newPosition = new Position(enemyPosition.getX_axis() + 1, enemyPosition.getY_axis());
                if (enemyMovement.movable(newPosition)) {
                    enemyPosition.addOnX_axis(1);;
                }
            } else {
                // Check if the new position is movable
                Position newPosition = new Position(enemyPosition.getX_axis() - 1, enemyPosition.getY_axis());
                if (enemyMovement.movable(newPosition)) {
                    enemyPosition.addOnX_axis(-1);
                }
            }
        } else {
            if (deltaY > 0) {
                // Check if the new position is movable
                Position newPosition = new Position(enemyPosition.getX_axis(), enemyPosition.getY_axis() + 1);
                if (enemyMovement.movable(newPosition)) {
                    enemyPosition.addOnY_axis(1);
                }
            } else {
                // Check if the new position is movable
                Position newPosition = new Position(enemyPosition.getX_axis(), enemyPosition.getY_axis() - 1);
                if (enemyMovement.movable(newPosition)) {
                    enemyPosition.addOnY_axis(-1);
                }
            }
        }
    }

    private void moveToRandomPosition() {
        // Create a random number generator
        Random random = new Random();

        // Create a list to store the available directions
        List<Integer> availableDirections = new ArrayList<>(Arrays.asList(0, 1, 2, 3));

        while (!availableDirections.isEmpty()) {
            // Generate a random index for the available directions
            int randomIndex = random.nextInt(availableDirections.size());

            // Get the direction at the random index
            int direction = availableDirections.get(randomIndex);

            // Create variables for the new position
            int newX = enemyPosition.getX_axis();
            int newY = enemyPosition.getY_axis();

            // Update the new position based on the selected direction
            switch (direction) {
                case 0:
                    // Move up
                    newY = newY - 1;
                    break;
                case 1:
                    // Move down
                    newY = newY + 1;
                    break;
                case 2:
                    // Move left
                    newX = newX - 1;
                    break;
                case 3:
                    // Move right
                    newX = newX + 1;
                    break;
            }

            // Create a new position object for the intended movement
            Position newPosition = new Position(newX, newY);

            // Check if the new position is movable
            if (enemyMovement.movable(newPosition)) {
                // Update the enemy position
                enemyPosition.setX_axis(newX);
                enemyPosition.setY_axis(newY);
                break; // Exit the loop if a movable direction is found
            } else {
                // Remove the selected direction from the available directions
                availableDirections.remove(randomIndex);
            }
        }
    }


    @Override
    public void catchPlayer() {
        getPlayerPosition();
        if (playerPosition == enemyPosition) {
            System.out.println("Ghost caught the player!");
        } else {
            System.out.println("Ghost fail to catch the player!");
        }

    }
}
