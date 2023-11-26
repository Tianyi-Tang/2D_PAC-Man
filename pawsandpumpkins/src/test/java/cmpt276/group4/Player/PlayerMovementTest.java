package cmpt276.group4.Player;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import cmpt276.group4.Position;
import cmpt276.group4.GameMap.RecordUsedPlace;
import cmpt276.group4.GameMap.RoomEnvironment;
import cmpt276.group4.GameMap.RoomLayout;
import cmpt276.group4.Logic.WindowConfig;
import cmpt276.group4.Reward.Candy;
import cmpt276.group4.Reward.Reward;
import cmpt276.group4.Room.Wall;

public class PlayerMovementTest {
    public PlayerMovement movement;

    public RoomEnvironment mockRoomEnvironment;
    public RecordUsedPlace record;
    public RoomLayout roomLayout;

    public Position availblePos;

    @BeforeEach
    public void setUpMovement(){
        movement = new PlayerMovement();

        record = new RecordUsedPlace();
        roomLayout = new RoomLayout();
        mockRoomEnvironment = mock(RoomEnvironment.class);

        roomLayout.init(record);
        availblePos = new Position(WindowConfig.tileSize,WindowConfig.tileSize);
        record.addAviable(availblePos);
        
        movement.init(roomLayout, mockRoomEnvironment, Player.getInstance());
    }


    @Test
    public void goToAviablePosition(){
        assertEquals(true,movement.isPositionAvailable(availblePos));
    }

    @Test
    public void goToUnaviablePosition(){
        roomLayout.addElementInMap(new Wall(availblePos));
        assertEquals(false, movement.isPositionAvailable(availblePos));
    }

    @Test
    public void collectReward(){
        Reward mockCandy = mock(Candy.class);
        when(mockRoomEnvironment.collectReward()).thenReturn(mockCandy);

        movement.checkReward();
        verify(mockCandy).addBenefit(Player.getInstance());
    }

    @Test
    public void failToCollect(){
        Reward mockReward = Mockito.mock(Reward.class);
        movement.checkReward();
        Mockito.verify(mockReward, Mockito.times(0)).addBenefit(Player.getInstance());
    }

}
