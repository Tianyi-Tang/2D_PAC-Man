package cmpt276.group4.WindowAndInput;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cmpt276.group4.GameManager;
import cmpt276.group4.gameLevel;
import cmpt276.group4.GameMap.RecordUsedPlace;
import cmpt276.group4.GameMap.RoomEnvironment;
import cmpt276.group4.GameMap.RoomLayout;

public class PanelControllerTest {
    public PanelController controller;
    public RecordUsedPlace mockrecord;
    public GameManager mockGameManager;
    public RoomEnvironment mockEnvironment;
    public RoomLayout mockLayout;

    @BeforeEach
    public void setUP(){
        controller = new PanelController();
        
        mockrecord = mock(RecordUsedPlace.class);
        mockGameManager = mock(GameManager.class);
        mockEnvironment = mock(RoomEnvironment.class);
        mockLayout = mock(RoomLayout.class);
    }

    @Test
    public void laodMainWind(){
        controller.createMainWindow(mockGameManager, mockrecord, mockLayout, mockEnvironment);
        verify(mockGameManager).init(controller);
    }

    @Test
    public void loadLoadingPanel() throws InterruptedException{

    }
    
}
