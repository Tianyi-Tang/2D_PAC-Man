package cmpt276.group4.Reward;
import java.util.ArrayList;
import java.util.List;
import cmpt276.group4.gameLevel;
public class RewardInitialization {
    private gameLevel gameLevel;
    private int candy,pumpkinHead;



    public void GenerateReward(gameLevel gameLevel, RewardFactory rFactory){
        this.gameLevel = gameLevel;
        switch (gameLevel) {
            case BASIC:
                candy = 10;
                break;
            case MEDIUM:
                candy = 8;
                break;
            case HARD:
                candy = 5;
                break;
        }
        rFactory.createReward(RewardType.Candy,candy);
        System.out.println("Rewards Initialization with " + (candy
        ) + " rewards.");


    }
}
