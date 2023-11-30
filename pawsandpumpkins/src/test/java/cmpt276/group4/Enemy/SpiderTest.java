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
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.*;

import java.awt.Graphics2D;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the Spider class in a game.
 * It tests various aspects of Spider behavior, including instantiation,
 * position handling,
 * interaction with the player, and rendering.
 * Mockito is used to mock dependencies such as RecordUsedPlace,
 * RoomEnvironment, and GameManager.
 */
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

    /**
     * Tests the Spider constructor with a specific case where the number of enemies leads to a particular spider type.
     * Verifies that the spider instance is correctly created.
     */
    @Test
    void constructorSpiderCase1While1Test() {
        when(mockRoomEnvironment.getEnemyNumber()).thenReturn(1);
        spider = new Spider();
        assertNotNull(spider, "Spider should be instantiated");
    }

    /**
     * Tests the Spider constructor with the default case for spider type determination.
     * Verifies that the spider instance is correctly created.
     */
     @Test
    void testSpiderCreationCase1() {
        when(mockRoomEnvironment.getEnemyNumber()).thenReturn(2);
        assertNotNull(spider, "Spider should be instantiated");
    }

  /**
     * Tests the spider constructor with an enemy number leading to the default case scenario.
     */
    @Test
    void testSpiderCreationDefaultCase() {
        when(mockRoomEnvironment.getEnemyNumber()).thenReturn(2);
                when(mockRoomEnvironment.addEnemy(any(Spider.class))).thenReturn(false).
                thenReturn(true);

        assertNotNull(spider, "Spider should be instantiated");
    }

   /**
     * Tests the spider constructor's behavior when an enemy cannot be added initially but succeeds on a subsequent try.
     */
    @Test
    void testSpiderCreationRetryOnFailure() {
        when(mockRoomEnvironment.getEnemyNumber()).thenReturn(1);
         when(mockRoomEnvironment.addEnemy(any(Spider.class))).thenReturn(false).
                thenReturn(true);

        spider = new Spider();
               
        assertNotNull(spider, "Spider should be instantiated");
    }

    /**
     * Tests obtaining the spider's initial position.
     */
    @Test
    void testGetInitialPosition() {
        assertEquals(new Position(0, 0), spider.getPosition(), "Position should match initial position");
    }

 /**
     * Tests the spider's behavior when it does not catch the player.
     */
    @Test
    void testCatchPlayer_NoCatch() {
        when(mockRoomEnvironment.sameAsPlayerPosition(any(Position.class))).thenReturn(false);
        spider.catchPlayer();
        verify(mockGameManager, never()).enemyCatachPlayer(false);
    }

    /**
     * Tests the spider's behavior when it successfully catches the player.
     */
    @Test
    void testCatchPlayer_Success() {
        when(mockRoomEnvironment.sameAsPlayerPosition(any(Position.class))).thenReturn(true);
        spider.catchPlayer();
        verify(mockGameManager).enemyCatachPlayer(false);
    }

    /**
     * Tests obtaining the spider's enemy position.
     */
    @Test
    void testGetEnemyPosition() {
        assertEquals(new Position(0, 0), spider.getPosition(), "Enemy position should match initial position");
    }

    /**
     * Tests setting a new position for the spider.
     */
    @Test
    void testSetNewEnemyPosition() {
        Position newPosition = new Position(5, 5);
        spider.setEnemyPosition(newPosition);
        assertEquals(newPosition, spider.getPosition(), "Enemy position should be updated");
    }

    /**
     * Tests the movability of the spider, expecting it to be immovable.
     */
    @Test
    void testIsMovable() {
        assertFalse(spider.isMovable(), "Spider should not be movable");
    }

    /**
     * Tests the getMovable method of the spider, expecting it to return false.
     */
    @Test
    void testGetMovable() {
        assertFalse(spider.getMovable(), "getMovable should return false for spider");
    }

    /**
     * Tests the action method of the spider, which should have no effect.
     */
    @Test
    void testAction_NoEffect() {
        spider.action();
    }

    /**
     * Tests the draw method of the spider, verifying that the correct image is
     * drawn.
     */
    @Test
    void testDrawSpiderOnGraphics() {
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
