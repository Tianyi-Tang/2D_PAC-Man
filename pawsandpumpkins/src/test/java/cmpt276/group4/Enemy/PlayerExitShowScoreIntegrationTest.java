package cmpt276.group4.Enemy;

import cmpt276.group4.GameManager;
import cmpt276.group4.Logic.GameConfig;
import cmpt276.group4.Player.Player;
import cmpt276.group4.Room.Door;
import cmpt276.group4.WindowAndInput.PanelController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * This class contains integration tests for the GameManager class in scenarios
 * where the player exits the game.
 * It tests the functionality of showing the game score when the player
 * successfully exits or is caught by a ghost.
 * The tests use Mockito to mock dependencies like Player, PanelController, and
 * Door.
 */
public class PlayerExitShowScoreIntegrationTest {

    private GameManager gameManager;
    private Player mockPlayer;
    private PanelController mockControll;
    private Door[] mockDoors;
    private int rewardAmount;

    @BeforeEach
    void setUp() {
        rewardAmount = 5;
        mockControll = mock(PanelController.class);
        mockPlayer = mock(Player.class);
        gameManager = new GameManager();
        gameManager.init(mockControll);

        GameManager.setInstance(gameManager);
        gameManager.setPlayer(mockPlayer);
        gameManager.init(mockControll);

        mockDoors = new Door[1];
        mockDoors[0] = mock(Door.class);
        gameManager.setDoors(mockDoors);

        when(mockPlayer.getGeneralRewardNum()).thenReturn(5);
        GameConfig mockGameConfig = mock(GameConfig.class);
        GameConfig.setInstance(mockGameConfig);
        when(mockGameConfig.getNumberOfRegularRewards()).thenReturn(rewardAmount);
        gameManager.setNumberOfGeneralRewards(mockGameConfig);

    }

    /**
     * Tests if the game correctly shows the score and ends the game when the player
     * exits with all rewards collected.
     * It verifies that the game state is changed to 'end' and the end screen is
     * displayed.
     */
    @Test
    void testShowScoreOnPlayerExitandWin() {

        gameManager.collectReward(rewardAmount);
        gameManager.playerLeaveDoor();
        System.out.println("isGameEnd: " + gameManager.isGameEnd());

        assertTrue(gameManager.isGameEnd());
        verify(mockControll).transformToEndScreen();
    }

    /**
     * Tests if the game correctly shows the score and ends the game when the player
     * is caught by a ghost.
     * It verifies that the game state is changed to 'end' and the end screen is
     * displayed.
     */
    @Test
    void testShowScoreOnPlayerCaughtByGhost() {

        gameManager.enemyCatachPlayer(true);
        System.out.println("isGameEnd: " + gameManager.isGameEnd());

        assertTrue(gameManager.isGameEnd());
        verify(mockControll).transformToEndScreen();
    }
}
