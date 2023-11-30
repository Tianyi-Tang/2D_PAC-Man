package cmpt276.group4;

import cmpt276.group4.Logic.GameConfig;
import cmpt276.group4.Room.Door;
import cmpt276.group4.WindowAndInput.PanelController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import cmpt276.group4.Player.Player;
import org.mockito.MockitoAnnotations;

import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameManagerTest {

    @Mock
    private GameConfig mockGameConfig;
    @Mock
    private PanelController mockPanelController;
    @Mock
    private Player mockPlayer;
    @Mock
    private Door mockDoor;

    private GameManager gameManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        gameManager = GameManager.getInstance();
        gameManager.init(mockPanelController);
    }
    @Test
    void testGameManagerInit() {
        gameManager.init(mockPanelController);
        assertNotNull(gameManager, "GameManager should be instantiated");
    }
    @Test
    void testSetNumberOfGeneralRewards() {
        when(mockGameConfig.getNumberOfRegularRewards()).thenReturn(5);
        gameManager.setNumberOfGeneralRewards(mockGameConfig);
        assertEquals(0, gameManager.getGeneralRewards_num(), "Number of general rewards should be set correctly");
    }

    @Test
    void testGameEndConditions() {
        gameManager.enemyCatachPlayer(true);
        assertFalse(gameManager.isGameEnd(), "Game should end when the player is caught by a moveable enemy");

        gameManager.negativePoint();
        assertFalse(gameManager.isGameEnd(), "Game should end when the player has negative points");
    }
    @Test
    void testNegativePointEndsGame() {

        when(mockPlayer.totalScore()).thenReturn(-1);

        gameManager.negativePoint();

        assertFalse(gameManager.isGameEnd(), "Game should end when the player has negative points");
    }
}
