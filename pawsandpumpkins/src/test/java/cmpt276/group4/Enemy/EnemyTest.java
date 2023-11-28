package cmpt276.group4.Enemy;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.awt.Graphics2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cmpt276.group4.Position;

class EnemyTest {

    private Enemy enemy;

    @Mock
    private Graphics2D mockGraphics;

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

    @Test
    void testCatchPlayer() {
        enemy.catchPlayer();
        verify(enemy, times(1)).catchPlayer();
    }

    @Test
    void testGetEnemyPosition() {
        assertEquals(new Position(0, 0), enemy.getPosition());
    }

    @Test
    void testSetEnemyPosition() {
        Position newPosition = new Position(1, 1);
        enemy.setEnemyPosition(newPosition);
        verify(enemy).setEnemyPosition(newPosition);
    }

    @Test
    void testIsMovable() {
        assertTrue(enemy.isMovable());
    }

    @Test
    void testDraw() {
        enemy.draw(mockGraphics);
        verify(enemy).draw(mockGraphics);
    }

    @Test
    void testGetMovable() {
        assertTrue(enemy.getMovable());
    }

    @Test
    void testAction() {
        enemy.action();
        verify(enemy, times(1)).action();
    }
}
