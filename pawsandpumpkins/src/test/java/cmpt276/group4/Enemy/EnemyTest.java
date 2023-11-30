package cmpt276.group4.Enemy;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.awt.Graphics2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cmpt276.group4.Position;

/**
 * Unit test class for Enemy interface.
 * Tests various methods of the Enemy interface using mock objects and verifies
 * their interactions and behaviors.
 */
class EnemyTest {

    private Enemy enemy;

    @Mock
    private Graphics2D mockGraphics;

    /**
     * Sets up the testing environment before each test.
     * Initializes a mock implementation of the Enemy interface and configures
     * default responses for testing.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Create a mock implementation of Enemy for testing
        enemy = mock(Enemy.class, CALLS_REAL_METHODS);

        // Setting up default responses for the mock
        when(enemy.getPosition()).thenReturn(new Position(0, 0));
        when(enemy.isMovable()).thenReturn(true);
        when(enemy.getMovable()).thenReturn(true);
    }

    /**
     * Tests the catchPlayer method of the Enemy interface.
     * Verifies that the catchPlayer method is called exactly once.
     */
    @Test
    void testCatchPlayer() {
        enemy.catchPlayer();
        verify(enemy, times(1)).catchPlayer();
    }

    /**
     * Tests the getPosition method of the Enemy interface.
     * Ensures that the getPosition method returns the correct position.
     */
    @Test
    void testGetEnemyPosition() {
        assertEquals(new Position(0, 0), enemy.getPosition());
    }

    /**
     * Tests the setEnemyPosition method of the Enemy interface.
     * Verifies that the setEnemyPosition method is called with the correct
     * parameter.
     */
    @Test
    void testSetEnemyPosition() {
        Position newPosition = new Position(1, 1);
        enemy.setEnemyPosition(newPosition);
        verify(enemy).setEnemyPosition(newPosition);
    }

    /**
     * Tests the isMovable method of the Enemy interface.
     * Ensures that isMovable method returns true.
     */
    @Test
    void testIsMovable() {
        assertTrue(enemy.isMovable());
    }

    /**
     * Tests the draw method of the Enemy interface.
     * Verifies that the draw method is called with the correct Graphics2D
     * parameter.
     */
    @Test
    void testDraw() {
        enemy.draw(mockGraphics);
        verify(enemy).draw(mockGraphics);
    }

    /**
     * Tests the getMovable method of the Enemy interface.
     * Ensures that getMovable method returns true.
     */
    @Test
    void testGetMovable() {
        assertTrue(enemy.getMovable());
    }

    /**
     * Tests the action method of the Enemy interface.
     * Verifies that the action method is called exactly once.
     */
    @Test
    void testAction() {
        enemy.action();
        verify(enemy, times(1)).action();
    }
}
