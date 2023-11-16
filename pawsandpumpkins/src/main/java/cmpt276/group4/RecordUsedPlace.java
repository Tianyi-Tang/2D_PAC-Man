package cmpt276.group4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import cmpt276.group4.Enemy.Enemy;
import cmpt276.group4.Enemy.Spider;
import cmpt276.group4.Player.Player;
import cmpt276.group4.Reward.Reward;
import cmpt276.group4.Room.Obstacle;
import cmpt276.group4.Room.Tile;
import cmpt276.group4.Room.Wall;

/**
 * Class record the position for every resoucre in game
 */
public class RecordUsedPlace {
    // for reward to check
    private ArrayList<CharacterAvaliablePosition> elements;//contain wall, obstcale for drawing
    private ArrayList<Position> available;

    private ArrayList<Position> characterAvaliable_pos;
    private ArrayList<Position> walls;
    private ArrayList<Position> obstacle_pos;

    private ArrayList<Enemy> enemies;
    private ArrayList<Reward> rewards;// all rewards 
    private Player player;
    private Iterator<Position> iterator_avaliablePos;
    private Iterator<Position> iterator_pos;
    private Iterator<Reward> iterator_reward;
    public static RecordUsedPlace instance;

    private int numberofTiles = 0;
    private int numberofWall = 0;
    private int numberOfObstacles = 0;

    public void printTileAndObstacleCounts() {
        int tileCount = 0;
        int obstacleCount = 0;

        for (CharacterAvaliablePosition element : elements) {
            if (element instanceof Tile) {
                tileCount++;
            } else if (element instanceof Obstacle) {
                obstacleCount++;
            }
        }

        System.out.println("Number of Tiles: " + tileCount);
        System.out.println("Number of Obstacles: " + obstacleCount);
    }

    public Position getRandomFromAvailablePosition() {

        // return a random position from variable available
        if (available == null || available.isEmpty()) {
            System.out.println("No available used place to choose from");
            return null;
        }
        Random random = new Random();

        return available.get(random.nextInt(available.size()));
    }

    public boolean containsCandyAtPosition(Position position) {
        for (Reward candy : rewards) {
            if (candy.getPosition().equals(position)) {
                // if (candy instanceof Candy && candy.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    public void initalAllInfor() {
        if (GameManager.getInstance().isGameEnd()) {
            characterAvaliable_pos = new ArrayList<Position>();
            obstacle_pos = new ArrayList<Position>();
            elements = new ArrayList<CharacterAvaliablePosition>();

            enemies = new ArrayList<Enemy>();
            rewards  = new ArrayList<Reward>();

            numberofTiles = 0;
            numberOfObstacles = 0;
            numberofWall = 0;
        }
    }

    /**
     * Retrieves a random position that is not currently occupied by any enemies.
     * This method ensures that the position selected is free from enemies,
     * providing a 'safe' spot.
     *
     * @return A random safe {@link Position} not occupied by enemies. Returns
     *         {@code null} if no such positions are available.
     */
    public Position getRandomSafePosition() {
        ArrayList<Position> availableWithoutSpiders = new ArrayList<>(available);

        // Iterate through available positions and remove those occupied by enemies
        Iterator<Position> positionIterator = availableWithoutSpiders.iterator();
        while (positionIterator.hasNext()) {
            Position pos = positionIterator.next();
            for (Enemy enemy : enemies) {
                // Check if any enemy occupies the position
                if (enemy.getEnemyPosition().equals(pos)) {
                    // Remove the position if occupied by an enemy
                    positionIterator.remove();
                    break; // No need to check other enemies for this position
                }
            }
        }
        if (availableWithoutSpiders.isEmpty()) {
            System.out.println("No available positions without enemies");
            return null;
        }

        // Return a random position from the list of available positions
        Random random = new Random();
        return availableWithoutSpiders.get(random.nextInt(availableWithoutSpiders.size()));
    }

    public RecordUsedPlace() {
        initalAvailableArray();

        characterAvaliable_pos = new ArrayList<Position>();
        obstacle_pos = new ArrayList<Position>();
        elements = new ArrayList<CharacterAvaliablePosition>();

        enemies = new ArrayList<Enemy>();
        rewards = new ArrayList<Reward>();

    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    private void initalAvailableArray() {
        available = new ArrayList<Position>();

        // add all aviable area in this function
    }

    public boolean addAviable(Position position) {
        if (isPlaceAviable(position))
            return false;
        else {
            available.add(position);
            return true;
        }
    }

    public static synchronized RecordUsedPlace getInstance() {
        if (instance == null)
            instance = new RecordUsedPlace();
        return instance;
    }

    /**
     * Add haracterAvaliablePosition elements in the map
     * Element can be tile, wall, obstacles 
     * @param object the element add to the room
     * @return If ture, then the element is successful be adding; else the positon is already taken by other
     */
    public boolean addElementToMap(CharacterAvaliablePosition object) {
        if (isPlaceAviable(object.getPosition())) {
            if (object.getPlayerAvaliable()) {
                characterAvaliable_pos.add(object.getPosition());
                numberofTiles++;
            } else {
                System.out.println("numberOfwall " + numberofWall);
                obstacle_pos.add(object.getPosition());
                removeCharaterAviable(object.getPosition());
                if (object instanceof Wall){
                    numberofWall++;
                }
                else
                    numberOfObstacles++;
            }


            elements.add(object);
            elementTakenPlace(object.getTakenPlace(), object.getPosition());
            return true;
        } else
            return false;
    }


    public boolean addEnemy(Enemy enemy) {
        if (isPlaceAviable(enemy.getEnemyPosition())) {
            enemies.add(enemy);
            elementTakenPlace(true, enemy.getEnemyPosition());
            return true;
        } else {
            System.out.println("no did not add");
            return false;
        }
    }

    public ArrayList<CharacterAvaliablePosition> getElemet() {
        return elements;
    }

    public boolean addReward(Reward reward) {
        if (isPlaceAviable(reward.getPosition())) {
            System.out.println("Adding reward at position: " + reward.getPosition());
            rewards.add(reward);
            elementTakenPlace(false, reward.getPosition());
            return true;
        } else {
            System.out.println("Failed to add reward at position: " + reward.getPosition());
            return false;
        }
    }

    public void removeReward(Reward reward) {
        available.add(reward.getPosition());
        iterator_reward = rewards.iterator();
        Reward rewardInList;
        while (iterator_reward.hasNext()) {
            rewardInList = iterator_reward.next();
            if (rewardInList == reward)
                iterator_reward.remove();
        }
    }

    public Position getPlayerPosition() {
        if(player != null)
            return player.getPosition();
        else 
            return null;
    }

    /**
     * Determines if the player is within a specified range from a given position.
     *
     * @param range The range to check around the position.
     * @param p     The position to check from.
     * @return true if the player is within the specified range, false otherwise.
     */
    public boolean isPlayerNearBy(int range, Position p) {
        int deltaX = Math.abs(getPlayerPosition().getX_axis() - p.getX_axis());
        int deltaY = Math.abs(getPlayerPosition().getY_axis() - p.getY_axis());
        return deltaX <= range && deltaY <= range;
    }

    public int getLengthOfAviable(){
        return available.size();
    }

    public int getLengthOfEnemies(){
        return enemies.size();
    }

    public int getTileNumber() {
        return numberofTiles;
    }

    public int getWallNumber() {
        return numberofWall;
    }

    public int getObstaclesNumber() {
        return numberOfObstacles;
    }

    public int getLengthOfRewards(){
        return rewards.size();
    }

    public ArrayList<Position> getAviablePosition() {
        return available;
    }

    public boolean characterMovable(Position destination) {
        for (Position position : characterAvaliable_pos) {
            if (destination.equal(position))
                return true;
        }
        return false;
    }

    public Reward playerGetReward() {
        for (Reward reward : rewards) {
            if (player.getPosition().equal(reward.getPosition()))
                return reward;
        }
        return null;
    }

    public Enemy playerMeetEnemy(){
        for(Enemy enemy : enemies){
            if(player.getPosition().equal(enemy.getPosition()))
                return enemy;
        }
        return null;
    }

    public boolean isPlaceAviable(Position planingPosition) {
        for (Position position : available) {
            if (position.equal(planingPosition))
                return true;
        }
        return false;
    }

    private void elementTakenPlace(boolean takenPlace, Position position) {
        if (takenPlace) {
            RemoveFromAviable(position);
        }
    }
    /**
     * Check if the position is be taken and remove the takne place 
     * @param obstPos
     */
    private void removeCharaterAviable(Position obstPos) {
        iterator_avaliablePos = characterAvaliable_pos.iterator();
        while (iterator_avaliablePos.hasNext()) {
            if (iterator_avaliablePos.next().equal(obstPos)) {
                iterator_avaliablePos.remove();
                break;
            }
        }
    }

    private void RemoveFromAviable(Position takePosition) {
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

    public List<Enemy> getEnemyList() {
        return enemies;
    }

    public List<Reward> getRewardList() {
        return rewards;
    }

    public boolean isNotSpiderPosition(Position pos) {
        for (Enemy enemy : enemies) {
            if (enemy instanceof Spider && enemy.getEnemyPosition().equals(pos)) {
                return false;
            }
        }
        return true;
    }

}
