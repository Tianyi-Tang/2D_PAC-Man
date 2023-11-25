package cmpt276.group4.Enemy;

import cmpt276.group4.GameManager;
import cmpt276.group4.Position;
import cmpt276.group4.GameMap.RecordUsedPlace;
import cmpt276.group4.GameMap.RoomEnvironment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class GhostTest {

    @Mock
    private RecordUsedPlace mockRecord;
    @Mock
    private RoomEnvironment mockRoomEnvironment;
    @Mock
    private EnemyMovement mockEnemyMovement;
    private Ghost ghost;
    private Position startPosition = new Position(0, 0);
    private Position playerPosition = new Position(10, 10);
    private GameManager mockGameManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        RecordUsedPlace.setInstance(mockRecord);
        RoomEnvironment.setInstance(mockRoomEnvironment);
        mockGameManager = mock(GameManager.class);
        GameManager.setInstance(mockGameManager);
        when(mockRecord.getRandomFromAvailablePosition()).thenReturn(new Position(0, 0));
        when(mockRoomEnvironment.addEnemy(any(Ghost.class))).thenReturn(true);
        when(mockRoomEnvironment.getPlayerPosition()).thenReturn(playerPosition);
        when(mockRecord.isPlayerNearBy(anyInt(), any(Position.class))).thenReturn(true);

        ghost = new Ghost(EnemyType.GHOST_BASIC);
        ghost.setPlayerMovement(mockEnemyMovement);
    }

    @Test
    void testConstructor() {
        assertNotNull(ghost);
    }

    @Test
    void testAction() {
        ghost.action();
        verify(mockRecord, times(1)).isPlayerNearBy(anyInt(), any(Position.class));
    }

    @Test
    void isMovableTest() {
        assertTrue(ghost.isMovable(), "Ghost should be movable");
    }

    @Test
    void getMovableTest() {
        assertTrue(ghost.getMovable(), "getMovable should return true for ghost");
    }

    @Test
    void setEnemyPositionTest() {
        Position newPosition = new Position(5, 5);
        ghost.setEnemyPosition(newPosition);
        assertEquals(newPosition, ghost.getEnemyPosition(), "Enemy position should be updated");
    }

    @Test
    void getPositionTest() {
        assertEquals(startPosition, ghost.getPosition(), "Positions should match");
    }

    @Test
    void catchPlayerTest_NotCatchingPlayer() {
        Position differentPosition = new Position(20, 20);
        ghost.setEnemyPosition(differentPosition);
        ghost.catchPlayer();
        verify(mockGameManager, times(0)).enemyCatachPlayer(anyBoolean());
    }

    @Test
    void catchPlayerTest_CatchingPlayer() {
        ghost.setEnemyPosition(playerPosition);
        ghost.catchPlayer();
        verify(mockGameManager, times(1)).enemyCatachPlayer(anyBoolean());
    }

}
