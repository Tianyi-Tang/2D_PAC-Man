package cmpt276.group4.GameMap;

import java.util.ArrayList;
import java.util.Iterator;

import cmpt276.group4.Position;
import cmpt276.group4.Enemy.Enemy;
import cmpt276.group4.Player.Player;
import cmpt276.group4.Reward.Reward;
import cmpt276.group4.Logic.WindowConfig;

/**
 * Storing the enemy and rewards
 */
public class RoomEnvironment {

    private RecordUsedPlace record;
    private static RoomEnvironment instacne;
    private Player player;

    private ArrayList<Reward> rewards;
    private ArrayList<Enemy> enemies;
    private Iterator<Reward> iterator_reward;

    /**
     * Get instance object from the class
     * 
     * @return the instance object
     */
    public static synchronized RoomEnvironment getInstance() {
        if (instacne == null)
            instacne = new RoomEnvironment();
        return instacne;
    }

    /**
     * Is should't be here!!!! I will not acepet this!!
     * 
     * @param instacne
     */
    public static void setInstance(RoomEnvironment instacne) {
        RoomEnvironment.instacne = instacne;
    }

    /**
     * Constuctor for the roomEnvironment
     */
    public RoomEnvironment() {
        rewards = new ArrayList<Reward>();
        enemies = new ArrayList<Enemy>();
    }

    /**
     * Setting the recordUsePlace for roomEnvironment
     * 
     * @param record the record use place you want to roomEnvironment use
     */
    public void init(RecordUsedPlace record) {
        this.record = record;
    }

    /**
     * Setting the player to the roomEnvironment
     * 
     * @param player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Get player position from player
     * 
     * @return if player exist then return player position or return null
     */
    public Position getPlayerPosition() {
        if (player != null)
            return player.getPosition();
        else
            return null;
    }

    /**
     * Add enemy into the enemy list
     * 
     * @param enemy the enemy you want to add
     * @return if the enemy is successful adding, then return true else return false
     */
    public boolean addEnemy(Enemy enemy) {
        if (placeAviableForEnemy(enemy.getPosition(), enemy.getMovable())) {
            enemies.add(enemy);
            record.removeFromAviable(enemy.getPosition());
            return true;
        }
        return false;
    }

    /**
     * Add reward into the reward list
     * 
     * @param reward the reward you want to add
     * @return if the reward is succesfful adding, then return treu else return
     *         false
     */
    public boolean addReward(Reward reward) {
        if (record.isPlaceAviable(reward.getPosition())) {
            rewards.add(reward);
            record.removeFromAviable(reward.getPosition());
            return true;
        }
        return false;
    }

    /**
     * Remove reward that already store in room environment
     * 
     * @param reward the reward going to rewmove
     */
    public void removeReward(Reward reward) {
        iterator_reward = rewards.iterator();
        while (iterator_reward.hasNext()) {
            if (iterator_reward.next() == reward) {
                iterator_reward.remove();
                record.addAviable(reward.getPosition());
            }
        }
    }

    /**
     * Check whether there is any reward in player position
     * 
     * @return if a reward have same position with player return reward, else return
     *         null
     */
    public Reward collectReward() {
        for (Reward reward : rewards) {
            if (player.getPosition().equal(reward.getPosition()))
                return reward;
        }
        return null;
    }

    /**
     * Check whether is position is same as player position
     * 
     * @param position the position want to check
     * @return if it is same with player position, then return true, else return
     *         false
     */
    public boolean sameAsPlayerPosition(Position position) {
        if (player != null && position.equal(player.getPosition()))
            return true;
        else
            return false;
    }

    /**
     * Check wehther is position is available for enemy
     * 
     * @param enmemyPos the position of enemy
     * @param movable   the the enmy can move
     * @return if this position is available then return true, else return false
     */
    private boolean placeAviableForEnemy(Position enmemyPos, boolean movable) {
        if (record.isPlaceAviable(enmemyPos)) {
            if (movable)
                return !record.isPlayerNearBy(4 * WindowConfig.tileSize, enmemyPos);
            else {
                return record.canPlaceEnemyAndObstacle(enmemyPos);
            }
        }
        return false;
    }

    /**
     * Get all eneies that store in the room environment
     * 
     * @return ther array list that store all enemy
     */
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * Get all reward that store in the room environment
     * 
     * @return
     */
    public ArrayList<Reward> getRewards() {
        return rewards;
    }

    /**
     * Get number of enemy store in room environment
     * 
     * @return number of enemy
     */
    public int getEnemyNumber() {
        return enemies.size();
    }

    /**
     * Get number of reward store in room environment
     * 
     * @return number of reward
     */
    public int getRewardNumber() {
        return rewards.size();
    }

}
