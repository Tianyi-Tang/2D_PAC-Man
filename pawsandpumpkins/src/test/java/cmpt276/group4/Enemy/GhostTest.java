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

/**
 * Test class for the Ghost class in the Enemy package.
 * It includes unit tests for various functionalities of the Ghost class,
 * such as movement, position setting, and player interaction.
 */
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

    /**
     * Sets up the test environment before each test.
     * Initializes mock objects and configures their behavior.
     */
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

    /**
     * Tests the constructor of the Ghost class to ensure correct initialization.
     */
    @Test
    void testConstructor() {
        assertNotNull(ghost);
    }

    /**
     * Tests the action method of the Ghost class to verify its behavior.
     */
    @Test
    void testAction() {
        ghost.action();
        verify(mockRecord, times(1)).isPlayerNearBy(anyInt(), any(Position.class));
    }

    /**
     * Tests if the Ghost is correctly identified as movable.
     */
    @Test
    void isMovableTest() {
        assertTrue(ghost.isMovable(), "Ghost should be movable");
    }

    /**
     * Tests if the getMovable method correctly identifies the Ghost as movable.
     */
    @Test
    void getMovableTest() {
        assertTrue(ghost.getMovable(), "getMovable should return true for ghost");
    }

    /**
     * Tests setting a new position for the Ghost and verifies the update.
     */
    @Test
    void setEnemyPositionTest() {
        Position newPosition = new Position(5, 5);
        ghost.setEnemyPosition(newPosition);
        assertEquals(newPosition, ghost.getPosition(), "Enemy position should be updated");
    }

    /**
     * Tests if the getPosition method correctly returns the Ghost's position.
     */
    @Test
    void getPositionTest() {
        assertEquals(startPosition, ghost.getPosition(), "Positions should match");
    }

    /**
     * Tests the catchPlayer method when the Ghost is not catching the player.
     * Verifies that no catching action is performed.
     */
    @Test
    void catchPlayerTest_NotCatchingPlayer() {
        Position differentPosition = new Position(20, 20);
        ghost.setEnemyPosition(differentPosition);
        ghost.catchPlayer();
        verify(mockGameManager, times(0)).enemyCatachPlayer(anyBoolean());
    }

    /**
     * Tests the catchPlayer method when the Ghost catches the player.
     * Verifies that the appropriate action is taken.
     */
    @Test
    void catchPlayerTest_CatchingPlayer() {
        ghost.setEnemyPosition(playerPosition);
        ghost.catchPlayer();
        verify(mockGameManager, times(1)).enemyCatachPlayer(anyBoolean());
    }

    /**
     * Tests Ghost's behavior when the player is directly above it.
     */
    @Test
    void testGhostActionWithPlayerAbove() {
        Position playerPos = new Position(0, WindowConfig.tileSize);
        when(mockRoomEnvironment.getPlayerPosition()).thenReturn(playerPos);
        ghost.setEnemyPosition(new Position(0, 0));
        ghost.action();
        verify(mockRecord, times(1)).isPlayerNearBy(anyInt(), any(Position.class));
    }

    /**
     * Tests Ghost's behavior when the player is directly below it.
     */
    @Test
    void testGhostActionWithPlayerBelow() {
        Position playerPos = new Position(0, 0);
        when(mockRoomEnvironment.getPlayerPosition()).thenReturn(playerPos);
        ghost.setEnemyPosition(new Position(0, WindowConfig.tileSize));
        ghost.action();
        verify(mockRecord, times(1)).isPlayerNearBy(anyInt(), any(Position.class));
    }

    /**
     * Tests Ghost's behavior when the player is to the right of it.
     */
    @Test
    void testGhostActionWithPlayerToRight() {
        Position playerPos = new Position(WindowConfig.tileSize, 0);
        when(mockRoomEnvironment.getPlayerPosition()).thenReturn(playerPos);
        ghost.setEnemyPosition(new Position(0, 0));
        ghost.action();
        verify(mockRecord, times(1)).isPlayerNearBy(anyInt(), any(Position.class));
    }

    /**
     * Tests Ghost's behavior when the player is to the left of it.
     */
    @Test
    void testGhostActionWithPlayerToLeft() {
        Position playerPos = new Position(0, WindowConfig.tileSize);
        when(mockRoomEnvironment.getPlayerPosition()).thenReturn(playerPos);
        ghost.setEnemyPosition(new Position(0, 0));
        ghost.action();
        verify(mockRecord, times(1)).isPlayerNearBy(anyInt(), any(Position.class));
    }

    /**
     * Tests Ghost's behavior when the player is diagonally top-left of it.
     */
    @Test
    void testGhostActionWithPlayerDiagonalTopLeft() {
        Position playerPos = new Position(2 * WindowConfig.tileSize, WindowConfig.tileSize);
        when(mockRoomEnvironment.getPlayerPosition()).thenReturn(playerPos);
        ghost.setEnemyPosition(new Position(0, 0));
        ghost.action();
        verify(mockRecord, times(1)).isPlayerNearBy(anyInt(), any(Position.class));
    }

    /**
     * Tests Ghost's behavior when the player is diagonally top-right of it.
     */
    @Test
    void testGhostActionWithPlayerDiagonalTopRight() {
        Position playerPos = new Position(2 * WindowConfig.tileSize, WindowConfig.tileSize);
        when(mockRoomEnvironment.getPlayerPosition()).thenReturn(playerPos);
        ghost.setEnemyPosition(new Position(2 * WindowConfig.tileSize, 0));
        ghost.action();
        verify(mockRecord, times(1)).isPlayerNearBy(anyInt(), any(Position.class));
    }

    /**
     * Tests Ghost's behavior when the player is diagonally bottom-right of it.
     */
    @Test
    void testGhostActionWithPlayerDiagonalBottomRight() {
        Position playerPos = new Position(2 * WindowConfig.tileSize, WindowConfig.tileSize);
        when(mockRoomEnvironment.getPlayerPosition()).thenReturn(playerPos);
        ghost.setEnemyPosition(new Position(2 * WindowConfig.tileSize, WindowConfig.tileSize));
        ghost.action();
        verify(mockRecord, times(1)).isPlayerNearBy(anyInt(), any(Position.class));
    }

    /**
     * Tests Ghost's behavior when the player is far to the left of it.
     */
    @Test
    void testGhostActionWithPlayerFarLeft() {
        Position playerPos = new Position(2 * WindowConfig.tileSize, 10);
        when(mockRoomEnvironment.getPlayerPosition()).thenReturn(playerPos);
        ghost.setEnemyPosition(new Position(3 * WindowConfig.tileSize, 0));
        ghost.action();
        verify(mockRecord, times(1)).isPlayerNearBy(anyInt(), any(Position.class));
    }

    /**
     * Tests Ghost's behavior when the player is far to the right of it.
     */
    @Test
    void testGhostActionWithPlayerFarRight() {
        Position playerPos = new Position(2 * WindowConfig.tileSize, 0);
        when(mockRoomEnvironment.getPlayerPosition()).thenReturn(playerPos);
        ghost.setEnemyPosition(new Position(3 * WindowConfig.tileSize, 0));
        ghost.action();
        verify(mockRecord, times(1)).isPlayerNearBy(anyInt(), any(Position.class));
    }

    /**
     * Tests Ghost's behavior when the player is diagonally bottom-left of it.
     */
    @Test
    void testGhostActionWithPlayerDiagonalBottomLeft() {
        Position playerPos = new Position(2 * WindowConfig.tileSize, 2 * WindowConfig.tileSize);
        when(mockRoomEnvironment.getPlayerPosition()).thenReturn(playerPos);
        ghost.setEnemyPosition(new Position(WindowConfig.tileSize, 8 * WindowConfig.tileSize));
        ghost.action();
        verify(mockRecord, times(1)).isPlayerNearBy(anyInt(), any(Position.class));
    }

    /**
     * Tests the draw method of Ghost.
     * Verifies that the Ghost is drawn using the provided Graphics2D object.
     */
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

    /**
     * Tests the action method when there are no available directions to move.
     * Verifies that the Ghost does not move from its initial position.
     */
    @Test
    void testMoveToRandomPosition_NoAvailableDirections() {
        when(mockEnemyMovement.isPositionAvailable(any(Position.class))).thenReturn(false);
        Position initialPosition = new Position(ghost.getPosition().getX_axis(), ghost.getPosition().getY_axis());
        ghost.action();
        verify(mockEnemyMovement, atLeast(4)).isPositionAvailable(any(Position.class)); // 4 directions checked
        assertEquals(initialPosition, ghost.getPosition(), "Ghost should not move if no direction is available");
    }

   /**
 * Tests Ghost's random movement when an available direction is immediately found.
 */
@Test
void testRandomMovementWithImmediateAvailability() {
        when(mockEnemyMovement.isPositionAvailable(any(Position.class)))
            .thenReturn(true);
        Position initialPosition = new Position(ghost.getPosition().getX_axis(), ghost.getPosition().getY_axis());
        ghost.action();
        assertNotEquals(initialPosition, ghost.getPosition(), "Ghost should move if a direction becomes available");
    }

  /**
 * Tests Ghost's random movement when an available direction is found after one failed attempt.
 */
@Test
void testRandomMovementAfterOneFailure() {
        when(mockEnemyMovement.isPositionAvailable(any(Position.class)))
            .thenReturn(false) 
            .thenReturn(true);
        Position initialPosition = new Position(ghost.getPosition().getX_axis(), ghost.getPosition().getY_axis());
        ghost.action();
        assertNotEquals(initialPosition, ghost.getPosition(), "Ghost should move if a direction becomes available");
    }

   /**
 * Tests Ghost's random movement when an available direction is found after two failed attempts.
 */
@Test
void testRandomMovementAfterTwoFailures() {
        when(mockEnemyMovement.isPositionAvailable(any(Position.class)))
            .thenReturn(false) 
            .thenReturn(false)
            .thenReturn(true); 
        Position initialPosition = new Position(ghost.getPosition().getX_axis(), ghost.getPosition().getY_axis());
        ghost.action();
        assertNotEquals(initialPosition, ghost.getPosition(), "Ghost should move if a direction becomes available");
    }

   /**
 * Tests Ghost's random movement when an available direction is found after three failed attempts.
 */
@Test
void testRandomMovementAfterThreeFailures() {
        when(mockEnemyMovement.isPositionAvailable(any(Position.class)))
            .thenReturn(false)
            .thenReturn(false)
            .thenReturn(false)
            .thenReturn(true); 
        Position initialPosition = new Position(ghost.getPosition().getX_axis(), ghost.getPosition().getY_axis());
        ghost.action();
        assertNotEquals(initialPosition, ghost.getPosition(), "Ghost should move if a direction becomes available");
    }

    /**
     * Tests the Ghost's action method when the player is far away.
     * Verifies that the Ghost's movement is random under this condition.
     */
    @Test
    void testAction_WithPlayerFarAway_MovementIsRandom() {
        Position farPlayerPosition = new Position(1000, 1000);
        when(mockRoomEnvironment.getPlayerPosition()).thenReturn(farPlayerPosition);
        when(mockRecord.isPlayerNearBy(anyInt(), any(Position.class))).thenReturn(false);
        when(mockEnemyMovement.isPositionAvailable(any(Position.class))).thenReturn(true);
        ghost.action();
        assertNotEquals(startPosition, ghost.getPosition(), "Ghost should have moved");
    }

    /**
     * Tests the Ghost's action method with limited movement options.
     * Verifies that the Ghost moves only in available directions.
     */
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

        assertTrue(ghost.getPosition().getY_axis() != ghostPosition.getY_axis(),
                "Ghost should have moved downwards");
    }

    /**
     * Tests getting the player movement object from a Ghost.
     * Verifies that the returned EnemyMovement object is the same as the one set.
     */
    @Test
    void testGetPlayerMovement() {
        EnemyMovement mockEnemyMovement = mock(EnemyMovement.class);
        ghost.setPlayerMovement(mockEnemyMovement);
        EnemyMovement retrievedMovement = ghost.getPlayerMovement();
        assertSame(mockEnemyMovement, retrievedMovement,
                "The retrieved EnemyMovement should be the same as the one set");
    }

}
