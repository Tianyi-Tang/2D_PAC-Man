package cmpt276.group4.Enemy;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import cmpt276.group4.GameManager;
import cmpt276.group4.Position;
import cmpt276.group4.RecordUsedPlace;
import cmpt276.group4.Player.Player;

import java.util.List;

public class EnemyFactoryTest {

    private EnemyFactory factory;
    private RecordUsedPlace mockRecord;
    private GameManager mockGameManager;
    private Player mockPlayer;

    @BeforeEach
    void setUp() {
        // Set up the necessary environment for creating Ghosts and Spiders
        mockRecord = mock(RecordUsedPlace.class);
        mockGameManager = mock(GameManager.class);
        mockPlayer = mock(Player.class);

        // Set up instances and return values for mocks
        RecordUsedPlace.setInstance(mockRecord);
        when(mockRecord.getRandomSafePosition()).thenReturn(new Position(10, 10));
        when(mockRecord.getPlayerPosition()).thenReturn(new Position(10, 10));

        // GameManager.setInstance(mockGameManager);
        mockGameManager.setPlayer(mockPlayer);

        factory = new EnemyFactory();
    }

    @Test
    void testCreateGhosts() {
        int numberOfGhosts = 5;

        // Test creating ghosts
        List<Enemy> ghosts = factory.createEnemies(EnemyType.GHOST_BASIC, numberOfGhosts);
        assertEquals(numberOfGhosts, ghosts.size(), "Incorrect number of ghosts created");
    }

    // @Test
    // void testCreateSpiders() {
    // int numberOfSpiders = 3;

    // // Test creating spiders
    // List<Enemy> spiders = factory.createEnemies(EnemyType.SPIDER,
    // numberOfSpiders);
    // assertEquals(numberOfSpiders, spiders.size(), "Incorrect number of spiders
    // created");
    // }
        
    
    /*    @Test
    void testCreateSpiders() {
        int numberOfSpiders = 3;

        // Mock RecordUsedPlace and its methods
        RecordUsedPlace mockRecord = mock(RecordUsedPlace.class);
        RecordUsedPlace.setInstance(mockRecord);

        // Configure the mock to return a fixed position
        Position fixedPosition = new Position(5, 5);
        when(mockRecord.getRandomSafePosition()).thenReturn(fixedPosition);
        when(mockRecord.containsCandyAtPosition(any(Position.class))).thenReturn(false);

        // Test creating spiders
        List<Enemy> spiders = factory.createEnemies(EnemyType.SPIDER, numberOfSpiders);

        // Reset RecordUsedPlace to its original state if necessary
        // RecordUsedPlace.setInstance(originalInstance);

        assertEquals(numberOfSpiders, spiders.size(), "Incorrect number of spiders created");
    }
    */

    // @Test
    // void testCreateInvalidEnemyType() {
    // // Test invalid enemy type
    // assertThrows(IllegalArgumentException.class, () -> {
    // factory.createEnemies(null, 1);
    // }, "Creating enemies with invalid type should throw
    // IllegalArgumentException");
    // }
}
