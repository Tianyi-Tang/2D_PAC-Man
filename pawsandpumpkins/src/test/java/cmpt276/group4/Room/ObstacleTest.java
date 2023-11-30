package cmpt276.group4.Room;
import org.junit.jupiter.api.Test;

import cmpt276.group4.Position;

import java.awt.Graphics2D;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ObstacleTest {

    @Test
    void testSetPlayerAccess() {
        Obstacle obstacle = new Obstacle(new Position(0, 0));

        // Initially, player access should be false
        assertFalse(obstacle.getPlayerAvaliable());

        // Call setPlayerAccess
        obstacle.setPlayerAccess();

        // After calling setPlayerAccess, player access should still be false
        assertFalse(obstacle.getPlayerAvaliable());
    }

    @Test
    void testGetPlayerAvaliable() {
        Obstacle obstacle = new Obstacle(new Position(0, 0));

        // Initially, player access should be false
        assertFalse(obstacle.getPlayerAvaliable());

        // Call setPlayerAccess
        obstacle.setPlayerAccess();

        // After calling setPlayerAccess, player access should still be false
        assertFalse(obstacle.getPlayerAvaliable());
    }

    @Test
    void testDraw() {
        Obstacle obstacle = new Obstacle(new Position(0, 0));

        // Create a mock Graphics2D object
        Graphics2D mockGraphics2D = mock(Graphics2D.class);

        // Call the draw method
        obstacle.draw(mockGraphics2D);

        // Verify that the draw method does not interact with the mockGraphics2D object
        verifyNoInteractions(mockGraphics2D);
    }
}
