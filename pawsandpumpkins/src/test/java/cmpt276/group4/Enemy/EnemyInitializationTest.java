package cmpt276.group4.Enemy;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cmpt276.group4.Logic.GameConfig;

/**
 * Provides unit tests for the EnemyInitialization class.
 * This class tests the functionality of EnemyInitialization, ensuring that
 * enemies are correctly initialized based on the game's configuration settings.
 */
class EnemyInitializationTest {
    private EnemyFactory mockFactory;
    private GameConfig mockGameConfig;
    private EnemyInitialization enemyInit;

    @BeforeEach
    void setUp() {
        mockFactory = mock(EnemyFactory.class);
        mockGameConfig = mock(GameConfig.class);
        GameConfig.setInstance(mockGameConfig);
        when(mockGameConfig.getNumberOfSpiders()).thenReturn(3);
        when(mockGameConfig.getNumberOfBasicGhosts()).thenReturn(2);
        when(mockGameConfig.getNumberOfAdvancedGhosts()).thenReturn(1);
        enemyInit = new EnemyInitialization(mockFactory);
    }

    
    /**
     * Tests if the EnemyInitialization class correctly initializes the number of each enemy type.
     * Verifies that the number of spiders, basic ghosts, and advanced ghosts match the expected values
     * as defined in the mocked GameConfig instance.
     */
    @Test
    void testEnemyInitializationCounts() {
        verify(mockFactory).createEnemies(EnemyType.SPIDER, 3);
        verify(mockFactory).createEnemies(EnemyType.GHOST_BASIC, 2);
        verify(mockFactory).createEnemies(EnemyType.GHOST_ADVANCED, 1);

        
        assertEquals(3, enemyInit.getSpider(), "Incorrect number of spiders initialized.");
        assertEquals(2, enemyInit.getBasicGhost(), "Incorrect number of basic ghosts initialized.");
        assertEquals(1, enemyInit.getAdvancedGhost(), "Incorrect number of advanced ghosts initialized.");
    }


}
