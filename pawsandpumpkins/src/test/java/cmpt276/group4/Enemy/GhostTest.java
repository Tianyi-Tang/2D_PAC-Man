package cmpt276.group4.Enemy;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cmpt276.group4.GameManager;
import cmpt276.group4.Position;
import cmpt276.group4.RecordUsedPlace;
import cmpt276.group4.Player.Player;

class GhostTest {

    private Ghost ghost;
    private RecordUsedPlace mockRecord;
    private GameManager mockGameManager;
    GameManager gameManagerIns;
    GameManager gameManagerSpy;
    private Player mockPlayer;

    @BeforeEach
    void setUp() {
        mockRecord = mock(RecordUsedPlace.class);
        RecordUsedPlace.setInstance(mockRecord);
        mockPlayer = mock(Player.class);
        mockGameManager = mock(GameManager.class);
        mockGameManager.setPlayerForTest(mockPlayer);
        gameManagerIns = GameManager.getInstance();
        gameManagerIns.setPlayerForTest(mockPlayer);

        when(mockRecord.getRandomSafePosition()).thenReturn(new Position(10, 10)); // Example safe position
        when(mockRecord.getPlayerPosition()).thenReturn(new Position(10, 10)); // Player's position

        ghost = new Ghost(EnemyType.GHOST_BASIC);
    }

    @Test
    void testCatchPlayerWhenPlayerIsCaught() {
         // Set both player and spider at the same position
        Position samePosition = new Position(48, 48);
        when(mockRecord.getPlayerPosition()).thenReturn(samePosition);
        ghost.setEnemyPosition(samePosition);

        ghost.catchPlayer();

        verify(mockPlayer).getCollectScore();
        verify(mockPlayer).getGeneralRewardNum();
        verify(mockPlayer).getBonusRewardNum();
        verify(mockPlayer).getDeductScore();
        verify(mockPlayer).totalScore();
       
    }

    @Test
    void testIsMovable() {
        assertTrue(ghost.isMovable(), "Ghost should be movable.");
    }

    @Test
    void testSpiderIsAddedToRecordUsedPlace() {
        verify(mockRecord).addEnemy(ghost);
    }
}
