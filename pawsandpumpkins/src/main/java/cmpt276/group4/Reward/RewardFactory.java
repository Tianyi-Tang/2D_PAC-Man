package main.java.cmpt276.group4.Reward;

import java.util.ArrayList;
import java.util.List;

import cmpt276.group4.Position;
import cmpt276.group4.RecordUsedPlace;

public class RewardFactory {

    public List<Reward> createReward(RewardType type, int number) {
        RecordUsedPlace recordUsedPlace = RecordUsedPlace.getInstance();

        List<Reward> rewards = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            Position availablePosition = recordUsedPlace.getRandomFromAvailablePosition();
            if (availablePosition != null) {
                Reward reward = createSingleReward(type);
                reward.setPosition(availablePosition);
                rewards.add(reward);
            } else {
                System.out.println("No available position to create a reward.");
            }
        }
        return rewards;
    }
    private Reward createSingleReward(RewardType type) {
        switch (type) {
            case Candy:
                System.out.println("Candy created.");
                return new Candy();
            case PumpkinHead:
                System.out.println("PumpkinHead created.");
                return new PumpkinHead();
            default:
                throw new IllegalArgumentException("Invalid Reward type");
        }
    }
}

