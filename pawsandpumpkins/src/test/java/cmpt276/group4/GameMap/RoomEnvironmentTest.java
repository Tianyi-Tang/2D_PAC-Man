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

       // RecordUsedPlace.getInstance().addAviable(availablePos);
        RoomEnvironment.getInstance().init(mockrecord);
    }

    @Test
    public void addGhost(){
        Enemy ghost = addEnemy(true, availablePos);
        assertEquals(true, roomEnvironment.addEnemy(ghost));
    }

    @Test
    public void failAddGhost(){
        Ghost ghostmock = mock(Ghost.class);
        roomEnvironment.addEnemy(ghostmock);

       assertEquals(0, roomEnvironment.getEnemyNumber());
    }

    @Test
    public void addSpider(){
        when(mockrecord.canPlaceEnemyAndObstacle(availablePos)).thenReturn(true);
        Enemy mockSpider = addEnemy(false, availablePos);

        assertEquals(true, roomEnvironment.addEnemy(mockSpider));
    }

    @Test 
    public void failAddSpider(){
        when(mockrecord.canPlaceEnemyAndObstacle(availablePos)).thenReturn(false);
        Spider mockSpider = mock(Spider.class);
        roomEnvironment.addEnemy(mockSpider);
        assertEquals(0, roomEnvironment.getEnemyNumber());
    }

    @Test 
    public  void addCandy(){
        addReward(true);
        assertEquals(1, roomEnvironment.getRewardNumber());
    }

    @Test 
    public  void failAddCandy(){
        when(mockrecord.isPlaceAviable(availablePos)).thenReturn(false);
        Candy mockCandy = mock(Candy.class);
        
        assertEquals(false, roomEnvironment.addReward(mockCandy));
    }

    @Test 
    public void addPumpkinHead(){
        ArrayList<Reward> rewards = new ArrayList<Reward>();
        rewards.add(addReward(false));

        assertEquals(rewards.get(0), roomEnvironment.getRewards().get(0));
    }

    @Test
    public void successCollectReward(){
        Player mockplayer = setupMockPlayer();
        when(mockplayer.getPosition()).thenReturn(availablePos);
        
        Reward reward = addReward(true);
        assertEquals(reward, roomEnvironment.collectReward());
    }

    @Test
    public void failCollectReward(){
        Player mockplayer = setupMockPlayer();
        when(mockplayer.getPosition()).thenReturn(new Position(0, 0));
        addReward(true);
        assertEquals(null, roomEnvironment.collectReward());
    }

    @Test
    public void removeReward(){
        Reward reward = addReward(true);
        roomEnvironment.removeReward(reward);
        assertEquals(0, roomEnvironment.getRewardNumber());
    }

    @Test
    public void successGetPlayerPosition(){
        Player mockplayer = setupMockPlayer();
        roomEnvironment.getPlayerPosition();
        verify(mockplayer).getPosition();
    }

    @Test
    public void fialGetPlayerPosition(){
        assertEquals(null, roomEnvironment.getPlayerPosition());
    }

    private Player setupMockPlayer(){
        Player mockPlayer = mock(Player.class);
        roomEnvironment.setPlayer(mockPlayer);
        return mockPlayer;
    }


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

    private Reward addReward(boolean candy){
        Reward reward;
        if(candy)
            reward = new Candy();
        else
            reward = new PumpkinHead();

        reward.setPosition(availablePos);
        roomEnvironment.addReward(reward);
        return reward;
    }


}
