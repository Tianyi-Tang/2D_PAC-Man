package cmpt276.group4.GameMap;

import static org.junit.Assert.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cmpt276.group4.Position;
import cmpt276.group4.Logic.GameConfig;
import cmpt276.group4.Logic.WindowConfig;

public class RecordUsedPlaceTest {
    public RecordUsedPlace record;

    @BeforeEach
    public void setUp(){
        record = new RecordUsedPlace();
    }

    @Test
    public void addAviableToRecord(){
        Position availblePos = new Position(12, 35);
        record.addAviable(availblePos);
        assertEquals(availblePos, record.getRandomFromAvailablePosition());
    }

    @Test
    public void rejectSameAviableToReocrd(){
        Position position1 = new Position(WindowConfig.tileSize, WindowConfig.tileSize);
        Position position2 = new Position(WindowConfig.tileSize, WindowConfig.tileSize);
        record.addAviable(position1);
        record.addAviable(position2);
        assertEquals(1, record.getLengthOfAviable());
    }

    @Test
    public void removeFromAviable(){
        Position position = new Position(WindowConfig.tileSize * 2, WindowConfig.tileSize);
        record.addAviable(position);
        assertEquals(1, record.getAviablePosition().size());
        record.removeFromAviable(position);
        assertEquals(null, record.getRandomFromAvailablePosition());
    }

    @Test
    public void playerNear(){
        Position nearByPos = new Position(WindowConfig.tileSize * 2, WindowConfig.tileSize);
        assertEquals(true, record.isPlayerNearBy(4 * WindowConfig.tileSize,nearByPos));
    }

    
}
