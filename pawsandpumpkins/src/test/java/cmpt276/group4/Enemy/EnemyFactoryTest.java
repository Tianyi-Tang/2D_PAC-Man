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
import cmpt276.group4.GameMap.RecordUsedPlace;
import cmpt276.group4.GameMap.RoomEnvironment;
import cmpt276.group4.Player.Player;

import java.util.List;

class EnemyFactoryTest {
    private EnemyFactory factory;
    private RecordUsedPlace mockRecord;
    private GameManager mockGameManager;
    private Player mockPlayer;
    private Spider mockSpider;
    private Position mockPlayerPosition;
    private RoomEnvironment mockRoomEnvironment;
    private Enemy mockEnemy;
    private Position mockEnemyPosition;

    @BeforeEach
    void setUp() {

        mockRecord = mock(RecordUsedPlace.class);
        RecordUsedPlace.setInstance(mockRecord);
        mockPlayerPosition = mock(Position.class);
        mockEnemyPosition = mock(Position.class);
        mockRoomEnvironment = mock(RoomEnvironment.class);
        RoomEnvironment.setInstacne(mockRoomEnvironment);
        mockPlayer = mock(Player.class);
        mockSpider = mock(Spider.class);
        mockEnemy = mock(Enemy.class);

        when(mockRecord.getRandomFromAvailablePosition()).thenReturn(mockEnemyPosition);
        when(mockRoomEnvironment.getPlayerPosition()).thenReturn(mockPlayerPosition);
        when(mockRoomEnvironment.addEnemy(mockEnemy)).thenReturn(true);
        when(mockRoomEnvironment.addEnemy(any(Enemy.class))).thenReturn(true);

        factory = new EnemyFactory();
    }

    @Test
    void testCreateBasicGhosts() {
        List<Enemy> enemies = factory.createEnemies(EnemyType.GHOST_BASIC, 3);
        assertEquals(3, enemies.size(), "Incorrect number of basic ghosts created");
        assertTrue(enemies.stream().allMatch(e -> e instanceof Ghost), "All enemies should be basic ghosts");
    }

    @Test
    void testCreateAdvancedGhosts() {
        List<Enemy> enemies = factory.createEnemies(EnemyType.GHOST_ADVANCED, 2);
        assertEquals(2, enemies.size(), "Incorrect number of advanced ghosts created");
        assertTrue(enemies.stream().allMatch(e -> e instanceof Ghost), "All enemies should be advanced ghosts");
    }

    @Test
    void testCreateSpiders() {
        List<Enemy> enemies = factory.createEnemies(EnemyType.SPIDER, 4);
        assertEquals(4, enemies.size(), "Incorrect number of spiders created");
        assertTrue(enemies.stream().allMatch(e -> e instanceof Spider), "All enemies should be spiders");
    }

    @Test
    void testCreateInvalidEnemyType() {
        assertThrows(IllegalArgumentException.class, () -> {
            factory.createEnemies(null, 3); // Assuming null is not a valid type
        }, "IllegalArgumentException should be thrown for invalid enemy type");
    }
}
