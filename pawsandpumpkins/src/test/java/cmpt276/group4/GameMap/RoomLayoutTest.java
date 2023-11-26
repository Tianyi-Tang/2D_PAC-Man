package cmpt276.group4.GameMap;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cmpt276.group4.CharacterAvaliablePosition;
import cmpt276.group4.Position;
import cmpt276.group4.Logic.WindowConfig;
import cmpt276.group4.Room.Obstacle;
import cmpt276.group4.Room.Tile;
import cmpt276.group4.Room.Tombstone;
import cmpt276.group4.Room.Wall;

public class RoomLayoutTest {
    RoomLayout roomLayout;
    RecordUsedPlace mockrecord;

    public Position availablePos;

    public enum RoomItemType{
        Wall,
        Tombstone,
        Tile
    }

    @BeforeEach
    public void setUP(){
        roomLayout = new RoomLayout();
        mockrecord = mock(RecordUsedPlace.class);

        availablePos = new Position(WindowConfig.tileSize, WindowConfig.tileSize);
        when(mockrecord.isPlaceAviable(availablePos)).thenReturn(true);

        roomLayout.init(mockrecord);
        RecordUsedPlace.getInstance().addAviable(availablePos);
        RoomEnvironment.getInstance().init(mockrecord);
    }

    /**
     * 
     */
    @Test
    public void successAddWall(){
        CharacterAvaliablePosition wall = addRoomItem(RoomItemType.Wall,availablePos);
        assertEquals(wall, roomLayout.getElements().get(0));
    }

    @Test
    public void failAddWall(){
        when(mockrecord.isPlaceAviable(availablePos)).thenReturn(false);
        addRoomItem(RoomItemType.Wall,availablePos);
        assertEquals(0, roomLayout.getWallNumber());
    }

    @Test
    public void successAddTile(){
        CharacterAvaliablePosition tile = addRoomItem(RoomItemType.Tile,availablePos);
        assertEquals(tile, roomLayout.getElements().get(0));
    }

    @Test
    public void failAddTile(){
        when(mockrecord.isPlaceAviable(availablePos)).thenReturn(false);
        addRoomItem(RoomItemType.Tile,availablePos);
        assertEquals(0, roomLayout.getTileNumber());
    }

    @Test
    public void successAddObstacle(){
        setCanPlaceEnemyAndObstacle_result(true,availablePos);
        CharacterAvaliablePosition tombstone = addRoomItem(RoomItemType.Tombstone,availablePos);
        assertEquals(tombstone, roomLayout.getElements().get(0));
    }

    @Test
    public void failAddObstacle(){
        setCanPlaceEnemyAndObstacle_result(false,availablePos);
        addRoomItem(RoomItemType.Tombstone,availablePos);
        assertEquals(0, roomLayout.getObstaclesNumber());
    }

    @Test
    public void moveToAviablePos(){
        addRoomItem(RoomItemType.Tile, availablePos);
        assertEquals(true, roomLayout.isPositionAviable(availablePos));
    }

    @Test
    public void moveToUnavailablePosition(){
        Position anotherPosition = new Position(WindowConfig.tileSize *3, 0);
        setCanPlaceEnemyAndObstacle_result(false, anotherPosition);
        addRoomItem(RoomItemType.Tombstone, anotherPosition);
        addRoomItem(RoomItemType.Wall,availablePos);
        
        assertEquals(false, roomLayout.isPositionAviable(availablePos)); 
    }

    @Test
    public void moveOutOfScreen_negativeX(){
        Position outofScreenPos = new Position(- WindowConfig.tileSize, 0);
        assertEquals(false, roomLayout.isPositionAviable(outofScreenPos));
    }

    @Test
    public void moveOutOfScreen_negativeY(){
        Position outofScreenPos = new Position(WindowConfig.tileSize, - WindowConfig.tileSize);
        assertEquals(false, roomLayout.isPositionAviable(outofScreenPos));
    }

    @Test
    public void moveOutOfScreen_postiveY(){
        Position outofScreenPos = new Position( WindowConfig.tileSize * 3, WindowConfig.tileSize * (WindowConfig.maxScreenRow+1));
        assertEquals(false, roomLayout.isPositionAviable(outofScreenPos));
    }

    @Test
    public void moveOutOfScreen_postiveX(){
        Position outofScreenPos = new Position( WindowConfig.tileSize * (WindowConfig.maxScreenRow+1), WindowConfig.tileSize );
        assertEquals(false, roomLayout.isPositionAviable(outofScreenPos));
    }


    private CharacterAvaliablePosition addRoomItem(RoomItemType type, Position position){
        CharacterAvaliablePosition roomItem;
        if(type == RoomItemType.Wall)
            roomItem= new Wall(position);
        else if(type == RoomItemType.Tombstone)
            roomItem = new Tombstone(position);
        else
            roomItem = new Tile(position);
        roomLayout.addElementInMap(roomItem);
        return roomItem;
    }

    private void setCanPlaceEnemyAndObstacle_result(boolean result,Position position){
        when(mockrecord.canPlaceEnemyAndObstacle(position)).thenReturn(result);
    }

}
