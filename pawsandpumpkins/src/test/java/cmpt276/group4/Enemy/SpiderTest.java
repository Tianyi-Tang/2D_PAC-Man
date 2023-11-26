package cmpt276.group4.Enemy;

import cmpt276.group4.GameMap.RecordUsedPlace;
import cmpt276.group4.GameMap.RoomEnvironment;
import cmpt276.group4.Position;
import cmpt276.group4.GameManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.*;

import java.awt.Graphics2D;

import static org.junit.jupiter.api.Assertions.*;

class SpiderTest {

    @Mock
    private RecordUsedPlace mockRecord;
    @Mock
    private RoomEnvironment mockRoomEnvironment;
    @Mock
    private GameManager mockGameManager;

    private Spider spider;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        RecordUsedPlace.setInstance(mockRecord);
        RoomEnvironment.setInstance(mockRoomEnvironment);
        GameManager.setInstance(mockGameManager);

        when(mockRecord.getRandomFromAvailablePosition()).thenReturn(new Position(0, 0));
        when(mockRoomEnvironment.addEnemy(any(Spider.class))).thenReturn(true);

        spider = new Spider();
    }

    @Test
    void constructorSpiderCase1While1Test() {
        when(mockRoomEnvironment.getEnemyNumber()).thenReturn(1);
        spider = new Spider();
        assertNotNull(spider, "Spider should be instantiated");
    }

    @Test
    void constructorSpiderCaseDefaultWhile1Test() {
        when(mockRoomEnvironment.getEnemyNumber()).thenReturn(2);
        assertNotNull(spider, "Spider should be instantiated");
    }

    @Test
    void constructorSpiderCaseDefaultWhile2Test() {
        when(mockRoomEnvironment.getEnemyNumber()).thenReturn(2);
                when(mockRoomEnvironment.addEnemy(any(Spider.class))).thenReturn(false).
                thenReturn(true);

        assertNotNull(spider, "Spider should be instantiated");
    }

    @Test
    void constructorSpiderCase1While2Test() {
        when(mockRoomEnvironment.getEnemyNumber()).thenReturn(1);
         when(mockRoomEnvironment.addEnemy(any(Spider.class))).thenReturn(false).
                thenReturn(true);

        spider = new Spider();
               
        assertNotNull(spider, "Spider should be instantiated");
    }

    @Test
    void getPositionTest() {
        assertEquals(new Position(0, 0), spider.getPosition(), "Position should match initial position");
    }

    @Test
    void catchPlayerTest_NotCatchingPlayer() {
        when(mockRoomEnvironment.sameAsPlayerPosition(any(Position.class))).thenReturn(false);
        spider.catchPlayer();
        verify(mockGameManager, never()).enemyCatachPlayer(false);
    }

    @Test
    void catchPlayerTest_CatchingPlayer() {
        when(mockRoomEnvironment.sameAsPlayerPosition(any(Position.class))).thenReturn(true);
        spider.catchPlayer();
        verify(mockGameManager).enemyCatachPlayer(false);
    }

    @Test
    void getEnemyPositionTest() {
        assertEquals(new Position(0, 0), spider.getEnemyPosition(), "Enemy position should match initial position");
    }

    @Test
    void setEnemyPositionTest() {
        Position newPosition = new Position(5, 5);
        spider.setEnemyPosition(newPosition);
        assertEquals(newPosition, spider.getEnemyPosition(), "Enemy position should be updated");
    }

    @Test
    void isMovableTest() {
        assertFalse(spider.isMovable(), "Spider should not be movable");
    }

    @Test
    void getMovableTest() {
        assertFalse(spider.getMovable(), "getMovable should return false for spider");
    }

    @Test
    void actionTest() {
        spider.action();
    }

    @Test
    void testDrawMethod() {
        Graphics2D mockGraphics = mock(Graphics2D.class);
        Spider spider = new Spider();
        spider.setEnemyPosition(new Position(100, 100));

        spider.draw(mockGraphics);

        verify(mockGraphics).drawImage(
                isNotNull(),
                eq(100), eq(100),
                anyInt(), anyInt(),
                isNull());
    }
}
