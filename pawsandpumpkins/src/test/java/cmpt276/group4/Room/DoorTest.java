package cmpt276.group4.Room;

import cmpt276.group4.Position;
import cmpt276.group4.Player.Player;
import cmpt276.group4.GameManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.awt.Graphics2D;

class DoorTest {

    private Door door;
    private Position mockPosition;
    @Mock
    private Graphics2D mockGraphics2D;
    @Mock
    private GameManager mockGameManager;
    @Mock
    private Player mockPlayer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockPosition = mock(Position.class);
        door = new Door(false, mockPosition);
        GameManager.setInstance(mockGameManager);
        when(mockPlayer.getPosition()).thenReturn(new Position(10, 10));
    }

    @Test
    void testGetPlayerAvailable() {
        assertTrue(door.getPlayerAvaliable());
    }

    @Test
    void testTurnOnDoorWithoutRewards() {
        // Make sure the door is initially closed
        assertFalse(door.isOpen());

        // Set up the GameManager mock to return false when isPlayerCollectAllRewards is called
        when(mockGameManager.isPlayerCollectAllRewards()).thenReturn(false);

        // Call the method under test
        door.turnOnDoor();

        // Verify that the door remains closed
        assertFalse(door.isOpen());
        
    }
    
    @Test
    void testTurnOnDoor() {
        assertFalse(door.isOpen());
        when(mockGameManager.isPlayerCollectAllRewards()).thenReturn(true);

        // Call the method under test
        door.turnOnDoor();

        // Verify that the door is open
        assertTrue(door.isOpen());
        
    }


    @Test
    void testGetPosition() {
        assertEquals(mockPosition, door.getPosition());
    }

    @Test
    void testGetTakenPlace() {
        assertTrue(door.getTakenPlace());
    }

    @Test
    void testDraw() {
        door.draw(mockGraphics2D);

        // Verify that the draw method is not called on the Graphics2D context
        verifyNoInteractions(mockGraphics2D);
    }
}
