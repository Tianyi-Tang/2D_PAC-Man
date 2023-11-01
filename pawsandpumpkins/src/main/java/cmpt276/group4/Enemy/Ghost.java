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

import cmpt276.group4.Position;
import cmpt276.group4.RecordUsedPlace;
import cmpt276.group4.Player.PlayerMovement;
import cmpt276.group4.WindowAndInput.GamePanel;


public class Ghost implements Enemy {
    private EnemyMovement enemyMovement;


    private Position playerPosition;
    private Position enemyPosition;
    //private boolean org_State = true;
    private   
    // private boolean findPlayer;
    EnemyType enemyType;
    private  BufferedImage ghost_basic, ghost_advanced;
    private  BufferedImage currentImage = null;
    private RecordUsedPlace record;

    public Ghost(EnemyType type) {
        record = RecordUsedPlace.getInstance();
        enemyType = type;
        getEnemyImage();
        this.enemyMovement = new EnemyMovement();

        this.enemyPosition = record.getRandomFromAvailablePosition();
        //this.enemyPosition = new Position(10, 10);
        record.addEnemy(this);
    }

    public void ghostMoveNextPosition() {
        // Get player's current position
        getPlayerPosition();

        // Check if the player is around
        if (isPlayerAround(4*GamePanel.tileSize)) {
            // If the player is around, move towards the player
            moveToClosestPlayerPosition();
        } else {
            // If the player is not around, move to a random position
            moveToRandomPosition();
        }
        enemyMovement.moveTo(enemyPosition);
    }

    private void getPlayerPosition() {

        RecordUsedPlace record = RecordUsedPlace.getInstance();
        playerPosition = record.getPlayerPosition();
    }

    public void setPlayerMovement(EnemyMovement eMovement){
        enemyMovement = eMovement;
    }

    public EnemyMovement getPlayerMovement(){
        return enemyMovement;
    }


    private boolean isPlayerAround(int range) {
        int deltaX = Math.abs(playerPosition.getX_axis() - enemyPosition.getX_axis());
        int deltaY = Math.abs(playerPosition.getY_axis() - enemyPosition.getY_axis());
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
        Position newPositionRight = new Position(enemyPosition.getX_axis() + GamePanel.tileSize, enemyPosition.getY_axis());
        Position newPositionLeft = new Position(enemyPosition.getX_axis() - GamePanel.tileSize, enemyPosition.getY_axis());
        Position newPositionUp = new Position(enemyPosition.getX_axis(), enemyPosition.getY_axis() + GamePanel.tileSize);
        Position newPositionDown = new Position(enemyPosition.getX_axis(), enemyPosition.getY_axis() - GamePanel.tileSize);
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
                    newY = newY - GamePanel.tileSize;
                    break;
                case 1:
                    // Move down
                    newY = newY + GamePanel.tileSize;
                    break;
                case 2:
                    // Move left
                    newX = newX - GamePanel.tileSize;
                    break;
                case 3:
                    // Move right
                    newX = newX + GamePanel.tileSize;
                    break;
            }

            // Create a new position object for the intended movement
            Position newPosition = new Position(newX, newY);

            // Check if the new position is movable
            System.out.println("try new position newX: " + newX);
            System.out.println("newY: " + newY);

            if (enemyMovement.isPositionAvailable(newPosition)) {
                enemyPosition.setX_axis(newX);
                enemyPosition.setY_axis(newY);
                break; // Exit the loop if a movable direction is found
            } else {
                availableDirections.remove(randomIndex);
            }
        }
        System.out.println("newX: " + enemyPosition.getX_axis());
        System.out.println("newY: " + enemyPosition.getY_axis());

    }


    @Override
    public Position getPosition(){
        return enemyPosition;
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

    @Override
    public Position getEnemyPosition() {
        return enemyPosition;
    }

    @Override
    public void setEnemyPosition(Position newPosition) {
        enemyPosition = newPosition;
    }

    @Override
    public boolean isMovable() {
        return movable;
    }

    public void draw(Graphics2D g2){
        //g2.setColor(Color.white);
        //g2.fillRect(enemyPosition.getX_axis(), enemyPosition.getY_axis(), 48, 48);
        switch (enemyType) {
            case GHOST_BASIC:
                currentImage = ghost_basic;
                break;
        
            default:
                currentImage = ghost_advanced;
                break;
        }
        g2.drawImage(currentImage, enemyPosition.getX_axis(), enemyPosition.getY_axis(), GamePanel.tileSize, GamePanel.tileSize,null);
    }

    //get enemy image
    private void getEnemyImage(){
        try {
            String directory = System.getProperty("user.dir");
            ghost_basic = ImageIO.read(new File(directory +"/res/Enemy/ghost_basic.png"));
            ghost_advanced = ImageIO.read(new File(directory +"/res/Enemy/ghost_advanced.png"));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
