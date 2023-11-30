package cmpt276.group4.Room;
import org.junit.jupiter.api.Test;

import cmpt276.group4.Position;
import cmpt276.group4.Logic.WindowConfig;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.runner.RunWith;


class TileTest {

    @Test
    void testDraw() {
        Tile tile = new Tile(new Position(0, 0));

        // Create a mock Graphics2D object
        Graphics2D mockGraphics2D = mock(Graphics2D.class);

        // Call the draw method
        tile.draw(mockGraphics2D);

        // Verify that the drawImage method is called on the mockGraphics2D object
        verify(mockGraphics2D, times(1)).drawImage(
                isNotNull(),
                eq(0), eq(0), eq(WindowConfig.tileSize), eq(WindowConfig.tileSize), isNull());
    }

    @Test
    void testDrawWithNullImage() {
        Tile tile = new Tile(new Position(0, 0));

        // Set the tileImage to null
        tile.tileImage = null;

        // Create a mock Graphics2D object
        Graphics2D mockGraphics2D = mock(Graphics2D.class);

        // Call the draw method
        tile.draw(mockGraphics2D);

        // Verify that the drawImage method is NOT called on the mockGraphics2D object
        verify(mockGraphics2D, never()).drawImage(any(), anyInt(), anyInt(), anyInt(), anyInt(), any());
    }

    @Test
    void testGetPlayerAvaliable() {
        Tile tile = new Tile(new Position(0, 0));

        // Initially, player availability should be true
        assertTrue(tile.getPlayerAvaliable());
    }

    @Test
    void testInitialTileImage() {
        Tile tile = new Tile(new Position(0, 0));

        // Initially, the tile image should not be null
        assertNotNull(tile.tileImage);
    }

}
