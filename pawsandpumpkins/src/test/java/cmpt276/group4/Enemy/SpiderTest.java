package cmpt276.group4.Enemy;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cmpt276.group4.GameManager;
import cmpt276.group4.Position;
import cmpt276.group4.RecordUsedPlace;
import cmpt276.group4.Player.Player;

public class SpiderTest {


    private Spider spider;
    private RecordUsedPlace mockRecord;
    private Position mockPlayerPosition;
    private Player mockPlayer;

    @BeforeEach
    void setUp() {
        mockRecord = mock(RecordUsedPlace.class);
        RecordUsedPlace.setInstance(mockRecord);

        mockPlayerPosition = mock(Position.class);
        mockPlayer = mock(Player.class);
        GameManager gameManagerIns = GameManager.getInstance();
        gameManagerIns.setPlayerForTest(mockPlayer);
        
        when(mockRecord.getPlayerPosition()).thenReturn(mockPlayerPosition);
        when(mockRecord.getRandomSafePosition()).thenReturn(new Position(10, 10)); // Example safe position

        spider = new Spider();
    }

    @Test
    void testCatchPlayerWhenPlayerIsCaught() {
        // Set both player and spider at the same position
        Position samePosition = new Position(48, 48);
        when(mockRecord.getPlayerPosition()).thenReturn(samePosition);
        spider.setEnemyPosition(samePosition);

        spider.catchPlayer();
        verify(mockPlayer).deductPoint(anyInt()); }

    @Test
    void testCatchPlayerWhenPlayerNotCaught() {
        // Set player and spider at different positions
        Position playerPosition = new Position(48, 48);
        Position spiderPosition = new Position(10, 10);
        when(mockRecord.getPlayerPosition()).thenReturn(playerPosition);
        spider.setEnemyPosition(spiderPosition);

        spider.catchPlayer();

        verify(mockPlayer, never()).deductPoint(anyInt());
    }

    @Test
    void testIsMovable() {
        assertFalse(spider.isMovable(), "Spiders should not be movable.");
    }

    
}
