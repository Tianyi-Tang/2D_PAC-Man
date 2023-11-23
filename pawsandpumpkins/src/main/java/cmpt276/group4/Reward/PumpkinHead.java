package cmpt276.group4.Reward;

import cmpt276.group4.Player.Player;
import cmpt276.group4.Position;
import cmpt276.group4.RecordUsedPlace;
import cmpt276.group4.Logic.WindowConfig;
import cmpt276.group4.WindowAndInput.GamePanel;
import cmpt276.group4.Reward.BonusReward;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import cmpt276.group4.GameTime;
public class PumpkinHead extends BonusReward {
    private long displayStartTime =10;
    private long displayDuration = 20;
    private int score = 5;
    private BufferedImage ppk1, ppk2, currentImage;
    private Position ppkPosition,playerPosition;

    private int stateCounter=0;
    private boolean org_State = true;
    private boolean available;
    private RecordUsedPlace record;
    public boolean isBonusReward = true;
    public PumpkinHead() {

        record = RecordUsedPlace.getInstance();
        getPumpkinImage();
        ppkPosition=record.getRandomSafePosition();
        record.addReward(this);
    }

    @Override
    public int getScore() {
        return score;
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
    public boolean isBonusReward(){
        return isBonusReward;
    }

    private void getPlayerPosition() {

        RecordUsedPlace record = RecordUsedPlace.getInstance();
        playerPosition = record.getPlayerPosition();
    }

    @Override
    public void addBenefit(Player player) {
            getPlayerPosition();
        if (playerPosition.equal(ppkPosition)) {
            addScore(player,score);
            record.removeReward(this);
        }
    }

    private void addScore(Player player, int score) {
        this.score=score;
        player.addScoreToPlayer(score,isBonusReward);
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

    GameTime gameTime = GameTime.getInstance();

@Override
    public void draw(Graphics2D g1) {
        stateCounter++;
    if (stateCounter >= 15) {
        org_State = !org_State;
        stateCounter = 0;
    }
         if (org_State)
            currentImage = ppk1;
        else
            currentImage = ppk2;
        if(displayStartTime<=gameTime.getTime() && gameTime.getTime()-displayStartTime<=displayDuration) {

    g1.drawImage(currentImage, ppkPosition.getX_axis(), ppkPosition.getY_axis(), WindowConfig.tileSize, WindowConfig.tileSize, null);

        }

    }

}

