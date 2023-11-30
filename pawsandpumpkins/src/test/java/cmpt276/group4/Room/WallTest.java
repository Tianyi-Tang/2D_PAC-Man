package cmpt276.group4.Room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import cmpt276.group4.Position;
import cmpt276.group4.GameMap.RecordUsedPlace;
import cmpt276.group4.Logic.GameConfig;
import cmpt276.group4.Logic.WindowConfig;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import cmpt276.group4.Room.Wall;

class WallTest {

    private Wall wall;
    private Position mockPosition;
    private GameConfig mockGameConfig;

    @BeforeEach
    void setUp() {
        mockPosition = mock(Position.class);
        wall = new Wall(mockPosition);
        mockGameConfig = mock(GameConfig.class);
    }

    // @Test
    // void testGetImageNameForPosition() {
    //     // Mock positions
    //     Position mockPosition = new Position(10, 20);
    //     Position northPosition = new Position(10, 10);
    //     Position southPosition = new Position(10, 30);
    //     Position eastPosition = new Position(30, 20);
    //     Position westPosition = new Position(0, 20);

    //     // Stub the behavior of GameConfig.getWallPositions
    //     List<Position> mockWallPositions = new ArrayList<>();
    //     mockWallPositions.add(northPosition);
    //     mockWallPositions.add(southPosition);
    //     mockWallPositions.add(eastPosition);
    //     mockWallPositions.add(westPosition);
    //     when(mockGameConfig.getWallPositions()).thenReturn(mockWallPositions);

    //     // Invoke the method under test
    //     String result = wall.getImageNameForPosition(mockPosition, mockWallPositions);

    //     // Assert the result
    //     assertTrue(result.contains("north"));
    //     assertTrue(result.contains("east"));
    //     assertTrue(result.contains("south"));
    //     assertTrue(result.contains("west"));
    //     assertFalse(result.contains("")); // Make sure there are no empty strings
    // }

    @Test
    void testDraw1() {
        // Mock dependencies
        Graphics2D mockGraphics2D = mock(Graphics2D.class);
        wall.wallImage = mock(BufferedImage.class);

        wall.draw(mockGraphics2D);

        // Verify that the drawImage method is called on the Graphics2D context
        verify(mockGraphics2D, times(1)).drawImage(wall.wallImage, mockPosition.getX_axis(), mockPosition.getY_axis(),
                WindowConfig.tileSize, WindowConfig.tileSize, null);
    }

    @Test
    void testDraw2() {
         // Mock dependencies
        Graphics2D mockGraphics2D = mock(Graphics2D.class);
        wall.wallImage = null; // Set wallImage to null

        wall.draw(mockGraphics2D);

        // Verify that the drawImage method is not called when wallImage is null
        verify(mockGraphics2D, never()).drawImage(any(), anyInt(), anyInt(), anyInt(), anyInt(), any());
    }
}
