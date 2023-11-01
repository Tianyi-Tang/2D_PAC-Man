package cmpt276.group4.Reward;

import cmpt276.group4.Player.Player;
import cmpt276.group4.Position;
import cmpt276.group4.WindowAndInput.GamePanel;
import cmpt276.group4.Reward.BonusReward;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PumpkinHead extends BonusReward {
    private static final int SCORE = 5;
    private BufferedImage ppk1, ppk2, currentImage;
    private Position ppkPosition;

    private int stateCounter=0;
    private boolean org_State = true;
    private boolean available;

    public PumpkinHead() {
        getPumpkinImage();
        // Initialize ppkPosition here if necessary, or through a setPosition method
    }

    @Override
    public int getScore() {
        return SCORE;
    }

    @Override
    public Position getPosition() {
        return ppkPosition;
    }

    @Override
    public void setPosition(Position position) {
        this.ppkPosition = position;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }



    @Override
    public void deleteImage() {
        this.ppk1 = null;
        this.ppk2 = null;
    }

    @Override
    public void addBenefit() {

    }

    @Override
    public void addScore(Player player, int score) {
    }
    public void update() {
        stateCounter++;
        if (stateCounter >= 15) {
            org_State = !org_State;
            stateCounter = 0;
        }
    }
    private void getPumpkinImage() {
        try {
            String directory = System.getProperty("user.dir");
            ppk1 = ImageIO.read(new File("res/Rewards/pumpkin1.png"));
            ppk2 = ImageIO.read(new File("res/Rewards/pumpkin2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawPumpkin(Graphics2D g1) {
        if (org_State)
            currentImage = ppk1;
        else
            currentImage = ppk2;

        g1.drawImage(currentImage, ppkPosition.getX_axis(), ppkPosition.getY_axis(), GamePanel.tileSize, GamePanel.tileSize, null);

    }
}
