package cmpt276.group4.GameMap;


import java.util.ArrayList;
import java.util.Iterator;

import cmpt276.group4.Position;
import cmpt276.group4.Enemy.Enemy;
import cmpt276.group4.Player.Player;
import cmpt276.group4.Reward.Reward;

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

    public static synchronized RoomEnvironment getInstance(){
        if(instacne == null)
            instacne = new RoomEnvironment();
        return instacne;
    }

    public static void setInstance(RoomEnvironment instacne) {
        RoomEnvironment.instacne = instacne;
    }

    private RoomEnvironment(){
        rewards = new ArrayList<Reward>();
        enemies = new ArrayList<Enemy>();
    }

    public void init(RecordUsedPlace record){
        this.record = record;
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public Position getPlayerPosition(){
        if(player != null)
            return player.getPosition();
        else
            return null;
    }

    public boolean addEnemy(Enemy enemy){
        if(placeAviableForEnemy(enemy.getEnemyPosition(), enemy.getMovable())){
            enemies.add(enemy);
            record.removeFromAviable(enemy.getPosition());
            return true;
        }
        return false;
    }

    public boolean addReward(Reward reward){
        if(record.isPlaceAviable(reward.getPosition())){
            rewards.add(reward);
            record.removeFromAviable(reward.getPosition());
            return true;
        }
        return false;
    }

    public void removeReward(Reward reward){
        iterator_reward = rewards.iterator();
        while (iterator_reward.hasNext()) {
            if(iterator_reward.next() == reward){
                iterator_reward.remove();
                record.addAviable(reward.getPosition());
            }
        }
    }

    public Reward collectReward(){
        for (Reward reward : rewards) {
            if(player.getPosition().equal(reward.getPosition()))
                return reward;
        }
        return null;
    }

    public boolean sameAsPlayerPosition(Position position){
        if(position.equal(player.getPosition()))
            return true;
        else
            return false;
    }

    /**
     * Note this function is not complenet, another function require from RecordUsedPlace
     * @param enmemyPos
     * @param movable
     * @return
     */
    private boolean placeAviableForEnemy(Position enmemyPos, boolean movable){
        if(record.isPlaceAviable(enmemyPos)){
            if(movable)
                return true;
            else{
                return record.canPlaceEnemyAndObstacle(enmemyPos);
            }
        }
        return false;
    }

    public ArrayList<Enemy> getEnemies(){
        return enemies;
    }

    public ArrayList<Reward> getRewards(){
        return rewards;
    }

    public int getEnemyNumber(){
        return enemies.size();
    }

    public int getRewardNumber(){
        return rewards.size();
    }




    
}
