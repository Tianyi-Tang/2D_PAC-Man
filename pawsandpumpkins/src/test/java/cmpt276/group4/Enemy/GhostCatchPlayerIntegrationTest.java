package cmpt276.group4.Enemy;

import cmpt276.group4.GameManager;
import cmpt276.group4.Position;
import cmpt276.group4.GameMap.RecordUsedPlace;
import cmpt276.group4.GameMap.RoomEnvironment;
import cmpt276.group4.Player.Player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class GhostCatchPlayerIntegrationTest {

    private Ghost ghost;
    private GameManager gameManager;
    private RecordUsedPlace mockRecord;
    private RoomEnvironment mockRoomEnvironment;
   static private Player mockPlayer; 
   private Position playerPosition;

    @BeforeEach
    void setUp() {
        mockRecord = Mockito.mock(RecordUsedPlace.class);
        mockRoomEnvironment = Mockito.mock(RoomEnvironment.class);
        mockPlayer = Mockito.mock(Player.class);
        gameManager = Mockito.mock(GameManager.class);

        GameManager.setInstance(gameManager);
        gameManager.setPlayer(mockPlayer);
        RecordUsedPlace.setInstance(mockRecord);
        RoomEnvironment.setInstance(mockRoomEnvironment);

        playerPosition = new Position(10, 10);
        when(mockRoomEnvironment.getPlayerPosition()).thenReturn(playerPosition);
        when(mockRecord.getRandomFromAvailablePosition()).thenReturn(new Position(48, 48));
        when(mockRoomEnvironment.addEnemy(any(Ghost.class))).thenReturn(true);
        when(mockRecord.isPlayerNearBy(anyInt(), any(Position.class))).thenReturn(true);

        ghost = new Ghost(EnemyType.GHOST_BASIC);
    }

    @Test
    void testGhostCatchesPlayer() {
        ghost.setEnemyPosition(playerPosition);

        ghost.action();

        verify(gameManager).enemyCatachPlayer(true);

        verify(mockPlayer, times(0)).deductPoint(anyInt());
    }
}
