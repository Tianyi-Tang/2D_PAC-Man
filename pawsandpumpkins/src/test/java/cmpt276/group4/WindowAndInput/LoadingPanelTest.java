package cmpt276.group4.WindowAndInput;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cmpt276.group4.gameLevel;
import cmpt276.group4.GameMap.RecordUsedPlace;
import cmpt276.group4.GameMap.RoomEnvironment;
import cmpt276.group4.GameMap.RoomLayout;
import cmpt276.group4.Logic.GameConfig;

public class LoadingPanelTest {
    public LoadingPanel loadingPanel;

    public InitialiseGameItem mockInialise;
    public RecordUsedPlace mockrecord;
    public RoomLayout mockroomLayout;
    public RoomEnvironment mockroomEnvironment;

    public GameConfig config;

    @BeforeEach
    public void setUp(){
        loadingPanel = new LoadingPanel();
        
        mockInialise = mock(InitialiseGameItem.class);
        mockrecord = mock(RecordUsedPlace.class);
        mockroomLayout = mock(RoomLayout.class);
        mockroomEnvironment = mock(RoomEnvironment.class);
        
        config = GameConfig.getGameConfigInstance();
        config.passGameLevel(gameLevel.BASIC);

        when(mockInialise.getConfig()).thenReturn(config);
        loadingPanel.init(mockInialise);
        loadingPanel.setKeySingleton(mockrecord, mockroomLayout, mockroomEnvironment);
    }

    @Test
    public void
}
