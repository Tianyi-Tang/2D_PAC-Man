package cmpt276.group4.Reward;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import cmpt276.group4.Logic.GameConfig;
import cmpt276.group4.gameLevel;

public class RewardInitialization {
    private GameConfig gameConfig;
    private List<Reward> generalRewards = new ArrayList<>();
    private List<Reward> bonusRewards = new ArrayList<>();
    private RewardFactory rFactory; // Assume this is initialized somewhere

    /**
     * Constructor for RewardInitialization.
     * Initializes the game configuration and sets the reward factory.
     *
     * @param rFactory The factory used to create rewards.
     */
    public RewardInitialization(RewardFactory rFactory) {
        this.gameConfig = GameConfig.getGameConfigInstance();
        this.rFactory = rFactory;
    }

    /**
     * Generates both regular and bonus rewards.
     * This method delegates to generateRegularReward and generateBonusRewards for actual creation.
     */
    public void generateReward() {
        generateRegularReward();
        generateBonusRewards();
    }

    /**
     * Generates regular rewards based on the game configuration.
     * This method fetches the number of regular rewards from the game configuration,
     * creates them using the reward factory, and adds them to the general rewards list.
     */
    private void generateRegularReward() {
        int numberOfRegularRewards = gameConfig.getNumberOfRegularRewards();
        List<Reward> newRewards = rFactory.createReward(RewardType.Candy, numberOfRegularRewards);
        generalRewards.addAll(newRewards);
    }

    /**
     * Generates bonus rewards based on the game configuration.
     * This method fetches the number of bonus rewards from the game configuration,
     * creates them using the reward factory, and adds them to the bonus rewards list.
     */
    private void generateBonusRewards() {
        int numberOfBonusRewards = gameConfig.getNumberOfBonusRewards();
        List<Reward> newRewards = rFactory.createReward(RewardType.PumpkinHead, numberOfBonusRewards);
        bonusRewards.addAll(newRewards);
    }

    /**
     * Gets the count of general rewards currently available.
     *
     * @return The number of general rewards.
     */
    public int getGeneralRewards() {
        return generalRewards.size();
    }

    /**
     * Gets the count of bonus rewards currently available.
     *
     * @return The number of bonus rewards.
     */
    public int getBonusRewards() {
        return bonusRewards.size();
    }

}
