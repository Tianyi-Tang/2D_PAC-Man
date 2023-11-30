
package cmpt276.group4.Enemy;

import cmpt276.group4.Position;
import cmpt276.group4.GameMap.RoomLayout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EnemyMovementTest {

    @Mock
    private RoomLayout mockRoomLayout;

    private EnemyMovement enemyMovement;
    private Position testPosition;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        enemyMovement = new EnemyMovement();
        enemyMovement.roomLayout = mockRoomLayout; // Inject mock into enemyMovement
        testPosition = new Position(10, 10);
    }

    @Test
    void isPositionAvailableTrue() {
        
        when(mockRoomLayout.isPositionAviable(testPosition)).thenReturn(true);

        boolean result = enemyMovement.isPositionAvailable(testPosition);

        assertTrue(result, "Expected position to be available");
    }

    @Test
    void isPositionAvailableFalse() {
        when(mockRoomLayout.isPositionAviable(testPosition)).thenReturn(false);

        boolean result = enemyMovement.isPositionAvailable(testPosition);

        assertFalse(result, "Expected position to be unavailable");
    }
}
