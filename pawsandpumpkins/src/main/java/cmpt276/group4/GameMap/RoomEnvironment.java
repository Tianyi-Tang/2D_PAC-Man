package cmpt276.group4.GameMap;


import java.util.ArrayList;

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

    public boolean addEnemy(Enemy enemy){
        if(enemy.isMovable()){
            return placeMoveEnemy(enemy);
        }
        else{
            return placeFixedEnemy(enemy);
        }
    }

    private boolean placeMoveEnemy(Enemy enemy){
        if(record.isPlaceAviable(enemy.getPosition())){
            enemies.add(enemy);
            return true;
        }
        return false;
            
    }

    /**
     * Note: this function is not compeleted wait another function in RecordUsedPlace
     * @param enemy
     * @return
     */
    private boolean placeFixedEnemy(Enemy enemy){
        if(record.isPlaceAviable(enemy.getPosition())){
            enemies.add(enemy);
            return true;
        }
        return false;
    }

    
}
