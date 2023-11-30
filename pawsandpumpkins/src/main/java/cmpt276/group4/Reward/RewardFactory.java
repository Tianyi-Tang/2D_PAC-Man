package cmpt276.group4.Reward;

import java.util.ArrayList;
import java.util.List;

import cmpt276.group4.Position;
import cmpt276.group4.GameMap.RecordUsedPlace;

public class RewardFactory {


    /**
     * Creates a list of rewards based on the specified reward type and quantity.
     * This method assigns each reward a random available position in the game environment.
     *
     * @param type   The type of reward to create, specified by the RewardType enum.
     * @param number The number of rewards to create.
     * @return A list of rewards of the specified type.
     */
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

    /**
     * Creates a single reward of the specified type.
     * The method supports different types of rewards and instantiates them accordingly.
     *
     * @param type The type of the reward to create, as defined in the RewardType enum.
     * @return A new instance of the specified reward type.
     * @throws IllegalArgumentException If the specified reward type is invalid or not supported.
     */
    private Reward createSingleReward(RewardType type) {
        return switch (type) {
            case Candy -> new Candy();
            case PumpkinHead -> new PumpkinHead();
        };
    }


}

