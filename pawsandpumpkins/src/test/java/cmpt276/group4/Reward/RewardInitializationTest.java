package cmpt276.group4.Reward;

import cmpt276.group4.Logic.GameConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RewardInitializationTest {

    @Test
    void generateReward_GenerateCorrectNumberOfReward() {
        GameConfig gameConfig = new GameConfig();
        RewardFactory rewardFactory = new RewardFactory();
        RewardInitialization rewardInit = new RewardInitialization(rewardFactory);
        rewardInit.generateReward();
        int expectedGeneralRewards = gameConfig.getNumberOfRegularRewards();
        int expectedBonusRewards = gameConfig.getNumberOfBonusRewards();
        assertEquals(expectedGeneralRewards, rewardInit.getGeneralRewards());
        assertEquals(expectedBonusRewards, rewardInit.getBonusRewards());
    }
}