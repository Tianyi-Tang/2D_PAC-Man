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

    
}
