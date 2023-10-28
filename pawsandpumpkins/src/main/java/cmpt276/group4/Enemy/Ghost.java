package cmpt276.group4.Enemy;

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
                // Move right
                enemyPosition.setX_axis(enemyPosition.getX_axis() + 1);
            } else {
                // Move left
                enemyPosition.setX_axis(enemyPosition.getX_axis() - 1);
            }
        } else {
            if (deltaY > 0) {
                // Move down
                enemyPosition.setY_axis(enemyPosition.getY_axis() + 1);
            } else {
                // Move up
                enemyPosition.setY_axis(enemyPosition.getY_axis() - 1);
            }
        }
    }

    private void moveToRandomPosition() {
        // Create a random number generator
        Random random = new Random();

        // Generate a random direction (0: up, 1: down, 2: left, 3: right)
        int direction = random.nextInt(4);

        // Move the ghost based on the random direction
        switch (direction) {
            case 0:
                // Move up
                enemyPosition.setY_axis(enemyPosition.getY_axis() - 1);
                break;
            case 1:
                // Move down
                enemyPosition.setY_axis(enemyPosition.getY_axis() + 1);
                break;
            case 2:
                // Move left
                enemyPosition.setX_axis(enemyPosition.getX_axis() - 1);
                break;
            case 3:
                // Move right
                enemyPosition.setX_axis(enemyPosition.getX_axis() + 1);
                break;
        }
    }

    @Override
    public void catchPlayer() {
        // If the next player input moves to the enemyPosition
        getPlayerPosition();
        if (playerPosition == enemyPosition){
            System.out.println("Ghost caught the player!");
        } else {
             System.out.println("Ghost fail to catch the player!");
        }
        
    }
}
