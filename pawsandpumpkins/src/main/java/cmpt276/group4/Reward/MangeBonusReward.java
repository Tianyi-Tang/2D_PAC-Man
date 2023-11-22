package cmpt276.group4.Reward;

import cmpt276.group4.GameTime;
import cmpt276.group4.Reward.BonusReward;
import cmpt276.group4.WindowAndInput.GamePanel;

import java.util.ArrayList;
import java.util.List;

import GameMap.RecordUsedPlace;

import java.awt.*;

public class MangeBonusReward {

    private List<Reward> rewardList;
    private int currentRewardIndex;
    private int rewardDisplayStartTime=10;
    private int rewardDisplayDuration;
    private boolean isRewardDisplayed;


    public MangeBonusReward(List<Reward> rewards, int displayDuration) {
        this.rewardList = rewards;
        this.rewardDisplayDuration = displayDuration;
        this.currentRewardIndex = 0;
        this.isRewardDisplayed = false;
    }
    public void update() {
        GameTime gameTime = GameTime.getInstance();
        int currentTime = gameTime.getTime();

        if (!isRewardDisplayed) {
            if (currentRewardIndex < rewardList.size()) {
                rewardDisplayStartTime = currentTime;
                isRewardDisplayed = true;
            } else {
                currentRewardIndex = 0;
            }
        } else {
            if (currentTime - rewardDisplayStartTime > rewardDisplayDuration) {
                isRewardDisplayed = false;
                currentRewardIndex++;
                if (currentRewardIndex >= rewardList.size()) {
                    currentRewardIndex = 0;
                }
            }
        }
    }

    public void draw(Graphics2D g2) {
        if (isRewardDisplayed && currentRewardIndex < rewardList.size()) {
            Reward reward = rewardList.get(currentRewardIndex);
            reward.draw(g2);
        }
    }
}


