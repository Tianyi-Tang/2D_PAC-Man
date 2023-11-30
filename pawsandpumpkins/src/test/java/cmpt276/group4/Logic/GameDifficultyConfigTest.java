package cmpt276.group4.Logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cmpt276.group4.Position;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains unit tests for the GameDifficultyConfigurations in the
 * game.
 * It tests the initialization of various difficulty levels (Basic, Medium, and
 * Hard)
 * and verifies the correct configuration settings for each difficulty level.
 */
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

    /**
     * Tests the initialization of the BasicConfig.
     * It checks that the correct number of obstacles and spiders are set for the
     * Basic difficulty level.
     */
    @Test
    void testBasicConfigInitialization() {
        assertEquals(3, basicConfig.getNumberOfObstacles());
        assertEquals(3, basicConfig.getNumberOfSpiders());
        assertEquals(1, basicConfig.getNumberOfBasicGhosts());
        assertEquals(0, basicConfig.getNumberOfAdvancedGhosts());
        assertEquals(5, basicConfig.getNumberOfRegularRewards());
        assertEquals(1, basicConfig.getNumberOfBonusRewards());

    }

    /**
     * Tests the initialization of the MediumConfig.
     * It checks that the correct number of obstacles and spiders are set for the
     * Medium difficulty level.
     */
    @Test
    void testMediumConfigInitialization() {
        assertEquals(4, mediumConfig.getNumberOfObstacles());
        assertEquals(4, mediumConfig.getNumberOfSpiders());
        assertEquals(2, mediumConfig.getNumberOfBasicGhosts());
        assertEquals(0, mediumConfig.getNumberOfAdvancedGhosts());
        assertEquals(6, mediumConfig.getNumberOfRegularRewards());
        assertEquals(3, mediumConfig.getNumberOfBonusRewards());
    }

    /**
     * Tests the initialization of the HardConfig.
     * It checks that the correct number of obstacles and spiders are set for the
     * Hard difficulty level.
     */
    @Test
    void testHardConfigInitialization() {
        assertEquals(5, hardConfig.getNumberOfObstacles());
        assertEquals(5, hardConfig.getNumberOfSpiders());
        assertEquals(3, hardConfig.getNumberOfBasicGhosts());
        assertEquals(0, hardConfig.getNumberOfAdvancedGhosts());
        assertEquals(10, hardConfig.getNumberOfRegularRewards());
        assertEquals(3, hardConfig.getNumberOfBonusRewards());
    }

}
