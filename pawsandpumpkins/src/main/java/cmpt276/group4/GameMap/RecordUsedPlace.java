package cmpt276.group4.GameMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import cmpt276.group4.Position;
import cmpt276.group4.Logic.WindowConfig;
import cmpt276.group4.Player.Player;

/**
 * Class record the position for every resoucre in game
 */
public class RecordUsedPlace {
    // for reward to check
    private ArrayList<Position> available;
    private ArrayList<Position> walls_pos;
    private ArrayList<Position> obstacle_pos;

    public static void setInstance(RecordUsedPlace instance) {
        RecordUsedPlace.instance = instance;
    }

    private Iterator<Position> iterator_pos;
    public static RecordUsedPlace instance;

    public RecordUsedPlace() {
        available = new ArrayList<Position>();

        obstacle_pos = new ArrayList<Position>();

        walls_pos = new ArrayList<Position>();

    }

    /**
     * Get RecordUsedPlace instance
     * @return RecordUsedPlace instance
     */
    public static synchronized RecordUsedPlace getInstance() {
        if (instance == null)
            instance = new RecordUsedPlace();
        return instance;
    }

    /**
     * Get a random position from availble, if availble is null or empty
     * retrun null
     * @return a position in avaible array or null
     */
    public Position getRandomFromAvailablePosition() {
        // return a random position from variable available
        if (available == null || available.isEmpty()) {
            System.out.println("No available used place to choose from");
            return null;
        }
        Random random = new Random();

        return available.get(random.nextInt(available.size()));
    }

    
    private List<Position> getAdjacentPositions(Position p) {
        int tileSize = WindowConfig.tileSize;
        ArrayList<Position> adjacentPositions =  new ArrayList<Position>();

        int startx = p.getX_axis() - tileSize;
        int starty = p.getY_axis() - tileSize;
        int x_chang, y_change = 0;
        for(int i = 0;i < 3;i++){
            x_chang = 0;
            for (int j=0;j < 3;j++){
                if(startx + x_chang != p.getX_axis() || starty + y_change != p.getY_axis())
                    adjacentPositions.add(new Position(startx + x_chang, starty + y_change));
                x_chang += tileSize;
            }
            y_change += tileSize;
        }
        
        return adjacentPositions;

        // return Arrays.asList(
        //         new Position(p.getX_axis() - tileSize, p.getY_axis() - tileSize), // Top-left
        //         new Position(p.getX_axis(), p.getY_axis() - tileSize), // Top
        //         new Position(p.getX_axis() + tileSize, p.getY_axis() - tileSize), // Top-right
        //         new Position(p.getX_axis() - tileSize, p.getY_axis()), // Left
        //         new Position(p.getX_axis() + tileSize, p.getY_axis()), // Right
        //         new Position(p.getX_axis() - tileSize, p.getY_axis() + tileSize), // Bottom-left
        //         new Position(p.getX_axis(), p.getY_axis() + tileSize), // Bottom
        //         new Position(p.getX_axis() + tileSize, p.getY_axis() + tileSize) // Bottom-right
        // );
    }

    private static boolean containsPosition(ArrayList<Position> positions, Position position) {
        for (Position pos : positions) {
            if (pos.equal(position)) {
                return true;
            }
        }
        return false;
    }

    private boolean isPositionAObstacle(Position position) {
        return containsPosition(walls_pos, position) || containsPosition(obstacle_pos, position);
    }

    public boolean canPlaceEnemyAndObstacle(Position p) {
        List<Position> adjacentPositions = getAdjacentPositions(p);
        List<Boolean> isAdjacentObstacle = new ArrayList<>();

        // Iterate through each adjacent position and check if it's an obstacle
        for (Position adjacentPos : adjacentPositions) {
            // if(isPositionAObstacle(adjacentPos))
            //     return false;
            isAdjacentObstacle.add(isPositionAObstacle(adjacentPos));
        }
        // return true;
        
        //combinations of surrounding obstac le that will ccause problem if place enemy or obstalcce in inpt position
        boolean condition1 = (isAdjacentObstacle.get(0) && isAdjacentObstacle.get(2)) || (isAdjacentObstacle.get(5) && isAdjacentObstacle.get(7)) 
                            || (isAdjacentObstacle.get(3) && isAdjacentObstacle.get(4));

        boolean condition4 = (isAdjacentObstacle.get(0) && isAdjacentObstacle.get(5)) || (isAdjacentObstacle.get(1) && isAdjacentObstacle.get(6)) 
                            ||(isAdjacentObstacle.get(2) && isAdjacentObstacle.get(7));

        boolean condition7 = (isAdjacentObstacle.get(0) && isAdjacentObstacle.get(7)) || (isAdjacentObstacle.get(2) && isAdjacentObstacle.get(5));

        boolean condition9 = (isAdjacentObstacle.get(1) && isAdjacentObstacle.get(5)) ||  (isAdjacentObstacle.get(1) && isAdjacentObstacle.get(7));

        boolean condition11 = (isAdjacentObstacle.get(4) && isAdjacentObstacle.get(5)) || (isAdjacentObstacle.get(4) && isAdjacentObstacle.get(0));

        boolean condition13 = (isAdjacentObstacle.get(6) && isAdjacentObstacle.get(0)) || (isAdjacentObstacle.get(6) && isAdjacentObstacle.get(2));

        boolean condition15 = (isAdjacentObstacle.get(3) && isAdjacentObstacle.get(2)) || (isAdjacentObstacle.get(3) && isAdjacentObstacle.get(7));

        //can place enemy or obstacle in the input position if no above conditions are 
        return !(condition1 || condition4 || condition1 || condition7 || condition9 || condition11 || condition13 || condition15); 
    }
    
    /**
     * Add position to availble array, the function will reject position 
     * that already in the avaible array
     * @param position the position want to add to availble array
     * @return If return true, it mean the position is successful adding, else fail to add
     */
    public boolean addAviable(Position position) {
        if (isPlaceAviable(position))
            return false;
        else {
            available.add(position);
            return true;
        }
    }

    /**
     * Determines if the player is within a specified range from a given position.
     *
     * @param range The range to check around the position.
     * @param p     The position to check from.
     * @return true if the player is within the specified range, false otherwise.
     */
    public boolean isPlayerNearBy(int range, Position p) {
        Player player = Player.getInstance();
        int deltaX = Math.abs(player.getPosition().getX_axis() - p.getX_axis());
        int deltaY = Math.abs(player.getPosition().getY_axis() - p.getY_axis());
        return deltaX <= range && deltaY <= range;
    }

    /**
     * Get number of position in availble array
     * @return size of availble array
     */
    public int getLengthOfAviable() {
        return available.size();
    }

    /**
     * Get all position in avaible array
     * @return Aviable array in record
     */
    public ArrayList<Position> getAviablePosition() {
        return available;
    }

    /**
     * Add wall Position to the wall_pos array
     * @param position the position of wall
     */
    public void addWallPosition(Position position) {
        walls_pos.add(position);
    }

    /**
     * Add Obstcale position to the obstacle_pos array
     * Obstcale can be fixed enemy or tombstone
     * @param position the position of obstcale
     */
    public void addObstcalePosition(Position position) {
        obstacle_pos.add(position);
    }

    /**
     * Check whether the passing position is in availble array or not
     * @param planingPosition the position want to check whether it in available array
     * @return If true, then the position is already in availble array; if false, the 
     * position is not in available array
     */
    public boolean isPlaceAviable(Position planingPosition) {
        for (Position position : available) {
            if (position.equal(planingPosition))
                return true;
        }
        return false;
    }

    /**
     * Romve the position from available array
     * @param takePosition the position want to remove from available array
     */
    public void removeFromAviable(Position takePosition) {
        iterator_pos = available.iterator();
        Position position;
        while (iterator_pos.hasNext()) {
            position = iterator_pos.next();
            if (position.equal(takePosition)) {
                iterator_pos.remove();
                break;
            }
        }
    }

        // public void initalAllInfor() {
    //     if (GameManager.getInstance().isGameEnd()) {
    //         obstacle_pos = new ArrayList<Position>();
    //         walls_pos = new ArrayList<>();
    //     }
    // }

        // public boolean containsCandyAtPosition(Position position) {
    // for (Reward candy : rewards) {
    // if (candy.getPosition().equals(position)) {
    // // if (candy instanceof Candy && candy.getPosition().equals(position)) {
    // return true;
    // }
    // }
    // return false;
    // }

    // /**
    // * Retrieves a random position that is not currently occupied by any enemies.
    // * This method ensures that the position selected is free from enemies,
    // * providing a 'safe' spot.
    // *
    // * @return A random safe {@link Position} not occupied by enemies. Returns
    // * {@code null} if no such positions are available.
    // */
    // public Position getRandomSafePosition() {
    // ArrayList<Position> availableWithoutSpiders = new ArrayList<>(available);

    // // Iterate through available positions and remove those occupied by enemies
    // Iterator<Position> positionIterator = availableWithoutSpiders.iterator();
    // while (positionIterator.hasNext()) {
    // Position pos = positionIterator.next();
    // for (Enemy enemy : enemies) {
    // // Check if any enemy occupies the position
    // if (enemy.getEnemyPosition().equals(pos)) {
    // // Remove the position if occupied by an enemy
    // positionIterator.remove();
    // break; // No need to check other enemies for this position
    // }
    // }
    // }
    // if (availableWithoutSpiders.isEmpty()) {
    // System.out.println("No available positions without enemies");
    // return null;
    // }

    // // Return a random position from the list of available positions
    // Random random = new Random();
    // return
    // availableWithoutSpiders.get(random.nextInt(availableWithoutSpiders.size()));
    // }


    // /**
    // * Add haracterAvaliablePosition elements in the map
    // * Element can be tile, wall, obstacles
    // * @param object the element add to the room
    // * @return If ture, then the element is successful be adding; else the positon
    // is already taken by other
    // */
    // public boolean addElementToMap(CharacterAvaliablePosition object) {
    // if (isPlaceAviable(object.getPosition())) {
    // if (object.getPlayerAvaliable())
    // return true;
    // else {
    // if (object instanceof Wall){
    // walls_pos.add(object.getPosition());
    // }
    // else{
    // obstacle_pos.add(object.getPosition());
    // }
    // removeFromAviable(object.getPosition());
    // }
    // return true;
    // } else
    // return false;
    // }

    // public boolean addEnemy(Enemy enemy) {
    // if (isPlaceAviable(enemy.getEnemyPosition())) {
    // if(enemy instanceof Spider)
    // obstacle_pos.add(enemy.getPosition());
    // enemies.add(enemy);
    // removeFromAviable(enemy.getEnemyPosition());
    // return true;
    // } else {
    // System.out.println("no did not add");
    // return false;
    // }
    // }

    // public boolean addReward(Reward reward) {
    // if (isPlaceAviable(reward.getPosition())) {
    // rewards.add(reward);
    // removeFromAviable(reward.getPosition());
    // return true;
    // } else {
    // return false;
    // }
    // }

    // public void removeReward(Reward reward) {
    // available.add(reward.getPosition());
    // iterator_reward = rewards.iterator();
    // Reward rewardInList;
    // while (iterator_reward.hasNext()) {
    // rewardInList = iterator_reward.next();
    // if (rewardInList == reward)
    // iterator_reward.remove();
    // }
    // }

    // public Position getPlayerPosition() {
    // if(player != null)
    // return player.getPosition();
    // else
    // return null;
    // }

    

    // public Reward playerGetReward() {
    // for (Reward reward : rewards) {
    // if (player.getPosition().equal(reward.getPosition()))
    // return reward;
    // }
    // return null;
    // }

    // public Enemy playerMeetEnemy(){
    // for(Enemy enemy : enemies){
    // if(player.getPosition().equal(enemy.getPosition()))
    // return enemy;
    // }
    // return null;
    // }


    // public boolean isNotSpiderPosition(Position pos) {
    // for (Enemy enemy : enemies) {
    // if (enemy instanceof Spider && enemy.getEnemyPosition().equals(pos)) {
    // return false;
    // }
    // }
    // return true;
    // }

}
