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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.*;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

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
        Position playerPos = new Position(2 * WindowConfig.tileSize, WindowConfig.tileSize);
        when(mockRoomEnvironment.getPlayerPosition()).thenReturn(playerPos);
        ghost.setEnemyPosition(new Position(0, 0));
        ghost.action();
        verify(mockRecord, times(1)).isPlayerNearBy(anyInt(), any(Position.class));
    }

    @Test
    void testAction_PlayerLeftOfGhost2() {
        Position playerPos = new Position(2 * WindowConfig.tileSize, WindowConfig.tileSize);
        when(mockRoomEnvironment.getPlayerPosition()).thenReturn(playerPos);
        ghost.setEnemyPosition(new Position(2 * WindowConfig.tileSize, 0));
        ghost.action();
        verify(mockRecord, times(1)).isPlayerNearBy(anyInt(), any(Position.class));
    }

    @Test
    void testAction_PlayerLeftOfGhost3() {
        Position playerPos = new Position(2 * WindowConfig.tileSize, WindowConfig.tileSize);
        when(mockRoomEnvironment.getPlayerPosition()).thenReturn(playerPos);
        ghost.setEnemyPosition(new Position(2 * WindowConfig.tileSize, WindowConfig.tileSize));
        ghost.action();
        verify(mockRecord, times(1)).isPlayerNearBy(anyInt(), any(Position.class));
    }

    @Test
    void testAction_PlayerLeftOfGhost4() {
        Position playerPos = new Position(2 * WindowConfig.tileSize, 10);
        when(mockRoomEnvironment.getPlayerPosition()).thenReturn(playerPos);
        ghost.setEnemyPosition(new Position(3 * WindowConfig.tileSize, 0));
        ghost.action();
        verify(mockRecord, times(1)).isPlayerNearBy(anyInt(), any(Position.class));
    }

    @Test
    void testAction_PlayerLeftOfGhost5() {
        Position playerPos = new Position(2 * WindowConfig.tileSize, 0);
        when(mockRoomEnvironment.getPlayerPosition()).thenReturn(playerPos);
        ghost.setEnemyPosition(new Position(3 * WindowConfig.tileSize, 0));
        ghost.action();
        verify(mockRecord, times(1)).isPlayerNearBy(anyInt(), any(Position.class));
    }

    @Test
    void testAction_PlayerLeftOfGhost6() {
        Position playerPos = new Position(2 * WindowConfig.tileSize, 2 * WindowConfig.tileSize);
        when(mockRoomEnvironment.getPlayerPosition()).thenReturn(playerPos);
        ghost.setEnemyPosition(new Position(WindowConfig.tileSize, 8 * WindowConfig.tileSize));
        ghost.action();
        verify(mockRecord, times(1)).isPlayerNearBy(anyInt(), any(Position.class));
    }

    @Test
    void testDrawGhostBasic() {
        Graphics2D mockGraphics = mock(Graphics2D.class);
        Ghost ghost = new Ghost(EnemyType.GHOST_BASIC);
        ghost.setEnemyPosition(new Position(10, 10));
        ghost.draw(mockGraphics);
        verify(mockGraphics).drawImage(
                isNotNull(),
                eq(10), eq(10), eq(WindowConfig.tileSize), eq(WindowConfig.tileSize), isNull());
    }

    @Test
    void testMoveToRandomPosition_NoAvailableDirections() {
        when(mockEnemyMovement.isPositionAvailable(any(Position.class))).thenReturn(false);
        Position initialPosition = new Position(ghost.getPosition().getX_axis(), ghost.getPosition().getY_axis());
        ghost.action();
        verify(mockEnemyMovement, atLeast(4)).isPositionAvailable(any(Position.class)); // 4 directions checked
        assertEquals(initialPosition, ghost.getPosition(), "Ghost should not move if no direction is available");
    }

    @Test
    void testMoveToRandomPosition_AvailableDirectionAfter0ailures() {
        when(mockEnemyMovement.isPositionAvailable(any(Position.class)))
            .thenReturn(true);
        Position initialPosition = new Position(ghost.getPosition().getX_axis(), ghost.getPosition().getY_axis());
        ghost.action();
        assertNotEquals(initialPosition, ghost.getPosition(), "Ghost should move if a direction becomes available");
    }

    @Test
    void testMoveToRandomPosition_AvailableDirectionAfter1Failures() {
        when(mockEnemyMovement.isPositionAvailable(any(Position.class)))
            .thenReturn(false) 
            .thenReturn(true);
        Position initialPosition = new Position(ghost.getPosition().getX_axis(), ghost.getPosition().getY_axis());
        ghost.action();
        assertNotEquals(initialPosition, ghost.getPosition(), "Ghost should move if a direction becomes available");
    }

    @Test
    void testMoveToRandomPosition_AvailableDirectionAfter2Failures() {
        when(mockEnemyMovement.isPositionAvailable(any(Position.class)))
            .thenReturn(false) 
            .thenReturn(false)
            .thenReturn(true); 
        Position initialPosition = new Position(ghost.getPosition().getX_axis(), ghost.getPosition().getY_axis());
        ghost.action();
        assertNotEquals(initialPosition, ghost.getPosition(), "Ghost should move if a direction becomes available");
    }

    @Test
    void testMoveToRandomPosition_AvailableDirectionAfter3Failures() {
        when(mockEnemyMovement.isPositionAvailable(any(Position.class)))
            .thenReturn(false)
            .thenReturn(false)
            .thenReturn(false)
            .thenReturn(true); 
        Position initialPosition = new Position(ghost.getPosition().getX_axis(), ghost.getPosition().getY_axis());
        ghost.action();
        assertNotEquals(initialPosition, ghost.getPosition(), "Ghost should move if a direction becomes available");
    }

    @Test
    void testAction_WithPlayerFarAway_MovementIsRandom() {
        Position farPlayerPosition = new Position(1000, 1000);
        when(mockRoomEnvironment.getPlayerPosition()).thenReturn(farPlayerPosition);
        when(mockRecord.isPlayerNearBy(anyInt(), any(Position.class))).thenReturn(false);
        when(mockEnemyMovement.isPositionAvailable(any(Position.class))).thenReturn(true);
        ghost.action();
        assertNotEquals(startPosition, ghost.getEnemyPosition(), "Ghost should have moved");
    }

    @Test
    void testAction_WithLimitedMovementOptions() {
        Position farPlayerPosition = new Position(1000, 1000); // Far away position
        when(mockRoomEnvironment.getPlayerPosition()).thenReturn(farPlayerPosition);
        when(mockRecord.isPlayerNearBy(anyInt(), any(Position.class))).thenReturn(false);

        when(mockEnemyMovement.isPositionAvailable(any(Position.class)))
                .thenAnswer(invocation -> {
                    Position proposedPosition = invocation.getArgument(0);
                    return proposedPosition.getY_axis() > ghostPosition.getY_axis();
                });

        ghost.action();

        assertTrue(ghost.getEnemyPosition().getY_axis() != ghostPosition.getY_axis(),
                "Ghost should have moved downwards");
    }

    @Test
    void testGetPlayerMovement() {
        EnemyMovement mockEnemyMovement = mock(EnemyMovement.class);
        ghost.setPlayerMovement(mockEnemyMovement);
        EnemyMovement retrievedMovement = ghost.getPlayerMovement();
        assertSame(mockEnemyMovement, retrievedMovement,
                "The retrieved EnemyMovement should be the same as the one set");
    }

}
