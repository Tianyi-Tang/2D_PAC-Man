package cmpt276.group4.Logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cmpt276.group4.Position;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class GameDifficultyConfigTest {

    private BasicConfig basicConfig;
    private MediumConfig mediumConfig;
    private HardConfig hardConfig;

    @BeforeEach
    void setUp() {
        basicConfig = new BasicConfig();
        mediumConfig = new MediumConfig();
        hardConfig = new HardConfig();
    }

    @Test
    void testBasicConfigInitialization() {
        assertEquals(3, basicConfig.getNumberOfObstacles());
        assertEquals(3, basicConfig.getNumberOfSpiders());
        // ... additional assertions for other fields
    }

    @Test
    void testMediumConfigInitialization() {
        assertEquals(4, mediumConfig.getNumberOfObstacles());
        assertEquals(4, mediumConfig.getNumberOfSpiders());
        // ... additional assertions for other fields
    }

    @Test
    void testHardConfigInitialization() {
        assertEquals(5, hardConfig.getNumberOfObstacles());
        assertEquals(5, hardConfig.getNumberOfSpiders());
        // ... additional assertions for other fields
    }

    // @Test
    // void testSwitchMapFunctionality() {
    //     // You might need to expose the switchMap method as public for testing
    //     // Alternatively, you can test this indirectly via the constructor
    //     assertEquals(expectedWallPositionsForBasicConfig, basicConfig.getWallPositions());
    //     assertEquals(expectedWallPositionsForMediumConfig, mediumConfig.getWallPositions());
    //     assertEquals(expectedWallPositionsForHardConfig, hardConfig.getWallPositions());
    // }

    // Helper method to create expected list of wall positions based on maze arrays
    private List<Position> createExpectedWallPositions(int[] xArray, int[] yArray) {
        List<Position> positions = new ArrayList<>();
        for (int i = 0; i < xArray.length; i++) {
            positions.add(new Position(xArray[i], yArray[i]));
        }
        return positions;
    }
}
