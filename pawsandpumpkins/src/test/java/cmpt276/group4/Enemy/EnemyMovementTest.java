// package cmpt276.group4.Enemy;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import cmpt276.group4.Position;
// import cmpt276.group4.GameMap.RecordUsedPlace;
// import cmpt276.group4.GameMap.RoomLayout;

// /**
//  * Test class for EnemyMovement.
//  * This class performs unit testing and integration testing for the EnemyMovement class.
//  */
// class EnemyMovementTest {

//     private EnemyMovement enemyMovement;
//     private RoomLayout mockRommLayout;
    
//     @BeforeEach
//     void setUp() {
//         mockRommLayout = mock(RoomLayout.class);
//         RecordUsedPlace.setInstance(mockRecord);

//         enemyMovement = new EnemyMovement();
//     }

//     /**
//      * Tests if the moveTo method returns true.
//      */
//     @Test
//     void testMoveTo() {
//         Position position = new Position(5, 5);
//         assertTrue(enemyMovement.moveTo(position), "moveTo should always return true.");
//     }

//     /**
//      * Tests if the isPositionAvailable method returns true for an available position.
//      */
//     @Test
//     void testIsPositionAvailableTrue() {
//         Position availablePosition = new Position(1, 1);
        
//         // Configure the mock to return true for the avai position
//         when(mockRecord.characterMovable(availablePosition)).thenReturn(true);

//         assertTrue(enemyMovement.isPositionAvailable(availablePosition), 
//                    "isPositionAvailable should return true for an available position.");
//     }

//     /**
//      * Tests if the isPositionAvailable method returns false for an unavailable position.
//      */
//     @Test
//     void testIsPositionAvailableFalse() {
//         Position unavailablePosition = new Position(2, 2);

//         // Configure the mock to return false for the unavailable position
//         when(mockRecord.characterMovable(unavailablePosition)).thenReturn(false);

//         assertFalse(enemyMovement.isPositionAvailable(unavailablePosition), 
//                     "isPositionAvailable should return false for an unavailable position.");
//     }
// }
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
