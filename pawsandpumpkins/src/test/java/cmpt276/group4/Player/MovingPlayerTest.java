package cmpt276.group4.Player;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cmpt276.group4.GameManager;
import cmpt276.group4.Position;
import cmpt276.group4.GameMap.RoomEnvironment;
import cmpt276.group4.GameMap.RoomLayout;
import cmpt276.group4.Logic.WindowConfig;
import cmpt276.group4.Reward.Reward;
import cmpt276.group4.WindowAndInput.KeyInput;
import cmpt276.group4.WindowAndInput.keyboardListener;
import cmpt276.group4.WindowAndInput.KeyInput.MovingKey;

/**
 * Integration test
 */
public class MovingPlayerTest {
    public keyboardListener lister;
    public Player player;
    public KeyInput keyInput;
    public PlayerUpdate playerUpdate;

    public GameManager mockManager;
    public RoomEnvironment mockEnvironment;
    public RoomLayout mockLayout;

    @BeforeEach
    public void setUp(){
        keyInput = new KeyInput();
        player = new Player();
        lister = new keyboardListener();
        lister.addPlayer(player);
        keyInput.setup(lister);

        playerUpdate = new PlayerUpdate(player);
        mockManager = mock(GameManager.class);
        mockEnvironment = mock(RoomEnvironment.class);
        mockLayout = mock(RoomLayout.class);
        player.init(mockManager, mockEnvironment, mockLayout);
    }

    @Test
    public void pessKeyMovePlayer(){
        Position[] positions = createAvailablePositions(1,-WindowConfig.tileSize,0);

        movePlayer(MovingKey.A, true, 1);
        assertEquals(positions[0], player.getPosition());
    }

    @Test
    public void pressKeyMovePlayerFail(){
        Position playInitPosition = player.getPosition();
        Position unaviablpos = new Position(WindowConfig.tileSize, WindowConfig.tileSize * 2);
        when(mockLayout.isPositionAviable(unaviablpos)).thenReturn(false);

        movePlayer(MovingKey.S, true, 1);
        assertEquals(playInitPosition, player.getPosition());
    }

    @Test
    public void keypressing(){
        Position[] positions = createAvailablePositions(2, 0, WindowConfig.tileSize);

        movePlayer(MovingKey.S, true, 3);
        assertEquals(positions[positions.length -1], player.getPosition());
    }

    @Test
    public void keypressOne(){
        Position[] positions = createAvailablePositions(4, WindowConfig.tileSize, 0);

        movePlayer(MovingKey.D, true, 1);
        movePlayer(MovingKey.D, false, 5);
        assertEquals(positions[0], player.getPosition());
    }

    @Test
    public void playerCollectReward(){
        createAvailablePositions(4, 0, -WindowConfig.tileSize);
        
        Reward reward = mock(Reward.class);
        when(mockEnvironment.collectReward()).thenReturn(reward);
        movePlayer(MovingKey.W, true, 1);
        verify(reward).addBenefit(player);
    }


    private void movePlayer(MovingKey key,boolean press,int time){
        keyInput.inputKey(key, press);
        playerUpdate.playerUpdate(time, true);
    }

    private Position[] createAvailablePositions(int number,int x_changing, int y_change){
        int x_init = player.getPosition().getX_axis();
        int y_init = player.getPosition().getY_axis();
        Position[] positions = new Position[number];

        for(int i=1;i < number+1;i++)
            positions[i-1] = new Position(x_init + x_changing*i, y_init + y_change *i);

        setAvailable(positions);
        return positions;
    }

    private void setAvailable(Position[] positions){
        for(int i=0;i < positions.length;i++)
            when(mockLayout.isPositionAviable(positions[i])).thenReturn(true);
    }

    

}
