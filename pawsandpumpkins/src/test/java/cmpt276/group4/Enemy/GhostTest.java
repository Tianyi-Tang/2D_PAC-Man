package cmpt276.group4.Enemy;

import cmpt276.group4.GameManager;
import cmpt276.group4.Position;
import cmpt276.group4.GameMap.RecordUsedPlace;
import cmpt276.group4.GameMap.RoomEnvironment;
import cmpt276.group4.Logic.WindowConfig;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import java.util.List;

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
    private Position ghostPosition;

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
        ghostPosition = new Position(100, 100);
       
    }

    // @BeforeEach
    // void setUp() {
    //     ghostPosition = new Position(100, 100); // Initial position of ghost
    //     ghost = new Ghost(EnemyType.GHOST_BASIC);
    //     ghost.setEnemyPosition(ghostPosition);
    // }

   

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

    @Test
    void testAction_PlayerTopOfGhost() {
        Position playerPos = new Position(0, WindowConfig.tileSize);
        when(mockRoomEnvironment.getPlayerPosition()).thenReturn(playerPos);
        ghost.setEnemyPosition(new Position(0, 0));
        ghost.action();
        verify(mockRecord, times(1)).isPlayerNearBy(anyInt(), any(Position.class));
    }

     @Test
    void testAction_PlayerBottomOfGhost() {
        Position playerPos = new Position(0, 0);
        when(mockRoomEnvironment.getPlayerPosition()).thenReturn(playerPos);
        ghost.setEnemyPosition(new Position(0, WindowConfig.tileSize));
        ghost.action();
        verify(mockRecord, times(1)).isPlayerNearBy(anyInt(), any(Position.class));
    }

    @Test
    void testAction_PlayerRightOfGhost() {
        Position playerPos = new Position(WindowConfig.tileSize, 0);
        when(mockRoomEnvironment.getPlayerPosition()).thenReturn(playerPos);
        ghost.setEnemyPosition(new Position(0, 0));
        ghost.action();
        verify(mockRecord, times(1)).isPlayerNearBy(anyInt(), any(Position.class));
    }

    @Test
    void testAction_PlayerLeftOfGhost() {
        Position playerPos = new Position(0, WindowConfig.tileSize);
        when(mockRoomEnvironment.getPlayerPosition()).thenReturn(playerPos);
        ghost.setEnemyPosition(new Position(0, 0));
        ghost.action();
        verify(mockRecord, times(1)).isPlayerNearBy(anyInt(), any(Position.class));
    }

    @Test
    void testAction_PlayerLeftOfGhost1() {
        Position playerPos = new Position(2*WindowConfig.tileSize, WindowConfig.tileSize);
        when(mockRoomEnvironment.getPlayerPosition()).thenReturn(playerPos);
        ghost.setEnemyPosition(new Position(0, 0));
        ghost.action();
        verify(mockRecord, times(1)).isPlayerNearBy(anyInt(), any(Position.class));
    }

    @Test
    void testAction_PlayerLeftOfGhost2() {
        Position playerPos = new Position(2*WindowConfig.tileSize, WindowConfig.tileSize);
        when(mockRoomEnvironment.getPlayerPosition()).thenReturn(playerPos);
        ghost.setEnemyPosition(new Position(2*WindowConfig.tileSize, 0));
        ghost.action();
        verify(mockRecord, times(1)).isPlayerNearBy(anyInt(), any(Position.class));
    }

    @Test
    void testAction_PlayerLeftOfGhost3() {
        Position playerPos = new Position(2*WindowConfig.tileSize, WindowConfig.tileSize);
        when(mockRoomEnvironment.getPlayerPosition()).thenReturn(playerPos);
        ghost.setEnemyPosition(new Position(2*WindowConfig.tileSize, WindowConfig.tileSize));
        ghost.action();
        verify(mockRecord, times(1)).isPlayerNearBy(anyInt(), any(Position.class));
    }

    @Test
    void testAction_PlayerLeftOfGhost4() {
        Position playerPos = new Position(2*WindowConfig.tileSize, 10);
        when(mockRoomEnvironment.getPlayerPosition()).thenReturn(playerPos);
        ghost.setEnemyPosition(new Position(3*WindowConfig.tileSize, 0));
        ghost.action();
        verify(mockRecord, times(1)).isPlayerNearBy(anyInt(), any(Position.class));
    }

    @Test
    void testAction_PlayerLeftOfGhost5() {
        Position playerPos = new Position(2*WindowConfig.tileSize, 0);
        when(mockRoomEnvironment.getPlayerPosition()).thenReturn(playerPos);
        ghost.setEnemyPosition(new Position(3*WindowConfig.tileSize, 0));
        ghost.action();
        verify(mockRecord, times(1)).isPlayerNearBy(anyInt(), any(Position.class));
    }

    @Test
    void testAction_PlayerLeftOfGhost6() {
        Position playerPos = new Position(2*WindowConfig.tileSize, 2*WindowConfig.tileSize);
        when(mockRoomEnvironment.getPlayerPosition()).thenReturn(playerPos);
        ghost.setEnemyPosition(new Position(WindowConfig.tileSize, 8*WindowConfig.tileSize));
        ghost.action();
        verify(mockRecord, times(1)).isPlayerNearBy(anyInt(), any(Position.class));
    }




}
