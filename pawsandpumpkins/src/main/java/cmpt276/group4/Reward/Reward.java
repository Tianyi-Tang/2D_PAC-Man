package main.java.cmpt276.group4.Reward;
import cmpt276.group4.Player.Player;
import  cmpt276.group4.Position;
public interface Reward {
    int getScore();
    Position getPosition();
    void setPosition(Position position);
    boolean isAvailable();
    void deleteImage();
    void addBenefit();

    void addScore(Player player,int score);


}
