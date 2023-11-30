package cmpt276.group4.Logic;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cmpt276.group4.Position;
import cmpt276.group4.gameLevel;

/**
 * This class contains unit tests for the GameConfig class in the game.
 * It covers various scenarios related to game configuration settings,
 * ensuring the correct behavior of the singleton instance, game levels,
 * initialization checks,
 * and retrieval of game settings such as wall positions, number of rewards, and
 * enemies.
 */
class GameConfigTest {

    private GameConfig gameConfig;

    @BeforeEach
    void setUp() {
        GameConfig.setInstance(null);
        gameConfig = GameConfig.getGameConfigInstance();
    }

    /**
     * Tests the singleton behavior of the GameConfig class.
     */
    @Test
    void testSingletonInstance() {
        GameConfig anotherInstance = GameConfig.getGameConfigInstance();
        assertSame(gameConfig, anotherInstance, "Singleton instances should be the same");
    }

    /**
     * Tests setting and getting the BASIC game level.
     */
    @Test
    void testPassGameLevelBasic() {
        gameConfig.passGameLevel(gameLevel.BASIC);
        assertEquals(gameLevel.BASIC, gameConfig.getGameLevel(), "Game level should be BASIC");
    }

    /**
     * Tests setting and getting the MEDIUM game level.
     */
    @Test
    void testPassGameLevelMedium() {
        gameConfig.passGameLevel(gameLevel.MEDIUM);
        assertEquals(gameLevel.MEDIUM, gameConfig.getGameLevel(), "Game level should be MEDIUM");
    }

    /**
     * Tests setting and getting the HARD game level.
     */
    @Test
    void testPassGameLevelHard() {
        gameConfig.passGameLevel(gameLevel.HARD);
        assertEquals(gameLevel.HARD, gameConfig.getGameLevel(), "Game level should be HARD");
    }

    /**
     * Tests the initialization status of the game configuration.
     */
    @Test
    void testAlreadyInitialize() {
        assertFalse(gameConfig.alreayInitialize(), "Should return false when not initialized");
        gameConfig.passGameLevel(gameLevel.BASIC);
        assertTrue(gameConfig.alreayInitialize(), "Should return true when initialized");
    }

    /**
     * Tests various getters for game configuration after setting a game level.
     */
    @Test
    void testGetters() {
        gameConfig.passGameLevel(gameLevel.BASIC);
        assertNotNull(gameConfig.getWallPositions(), "Wall positions should not be null");
        assertTrue(gameConfig.getNumberOfWall() >= 0, "Number of walls should be non-negative");
        assertTrue(gameConfig.getNumberOfObstacles() >= 0, "Number of obstacles should be non-negative");
    }

    /**
     * Tests calculating the area of the game room.
     */
    @Test
    void testAreaOfRoom() {
        int expectedArea = gameConfig.getRoomRow() * gameConfig.getRoomColumn();
        assertEquals(expectedArea, gameConfig.areaofRoom(),
                "Area of room should match the product of rows and columns");
    }

    /**
     * Tests calculating the total number of rewards.
     */
    @Test
    void testGetAllRewardNum() {
        gameConfig.passGameLevel(gameLevel.BASIC);
        int expectedTotalRewards = gameConfig.getNumberOfRegularRewards() + gameConfig.getNumberOfBonusRewards();
        assertEquals(expectedTotalRewards, gameConfig.getAllRewardNum(),
                "Total rewards should be the sum of regular and bonus rewards");
    }

    /**
     * Tests getting the number of spiders for a game level.
     */
    @Test
    void testGetNumberOfSpiders() {
        gameConfig.passGameLevel(gameLevel.BASIC);
        assertTrue(gameConfig.getNumberOfSpiders() >= 0, "Number of spiders should be non-negative");
    }

    /**
     * Tests getting the number of basic ghosts for a game level.
     */
    @Test
    void testGetNumberOfBasicGhosts() {
        gameConfig.passGameLevel(gameLevel.BASIC);
        assertTrue(gameConfig.getNumberOfBasicGhosts() >= 0, "Number of basic ghosts should be non-negative");
    }

    /**
     * Tests getting the number of advanced ghosts for a game level.
     */
    @Test
    void testGetNumberOfAdvancedGhosts() {
        gameConfig.passGameLevel(gameLevel.BASIC);
        assertTrue(gameConfig.getNumberOfAdvancedGhosts() >= 0, "Number of advanced ghosts should be non-negative");
    }

    /**
     * Tests calculating the total number of enemies (ghosts and spiders).
     */
    @Test
    void testGetTotalGhosts() {
        gameConfig.passGameLevel(gameLevel.BASIC);
        int expectedTotalGhosts = gameConfig.getNumberOfBasicGhosts() + gameConfig.getNumberOfAdvancedGhosts()
                + gameConfig.getNumberOfSpiders();
        assertEquals(expectedTotalGhosts, gameConfig.getTotalEnemy(),
                "Total ghosts should be the sum of basic, advanced ghosts, and spiders");
    }

    /**
     * Tests initialization status when all conditions are true.
     */
    @Test
    void testAlreadyInitialize_AllConditionsTrue() {
        gameConfig.getWallPositions().add(new Position(0, 0));
        gameConfig.passGameLevel(gameLevel.BASIC);

        assertTrue(gameConfig.alreayInitialize(), "Should return true when both conditions are met");
    }

    /**
     * Tests the alreadyInitialize method when wall positions are set to null.
     * It checks that the game configuration correctly reports that it has not been
     * initialized.
     */
    @Test
    void testAlreadyInitialize_WallPositionsNull() {

        gameConfig.passGameLevel(gameLevel.BASIC);
        gameConfig.setWallPositions(null);
        assertFalse(gameConfig.alreayInitialize(), "Should return false when wallPositions are null");
    }

    /**
     * Tests the alreadyInitialize method when the number of regular rewards is
     * zero.
     * It checks that the game configuration correctly reports that it has not been
     * initialized.
     */
    @Test
    void testAlreadyInitialize_ZeroRegularRewards() {

        gameConfig.getWallPositions().add(new Position(0, 0));
        gameConfig.setNumberOfRegularRewards(0);

        assertFalse(gameConfig.alreayInitialize(), "Should return false when there are zero regular rewards");
    }

    /**
     * Tests the alreadyInitialize method when both wall positions are null and the
     * number of regular rewards is zero.
     * It checks that the game configuration correctly reports that it has not been
     * initialized in both cases.
     */
    @Test
    void testAlreadyInitialize_BothConditionsFalse() {

        gameConfig.setWallPositions(null);

        assertFalse(gameConfig.alreayInitialize(), "Should return false when both conditions are not met");
    }

}
