package cmpt276.group4.Enemy;

import cmpt276.group4.Position;
import cmpt276.group4.GameMap.RoomLayout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit test class for EnemyMovement.
 * This class tests the functionality of the EnemyMovement class, particularly
 * focusing on the availability of positions for enemy movement within a room
 * layout.
 */
class EnemyMovementTest {

    @Mock
    private RoomLayout mockRoomLayout;

    private EnemyMovement enemyMovement;
    private Position testPosition;

    /**
     * Sets up the testing environment before each test.
     * Initializes mock objects and configures an EnemyMovement instance
     * with a mocked RoomLayout for testing position availability.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        enemyMovement = new EnemyMovement();
        enemyMovement.roomLayout = mockRoomLayout; // Inject mock into enemyMovement
        testPosition = new Position(10, 10);
    }

    /**
     * Tests the isPositionAvailable method when the position is available.
     * Ensures that the method returns true when a position within the room layout
     * is marked as available.
     */
      @Test
    void isPositionAvailableTrue() {
        
        when(mockRoomLayout.isPositionAviable(testPosition)).thenReturn(true);

        boolean result = enemyMovement.isPositionAvailable(testPosition);

        assertTrue(result, "Expected position to be available");
    }

/**
     * Tests the isPositionAvailable method when the position is not available.
     * Ensures that the method returns false when a position within the room layout
     * is marked as unavailable.
     */
    @Test
    void isPositionAvailableFalse() {
        when(mockRoomLayout.isPositionAviable(testPosition)).thenReturn(false);

        boolean result = enemyMovement.isPositionAvailable(testPosition);

        assertFalse(result, "Expected position to be unavailable");
    }
}
