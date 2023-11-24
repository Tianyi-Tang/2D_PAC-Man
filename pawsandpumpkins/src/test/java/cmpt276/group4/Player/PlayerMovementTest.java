package cmpt276.group4.Player;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cmpt276.group4.Position;
import cmpt276.group4.GameMap.RoomEnvironment;
import cmpt276.group4.GameMap.RoomLayout;
import cmpt276.group4.Room.Tile;
import cmpt276.group4.Room.Tombstone;
import cmpt276.group4.Room.Wall;

public class PlayerMovementTest {
    public PlayerMovement movement;
    public RoomEnvironment mockRoomEnvironment;
    public RoomLayout roomLayout;
    public Position unaivailbePosition;

    @BeforeEach
    public void setUpMovement(){
        movement = new PlayerMovement();

        roomLayout = RoomLayout.getInstance();
        mockRoomEnvironment = mock(RoomEnvironment.class);

        unaivailbePosition = new Position(2* 48,48);
        roomLayout.addElementInMap(new Wall(unaivailbePosition));
        movement.init(roomLayout, mockRoomEnvironment, Player.getInstance());
    }


    @Test
    public void goToAviablePosition(){
        assertEquals(true,movement.isPositionAvailable(new Position(48, 48)));
    }

    @Test
    public void goToUnaviablePosition(){
        assertEquals(false, movement.isPositionAvailable(unaivailbePosition));
    }
}
