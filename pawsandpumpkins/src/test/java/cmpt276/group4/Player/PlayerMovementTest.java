package cmpt276.group4.Player;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cmpt276.group4.Position;
import cmpt276.group4.GameMap.RecordUsedPlace;
import cmpt276.group4.GameMap.RoomEnvironment;
import cmpt276.group4.GameMap.RoomLayout;
import cmpt276.group4.Reward.Candy;
import cmpt276.group4.Room.Room;
import cmpt276.group4.Room.Tile;
import cmpt276.group4.Room.Tombstone;
import cmpt276.group4.Room.Wall;

public class PlayerMovementTest {
    public PlayerMovement movement;

    public RoomEnvironment roomEnvironment;
    public RecordUsedPlace record;
    public RoomLayout roomLayout;

    public Position wallPosition;

    @BeforeEach
    public void setUpMovement(){
        movement = new PlayerMovement();

        record = new RecordUsedPlace();
        roomLayout = new RoomLayout();
        roomEnvironment = new RoomEnvironment();

        roomLayout.init(record);
        roomEnvironment.init(record);

        wallPosition = new Position(2* 48,48);
        record.addAviable(wallPosition);
        roomLayout.addElementInMap(new Wall(wallPosition));
        movement.init(roomLayout, roomEnvironment, Player.getInstance());
    }


    @Test
    public void goToAviablePosition(){
        assertEquals(true,movement.isPositionAvailable(new Position(48, 48)));
    }

    @Test
    public void goToUnaviablePosition(){
        assertEquals(false, movement.isPositionAvailable(wallPosition));
    }

}
