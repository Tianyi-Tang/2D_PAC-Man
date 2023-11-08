package cmpt276.group4.Reward;
import java.util.ArrayList;
import java.util.List;
import cmpt276.group4.gameLevel;
public class RewardInitialization {
    private gameLevel gameLevel;
    private int candy,pumpkinHead;



    public RewardInitialization(gameLevel gameLevel, RewardFactory rFactory){
        this.gameLevel = gameLevel;
        switch (gameLevel) {
            case BASIC:
                candy = 10;
                pumpkinHead = 3;
                break;
            case MEDIUM:
                candy = 10;
                pumpkinHead = 2;
                break;
            case HARD:
                candy = 10;
                pumpkinHead = 1;
                break;
        }
        rFactory.createReward(RewardType.Candy,candy);
        rFactory.createReward(RewardType.PumpkinHead,pumpkinHead);
        System.out.println("Rewards Initialization with " + (candy + pumpkinHead
        ) + " rewards.");


    }
}
