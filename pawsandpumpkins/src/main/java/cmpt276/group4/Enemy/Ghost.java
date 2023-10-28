package cmpt276.group4.Enemy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import cmpt276.group4.Position;
import cmpt276.group4.RecordUsedPlace;

public class Ghost implements Enemy {
    private EnemyMovement enemyMovement;
    private Position playerPosition;
    private Position enemyPosition;
    // private boolean findPlayer;

    public Ghost() {
        this.enemyMovement = new EnemyMovement();
        // Initialize playerPosition and enemyPosition with placeholder values
        this.playerPosition = new Position(0, 0);
        this.enemyPosition = new Position(5, 5);
        // this.findPlayer = false;
    }

    public void ghostNextPosition() {
        // Get the player's position
        getPlayerPosition();

        // Check if the player is around
        if (isPlayerAround(4)) {
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
        RecordUsedPlace record = RecordUsedPlace.getInstance();
        playerPosition = record.getPlayerPosition();
    }

    private boolean isPlayerAround(int range) {
        // Calculate the absolute differences in x and y coordinates
        int deltaX = Math.abs(playerPosition.getX_axis() - enemyPosition.getX_axis());
        int deltaY = Math.abs(playerPosition.getY_axis() - enemyPosition.getY_axis());

        // Check if both differences are less than or equal to 4
        return deltaX <= range && deltaY <= range;
    }

    private void moveToClosestPlayerPosition() {

        List<Position> availableDirections = getPriorityPositions();

        Position highestPriorityAvailablePosition = null;
        for (Position position : availableDirections) {
            if (enemyMovement.isPositionAvailable(position)) {
                highestPriorityAvailablePosition = position;
                break;
            }
        }

        if (highestPriorityAvailablePosition != null) {
            System.out.println("Highest priority available position: (" + highestPriorityAvailablePosition.getX_axis()
                    + ", " + highestPriorityAvailablePosition.getY_axis() + ")");
            enemyPosition = highestPriorityAvailablePosition;
        } else {
            System.out.println("No available position found.");
        }
    }

    private List<Position> getPriorityPositions() {
        int deltaX = playerPosition.getX_axis() - enemyPosition.getX_axis();
        int deltaY = playerPosition.getY_axis() - enemyPosition.getY_axis();
        Position newPositionRight = new Position(enemyPosition.getX_axis() + 1, enemyPosition.getY_axis());
        Position newPositionLeft = new Position(enemyPosition.getX_axis() - 1, enemyPosition.getY_axis());
        Position newPositionUp = new Position(enemyPosition.getX_axis(), enemyPosition.getY_axis() + 1);
        Position newPositionDown = new Position(enemyPosition.getX_axis(), enemyPosition.getY_axis() - 1);
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
            if (enemyMovement.isPositionAvailable(newPosition)) {
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
