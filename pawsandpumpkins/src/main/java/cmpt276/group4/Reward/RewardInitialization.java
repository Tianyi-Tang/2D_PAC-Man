package cmpt276.group4.Reward;
import java.util.ArrayList;
import java.util.List;
import cmpt276.group4.Logic.GameConfig;
import cmpt276.group4.gameLevel;

public class RewardInitialization {
    private GameConfig gameConfig;
    private List<Reward> generalRewards = new ArrayList<>();
    private List<Reward> bonusRewards = new ArrayList<>();
    private RewardFactory rFactory; // Assume this is initialized somewhere

    // Constructor takes GameConfig instance
    public RewardInitialization(GameConfig gameConfig, RewardFactory rFactory) {
        this.gameConfig = gameConfig;
        this.rFactory = rFactory;
    }

    public void GenerateReward(){
        int numberOfRegularRewards = gameConfig.getNumberOfRegularRewards();
        System.out.println(numberOfRegularRewards);
        System.out.println("+ Get number of reward!");
        List<Reward> newRewards = rFactory.createReward(RewardType.Candy, numberOfRegularRewards);
        generalRewards.addAll(newRewards);
    }

    public void GenerateBonusRewards(){
        int numberOfBonusRewards = gameConfig.getNumberOfBonusRewards();
        System.out.println(numberOfBonusRewards);
        System.out.println("+ Get number of bonusReward!");
        List<Reward> newRewards = rFactory.createReward(RewardType.PumpkinHead, numberOfBonusRewards);
        bonusRewards.addAll(newRewards);
    }
}
