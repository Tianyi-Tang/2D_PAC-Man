package cmpt276.group4.Room;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cmpt276.group4.GameMap.RecordUsedPlace;
import cmpt276.group4.Position;
import cmpt276.group4.Logic.GameConfig;
import cmpt276.group4.Logic.WindowConfig;

class RoomTest {

    @Test
    void testGetDoorPosition() {
        Room room = Room.getInstance();
        Position doorPosition = new Position(1 * WindowConfig.tileSize, 0);
        room.setDoorPosition(doorPosition);

        assertEquals(doorPosition, room.getDoorPosition(), "Door position should match the set position");
    }

    @Test
    void testGetObstacles() {
        Room room = Room.getInstance();
        Obstacle[] obstacles = { new Tombstone(new Position(1, 1)), new Wall(new Position(2, 2)) };
        room.setObstacles(obstacles);

        assertArrayEquals(obstacles, room.getObstacles(), "Obstacles array should match the set obstacles");
    }

    @Test
    void testSetDoorPosition() {
        Room room = Room.getInstance();
        Position doorPosition = new Position(3 * WindowConfig.tileSize, 3 * WindowConfig.tileSize);
        room.setDoorPosition(doorPosition);

        assertEquals(doorPosition, room.getDoorPosition(), "Door position should match the set position");
    }

    @Test
    void testSetObstacles() {
        Room room = Room.getInstance();
        Obstacle[] obstacles = { new Tombstone(new Position(1, 1)), new Wall(new Position(2, 2)) };
        room.setObstacles(obstacles);

        assertArrayEquals(obstacles, room.getObstacles(), "Obstacles array should match the set obstacles");
    }
}

