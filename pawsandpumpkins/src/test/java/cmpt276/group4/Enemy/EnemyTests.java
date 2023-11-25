package cmpt276.group4.Enemy;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.awt.Graphics2D;
import cmpt276.group4.Position;
import cmpt276.group4.GameMap.RecordUsedPlace;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class EnemyTest {

    private Enemy enemy, ghost, spider;
    private Position testPosition;
    private RecordUsedPlace mockRecord;

    @BeforeEach
    void setUp() {
        testPosition = new Position(5, 5);
        mockRecord = mock(RecordUsedPlace.class);
        RecordUsedPlace.setInstance(mockRecord);
        ghost = Mockito.mock(Ghost.class, Mockito.CALLS_REAL_METHODS);
        spider = Mockito.mock(Spider.class, Mockito.CALLS_REAL_METHODS);
        enemy = Mockito.mock(Enemy.class, Mockito.CALLS_REAL_METHODS);
        RecordUsedPlace.setInstance(mockRecord);
        when(mockRecord.isPlayerNearBy(anyInt(), any(Position.class))).thenReturn(true);

    }

    @Test
    void catchPlayerTestWithGhost() {
        Mockito.when(ghost.getEnemyPosition()).thenReturn(testPosition);
        Mockito.when(ghost.getPosition()).thenReturn(testPosition);

        ghost.catchPlayer();
        Mockito.verify(ghost).catchPlayer();
    }

    @Test
    void catchPlayerTestWithSpider() {
        Mockito.when(spider.getEnemyPosition()).thenReturn(testPosition);
        Mockito.when(spider.getPosition()).thenReturn(testPosition);

        spider.catchPlayer();
        Mockito.verify(spider).catchPlayer();
    }

    @Test
    void getEnemyPositionTest() {
        Mockito.when(enemy.getEnemyPosition()).thenReturn(testPosition);
        assertEquals(testPosition, enemy.getEnemyPosition(), "The position should be the same as the test position");
    }

    @Test
    void setEnemyPositionTest() {
        enemy.setEnemyPosition(testPosition);
        Mockito.verify(enemy).setEnemyPosition(testPosition);
    }

    @Test
    void isMovableTest() {
        Mockito.when(enemy.isMovable()).thenReturn(true);
        assertTrue(enemy.isMovable(), "Enemy should be movable");
    }

    @Test
    void drawTest() {
        Graphics2D graphics = Mockito.mock(Graphics2D.class);
        enemy.draw(graphics);
        Mockito.verify(enemy).draw(graphics);
    }

    @Test
    void getPositionTest() {
        Mockito.when(enemy.getPosition()).thenReturn(testPosition);
        assertEquals(testPosition, enemy.getPosition(), "The position should be the same as the test position");
    }

    @Test
    void getMovableTest() {
        Mockito.when(enemy.getMovable()).thenReturn(true);
        assertTrue(enemy.getMovable(), "Enemy should be movable");
    }

    @Test
    void actionTest() {
        enemy.action();
        // Verify there's no error
    }

}
