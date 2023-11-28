package cmpt276.group4.Reward;

import cmpt276.group4.Player.Player;
import cmpt276.group4.Position;
import cmpt276.group4.GameMap.RecordUsedPlace;
import cmpt276.group4.GameMap.RoomEnvironment;
import cmpt276.group4.Logic.WindowConfig;

import cmpt276.group4.Time.GameTime;


import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class PumpkinHead extends BonusReward {
    private long displayDuration = 10;
    private int score = 5;
    private BufferedImage ppk1, ppk2, currentImage;
    private Position ppkPosition;

    private int stateCounter=0;
    private boolean org_State = true;
    private boolean available;
    private RecordUsedPlace record;
    public boolean isBonusReward = true;
    private boolean isAvailable = true;
    /**
     * Constructor for the PumpkinHead class.
     * Initializes a record from RecordUsedPlace, fetches pumpkin images, sets a random position for the pumpkin,
     * and adds the pumpkin to the RoomEnvironment as a reward.
     */
    public PumpkinHead() {
        record = RecordUsedPlace.getInstance();
        getPumpkinImage();
        ppkPosition = record.getRandomFromAvailablePosition();
        RoomEnvironment.getInstance().addReward(this);
    }

    /**
     * Returns the score value associated with the pumpkin.
     *
     * @return The score value of the pumpkin.
     */
    @Override
    public int getScore() {
        return score;
    }

    /**
     * Returns the current position of the pumpkin.
     *
     * @return The position of the pumpkin.
     */
    @Override
    public Position getPosition() {
        return ppkPosition;
    }

    /**
     * Sets the position of the pumpkin.
     *
     * @param position The new position for the pumpkin.
     */
    @Override
    public void setPosition(Position position) {
        this.ppkPosition = position;
    }

    /**
     * Checks if the pumpkin is a bonus reward.
     *
     * @return True if the pumpkin is a bonus reward, false otherwise.
     */
    @Override
    public boolean isBonusReward() {
        return isBonusReward;
    }

    /**
     * Adds the benefit of the pumpkin to the player.
     * If the player's position matches the pumpkin's position, the player's score is increased.
     *
     * @param player The player to whom the benefit will be added.
     */
    @Override
    public void addBenefit(Player player) {
        if (RoomEnvironment.getInstance().sameAsPlayerPosition(ppkPosition)&&isAvailable) {
            addScore(player, score);
            RoomEnvironment.getInstance().removeReward(this);
        }
    }

    /**
     * Private method to add the score to the player.
     *
     * @param player The player who will receive the score.
     * @param score  The score to be added to the player.
     */
    public void addScore(Player player, int score) {
        this.score = score;
        player.addScoreToPlayer(score, isBonusReward);
    }

    /**
     * Loads the pumpkin images from the resources directory.
     */
    private void getPumpkinImage() {
        try {
            String directory = System.getProperty("user.dir");
            ppk1 = ImageIO.read(new File(directory + "/res/Rewards/pumpkin1.png"));
            ppk2 = ImageIO.read(new File(directory + "/res/Rewards/pumpkin2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if the pumpkin is currently available.
     *
     * @return True if the pumpkin is available, false otherwise.
     */
    public boolean getAvailable() {
        return isAvailable;
    }

    /**
     * Draws the pumpkin on the given Graphics2D object.
     * The pumpkin alternates its image between two states to create an animation effect.
     * The pumpkin's availability is determined based on the current game time.
     *
     * @param g1 The Graphics2D object on which the pumpkin will be drawn.
     */
    @Override
    public void draw(Graphics2D g1) {
        GameTime gameTime = GameTime.getInstance();
        stateCounter++;
        if (stateCounter >= 15) {
            org_State = !org_State;
            stateCounter = 0;
        }
        if (org_State)
            currentImage = ppk1;
        else
            currentImage = ppk2;

        if (gameTime.getTime() % displayDuration >= displayDuration / 2) {
            isAvailable = true;
            g1.drawImage(currentImage, ppkPosition.getX_axis(), ppkPosition.getY_axis(), WindowConfig.tileSize, WindowConfig.tileSize, null);
        } else {
            isAvailable = false;
        }
    }


}

