package cmpt276.group4.WindowAndInput;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import cmpt276.group4.GameManager;
import cmpt276.group4.GameStatus;
import cmpt276.group4.gameLevel;
import cmpt276.group4.GameMap.RecordUsedPlace;
import cmpt276.group4.GameMap.RoomEnvironment;
import cmpt276.group4.GameMap.RoomLayout;
import cmpt276.group4.Player.Player;

/**
 * Class for unit test panel controller
 */
public class PanelControllerTest {
    public PanelController controller;
    public RecordUsedPlace mockrecord;
    public GameManager mockGameManager;
    public RoomEnvironment mockEnvironment;
    public RoomLayout mockLayout;

    /**
     * Set up the panel controller for testing
     */
    @BeforeEach
    public void setUP(){
        controller = new PanelController();
        
        mockrecord = mock(RecordUsedPlace.class);
        mockGameManager = mock(GameManager.class);
        mockEnvironment = mock(RoomEnvironment.class);
        mockLayout = mock(RoomLayout.class);
    }

    /**
     * Test panel controller will gennerate one instance in the game
     */
    @Test
    public void testGetInstance(){
        PanelController instance1 = PanelController.getInstance();
        PanelController instance2 = PanelController.getInstance();
        assertEquals(instance1, instance2);
    }

    /**
     * Test panel controller can load the main panel
     */
    @Test
    public void laodMainWind(){
        controller.createMainWindow(mockGameManager, mockrecord, mockLayout, mockEnvironment);
        verify(mockGameManager).init(controller);
    }

    /**
     * Test panel controller can load the loading panel
     */
    @Test
    public void loadLoadingPanel(){
        controller.transformToLoadingScreen(gameLevel.BASIC);
        assertEquals(GameStatus.LoadingPanel, controller.getGameStatu());
    }

    /**
     * Test panel controller can load the game panel
     */
    @Test
    public void loadGamePanel(){
        controller.createMainWindow(mockGameManager, mockrecord, mockLayout, mockEnvironment);
        controller.transformToGameScreen();
        verify(mockGameManager).setPlayer(Player.getInstance());
    }

    /**
     * Test panel controller can laod the end screen panel
     */
    @Test
    public void loadEndScreen(){
        when(mockGameManager.isGameEnd()).thenReturn(true);
        controller.createMainWindow(mockGameManager, mockrecord, mockLayout, mockEnvironment);
        controller.transformToEndScreen();
        verify(mockGameManager).isPalyerWin();
    }

    /**
     * Test panel controller will reject loading the final screen if the games is not end
     */
    @Test
    public void failLoadEndScreen(){
        when(mockGameManager.isGameEnd()).thenReturn(false);
        controller.createMainWindow(mockGameManager, mockrecord, mockLayout, mockEnvironment);
        controller.transformToEndScreen();
        verify(mockGameManager, Mockito.never()).isPalyerWin();
    }

    
}
