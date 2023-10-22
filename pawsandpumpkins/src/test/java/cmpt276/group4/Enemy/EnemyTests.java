package cmpt276.group4.Enemy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnemyTests {

    private EnemyFactory enemyFactory;
    private EnemyInitialization enemyInitialization;

    @BeforeEach
    public void setUp() {
        enemyFactory = new EnemyFactory();
        enemyInitialization = new EnemyInitialization(5); // Initializing 5 enemies
    }

    @Test
    public void testCreateGhostEnemies() {
        List<Enemy> enemies = enemyFactory.createEnemies(EnemyType.GHOST, enemyInitialization.getEnemyNum());
        assertEquals(5, enemies.size(), "Should create 5 ghost enemies");
        // Add more assertions or checks if needed, e.g., check the type of enemies
    }

    @Test
    public void testCreateSpiderEnemies() {
        List<Enemy> enemies = enemyFactory.createEnemies(EnemyType.SPIDER, enemyInitialization.getEnemyNum());
        assertEquals(5, enemies.size(), "Should create 5 spider enemies");
        // Add more assertions or checks if needed, e.g., check the type of enemies
    }
}
