package cmpt276.group4.WindowAndInput;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import cmpt276.group4.GameManager;
import cmpt276.group4.gameLevel;
import cmpt276.group4.GameMap.RoomEnvironment;
import cmpt276.group4.GameMap.RoomLayout;
import cmpt276.group4.Logic.GameConfig;
import cmpt276.group4.Player.Player;
import cmpt276.group4.Player.PlayerGenerator;
import cmpt276.group4.Room.RoomFactory;
import cmpt276.group4.Room.RoomInitialization;

public class InitialiseGameItemTest {
    public InitialiseGameItem initialiseGameItem;
    public gameLevel level = gameLevel.BASIC;
    public RoomInitialization mockRoomInitialization;
    public RoomFactory mockRoomFactory;

    @BeforeEach
    public void setUp(){
        GameConfig config = new GameConfig();
        config.passGameLevel(level);

        mockRoomInitialization = mock(RoomInitialization.class);
        mockRoomFactory = mock(RoomFactory.class);
        initialiseGameItem = new InitialiseGameItem(config);
        initialiseGameItem.setRoomInitialize(mockRoomInitialization, mockRoomFactory);
    }

    @Test
    public void createRoom(){
        initialiseGameItem.createRoom();
        verify(mockRoomInitialization).iRoom(mockRoomFactory);
    }

    @Test
    public void createTile(){
        initialiseGameItem.createTile();
        verify(mockRoomInitialization).iTiles(mockRoomFactory);
    }


    @Test
    public void createPlayer(){
        RoomEnvironment mockRoomEnvironment = mock(RoomEnvironment.class);
        initialiseGameItem.createPlayer(RoomLayout.getInstance(), mockRoomEnvironment);
        verify(mockRoomEnvironment).setPlayer(Player.getInstance());
    }


}
