package cmpt276.group4.GameMap;


import java.util.ArrayList;

import cmpt276.group4.Position;
import cmpt276.group4.Enemy.Enemy;
import cmpt276.group4.Reward.Reward;

public class RoomEnvironment {
    private RecordUsedPlace record;
    private static RoomEnvironment instacne;

    private ArrayList<Reward> rewards;
    private ArrayList<Enemy> enemies;

    public static synchronized RoomEnvironment getInstance(){
        if(instacne == null)
            instacne = new RoomEnvironment();
        return instacne;
    }

    public RoomEnvironment(){
        rewards = new ArrayList<Reward>();
        enemies = new ArrayList<Enemy>();
        record = RecordUsedPlace.getInstance();
    }

    public boolean addEnemy(Enemy enemy){
        if(placeAviableForEnemy(enemy.getEnemyPosition(), enemy.getMovable())){
            enemies.add(enemy);
            record.removeFromAviable(enemy.getPosition());
            return true;
        }
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
                // another function neeed to for if statement
                return true;
            }
        }
        return false;
    }

    public ArrayList<Enemy> getEnemies(){
        return enemies;
    }

    public int getEnemyNumber(){
        return enemies.size();
    }

    
}
