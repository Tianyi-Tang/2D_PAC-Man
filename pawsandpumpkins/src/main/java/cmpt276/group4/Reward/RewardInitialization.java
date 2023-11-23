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

    // Constructor takes GameConfig instance
    public RewardInitialization(RewardFactory rFactory) {
        this.gameConfig = GameConfig.getGameConfigInstance();
        this.rFactory = rFactory;
    }

    public void generateReward(){
        generateRegularReward();
        generateBonusRewards();
    }

    private void generateRegularReward(){
        int numberOfRegularRewards = gameConfig.getNumberOfRegularRewards();
        List<Reward> newRewards = rFactory.createReward(RewardType.Candy, numberOfRegularRewards);
        generalRewards.addAll(newRewards);
    }

    private void generateBonusRewards(){
        int numberOfBonusRewards = gameConfig.getNumberOfBonusRewards();
        List<Reward> newRewards = rFactory.createReward(RewardType.PumpkinHead, numberOfBonusRewards);
        bonusRewards.addAll(newRewards);
    }

    public int getGeneralRewards() {
        return generalRewards.size();
    }

    public int getBonusRewards() {
        return bonusRewards.size();
    }
}
