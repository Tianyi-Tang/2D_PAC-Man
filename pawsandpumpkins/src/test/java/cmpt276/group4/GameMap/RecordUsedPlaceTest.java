package cmpt276.group4.GameMap;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cmpt276.group4.Position;
import cmpt276.group4.Logic.WindowConfig;

public class RecordUsedPlaceTest {
    public RecordUsedPlace record;

    /**
     * set up the RecordUsedPlace before each test cause run
     */
    @BeforeEach
    public void setUp(){
        record = new RecordUsedPlace();
    }

    /**
     * Test the position can add the to aviable position and other class can use
     * getRandomFromAvailablePosition function to get aviable position
     */
    @Test
    public void addAviableToRecord(){
        Position availblePos = new Position(12, 35);
        record.addAviable(availblePos);
        assertEquals(availblePos, record.getRandomFromAvailablePosition());
    }

    /**
     * Test the add multiple position to aviable and get from record
     */
    @Test void addThreeAviable(){
        ArrayList<Position> positions = new ArrayList<Position>();
        Position position;
        for(int i=0;i < 3;i++){
            position = new Position(i * WindowConfig.tileSize, WindowConfig.tileSize);
            record.addAviable(position);
            positions.add(position);
        }

        ArrayList<Position> acutalPositions = record.getAviablePosition();
        for(int i=0; i< 3;i++){
            assertEquals(positions.get(i), acutalPositions.get(i));
        }
    }

    /**
     * Test the record will reject the same position adding to aviable 
     */
    @Test
    public void rejectSameAviableToReocrd(){
        Position position1 = new Position(WindowConfig.tileSize, WindowConfig.tileSize);
        Position position2 = new Position(WindowConfig.tileSize, WindowConfig.tileSize);
        record.addAviable(position1);
        record.addAviable(position2);
        assertEquals(1, record.getLengthOfAviable());
    }

    /**
     * Test the after adding position to aviable, record can remove it from
     * aviable 
     */
    @Test
    public void removeFromAviable(){
        Position position = new Position(WindowConfig.tileSize * 2, WindowConfig.tileSize);
        record.addAviable(position);
        record.removeFromAviable(position);
        assertEquals(null, record.getRandomFromAvailablePosition());
    }
    /**
     * Test record can check the position is near to player
     */
    @Test
    public void playerNear(){
        Position nearByPos = new Position(WindowConfig.tileSize * 2, WindowConfig.tileSize);
        assertEquals(true, record.isPlayerNearBy(4 * WindowConfig.tileSize,nearByPos));
    }

    
}
