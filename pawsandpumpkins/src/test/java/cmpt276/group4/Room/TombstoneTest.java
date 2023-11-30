package cmpt276.group4.Room;

import cmpt276.group4.GameMap.RecordUsedPlace;
import cmpt276.group4.Position;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TombstoneTest {

    @Test
    void constructorInitializesPosition() {
        RecordUsedPlace mockRecord = mock(RecordUsedPlace.class);
        Position mockPosition = new Position(5, 5);
        when(mockRecord.getRandomFromAvailablePosition()).thenReturn(mockPosition);
        when(mockRecord.canPlaceEnemyAndObstacle(mockPosition)).thenReturn(true);

        RecordUsedPlace.setInstance(mockRecord);

        Tombstone tombstone = new Tombstone(mockPosition);

        assertNotNull(tombstone.getPosition());
        assertEquals(mockPosition, tombstone.getPosition());
    }

    @Test
    void drawCallsDrawImage() {
        Tombstone tombstone = new Tombstone(new Position(5, 5));
        Graphics2D mockedGraphics = mock(Graphics2D.class);

        tombstone.draw(mockedGraphics);

        verify(mockedGraphics).drawImage(any(), anyInt(), anyInt(), anyInt(), anyInt(), isNull());
    }}