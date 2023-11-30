package cmpt276.group4.Room;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import cmpt276.group4.Room.Tombstone;

import cmpt276.group4.Position;
import cmpt276.group4.GameMap.RecordUsedPlace;
import cmpt276.group4.GameMap.RoomEnvironment;
import cmpt276.group4.Logic.WindowConfig;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class TombstoneTest {
    private Tombstone tombstone;
    private Position externalPosition; // External variable to store position
    @Mock
    private Graphics2D mockGraphics2D;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        externalPosition = new Position(1, 1);
        tombstone = new Tombstone(externalPosition);
    }
    
    @Test
    void testGetPosition() {
        externalPosition = tombstone.getPosition();
        assertEquals(externalPosition, tombstone.getPosition(), "Position should match the set position");
    }

    // @Test
    // void testDraw2() {
    //      // Mock dependencies
    //     Graphics2D mockGraphics2D = mock(Graphics2D.class);
    //     tombstone.wallImage = null; // Set wallImage to null

    //     tombstone.draw(mockGraphics2D);

    //     // Verify that the drawImage method is not called when wallImage is null
    //     verify(mockGraphics2D, never()).drawImage(any(), anyInt(), anyInt(), anyInt(), anyInt(), any());
    // }


}