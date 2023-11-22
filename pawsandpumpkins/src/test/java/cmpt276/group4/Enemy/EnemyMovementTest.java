package cmpt276.group4.Enemy;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cmpt276.group4.Position;
import cmpt276.group4.GameMap.RecordUsedPlace;
import cmpt276.group4.GameMap.RoomLayout;

/**
 * Test class for EnemyMovement.
 * This class performs unit testing and integration testing for the EnemyMovement class.
 */
class EnemyMovementTest {

    private EnemyMovement enemyMovement;
    private RoomLayout mockRommLayout;
    
    @BeforeEach
    void setUp() {
        mockRommLayout = mock(RoomLayout.class);
        RecordUsedPlace.setInstance(mockRecord);

        enemyMovement = new EnemyMovement();
    }

    /**
     * Tests if the moveTo method returns true.
     */
    @Test
    void testMoveTo() {
        Position position = new Position(5, 5);
        assertTrue(enemyMovement.moveTo(position), "moveTo should always return true.");
    }

    /**
     * Tests if the isPositionAvailable method returns true for an available position.
     */
    @Test
    void testIsPositionAvailableTrue() {
        Position availablePosition = new Position(1, 1);
        
        // Configure the mock to return true for the avai position
        when(mockRecord.characterMovable(availablePosition)).thenReturn(true);

        assertTrue(enemyMovement.isPositionAvailable(availablePosition), 
                   "isPositionAvailable should return true for an available position.");
    }

    /**
     * Tests if the isPositionAvailable method returns false for an unavailable position.
     */
    @Test
    void testIsPositionAvailableFalse() {
        Position unavailablePosition = new Position(2, 2);

        // Configure the mock to return false for the unavailable position
        when(mockRecord.characterMovable(unavailablePosition)).thenReturn(false);

        assertFalse(enemyMovement.isPositionAvailable(unavailablePosition), 
                    "isPositionAvailable should return false for an unavailable position.");
    }
}
