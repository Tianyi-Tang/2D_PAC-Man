// package cmpt276.group4.Room;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import cmpt276.group4.Position;
// import cmpt276.group4.GameMap.RecordUsedPlace;

// import java.util.ArrayList;
// import java.util.List;

// class RoomFactoryTest {
//     private RoomFactory roomFactory;
//     private RecordUsedPlace mockRecord;

//     @BeforeEach
//     void setUp() {
//         mockRecord = mock(RecordUsedPlace.class);
//         RecordUsedPlace.setInstance(mockRecord);
//         roomFactory = new RoomFactory();
//     }

//     @Test
//     void testCreateRoom() {
//         assertNotNull(roomFactory.createRoom(), "Room should not be null");
//     }

//     @Test
//     void testCreateTombstones() {
//         Position position = new Position(1, 1);
//         int amount = 3;

//         // Set up any necessary behavior on the mockRecord

//         roomFactory.createTombstones(position, amount);

//         // Add assertions to verify that the tombstones are created in the RoomLayout
//         // You may need to have a method in RoomLayout to check the existence of elements.
//         // For example:
//         // assertTrue(RoomLayout.getInstance().hasElementAt(position, Tombstone.class));

//         // Verify that RoomLayout.getInstance().addElementInMap is called the correct number of times
//         // For example:
//         // verify(RoomLayout.getInstance(), times(amount)).addElementInMap(any());
//     }

//     @Test
//     void testCreateWall() {
//         List<Position> positions = new ArrayList<>();
//         positions.add(new Position(1, 1));
//         int amount = 2;

//         // Set up any necessary behavior on the mockRecord

//         roomFactory.createWall(positions, amount);

//         // Add assertions to verify that the walls are created in the RoomLayout
//         // Similar to the createTombstones method
//     }

//     @Test
//     void testCreateTile() {
//         List<Position> positions = new ArrayList<>();
//         positions.add(new Position(1, 1));
//         positions.add(new Position(2, 2));

//         // Set up any necessary behavior on the mockRecord

//         roomFactory.createTile(positions);

//         // Add assertions to verify that the tiles are created in the RoomLayout
//         // Similar to the createTombstones method
//     }
// }
