// package cmpt276.group4.Enemy;
// import static org.mockito.ArgumentMatchers.anyInt;
// import static org.mockito.Mockito.*;
// import static org.junit.jupiter.api.Assertions.*;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import cmpt276.group4.GameManager;
// import cmpt276.group4.Position;
// import cmpt276.group4.RecordUsedPlace;
// import cmpt276.group4.Player.Player;

// public class SpiderTest {


//     private Spider spider;
//     private RecordUsedPlace mockRecord;
//     private Position mockPlayerPosition;
//     private Player mockPlayer;

//     @BeforeEach
//     void setUp() {
//         mockRecord = mock(RecordUsedPlace.class);
//         RecordUsedPlace.setInstance(mockRecord);

//         mockPlayerPosition = mock(Position.class);
//         mockPlayer = mock(Player.class);
//         GameManager gameManagerIns = GameManager.getInstance();
//         gameManagerIns.setPlayerForTest(mockPlayer);
        
//         when(mockRecord.getPlayerPosition()).thenReturn(mockPlayerPosition);
//         when(mockRecord.getRandomSafePosition()).thenReturn(new Position(10, 10)); 

//         spider = new Spider();
//     }

//     @Test
//     void testCatchPlayerWhenPlayerIsCaught() {
//         // Set both player and spider at the same position
//         Position samePosition = new Position(48, 48);
//         when(mockRecord.getPlayerPosition()).thenReturn(samePosition);
//         spider.setEnemyPosition(samePosition);

//         spider.catchPlayer();
//         verify(mockPlayer).deductPoint(anyInt()); }

//     @Test
//     void testCatchPlayerWhenPlayerNotCaught() {
//         // Set player and spider at different positions
//         Position playerPosition = new Position(48, 48);
//         Position spiderPosition = new Position(10, 10);
//         when(mockRecord.getPlayerPosition()).thenReturn(playerPosition);
//         spider.setEnemyPosition(spiderPosition);

//         spider.catchPlayer();

//         verify(mockPlayer, never()).deductPoint(anyInt());
//     }

//     @Test
//     void testIsMovable() {
//         assertFalse(spider.isMovable(), "Spiders should not be movable.");
//     }

//     @Test
//     void testSpiderIsAddedToRecordUsedPlace() {
//         verify(mockRecord).addEnemy(spider);
//     }

    
// }
package cmpt276.group4.Enemy;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import GameMap.RecordUsedPlace;
import cmpt276.group4.GameManager;
import cmpt276.group4.Position;
import cmpt276.group4.Player.Player;

/**
 * Test class for Spider.
 * This class performs unit testing and integration testing for the Spider class using Mockito to isolate dependencies.
 */
public class SpiderTest {

    private Spider spider;
    private RecordUsedPlace mockRecord;
    private Position mockPlayerPosition;
    private Player mockPlayer;

    /**
     * Sets up mocks and test environment before each test.
     * Creates mock objects for RecordUsedPlace, GameManager, and Player.
     * Initializes a new Spider instance for testing.
     */
    @BeforeEach
    void setUp() {
        mockRecord = mock(RecordUsedPlace.class);
        RecordUsedPlace.setInstance(mockRecord);

        mockPlayerPosition = mock(Position.class);
        mockPlayer = mock(Player.class);
        GameManager gameManagerIns = GameManager.getInstance();
        gameManagerIns.setPlayer(mockPlayer);
        
        when(mockRecord.getPlayerPosition()).thenReturn(mockPlayerPosition);
        when(mockRecord.getRandomSafePosition()).thenReturn(new Position(10, 10)); 

        spider = new Spider();
    }

    /**
     * Tests the catchPlayer method when the player is in the same position as the spider.
     * Verifies if the correct methods are called on the mock player.
     */
    @Test
    void testCatchPlayerWhenPlayerIsCaught() {
        Position samePosition = new Position(48, 48);
        when(mockRecord.getPlayerPosition()).thenReturn(samePosition);
        spider.setEnemyPosition(samePosition);

        spider.catchPlayer();
        verify(mockPlayer).deductPoint(anyInt());
    }

    /**
     * Tests the catchPlayer method when the player and the spider are in different positions.
     * Ensures no points are deducted from the player in this scenario.
     */
    @Test
    void testCatchPlayerWhenPlayerNotCaught() {
        Position playerPosition = new Position(48, 48);
        Position spiderPosition = new Position(10, 10);
        when(mockRecord.getPlayerPosition()).thenReturn(playerPosition);
        spider.setEnemyPosition(spiderPosition);

        spider.catchPlayer();

        verify(mockPlayer, never()).deductPoint(anyInt());
    }

    /**
     * Tests the isMovable method of the Spider class.
     * Asserts that the Spider is not movable.
     */
    @Test
    void testIsMovable() {
        assertFalse(spider.isMovable(), "Spiders should not be movable.");
    }

    /**
     * Verifies if the Spider instance is correctly added to RecordUsedPlace.
     */
    @Test
    void testSpiderIsAddedToRecordUsedPlace() {
        verify(mockRecord).addEnemy(spider);
    }
}
