package cmpt276.group4.Reward;

import cmpt276.group4.RecordUsedPlace;
import cmpt276.group4.Reward.BonusReward;
import cmpt276.group4.WindowAndInput.GamePanel;

import java.util.List;
import java.awt.*;

public class MangeBonusReward {
    private float apperintrenal;

    private float existTime;

    private BonusReward bonusReward;

    private int bonusRewardIndex =0;

    private RecordUsedPlace record;

    private GamePanel gamePanel;

    public void manageAppearance(float apperintrenal) {

        setPos();
        // Logic to decide time of reward appearance
    }

    public void manageDisappearance(float existTime) {


    }
    private void setPos(){}

    public void setApperintrenal(float apperintrenal) {
        this.apperintrenal = apperintrenal;
    }

    public void setExistTime(float existTime) {
        this.existTime = existTime;
    }

    public void drawCurrentReward(Graphics2D g2){

        List<Reward> bonusRewards = record.getBonusReward();

        if (!bonusRewards.isEmpty()) {

            bonusRewardIndex = bonusRewardIndex % bonusRewards.size();


            Reward reward = bonusRewards.get(bonusRewardIndex);
            // Time time = new Time();
            // int gameTime= time.time;
            // if(reward.shouldDraw(gameTime)){
            //     reward.draw(g2);
            //     bonusRewardIndex++;

        }

    }
}

