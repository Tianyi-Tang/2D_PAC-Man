package cmpt276.group4.Enemy;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

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
    private Spider mockSpider;
    private Position mockPlayerPosition;

    @BeforeEach
    void setUp() {

        mockRecord = mock(RecordUsedPlace.class);
        RecordUsedPlace.setInstance(mockRecord);
        mockPlayerPosition = mock(Position.class);

        mockGameManager = mock(GameManager.class);
        GameManager gameManagerIns = GameManager.getInstance();
        gameManagerIns.setPlayer(mockPlayer);
        mockPlayer = mock(Player.class);
        mockSpider = mock(Spider.class);

        // Set up instances and return values for mocks
        RecordUsedPlace.setInstance(mockRecord);
        //when(mockRecord.getRandomSafePosition()).thenReturn(new Position(10, 10));
        //when(mockRecord.getPlayerPosition()).thenReturn(new Position(10, 10));
        when(mockRecord.containsCandyAtPosition(any(Position.class))).thenReturn(false);
        when(mockRecord.getRandomSafePosition()).thenReturn(new Position(10, 10)); 
        when(mockRecord.getPlayerPosition()).thenReturn(mockPlayerPosition);
        
        mockGameManager.setPlayer(mockPlayer);

        factory = new EnemyFactory();
    }

    @Test
    void testCreateGhosts() {
        int numberOfGhosts = 3;

        // Test creating ghosts
        List<Enemy> ghosts = factory.createEnemies(EnemyType.GHOST_BASIC, numberOfGhosts);
        assertEquals(numberOfGhosts, ghosts.size(), "Incorrect number of ghosts created");
    }

    @Test
    void testCreateSpiders() {
        int numberOfSpiders = 3;

        
        List<Enemy> spiders = factory.createEnemies(EnemyType.SPIDER, numberOfSpiders);
        assertEquals(numberOfSpiders, spiders.size(), "Incorrect number of ghosts created");
  
    }
    

    @Test
    void testCreateInvalidEnemyType() {

        Exception exception = assertThrows(NullPointerException.class, () -> {
             factory.createEnemies(null, 1);
        });
    }
    
}
