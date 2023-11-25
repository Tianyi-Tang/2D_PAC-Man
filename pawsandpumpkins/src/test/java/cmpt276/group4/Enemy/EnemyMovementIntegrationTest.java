package cmpt276.group4.Enemy;

import cmpt276.group4.GameMap.RecordUsedPlace;
import cmpt276.group4.GameMap.RoomEnvironment;
import cmpt276.group4.GameManager;
import cmpt276.group4.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

// Assuming this test is placed in the same package as the classes it tests
public class EnemyMovementIntegrationTest {

    private Ghost ghost;
    private RecordUsedPlace recordUsedPlace;
    private EnemyMovement enemyMovement;
    private RoomEnvironment roomEnvironment;

    @Mock
    private RecordUsedPlace mockRecord;
    @Mock
    private RoomEnvironment mockRoomEnvironment;
    @Mock
    private EnemyMovement mockEnemyMovement;
    // private Ghost ghost;
    private Position startPosition = new Position(0, 0);
    private Position playerPosition = new Position(10, 10);
    private GameManager mockGameManager;

    @BeforeEach
    void setUp() {
        // Mock dependencies

         MockitoAnnotations.openMocks(this);
        RecordUsedPlace.setInstance(mockRecord);
        RoomEnvironment.setInstance(mockRoomEnvironment);
        mockGameManager = mock(GameManager.class);
        GameManager.setInstance(mockGameManager);
        when(mockRecord.getRandomFromAvailablePosition()).thenReturn(new Position(0, 0));
        when(mockRoomEnvironment.addEnemy(any(Ghost.class))).thenReturn(true);
        when(mockRoomEnvironment.getPlayerPosition()).thenReturn(playerPosition);
        when(mockRecord.isPlayerNearBy(anyInt(), any(Position.class))).thenReturn(true);
        //when(mockPlayerPosition.equal(any(Position.class))).thenReturn(true);


        recordUsedPlace = Mockito.mock(RecordUsedPlace.class);
        roomEnvironment = Mockito.mock(RoomEnvironment.class);

        // Use real methods for the key class being tested
        ghost = new Ghost(EnemyType.GHOST_BASIC);
        enemyMovement = new EnemyMovement();

        // Inject mocks
        ghost.setPlayerMovement(enemyMovement);
        RecordUsedPlace.setInstance(recordUsedPlace);
    }

    @Test
    void testEnemyMovementTowardsPlayer() {
        // Arrange
        Position playerPosition = new Position(10, 10);
        Position initialGhostPosition = new Position(0, 0);
        ghost.setEnemyPosition(initialGhostPosition);

        // Assume player is nearby
        when(recordUsedPlace.isPlayerNearBy(anyInt(), any(Position.class))).thenReturn(true);
        when(roomEnvironment.getPlayerPosition()).thenReturn(playerPosition);

        // Act
        ghost.action();

        // Assert - Check that the ghost has moved closer to the player
        // Depending on the implementation, you might need to verify certain behaviors or final positions
    }

    // @Test
    // void testEnemyRandomMovementWhenPlayerIsNotNearby() {
    //     // Arrange
    //     Position initialGhostPosition = new Position(0, 0);
    //     ghost.setEnemyPosition(initialGhostPosition);

    //     // Assume player is not nearby
    //     when(recordUsedPlace.isPlayerNearBy(anyInt(), any(Position.class))).thenReturn(false);

    //     // Act
    //     ghost.action();

    //     // Assert - Check that the ghost has moved to a random position
    // }

    // Add more tests to cover different scenarios and branches in the movement logic
}
