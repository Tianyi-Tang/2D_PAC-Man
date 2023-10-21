package cmpt276.group4.Enemy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnemyTests {

    private EnemyFactory enemyFactory;

    @BeforeEach
    public void setUp() {
        enemyFactory = new EnemyFactory();
    }

    @Test
    public void testCreateGhostEnemies() {
        List<Enemy> enemies = enemyFactory.createEnemies(EnemyType.GHOST, 5);
        assertEquals(5, enemies.size(), "Should create 5 ghost enemies");
        // Add more assertions or checks if needed, e.g., check the type of enemies
    }

    @Test
    public void testCreateSpiderEnemies() {
        List<Enemy> enemies = enemyFactory.createEnemies(EnemyType.SPIDER, 3);
        assertEquals(3, enemies.size(), "Should create 3 spider enemies");
        // Add more assertions or checks if needed, e.g., check the type of enemies
    }

    @Test
    public void testEnemyInitializationWithGhosts() {
        EnemyInitialization enemyInitialization = new EnemyInitialization(EnemyType.GHOST, 5);
        List<Enemy> ghosts = enemyInitialization.createEnemies();
        assertEquals(5, ghosts.size(), "Enemy initialization should create 5 ghost enemies");
    }

    @Test
    public void testEnemyInitializationWithSpiders() {
        EnemyInitialization enemyInitialization = new EnemyInitialization(EnemyType.SPIDER, 4);
        List<Enemy> spiders = enemyInitialization.createEnemies();
        assertEquals(4, spiders.size(), "Enemy initialization should create 4 spider enemies");
    }
}
