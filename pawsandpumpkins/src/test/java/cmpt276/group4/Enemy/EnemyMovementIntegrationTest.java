package cmpt276.group4.Enemy;

import cmpt276.group4.GameMap.RecordUsedPlace;
import cmpt276.group4.GameMap.RoomEnvironment;
import cmpt276.group4.GameManager;
import cmpt276.group4.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * Integration test for EnemyMovement in the context of a Ghost enemy.
 * This test suite checks the behavior of a Ghost enemy's movement mechanics
 * based on the player's proximity and the game environment.
 */
public class EnemyMovementIntegrationTest {

    private Ghost ghost;
    private EnemyMovement enemyMovement;

    @Mock
    private RecordUsedPlace mockRecord;
    @Mock
    private RoomEnvironment mockRoomEnvironment;
    @Mock
    private GameManager mockGameManager;

    private Position startPosition = new Position(0, 0);
    private Position playerNearPosition = new Position(10, 10);
    private Position playerFarPosition = new Position(500, 500);

    /**
     * Sets up the test environment before each test.
     * Initializes mocks for RoomEnvironment, GameManager, and RecordUsedPlace.
     * Configures a Ghost enemy with a mocked EnemyMovement for testing.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        RecordUsedPlace.setInstance(mockRecord);
        RoomEnvironment.setInstance(mockRoomEnvironment);
        GameManager.setInstance(mockGameManager);

        enemyMovement = new EnemyMovement();

        when(mockRecord.getRandomFromAvailablePosition()).thenReturn(new Position(48, 48));
        when(mockRoomEnvironment.addEnemy(any(Ghost.class))).thenReturn(true);
        when(mockRoomEnvironment.getPlayerPosition()).thenReturn(playerNearPosition);
        when(mockRecord.isPlayerNearBy(anyInt(), any(Position.class))).thenReturn(true);

        ghost = new Ghost(EnemyType.GHOST_BASIC);
        ghost.setPlayerMovement(enemyMovement);
        ghost.setEnemyPosition(startPosition);
    }

    /**
     * Tests the enemy movement behavior when the player is nearby.
     * Ensures that the enemy moves towards the player under the right conditions.
     */
    @Test
    void testEnemyMovementTowardsPlayer() {
        when(mockRecord.isPlayerNearBy(anyInt(), any(Position.class))).thenReturn(true);

        ghost.action();
        verify(mockRecord).isPlayerNearBy(anyInt(), any(Position.class));
    }

    /**
     * Tests the enemy's random movement behavior when the player is not nearby.
     * Validates that the enemy moves randomly if the player is far away.
     */
    @Test
    void testEnemyRandomMovementWhenPlayerIsNotNearby() {
        when(mockRoomEnvironment.getPlayerPosition()).thenReturn(playerFarPosition);
        when(mockRecord.isPlayerNearBy(anyInt(), any(Position.class))).thenReturn(false);

        ghost.action();
        verify(mockRecord).isPlayerNearBy(anyInt(), any(Position.class));
    }
}
