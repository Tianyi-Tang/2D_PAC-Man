package cmpt276.group4.GameMap;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cmpt276.group4.CharacterAvaliablePosition;
import cmpt276.group4.Position;
import cmpt276.group4.Logic.WindowConfig;
import cmpt276.group4.Room.Tile;
import cmpt276.group4.Room.Wall;

public class RoomLayoutTest {
    RoomLayout roomLayout;
    RecordUsedPlace record;

    @BeforeEach
    public void setUP(){
        roomLayout = new RoomLayout();
        record = new RecordUsedPlace();
        roomLayout.init(record);
    }

    /**
     * 
     */
    @Test
    public void addWall(){
        Position wallPosition = new Position(WindowConfig.tileSize*2, WindowConfig.tileSize);
        record.addAviable(wallPosition);
        roomLayout.addElementInMap(new Wall(wallPosition));
        assertEquals(1, roomLayout.getWallNumber());
    }

    @Test
    public void addTiles(){
        ArrayList<CharacterAvaliablePosition> allItem = new ArrayList<CharacterAvaliablePosition>();

        Position tilePosition;
        Tile tile;
        for(int i =0;i < 3;i++){
            tilePosition = new Position(i * WindowConfig.tileSize, 1 * WindowConfig.tileSize);
            record.addAviable(tilePosition);
            tile = new Tile(tilePosition);
            roomLayout.addElementInMap(tile);
            allItem.add(tile);
        }

        for(int i = 0;i < allItem.size();i++){
            assertEquals(allItem.get(i), roomLayout.getElements().get(i));
        }
    }

    @Test
    public void moveToWall(){
        Position wallPosition = new Position(WindowConfig.tileSize * 2, 0);
        record.addAviable(wallPosition);
        roomLayout.addElementInMap( new Wall(wallPosition));
        assertEquals(false, roomLayout.isPositionAviable(wallPosition));
    }

    @Test
    public void moveOutOfScreenMin(){
        Position outofScreenPos = new Position(- WindowConfig.tileSize, 0);
        assertEquals(false, record.isPlaceAviable(outofScreenPos));
    }

    @Test
    public void moveOutOfScreenMax(){
        Position outofScreenPos = new Position( WindowConfig.tileSize * 3, WindowConfig.tileSize * (WindowConfig.maxScreenRow+1));
        assertEquals(false, record.isPlaceAviable(outofScreenPos));
    }

}
