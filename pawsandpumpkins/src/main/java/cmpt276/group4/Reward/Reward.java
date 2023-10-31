package cmpt276.group4.Reward;
import cmpt276.group4.Player.Player;
import  cmpt276.group4.Position;
public interface Reward {
    int getScore();
    Position getPosition();
    void setPosition(int x_ray,int y_ray);
    boolean isAvailable();
    void deleteImage();
    void addBenefit();
    void setAvailable(boolean available);
    void addScore(Player player,int score);

}
