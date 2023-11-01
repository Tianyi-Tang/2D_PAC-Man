package cmpt276.group4.Reward;

import cmpt276.group4.Player.Player;
import cmpt276.group4.Position;
import cmpt276.group4.WindowAndInput.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class PumpkinHead extends BonusReward{
    public int score=5;
    private BufferedImage ppk1 , ppk2;
    private Position ppkpsition;
    @Override
    public int getScore() {
        return score;
    }

    @Override
    public Position getPosition() {
        return null;
    }

    @Override
    public void setPosition(Position position) {

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
    private void getPumpkinImage(){
        try{
            String directory = System.getProperty("user.dir");
            ppk1= ImageIO.read(new File(directory +"/res/Rewards/pumpkin1.png"));
            ppk2= ImageIO.read(new File(directory +"/res/Rewards/pumpkin2.png"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void drawPumpkin(Graphics2D g1){

        g1.drawImage(ppk1,ppkpsition.getX_axis(),ppkpsition.getY_axis(), GamePanel.tileSize,GamePanel.tileSize,null);
    }
}
