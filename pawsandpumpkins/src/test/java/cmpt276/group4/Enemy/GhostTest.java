// package cmpt276.group4.Enemy;

// import static org.mockito.Mockito.*;
// import static org.junit.jupiter.api.Assertions.*;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import cmpt276.group4.GameManager;
// import cmpt276.group4.Position;
// import cmpt276.group4.GameMap.RecordUsedPlace;
// import cmpt276.group4.Player.Player;

// /**
//  * Test class for Ghost.
//  * This class contains unit tests and integration tests for the Ghost class using Mockito to mock dependencies.
//  */
// class GhostTest {

//     private Ghost ghost;
//     private RecordUsedPlace mockRecord;
//     private GameManager mockGameManager;
//     private GameManager gameManagerIns;
//     private GameManager gameManagerSpy;
//     private Player mockPlayer;

//     /**
//      * Sets up the test environment before each test.
//      * Initializes mocks for RecordUsedPlace, GameManager, and Player.
//      * Creates a new instance of Ghost for testing.
//      */
//     @BeforeEach
//     void setUp() {
//         mockRecord = mock(RecordUsedPlace.class);
//         RecordUsedPlace.setInstance(mockRecord);
//         mockPlayer = mock(Player.class);
//         mockGameManager = mock(GameManager.class);
//         mockGameManager.setPlayer(mockPlayer);
//         gameManagerIns = GameManager.getInstance();
//         gameManagerIns.setPlayer(mockPlayer);

//         when(mockRecord.getRandomSafePosition()).thenReturn(new Position(10, 10)); // Example safe position
//         when(mockRecord.getPlayerPosition()).thenReturn(new Position(10, 10)); // Player's position

//         ghost = new Ghost(EnemyType.GHOST_BASIC);
//     }
    
//     /**
//      * Tests the scenario where the player is caught by the ghost.
//      * Verifies if the correct methods are called on the mock player.
//      */
//     @Test
//     void testCatchPlayerWhenPlayerIsCaught() {
//         Position samePosition = new Position(48, 48);
//         when(mockRecord.getPlayerPosition()).thenReturn(samePosition);
//         ghost.setEnemyPosition(samePosition);

//         ghost.catchPlayer();

//         // verify(mockPlayer).getCollectScore();
//         // verify(mockPlayer).getGeneralRewardNum();
//         // verify(mockPlayer).getBonusRewardNum();
//         // verify(mockPlayer).getDeductScore();
//         // verify(mockPlayer).totalScore();
//     }

//     /**
//      * Tests the isMovable method of the Ghost class.
//      * Asserts that the Ghost is movable.
//      */
//     @Test
//     void testIsMovable() {
//         assertTrue(ghost.isMovable(), "Ghost should be movable.");
//     }

//     /**
//      * Verifies if the Ghost instance is correctly added to RecordUsedPlace.
//      */
//     @Test
//     void testGhostIsAddedToRecordUsedPlace() {
//         verify(mockRecord).addEnemy(ghost);
//     }
// }
