package cmpt276.group4.WindowAndInput;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cmpt276.group4.CharacterAvaliablePosition;
import cmpt276.group4.Enemy.Enemy;
import cmpt276.group4.GameMap.RoomEnvironment;
import cmpt276.group4.GameMap.RoomLayout;
import cmpt276.group4.Player.Player;
import cmpt276.group4.Reward.Reward;
import cmpt276.group4.Room.Door;

public class GamePanelTest {
    public GamePanel panel;

    public RoomLayout mockRoomLayout;
    public RoomEnvironment mcokRoomEnvironment;
    public Player mockPlayer;

    public Door[] door = new Door[1];
    public ArrayList<Enemy> enemy = new ArrayList<Enemy>();
    public ArrayList<CharacterAvaliablePosition> item = new ArrayList<CharacterAvaliablePosition>();
    public ArrayList<Reward> reward = new ArrayList<Reward>();


    @BeforeEach 
    public void setUp(){
        panel = new GamePanel();
        initalArray();

        mockRoomLayout = mock(RoomLayout.class);
        mcokRoomEnvironment = mock(RoomEnvironment.class);
        mockPlayer = mock(Player.class);

        when(mockRoomLayout.getElements()).thenReturn(item);
        when(mcokRoomEnvironment.getEnemies()).thenReturn(enemy);
        when(mcokRoomEnvironment.getRewards()).thenReturn(reward);

        panel.setKeySingleton(mockRoomLayout, mcokRoomEnvironment);
        panel.setDoors(door);
    }

    @Test
    public void playerRun() throws InterruptedException{
        panel.createTimeLine();
        CountDownLatch latch = new CountDownLatch(1);
    }

    private void initalArray(){
        door[0] = mock(Door.class);
        enemy.add(mock(Enemy.class));
        item.add(mock(CharacterAvaliablePosition.class));
        reward.add(mock(Reward.class));
    }



    



}
