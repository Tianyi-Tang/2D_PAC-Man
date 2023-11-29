package cmpt276.group4.Reward;

import cmpt276.group4.Logic.GameConfig;
import cmpt276.group4.gameLevel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RewardInitializationTest {

    @Test
    void generateReward_GenerateCorrectNumberOfRewardBasic() {
        GameConfig gameConfig = new GameConfig();
        RewardFactory rewardFactory = new RewardFactory();
        RewardInitialization rewardInit = new RewardInitialization(rewardFactory);
        gameConfig.passGameLevel(gameLevel.BASIC);
        rewardInit.generateReward();
        int expectedGeneralRewards = gameConfig.getNumberOfRegularRewards();
        int expectedBonusRewards = gameConfig.getNumberOfBonusRewards();
        assertEquals(expectedGeneralRewards, rewardInit.getGeneralRewards());
        assertEquals(expectedBonusRewards, rewardInit.getBonusRewards());
    }
    @Test
    void generateReward_GenerateCorrectNumberOfRewardInHardLevel() {
        GameConfig gameConfig = new GameConfig();
        RewardFactory rewardFactory = new RewardFactory();
        RewardInitialization rewardInit = new RewardInitialization(rewardFactory);
        gameConfig.passGameLevel(gameLevel.HARD);
        rewardInit.generateReward();
        int expectedGeneralRewards = gameConfig.getNumberOfRegularRewards();
        int expectedBonusRewards = gameConfig.getNumberOfBonusRewards();
        assertEquals(expectedGeneralRewards, rewardInit.getGeneralRewards());
        assertEquals(expectedBonusRewards, rewardInit.getBonusRewards());
    }
    @Test
    void generateReward_GenerateCorrectNumberOfRewardInMedium() {
        GameConfig gameConfig = new GameConfig();
        RewardFactory rewardFactory = new RewardFactory();
        RewardInitialization rewardInit = new RewardInitialization(rewardFactory);
        rewardInit.generateReward();
        int expectedGeneralRewards = gameConfig.getNumberOfRegularRewards();
        int expectedBonusRewards = gameConfig.getNumberOfBonusRewards();
        assertEquals(expectedGeneralRewards, rewardInit.getGeneralRewards());
        assertEquals(expectedBonusRewards, rewardInit.getBonusRewards());
    }
    @Test
    void passGameLevel_SetsCorrectConfigForBasicLevel() {
        GameConfig gameConfig = new GameConfig();
        gameConfig.passGameLevel(gameLevel.BASIC);
        // Add assertions to check if gameConfig is correctly set up for the BASIC level
        assertEquals(5, gameConfig.getNumberOfRegularRewards());
        // ... other assertions for BASIC level config
    }

}