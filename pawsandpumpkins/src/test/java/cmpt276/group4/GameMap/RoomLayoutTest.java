package cmpt276.group4.GameMap;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cmpt276.group4.CharacterAvaliablePosition;
import cmpt276.group4.Position;
import cmpt276.group4.Logic.WindowConfig;
import cmpt276.group4.Room.Tile;
import cmpt276.group4.Room.Tombstone;
import cmpt276.group4.Room.Wall;

/**
 * Class for room layout unit test
 */
public class RoomLayoutTest {
    RoomLayout roomLayout;
    RecordUsedPlace mockrecord;

    public Position availablePos;

    public enum RoomItemType{
        Wall,
        Tile
    }

    /**
     * set up the roomlayout for testing
     */
    @BeforeEach
    public void setUP(){
        roomLayout = new RoomLayout();
        mockrecord = mock(RecordUsedPlace.class);

        availablePos = new Position(WindowConfig.tileSize, WindowConfig.tileSize);
        when(mockrecord.isPlaceAviable(availablePos)).thenReturn(true);

        roomLayout.init(mockrecord);
        RoomEnvironment.getInstance().init(mockrecord);
    }

    /**
     * Test room layout can add to the wall if the position is available
     */
    @Test
    public void successAddWall(){
        CharacterAvaliablePosition wall = addRoomItem(RoomItemType.Wall,availablePos);
        assertEquals(wall, roomLayout.getElements().get(0));
    }

    /**
     * Test room layout will reject adding wall if the position is not available
     */
    @Test
    public void failAddWall(){
        when(mockrecord.isPlaceAviable(availablePos)).thenReturn(false);
        addRoomItem(RoomItemType.Wall,availablePos);
        assertEquals(0, roomLayout.getWallNumber());
    }

    /**
     * Test room layout can add to the tile if the position is available
     */
    @Test
    public void successAddTile(){
        CharacterAvaliablePosition tile = addRoomItem(RoomItemType.Tile,availablePos);
        assertEquals(tile, roomLayout.getElements().get(0));
    }

    /**
     * Test room layout will reject adding tile if position is not available
     */
    @Test
    public void failAddTile(){
        when(mockrecord.isPlaceAviable(availablePos)).thenReturn(false);
        addRoomItem(RoomItemType.Tile,availablePos);
        assertEquals(0, roomLayout.getTileNumber());
    }

    /**
     * Test room layout will add obstacle if position is avaible and not block
     * some key point that player will eneter 
     */
    @Test
    public void successAddObstacle(){
        setCanPlaceEnemyAndObstacle_result(true,availablePos);

        addTomestone(availablePos);
        assertEquals(1, roomLayout.getObstaclesNumber());
    }

    /**
     * Test room layout reject adding obstacle if position is available but block
     * player to go to some area
     */
    @Test
    public void failAddObstacle(){
        setCanPlaceEnemyAndObstacle_result(false,availablePos);
        addTomestone(availablePos);
        assertEquals(0, roomLayout.getObstaclesNumber());
    }

    @Test
    public void AddObstacleUnavaliable(){
        when(mockrecord.isPlaceAviable(availablePos)).thenReturn(false);
        addTomestone(availablePos);
        assertEquals(0, roomLayout.getObstaclesNumber());
    }


    /**
     * Test room layout will allow character move to that position if 
     * the it is not in unaviable list
     */
    @Test
    public void moveToAviablePos(){
        addRoomItem(RoomItemType.Tile, availablePos);
        assertEquals(true, roomLayout.isPositionAviable(availablePos));
    }

    /**
     * Test room layout will reject character move to that position if 
     * the position is not in unaviable list
     */
    @Test
    public void moveToUnavailablePosition(){
        Position anotherPosition = new Position(WindowConfig.tileSize *3, 0);
        when(mockrecord.isPlaceAviable(anotherPosition)).thenReturn(true);
        addRoomItem(RoomItemType.Wall, anotherPosition);
        addRoomItem(RoomItemType.Wall,availablePos);
        
        assertEquals(false, roomLayout.isPositionAviable(availablePos)); 
    }

    /**
     * Test room layout will reject character move to that position if 
     * position x-axis have negatvie value 
     */
    @Test
    public void moveOutOfScreen_negativeX(){
        Position outofScreenPos = new Position(- WindowConfig.tileSize, 0);
        assertEquals(false, roomLayout.isPositionAviable(outofScreenPos));
    }

    /**
    * Test room layout will reject character move to that position if 
    * position y-axis have negatvie value
     */
    @Test
    public void moveOutOfScreen_negativeY(){
        Position outofScreenPos = new Position(WindowConfig.tileSize, - WindowConfig.tileSize);
        assertEquals(false, roomLayout.isPositionAviable(outofScreenPos));
    }

    /**
    * Test room layout will reject character move to that position if 
    * position x-axis are bigger than screen width value 
     */
    @Test
    public void moveOutOfScreen_postiveY(){
        Position outofScreenPos = new Position( WindowConfig.tileSize * 3, WindowConfig.tileSize * (WindowConfig.maxScreenRow+1));
        assertEquals(false, roomLayout.isPositionAviable(outofScreenPos));
    }

    /**
    * Test room layout will reject character move to that position if 
    * position y-axis are bigger than screen height value 
     */
    @Test
    public void moveOutOfScreen_postiveX(){
        Position outofScreenPos = new Position( WindowConfig.tileSize * (WindowConfig.maxScreenRow+1), WindowConfig.tileSize );
        assertEquals(false, roomLayout.isPositionAviable(outofScreenPos));
    }

    /**
     * Create the mock tomestone and add the position to the roomlayout
     * @param position the position of tomestone
     */
    private void addTomestone(Position position){
        Tombstone mockTombstone = mock(Tombstone.class);
        when(mockTombstone.getPosition()).thenReturn(position);
        roomLayout.addElementInMap(mockTombstone);
    }

    /**
     * Create a wall or tile to add into roomlayout
     * @param type want to add wall or tile to room
     * @param position the position of that object
     * @return the object that create
     */
    private CharacterAvaliablePosition addRoomItem(RoomItemType type, Position position){
        CharacterAvaliablePosition roomItem;
        if(type == RoomItemType.Wall)
            roomItem= new Wall(position);
        else
            roomItem = new Tile(position);
            
        roomLayout.addElementInMap(roomItem);
        return roomItem;
    }

    /**
     * Set the mock RecordUsedPlace to allow or reject the position can place the obstacle
     * @param result allow or reject place obstacle in that position
     * @param position the ppsition you want to set
     */
    private void setCanPlaceEnemyAndObstacle_result(boolean result,Position position){
        when(mockrecord.canPlaceEnemyAndObstacle(position)).thenReturn(result);
    }

}