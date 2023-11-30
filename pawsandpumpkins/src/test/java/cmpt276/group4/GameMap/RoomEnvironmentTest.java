package cmpt276.group4.GameMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cmpt276.group4.Position;
import cmpt276.group4.Enemy.Enemy;
import cmpt276.group4.Enemy.Ghost;
import cmpt276.group4.Enemy.Spider;
import cmpt276.group4.Logic.WindowConfig;
import cmpt276.group4.Player.Player;
import cmpt276.group4.Reward.Candy;
import cmpt276.group4.Reward.PumpkinHead;
import cmpt276.group4.Reward.Reward;

public class RoomEnvironmentTest {
    public RoomEnvironment roomEnvironment;
    public RecordUsedPlace mockrecord;
    public Player player;

    public Position availablePos;

    @BeforeEach
    public void setUp(){
        roomEnvironment = new RoomEnvironment();

        mockrecord = mock(RecordUsedPlace.class);
        roomEnvironment.init(mockrecord);
        
        availablePos = new Position(WindowConfig.tileSize *2, WindowConfig.tileSize);
        when(mockrecord.isPlaceAviable(availablePos)).thenReturn(true);

        RoomEnvironment.getInstance().init(mockrecord);
    }

    /**
     * Test roomEnvironment can success add the ghost as
     * long as ghost's position is available 
     */
    @Test
    public void addGhost(){
        Enemy ghost = addEnemy(true, availablePos);
        assertEquals(true, roomEnvironment.addEnemy(ghost));
    }

    /**
     * Test roomEnvironment will reject to add the ghost if
     * ghost's position is not available
     */
    @Test
    public void failAddGhost(){
        Ghost ghostmock = mock(Ghost.class);
        roomEnvironment.addEnemy(ghostmock);

       assertEquals(0, roomEnvironment.getEnemies().size());
    }

    /**
     * Test roomEnvironment can success add the spider, if spider's
     * position is avaliable and not affect player into some area
     */
    @Test
    public void addSpider(){
        when(mockrecord.canPlaceEnemyAndObstacle(availablePos)).thenReturn(true);
        Enemy mockSpider = addEnemy(false, availablePos);

        assertEquals(true, roomEnvironment.addEnemy(mockSpider));
    }

    /**
     * Test roomEnvironment will reject to add the ghost if ghost's 
     * position is avaliable but block player to eneter some area
     */
    @Test 
    public void failAddSpider(){
        when(mockrecord.canPlaceEnemyAndObstacle(availablePos)).thenReturn(false);
        Spider mockSpider = mock(Spider.class);
        roomEnvironment.addEnemy(mockSpider);
        assertEquals(0, roomEnvironment.getEnemyNumber());
    }

    /**
     * Test roomEnvironment can add the candy if the candy position is
     * avaliable 
     */
    @Test 
    public  void addCandy(){
        addReward(true,availablePos);
        assertEquals(1, roomEnvironment.getRewardNumber());
    }

    /**
     * Test roomEnvironment will reject add candy if candy's position is
     * not avaliable
     */
    @Test 
    public  void failAddCandy(){
        when(mockrecord.isPlaceAviable(availablePos)).thenReturn(false);
        Candy mockCandy = mock(Candy.class);
        
        assertEquals(false, roomEnvironment.addReward(mockCandy));
    }

    /**
     * Test roomEnvironment the can add the pumpkin head if 
     * the pumpkin head's position is avaliable 
     */
    @Test 
    public void addPumpkinHead(){
        ArrayList<Reward> rewards = new ArrayList<Reward>();
        rewards.add(addReward(false,availablePos));

        assertEquals(rewards.get(0), roomEnvironment.getRewards().get(0));
    }

    /**
     * Test roomEnvironment will return reward if player 
     * have same position with any reward
     */
    @Test
    public void successCollectReward(){
        Player mockplayer = setupMockPlayer();
        when(mockplayer.getPosition()).thenReturn(availablePos);
        
        Reward reward = addReward(true,availablePos);
        assertEquals(reward, roomEnvironment.collectReward());
    }

    /**
     * Test roomEnvironment will retrun null if player don't
     * have same position with any reward
     */
    @Test
    public void failCollectReward(){
        Player mockplayer = setupMockPlayer();
        when(mockplayer.getPosition()).thenReturn(new Position(0, 0));
        addReward(true,availablePos);
        assertEquals(null, roomEnvironment.collectReward());
    }

    /**
     * Test roomEnvironment will remove reward from list and will not
     * remove other reward
     */
    @Test
    public void removeReward(){
        Position position = new Position(0, 0);
        when(mockrecord.isPlaceAviable(position)).thenReturn(true);
        addReward(true, position);

        Reward reward2 = addReward(true,availablePos);
        roomEnvironment.removeReward(reward2);
        assertEquals(1, roomEnvironment.getRewardNumber());
    }

    /**
     * Test roomEnvironment will return true when the given position
     * is same as player position
     */
    @Test
    public void positionSameAsPlayer(){
        Player player = setupMockPlayer();
        when(player.getPosition()).thenReturn(availablePos);
        assertEquals(true, roomEnvironment.sameAsPlayerPosition(availablePos));
    }

    /**
     * Test roomEnvironment will false true when the given position
     * is not same as player position
     */
    @Test
    public void positionNotSameAsPlaye(){
        Position position = new Position(0, 0);
        Player player = setupMockPlayer();
        when(player.getPosition()).thenReturn(position);
        assertEquals(false, roomEnvironment.sameAsPlayerPosition(availablePos));
    }

    /**
     * Test roomEnvironment will not produce error even the it be call to check player
     * position and player are not setting
     */
    @Test
    public void playerNotExist(){
         assertEquals(false, roomEnvironment.sameAsPlayerPosition(availablePos));
    }

    /**
     * Test roomEnvironment can get player position if it has
     * player
     */
    @Test
    public void successGetPlayerPosition(){
        Player mockplayer = setupMockPlayer();
        roomEnvironment.getPlayerPosition();
        verify(mockplayer).getPosition();
    }

    /**
     * Test roomEnvironment will return null if player is not setting
     */
    @Test
    public void fialGetPlayerPosition(){
        assertEquals(null, roomEnvironment.getPlayerPosition());
    }

    /**
     * Creating and set up mock player and put it into rommEnvironment 
     * @return the mock player
     */
    private Player setupMockPlayer(){
        Player mockPlayer = mock(Player.class);
        roomEnvironment.setPlayer(mockPlayer);
        return mockPlayer;
    }

    /**
     * Create the mock ghost or spider and set retrun value for
     * specific function
     * @param ghost want to create a ghost enemy or spider enemy
     * @param position the position of enemy
     * @return the enemy object 
     */
    private Enemy addEnemy(boolean ghost, Position position){
        Enemy enemy;
        if(ghost)
            enemy = mock(Ghost.class);
        else
            enemy = mock(Spider.class);

        when(enemy.getMovable()).thenReturn(ghost);
        when(enemy.getPosition()).thenReturn(position);
        return enemy;
    }

    /**
     * Create candy or pumpkin head in given position and add them
     * into rommEnvironment
     * @param candy want to create candy or pumpkin head
     * @param position the position of reward
     * @return the reward
     */
    private Reward addReward(boolean candy, Position position){
        Reward reward;
        if(candy)
            reward = new Candy();
        else
            reward = new PumpkinHead();

        reward.setPosition(position);
        roomEnvironment.addReward(reward);
        return reward;
    }


}