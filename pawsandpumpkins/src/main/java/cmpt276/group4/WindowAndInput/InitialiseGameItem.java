package cmpt276.group4.WindowAndInput;

import cmpt276.group4.GameManager;
import cmpt276.group4.Enemy.EnemyFactory;
import cmpt276.group4.Enemy.EnemyInitialization;
import cmpt276.group4.GameMap.RoomEnvironment;
import cmpt276.group4.GameMap.RoomLayout;
import cmpt276.group4.Logic.GameConfig;
import cmpt276.group4.Player.Player;
import cmpt276.group4.Player.PlayerGenerator;
import cmpt276.group4.Reward.RewardFactory;
import cmpt276.group4.Reward.RewardInitialization;
import cmpt276.group4.Room.RoomFactory;
import cmpt276.group4.Room.RoomInitialization;

public class InitialiseGameItem {
    private GameConfig config;
    private RoomInitialization roomInitialization;
    private RoomFactory roomFactory;

    public void setGameConfig(GameConfig config){
        this.config = config;
    }

    public void createRoom(){
        if(config != null){
            roomInitialization = new RoomInitialization();
            roomFactory = new RoomFactory();
            roomInitialization.initializeRoom(config.getGameLevel(), roomFactory);
            roomInitialization.iRoom(roomFactory);
        }
    }

    public void createTile(){
        if(roomFactory != null)
            roomInitialization.iTiles(roomFactory);
    }

    public void createWall(){
        if(roomFactory != null)
            roomInitialization.iWalls(roomFactory);
    }

    public void createPlayer(RoomLayout roomLayout ,RoomEnvironment roomEnvironment){
        Player player = PlayerGenerator.creatPlayer(GameManager.getInstance(), roomEnvironment,roomLayout);
        roomEnvironment.setPlayer(player);
    }


     /**
     * loading all obstacles in the game
     */
    public void  createObstacle(){
        if(roomInitialization != null)
            roomInitialization.iTombs(roomFactory);
    }

     /**
     * loading all enemies in the game
     */
    public void createEnemy(){
        new EnemyInitialization(new EnemyFactory());
    }

     /**
     * loading all rewards in the game
     */
    public void createReward(){
        RewardInitialization reward_initialization = new RewardInitialization(new RewardFactory());
        reward_initialization.generateReward();
    }

}
