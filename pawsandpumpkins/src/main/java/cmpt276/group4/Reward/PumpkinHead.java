package cmpt276.group4.Reward;

import cmpt276.group4.Player.Player;
import cmpt276.group4.Position;

public class PumpkinHead extends BonusReward{
    public int score=5;
    @Override
    public int getScore() {
        return score;
    }

    @Override
    public Position getPosition() {
        return null;
    }

    @Override
    public void setPosition(int x_ray, int y_ray) {

    }

    @Override
    public boolean isAvailable() {
        return false;
    }

    @Override
    public void deleteImage() {

    }

    @Override
    public void addBenefit() {

    }

    @Override
    public void setAvailable(boolean available) {

    }

    @Override
    public void addScore(Player player, int score) {

    }
}
