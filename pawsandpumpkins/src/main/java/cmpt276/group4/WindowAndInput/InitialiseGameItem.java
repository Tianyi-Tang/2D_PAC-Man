package cmpt276.group4.WindowAndInput;

import cmpt276.group4.GameManager;
import cmpt276.group4.Enemy.EnemyFactory;
import cmpt276.group4.Enemy.EnemyInitialization;
import cmpt276.group4.GameMap.RecordUsedPlace;
import cmpt276.group4.GameMap.RoomEnvironment;
import cmpt276.group4.GameMap.RoomLayout;
import cmpt276.group4.Logic.GameConfig;
import cmpt276.group4.Player.Player;
import cmpt276.group4.Player.PlayerGenerator;
import cmpt276.group4.Reward.RewardFactory;
import cmpt276.group4.Reward.RewardInitialization;
import cmpt276.group4.Room.RoomFactory;
import cmpt276.group4.Room.RoomInitialization;

/**
 * Class to create and initlalise all object in the game
 */
public class InitialiseGameItem {
    private GameConfig config;
    private RoomInitialization roomInitialization;
    private RoomFactory roomFactory;

    /**
     * Setting the game config to InitialiseGameItem will use
     * 
     * @param config game config that InitialiseGameItem will use
     */
    public InitialiseGameItem(GameConfig config) {
        this.config = config;
    }

    /**
     * Give the game config in InitialiseGameItem
     * 
     * @return
     */
    public GameConfig getConfig() {
        return config;
    }

    /**
     * Setting the room initialization and room factory will be used
     * 
     * @param roomInitialization room initialization will used by InitialiseGameItem
     * @param roomFactory        room factory will be used by InitialiseGameItem
     */
    public void setRoomInitialize(RoomInitialization roomInitialization, RoomFactory roomFactory) {
        this.roomInitialization = roomInitialization;
        this.roomFactory = roomFactory;
    }

    /**
     * Create the room for the game
     */
    public void createRoom() {
        if (config != null && roomFactory != null) {
            roomInitialization.initializeRoom(config.getGameLevel(), roomFactory);
            roomInitialization.iRoom(roomFactory);
        }
    }

    /**
     * Create the tile in game
     */
    public void createTile() {
        if (roomInitialization != null)
            roomInitialization.iTiles(roomFactory);
    }

    /**
     * Create the wall in game
     */
    public void createWall() {
        if (roomInitialization != null)
            roomInitialization.iWalls(roomFactory);
    }

    /**
     * Create player character and pass it to room rnvironment
     * 
     * @param roomLayout      roomLayout that player will use
     * @param roomEnvironment room environment that going to get player
     */
    public void createPlayer(RoomLayout roomLayout, RoomEnvironment roomEnvironment) {
        Player player = PlayerGenerator.creatPlayer(GameManager.getInstance(), roomEnvironment, roomLayout);
        roomEnvironment.setPlayer(player);
    }

    /**
     * loading all obstacles in the game
     */
    public void createObstacle() {
        if (roomInitialization != null)
            roomInitialization.iTombs(roomFactory);
    }

    /**
     * loading all enemies in the game
     * * @param factory factory that going to create enemy
     */
    public void createEnemy(EnemyFactory factory) {
        new EnemyInitialization(factory);
    }

    /**
     * loading all rewards in the game
     * 
     * @param factory factory that going to create reward
     */
    public void createReward(RewardFactory factory) {
        RewardInitialization reward_initialization = new RewardInitialization(factory);
        reward_initialization.generateReward();
    }

}
