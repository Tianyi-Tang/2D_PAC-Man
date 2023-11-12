package cmpt276.group4.Reward;
import cmpt276.group4.Player.Player;
import  cmpt276.group4.Position;

import java.awt.*;

public interface Reward {
    int getScore();
    Position getPosition();
    void draw(Graphics2D g2);
    void setPosition(Position position);
    boolean isAvailable();
    boolean isBonusReward();
    void deleteImage();
    void addBenefit(Player player);
    boolean shouldDraw(long time);



}
