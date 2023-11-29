package cmpt276.group4.Reward;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RewardFactoryTest {

    @Test
    void createReward_CreateRightNumberOfCandy() {
        var rewardfactory = new RewardFactory();
        int numberOfReward = 5;
        List<Reward> rewards = rewardfactory.createReward(RewardType.Candy,numberOfReward);
        assertEquals(5,rewards.size());

    }

    @Test
    void createReward_CreateRightNumberOfPumpkinHead() {
        var rewardfactory = new RewardFactory();
        int numberOfReward = 5;
        List<Reward> rewards = rewardfactory.createReward(RewardType.PumpkinHead,numberOfReward);
        assertEquals(5,rewards.size());

    }
}