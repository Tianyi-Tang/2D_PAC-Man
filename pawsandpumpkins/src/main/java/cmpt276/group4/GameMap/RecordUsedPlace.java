package cmpt276.group4.GameMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import cmpt276.group4.Position;
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
     * 
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
     * 
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

    /**
     * Generates a list of adjacent positions around a given position.
     * This method calculates positions in all eight directions (top-left, top,
     * top-right,
     * left, right, bottom-left, bottom, bottom-right) relative to the provided
     * position.
     *
     * @param p The base position to calculate adjacent positions from.
     * @return A list of positions adjacent to the given position.
     */
    private List<Position> getAdjacentPositions(Position p) {
        int tileSize = 48;
        return Arrays.asList(
                createPosition(p, -tileSize, -tileSize), // Top-left
                createPosition(p, 0, -tileSize), // Top
                createPosition(p, tileSize, -tileSize), // Top-right
                createPosition(p, -tileSize, 0), // Left
                createPosition(p, tileSize, 0), // Right
                createPosition(p, -tileSize, tileSize), // Bottom-left
                createPosition(p, 0, tileSize), // Bottom
                createPosition(p, tileSize, tileSize) // Bottom-right
        );
    }

    /**
     * Creates a new position based on a base position and x/y offsets.
     *
     * @param base    The base position to which the offsets are applied.
     * @param xOffset The offset in the x-axis.
     * @param yOffset The offset in the y-axis.
     * @return A new Position object offset from the base.
     */
    private Position createPosition(Position base, int xOffset, int yOffset) {
        return new Position(base.getX_axis() + xOffset, base.getY_axis() + yOffset);
    }

    /**
     * Checks if a given position is contained within a list of positions.
     *
     * @param positions The list of positions to check against.
     * @param position  The position to be checked.
     * @return True if the position is contained in the list, otherwise false.
     */

    private boolean containsPosition(List<Position> positions, Position position) {
        return positions.stream().anyMatch(pos -> pos.equal(position));
    }

    /**
     * Determines if a given position is considered an obstacle.
     * 
     * @param position The position to check.
     * @return True if the position is an obstacle, otherwise false.
     */
    private boolean isPositionAObstacle(Position position) {
        return containsPosition(walls_pos, position) || containsPosition(obstacle_pos, position);
    }

    /**
     * Determines whether an enemy or obstacle can be placed at a given position.
     * This method checks adjacent positions to ensure that placing an enemy or
     * obstacle
     * does not cause path-blocking scenarios.
     *
     * @param p The position to check for placement feasibility.
     * @return True if an enemy or obstacle can be safely placed, otherwise false.
     */
    public boolean canPlaceEnemyAndObstacle(Position p) {
        List<Position> adjacentPositions = getAdjacentPositions(p);
        boolean[] isAdjacentObstacle = new boolean[adjacentPositions.size()];

        for (int i = 0; i < adjacentPositions.size(); i++) {
            isAdjacentObstacle[i] = isPositionAObstacle(adjacentPositions.get(i));
        }

        return !checkObstacleConditions(isAdjacentObstacle);
    }

    /**
     * Evaluates a set of boolean conditions to determine if placing an obstacle
     * would result in path-blocking.
     *
     * @param obstacles An array of boolean values representing the presence of
     *                  obstacles
     *                  in adjacent positions.
     * @return True if any condition for path-blocking is met, otherwise false.
     */
    private boolean checkObstacleConditions(boolean[] obstacles) {
        return (obstacles[0] && obstacles[2]) || (obstacles[5] && obstacles[7]) ||
                (obstacles[3] && obstacles[4]) || (obstacles[0] && obstacles[5]) ||
                (obstacles[1] && obstacles[6]) || (obstacles[2] && obstacles[7]) ||
                (obstacles[0] && obstacles[7]) || (obstacles[2] && obstacles[5]) ||
                (obstacles[1] && obstacles[5]) || (obstacles[1] && obstacles[7]) ||
                (obstacles[4] && obstacles[5]) || (obstacles[4] && obstacles[0]) ||
                (obstacles[6] && obstacles[0]) || (obstacles[6] && obstacles[2]) ||
                (obstacles[3] && obstacles[2]) || (obstacles[3] && obstacles[7]);
    }

    /**
     * Add position to availble array, the function will reject position
     * that already in the avaible array
     * 
     * @param position the position want to add to availble array
     * @return If return true, it mean the position is successful adding, else fail
     *         to add
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
     * 
     * @return size of availble array
     */
    public int getLengthOfAviable() {
        return available.size();
    }

    /**
     * Get all position in avaible array
     * 
     * @return Aviable array in record
     */
    public ArrayList<Position> getAviablePosition() {
        return available;
    }

    /**
     * Add wall Position to the wall_pos array
     * 
     * @param position the position of wall
     */
    public void addWallPosition(Position position) {
        walls_pos.add(position);
    }

    /**
     * Add Obstcale position to the obstacle_pos array
     * Obstcale can be fixed enemy or tombstone
     * 
     * @param position the position of obstcale
     */
    public void addObstcalePosition(Position position) {
        obstacle_pos.add(position);
    }

    /**
     * Check whether the passing position is in availble array or not
     * 
     * @param planingPosition the position want to check whether it in available
     *                        array
     * @return If true, then the position is already in availble array; if false,
     *         the
     *         position is not in available array
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
     * 
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
    // if (GameManager.getInstance().isGameEnd()) {
    // obstacle_pos = new ArrayList<Position>();
    // walls_pos = new ArrayList<>();
    // }
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



// package cmpt276.group4.GameMap;

// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.Iterator;
// import java.util.List;
// import java.util.Random;

// import cmpt276.group4.Position;
// import cmpt276.group4.Player.Player;
// import cmpt276.group4.Logic.WindowConfig;

// /**
//  * Class record the position for every resoucre in game
//  */
// public class RecordUsedPlace {
//     // for reward to check
//     private ArrayList<Position> available;
//     private ArrayList<Position> walls_pos;
//     private ArrayList<Position> obstacle_pos;

//     public static void setInstance(RecordUsedPlace instance) {
//         RecordUsedPlace.instance = instance;
//     }

//     private Iterator<Position> iterator_pos;
//     public static RecordUsedPlace instance;

//     public RecordUsedPlace() {
//         available = new ArrayList<Position>();

//         obstacle_pos = new ArrayList<Position>();

//         walls_pos = new ArrayList<Position>();

//     }

//     /**
//      * Get RecordUsedPlace instance
//      * 
//      * @return RecordUsedPlace instance
//      */
//     public static synchronized RecordUsedPlace getInstance() {
//         if (instance == null)
//             instance = new RecordUsedPlace();
//         return instance;
//     }

//     /**
//      * Get a random position from availble, if availble is null or empty
//      * retrun null
//      * 
//      * @return a position in avaible array or null
//      */
//     public Position getRandomFromAvailablePosition() {
//         // return a random position from variable available
//         if (available == null || available.isEmpty()) {
//             System.out.println("No available used place to choose from");
//             return null;
//         }
//         Random random = new Random();

//         return available.get(random.nextInt(available.size()));
//     }

//     /**
//      * Generates a list of adjacent positions around a given position.
//      * This method calculates positions in all eight directions (top-left, top,
//      * top-right,
//      * left, right, bottom-left, bottom, bottom-right) relative to the provided
//      * position.
//      *
//      * @param p The base position to calculate adjacent positions from.
//      * @return A list of positions adjacent to the given position.
//      */
//     private List<Position> getAdjacentPositions(Position p) {
//         int tileSize = 48;
//         return Arrays.asList(
//                 createPosition(p, -tileSize, -tileSize), // Top-left
//                 createPosition(p, 0, -tileSize), // Top
//                 createPosition(p, tileSize, -tileSize), // Top-right
//                 createPosition(p, -tileSize, 0), // Left
//                 createPosition(p, tileSize, 0), // Right
//                 createPosition(p, -tileSize, tileSize), // Bottom-left
//                 createPosition(p, 0, tileSize), // Bottom
//                 createPosition(p, tileSize, tileSize) // Bottom-right
//         );
//     }

//     /**
//      * Creates a new position based on a base position and x/y offsets.
//      *
//      * @param base    The base position to which the offsets are applied.
//      * @param xOffset The offset in the x-axis.
//      * @param yOffset The offset in the y-axis.
//      * @return A new Position object offset from the base.
//      */
//     private Position createPosition(Position base, int xOffset, int yOffset) {
//         return new Position(base.getX_axis() + xOffset, base.getY_axis() + yOffset);
//     }

//     /**
//      * Checks if a given position is contained within a list of positions.
//      *
//      * @param positions The list of positions to check against.
//      * @param position  The position to be checked.
//      * @return True if the position is contained in the list, otherwise false.
//      */

//     private boolean containsPosition(List<Position> positions, Position position) {
//         return positions.stream().anyMatch(pos -> pos.equal(position));
//     }

//     /**
//      * Determines if a given position is considered an obstacle.
//      * 
//      * @param position The position to check.
//      * @return True if the position is an obstacle, otherwise false.
//      */
//     private boolean isPositionAObstacle(Position position) {
//         return containsPosition(walls_pos, position) || containsPosition(obstacle_pos, position);
//     }

//     /**
//      * Determines whether an enemy or obstacle can be placed at a given position.
//      * This method checks adjacent positions to ensure that placing an enemy or
//      * obstacle
//      * does not cause path-blocking scenarios.
//      *
//      * @param p The position to check for placement feasibility.
//      * @return True if an enemy or obstacle can be safely placed, otherwise false.
//      */
//     public boolean canPlaceEnemyAndObstacle(Position p) {
//         List<Position> adjacentPositions = getAdjacentPositions(p);
//         boolean[] isAdjacentObstacle = new boolean[adjacentPositions.size()];

//         for (int i = 0; i < adjacentPositions.size(); i++) {
//             isAdjacentObstacle[i] = isPositionAObstacle(adjacentPositions.get(i));
//         }

//         return !checkObstacleConditions(isAdjacentObstacle);
//     }

//     /**
//      * Evaluates a set of boolean conditions to determine if placing an obstacle
//      * would result in path-blocking.
//      *
//      * @param obstacles An array of boolean values representing the presence of
//      *                  obstacles
//      *                  in adjacent positions.
//      * @return True if any condition for path-blocking is met, otherwise false.
//      */
//     private boolean checkObstacleConditions(boolean[] obstacles) {
//         return (obstacles[0] && obstacles[2]) || (obstacles[5] && obstacles[7]) ||
//                 (obstacles[3] && obstacles[4]) || (obstacles[0] && obstacles[5]) ||
//                 (obstacles[1] && obstacles[6]) || (obstacles[2] && obstacles[7]) ||
//                 (obstacles[0] && obstacles[7]) || (obstacles[2] && obstacles[5]) ||
//                 (obstacles[1] && obstacles[5]) || (obstacles[1] && obstacles[7]) ||
//                 (obstacles[4] && obstacles[5]) || (obstacles[4] && obstacles[0]) ||
//                 (obstacles[6] && obstacles[0]) || (obstacles[6] && obstacles[2]) ||
//                 (obstacles[3] && obstacles[2]) || (obstacles[3] && obstacles[7]);
//     }

//     /**
//      * Add position to availble array, the function will reject position
//      * that already in the avaible array
//      * 
//      * @param position the position want to add to availble array
//      * @return If return true, it mean the position is successful adding, else fail
//      *         to add
//      */
//     public boolean addAviable(Position position) {
//         if (isPlaceAviable(position))
//             return false;
//         else {
//             available.add(position);
//             return true;
//         }
//     }

//     /**
//      * Determines if the player is within a specified range from a given position.
//      *
//      * @param range The range to check around the position.
//      * @param p     The position to check from.
//      * @return true if the player is within the specified range, false otherwise.
//      */
//     public boolean isPlayerNearBy(int range, Position p) {
//         Player player = Player.getInstance();
//         int deltaX = Math.abs(player.getPosition().getX_axis() - p.getX_axis());
//         int deltaY = Math.abs(player.getPosition().getY_axis() - p.getY_axis());
//         return deltaX <= range && deltaY <= range;
//     }

//     /**
//      * Get number of position in availble array
//      * 
//      * @return size of availble array
//      */
//     public int getLengthOfAviable() {
//         return available.size();
//     }

//     /**
//      * Get all position in avaible array
//      * 
//      * @return Aviable array in record
//      */
//     public ArrayList<Position> getAviablePosition() {
//         return available;
//     }

//     /**
//      * Add wall Position to the wall_pos array
//      * 
//      * @param position the position of wall
//      */
//     public void addWallPosition(Position position) {
//         walls_pos.add(position);
//     }

//     /**
//      * Add Obstcale position to the obstacle_pos array
//      * Obstcale can be fixed enemy or tombstone
//      * 
//      * @param position the position of obstcale
//      */
//     public void addObstcalePosition(Position position) {
//         obstacle_pos.add(position);
//     }

//     /**
//      * Check whether the passing position is in availble array or not
//      * 
//      * @param planingPosition the position want to check whether it in available
//      *                        array
//      * @return If true, then the position is already in availble array; if false,
//      *         the
//      *         position is not in available array
//      */
//     public boolean isPlaceAviable(Position planingPosition) {
//         for (Position position : available) {
//             if (position.equal(planingPosition))
//                 return true;
//         }
//         return false;
//     }

//     /**
//      * Romve the position from available array
//      * 
//      * @param takePosition the position want to remove from available array
//      */
//     public void removeFromAviable(Position takePosition) {
//         iterator_pos = available.iterator();
//         Position position;
//         while (iterator_pos.hasNext()) {
//             position = iterator_pos.next();
//             if (position.equal(takePosition)) {
//                 iterator_pos.remove();
//                 break;
//             }
//         }
//     }

//     // public void initalAllInfor() {
//     // if (GameManager.getInstance().isGameEnd()) {
//     // obstacle_pos = new ArrayList<Position>();
//     // walls_pos = new ArrayList<>();
//     // }
//     // }

//     // public boolean containsCandyAtPosition(Position position) {
//     // for (Reward candy : rewards) {
//     // if (candy.getPosition().equals(position)) {
//     // // if (candy instanceof Candy && candy.getPosition().equals(position)) {
//     // return true;
//     // }
//     // }
//     // return false;
//     // }

//     // /**
//     // * Retrieves a random position that is not currently occupied by any enemies.
//     // * This method ensures that the position selected is free from enemies,
//     // * providing a 'safe' spot.
//     // *
//     // * @return A random safe {@link Position} not occupied by enemies. Returns
//     // * {@code null} if no such positions are available.
//     // */
//     // public Position getRandomSafePosition() {
//     // ArrayList<Position> availableWithoutSpiders = new ArrayList<>(available);

//     // // Iterate through available positions and remove those occupied by enemies
//     // Iterator<Position> positionIterator = availableWithoutSpiders.iterator();
//     // while (positionIterator.hasNext()) {
//     // Position pos = positionIterator.next();
//     // for (Enemy enemy : enemies) {
//     // // Check if any enemy occupies the position
//     // if (enemy.getEnemyPosition().equals(pos)) {
//     // // Remove the position if occupied by an enemy
//     // positionIterator.remove();
//     // break; // No need to check other enemies for this position
//     // }
//     // }
//     // }
//     // if (availableWithoutSpiders.isEmpty()) {
//     // System.out.println("No available positions without enemies");
//     // return null;
//     // }

//     // // Return a random position from the list of available positions
//     // Random random = new Random();
//     // return
//     // availableWithoutSpiders.get(random.nextInt(availableWithoutSpiders.size()));
//     // }

//     // /**
//     // * Add haracterAvaliablePosition elements in the map
//     // * Element can be tile, wall, obstacles
//     // * @param object the element add to the room
//     // * @return If ture, then the element is successful be adding; else the positon
//     // is already taken by other
//     // */
//     // public boolean addElementToMap(CharacterAvaliablePosition object) {
//     // if (isPlaceAviable(object.getPosition())) {
//     // if (object.getPlayerAvaliable())
//     // return true;
//     // else {
//     // if (object instanceof Wall){
//     // walls_pos.add(object.getPosition());
//     // }
//     // else{
//     // obstacle_pos.add(object.getPosition());
//     // }
//     // removeFromAviable(object.getPosition());
//     // }
//     // return true;
//     // } else
//     // return false;
//     // }

//     // public boolean addEnemy(Enemy enemy) {
//     // if (isPlaceAviable(enemy.getEnemyPosition())) {
//     // if(enemy instanceof Spider)
//     // obstacle_pos.add(enemy.getPosition());
//     // enemies.add(enemy);
//     // removeFromAviable(enemy.getEnemyPosition());
//     // return true;
//     // } else {
//     // System.out.println("no did not add");
//     // return false;
//     // }
//     // }

//     // public boolean addReward(Reward reward) {
//     // if (isPlaceAviable(reward.getPosition())) {
//     // rewards.add(reward);
//     // removeFromAviable(reward.getPosition());
//     // return true;
//     // } else {
//     // return false;
//     // }
//     // }

//     // public void removeReward(Reward reward) {
//     // available.add(reward.getPosition());
//     // iterator_reward = rewards.iterator();
//     // Reward rewardInList;
//     // while (iterator_reward.hasNext()) {
//     // rewardInList = iterator_reward.next();
//     // if (rewardInList == reward)
//     // iterator_reward.remove();
//     // }
//     // }

//     // public Position getPlayerPosition() {
//     // if(player != null)
//     // return player.getPosition();
//     // else
//     // return null;
//     // }

//     // public Reward playerGetReward() {
//     // for (Reward reward : rewards) {
//     // if (player.getPosition().equal(reward.getPosition()))
//     // return reward;
//     // }
//     // return null;
//     // }

//     // public Enemy playerMeetEnemy(){
//     // for(Enemy enemy : enemies){
//     // if(player.getPosition().equal(enemy.getPosition()))
//     // return enemy;
//     // }
//     // return null;
//     // }

//     // public boolean isNotSpiderPosition(Position pos) {
//     // for (Enemy enemy : enemies) {
//     // if (enemy instanceof Spider && enemy.getEnemyPosition().equals(pos)) {
//     // return false;
//     // }
//     // }
//     // return true;
//     // }

// }
