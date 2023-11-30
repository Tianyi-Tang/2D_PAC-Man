// package cmpt276.group4.Room;


// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mock;
// import org.mockito.MockedStatic;
// import org.mockito.MockitoAnnotations;

// import cmpt276.group4.Room.Tombstone;

// import cmpt276.group4.Position;
// import cmpt276.group4.GameMap.RecordUsedPlace;
// import cmpt276.group4.GameMap.RoomEnvironment;
// import cmpt276.group4.Logic.WindowConfig;

// import java.awt.Graphics2D;
// import java.awt.image.BufferedImage;
// import java.io.File;

// import javax.imageio.ImageIO;

// public class TombstoneTest {
//     public Tombstone tombstone;
//     public Position mockPosition;

//     @BeforeEach
//     void setUp() {
//         MockitoAnnotations.openMocks(this);
//         tombstone = new Tombstone(mockPosition);
//     }

//     @Test
//     void testDraw1() {
//         // Mock dependencies
//         Graphics2D mockGraphics2D = mock(Graphics2D.class);
//         tombstone.tombImage = mock(BufferedImage.class);

//         tombstone.draw(mockGraphics2D);

//         // Verify that the drawImage method is called on the Graphics2D context
//         verify(mockGraphics2D, times(1)).drawImage(tombstone.tombImage, mockPosition.getX_axis(), mockPosition.getY_axis(),
//                 WindowConfig.tileSize, WindowConfig.tileSize, null);
//     }

//     @Test
//     void testDraw2() {
//         // Mock dependencies
//         Graphics2D mockGraphics2D = mock(Graphics2D.class);
//         tombstone.tombImage = null; // Set tombImage to null

//         tombstone.draw(mockGraphics2D);

//         // Verify that the drawImage method is not called when tombImage is null
//         verify(mockGraphics2D, never()).drawImage(any(), anyInt(), anyInt(), anyInt(), anyInt(), any());
//     }
// }
