package cmpt276.group4.Logic;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cmpt276.group4.Position;
import cmpt276.group4.gameLevel;

class GameConfigTest {

    private GameConfig gameConfig;
    

    @BeforeEach
    void setUp() {
        GameConfig.setInstance(null);
        gameConfig = GameConfig.getGameConfigInstance();
    }

    @Test
    void testSingletonInstance() {
        GameConfig anotherInstance = GameConfig.getGameConfigInstance();
        assertSame(gameConfig, anotherInstance, "Singleton instances should be the same");
    }

    @Test
    void testPassGameLevelBasic() {
        gameConfig.passGameLevel(gameLevel.BASIC);
        assertEquals(gameLevel.BASIC, gameConfig.getGameLevel(), "Game level should be BASIC");
    }

    @Test
    void testPassGameLevelMedium() {
        gameConfig.passGameLevel(gameLevel.MEDIUM);
        assertEquals(gameLevel.MEDIUM, gameConfig.getGameLevel(), "Game level should be MEDIUM");
    }

    @Test
    void testPassGameLevelHard() {
        gameConfig.passGameLevel(gameLevel.HARD);
        assertEquals(gameLevel.HARD, gameConfig.getGameLevel(), "Game level should be HARD");
    }

    @Test
    void testAlreadyInitialize() {
        assertFalse(gameConfig.alreayInitialize(), "Should return false when not initialized");
        gameConfig.passGameLevel(gameLevel.BASIC);
        assertTrue(gameConfig.alreayInitialize(), "Should return true when initialized");
    }

    @Test
    void testGetters() {
        gameConfig.passGameLevel(gameLevel.BASIC);
        assertNotNull(gameConfig.getWallPositions(), "Wall positions should not be null");
        assertTrue(gameConfig.getNumberOfWall() >= 0, "Number of walls should be non-negative");
        assertTrue(gameConfig.getNumberOfObstacles() >= 0, "Number of obstacles should be non-negative");
    }

    @Test
    void testAreaOfRoom() {
        int expectedArea = gameConfig.getRoomRow() * gameConfig.getRoomColumn();
        assertEquals(expectedArea, gameConfig.areaofRoom(),
                "Area of room should match the product of rows and columns");
    }

    @Test
    void testGetAllRewardNum() {
        gameConfig.passGameLevel(gameLevel.BASIC);
        int expectedTotalRewards = gameConfig.getNumberOfRegularRewards() + gameConfig.getNumberOfBonusRewards();
        assertEquals(expectedTotalRewards, gameConfig.getAllRewardNum(),
                "Total rewards should be the sum of regular and bonus rewards");
    }

    @Test
    void testGetNumberOfSpiders() {
        gameConfig.passGameLevel(gameLevel.BASIC);
        assertTrue(gameConfig.getNumberOfSpiders() >= 0, "Number of spiders should be non-negative");
    }

    @Test
    void testGetNumberOfBasicGhosts() {
        gameConfig.passGameLevel(gameLevel.BASIC);
        assertTrue(gameConfig.getNumberOfBasicGhosts() >= 0, "Number of basic ghosts should be non-negative");
    }

    @Test
    void testGetNumberOfAdvancedGhosts() {
        gameConfig.passGameLevel(gameLevel.BASIC);
        assertTrue(gameConfig.getNumberOfAdvancedGhosts() >= 0, "Number of advanced ghosts should be non-negative");
    }

    @Test
    void testGetTotalGhosts() {
        gameConfig.passGameLevel(gameLevel.BASIC);
        int expectedTotalGhosts = gameConfig.getNumberOfBasicGhosts() + gameConfig.getNumberOfAdvancedGhosts()
                + gameConfig.getNumberOfSpiders();
        assertEquals(expectedTotalGhosts, gameConfig.getTotalEnemy(),
                "Total ghosts should be the sum of basic, advanced ghosts, and spiders");
    }

    @Test
    void testAlreadyInitialize_AllConditionsTrue() {
        gameConfig.getWallPositions().add(new Position(0, 0));
        gameConfig.passGameLevel(gameLevel.BASIC);

        assertTrue(gameConfig.alreayInitialize(), "Should return true when both conditions are met");
    }

    @Test
    void testAlreadyInitialize_WallPositionsNull() {

        gameConfig.passGameLevel(gameLevel.BASIC);
        gameConfig.setWallPositions(null);
        assertFalse(gameConfig.alreayInitialize(), "Should return false when wallPositions are null");
    }

    @Test
    void testAlreadyInitialize_ZeroRegularRewards() {

        gameConfig.getWallPositions().add(new Position(0, 0));
        gameConfig.setNumberOfRegularRewards(0);

        assertFalse(gameConfig.alreayInitialize(), "Should return false when there are zero regular rewards");
    }

    @Test
    void testAlreadyInitialize_BothConditionsFalse() {

        gameConfig.setWallPositions(null);

        assertFalse(gameConfig.alreayInitialize(), "Should return false when both conditions are not met");
    }

}
