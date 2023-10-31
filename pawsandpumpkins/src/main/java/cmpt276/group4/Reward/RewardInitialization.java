package cmpt276.group4.Reward;
import java.util.ArrayList;
import java.util.List;

public class RewardInitialization {
    private int numberOfCandy;
    private int numberOfPumpkinHead;

    public RewardInitialization(int numberOfCandy, int numberOfPumpkinHead){
        this.numberOfCandy=numberOfCandy;
        this.numberOfPumpkinHead=numberOfPumpkinHead;
        initializeRewards();
    }
    private void initializeRewards(){
        List<Reward> rewardList = new ArrayList<Reward>();
        for (int i = 0; i< numberOfCandy; i++){
            rewardList.add(RewardFactory.createCandy());
        }
        for (int i = 0; i< numberOfPumpkinHead; i++){
            rewardList.add(RewardFactory.createPumpkinHead());
        }

    }
}
