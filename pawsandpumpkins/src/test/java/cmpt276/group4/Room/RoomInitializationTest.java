package cmpt276.group4.Room;

import cmpt276.group4.GameMap.RecordUsedPlace;
import cmpt276.group4.Logic.GameConfig;
import cmpt276.group4.Position;
import cmpt276.group4.gameLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class RoomInitializationTest {

    private RoomInitialization roomInitialization;
    private GameConfig mockGameConfig;
    private List<Position> mockWallPositionList;
    private ArrayList<Position> mockTilesPosition;
    private RoomFactory mockFactory;

    @BeforeEach
    void setUp() {
        mockFactory = mock(RoomFactory.class);
        mockGameConfig = mock(GameConfig.class);
        GameConfig.setInstance(mockGameConfig);
        when(mockGameConfig.getNumberOfObstacles()).thenReturn(3);
        when(mockGameConfig.getNumberOfBasicGhosts()).thenReturn(2);
        when(mockGameConfig.getNumberOfAdvancedGhosts()).thenReturn(1);
        roomInitialization = new RoomInitialization();

    }

    @Test
    void initializeRoomTest() {
        roomInitialization.initializeRoom(gameLevel.BASIC, mockFactory);

        assertNotNull(roomInitialization.wallPositionList, "Wall position list should not be null");
        assertNotNull(roomInitialization.tilesPosition, "Tiles position should not be null");
        assertEquals(3, roomInitialization.tombstone, "Number of tombstones should be 3");
    }

    @Test
    void iRoomTest() {
        Room mockRoom = mock(Room.class);
        when(mockFactory.createRoom()).thenReturn(mockRoom);

        Room result = roomInitialization.iRoom(mockFactory);
        assertEquals(mockRoom, result, "Room instance should be the same as created by factory");
    }

    @Test
    void iTombsTest() {
        Position mockPosition = new Position(0, 0);
        roomInitialization.position = mockPosition;
        roomInitialization.tombstone = 5;

        roomInitialization.iTombs(mockFactory);
        verify(mockFactory).createTombstones(mockPosition, 5);
    }

    @Test
    void iWallsTest() {
        roomInitialization.wall = 10;
        roomInitialization.wallPositionList = mockWallPositionList;

        roomInitialization.iWalls(mockFactory);
        verify(mockFactory).createWall(mockWallPositionList, 10);
    }

    @Test
    void iTilesTest() {
        roomInitialization.iTiles(mockFactory);

        // Verify that createTile method is called with the correct arguments
        verify(mockFactory).createTile(mockTilesPosition);
    }

    }
