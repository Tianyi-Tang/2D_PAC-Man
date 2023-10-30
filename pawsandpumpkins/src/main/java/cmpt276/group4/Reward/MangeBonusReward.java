package cmpt276.group4.Reward;

public class MangeBonusReward {
    private float apperintrenal;

    private float existTime;

    private BonusReward bonusReward;

    public void manageAppearance(float apperintrenal) {

        setPos();
        // Logic to decide time of reward appearance
    }

    public void manageDisappearance(float existTime) {
        // Logic to delete bonus reward after certain time
    }
    private void setPos(){}

    public void setApperintrenal(float apperintrenal) {
        this.apperintrenal = apperintrenal;
    }

    public void setExistTime(float existTime) {
        this.existTime = existTime;
    }
}
