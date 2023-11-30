package cmpt276.group4.WindowAndInput;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cmpt276.group4.gameLevel;
import cmpt276.group4.Enemy.EnemyFactory;
import cmpt276.group4.Enemy.EnemyType;
import cmpt276.group4.GameMap.RoomEnvironment;
import cmpt276.group4.GameMap.RoomLayout;
import cmpt276.group4.Logic.GameConfig;
import cmpt276.group4.Player.Player;
import cmpt276.group4.Reward.RewardFactory;
import cmpt276.group4.Reward.RewardType;
import cmpt276.group4.Room.RoomFactory;
import cmpt276.group4.Room.RoomInitialization;

/**
 * Class for InitialiseGameItem unit test
 */
public class InitialiseGameItemTest {
    public InitialiseGameItem initialiseGameItem;
    public gameLevel level = gameLevel.BASIC;
    public RoomInitialization mockRoomInitialization;
    public RoomFactory mockRoomFactory;
    public GameConfig config;

    /**
     * Set up the InitialiseGameItem that will use in testing
     */
    @BeforeEach
    public void setUp(){
        config = GameConfig.getGameConfigInstance();
        config.passGameLevel(level);

        mockRoomInitialization = mock(RoomInitialization.class);
        mockRoomFactory = mock(RoomFactory.class);
        initialiseGameItem = new InitialiseGameItem(config);
        initialiseGameItem.setRoomInitialize(mockRoomInitialization, mockRoomFactory);
    }

    /**
     * Test initialiseGameItem can create the room if it has config and mockRoomInitialization
     */
    @Test
    public void createRoom(){
        initialiseGameItem.createRoom();
        verify(mockRoomInitialization).iRoom(mockRoomFactory);
    }

    /**
     * Test initialiseGameItem will not create room if when missing the room initialization
     */
    @Test
    public void failCreateRoom(){
        initialiseGameItem.setRoomInitialize(null, mockRoomFactory);
        initialiseGameItem.createRoom();
        verify(mockRoomInitialization, never()).initializeRoom(level, mockRoomFactory);
    }

    /**
     * Test initialiseGameItem will not create room when is missing the game config
     */
    @Test
    public void failCreateConfig(){
        InitialiseGameItem otherInitialise =  new InitialiseGameItem(null);
        otherInitialise.setRoomInitialize(mockRoomInitialization, mockRoomFactory);
        otherInitialise.createRoom();
        verify(mockRoomInitialization, never()).iRoom(mockRoomFactory);
    }


    /**
     * Test initialiseGameIte will creat the tile if the room initialization exist
     */
    @Test
    public void createTile(){
        initialiseGameItem.createTile();
        verify(mockRoomInitialization).iTiles(mockRoomFactory);
    }

    /**
     * Test initialiseGameIte will not creat tile when the room initialization
     * not exist
     */
    @Test
    public void failCreateTile(){
        initialiseGameItem.setRoomInitialize(null, mockRoomFactory);
        initialiseGameItem.createTile();
        verify(mockRoomInitialization, never()).iTiles(mockRoomFactory);
    }

    /**
     * Test initialiseGameIte will creat wall if the room initialization exist
     */
    @Test
    public void createWall(){
        initialiseGameItem.createWall();
        verify(mockRoomInitialization).iWalls(mockRoomFactory);
    }

    /**
     * Test initialiseGameIte will not creat wall when room initialization not exist
     */
    @Test
    public void failCreateWall(){
        initialiseGameItem.setRoomInitialize(null, mockRoomFactory);
        initialiseGameItem.createWall();
        verify(mockRoomInitialization, never()).iWalls(mockRoomFactory);
    }

    /**
     * Test initialiseGameIte will create the player and pass to RoomEnvironment
     */
    @Test
    public void createPlayer(){
        RoomEnvironment mockRoomEnvironment = mock(RoomEnvironment.class);
        initialiseGameItem.createPlayer(RoomLayout.getInstance(), mockRoomEnvironment);
        verify(mockRoomEnvironment).setPlayer(Player.getInstance());
    }

    /**
     * Test initialiseGameIte will create obstacle if the room initialization
     */
    @Test
    public void createObstacle(){
        initialiseGameItem.createObstacle();
        verify(mockRoomInitialization).iTombs(mockRoomFactory);
    }

     /**
     * Test initialiseGameIte will not create obstacle when the room initialization
     * not exist
     */
    @Test
    public void failCreateObstacle(){
        initialiseGameItem.setRoomInitialize(null, null);
        initialiseGameItem.createObstacle();
        verify(mockRoomInitialization, never()).iTombs(mockRoomFactory);
    }

    /**
     * Test initialiseGameIte will creat the enemy 
     */
    @Test
    public void createEnemy(){
        EnemyFactory factory = mock(EnemyFactory.class);
        initialiseGameItem.createEnemy(factory);
        verify(factory).createEnemies(EnemyType.SPIDER, config.getNumberOfSpiders());
    }

    /**
     * Test initialiseGameIte will creat the reward
     */
    @Test
    public void createReward(){
        RewardFactory factory = mock(RewardFactory.class);
        initialiseGameItem.createReward(factory);
        verify(factory).createReward(RewardType.Candy, config.getNumberOfRegularRewards());
    }


}
