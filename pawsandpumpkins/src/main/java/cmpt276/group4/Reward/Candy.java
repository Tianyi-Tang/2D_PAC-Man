package cmpt276.group4.Reward;

import cmpt276.group4.Player.Player;
import cmpt276.group4.Position;
import cmpt276.group4.GameMap.RecordUsedPlace;
import cmpt276.group4.GameMap.RoomEnvironment;
import cmpt276.group4.GameMap.RoomLayout;
import cmpt276.group4.Logic.WindowConfig;
import cmpt276.group4.WindowAndInput.GamePanel;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Candy extends GeneralReward {
    private Position cdposition,playerPosition;
    private boolean org_State = true;
    private int stateCounter = 0;
    private int score =1;
    private BufferedImage cd1 , cd2, currentImage;
    private RecordUsedPlace record;
    public boolean isBonusReward = false;


    GamePanel gp;

    /**
     * Constructor for the Candy class.
     * Initializes a record from RecordUsedPlace, fetches a candy image, sets a random position for the candy,
     * and adds the candy to the RoomEnvironment as a reward.
     */
    public Candy() {
        record = RecordUsedPlace.getInstance();
        getCandyImage();
        cdposition = record.getRandomFromAvailablePosition();
        RoomEnvironment.getInstance().addReward(this);
    }

    /**
     * Returns the score value associated with the candy.
     *
     * @return The score value of the candy.
     */
    @Override
    public int getScore() {
        return score;
    }

    /**
     * Returns the current position of the candy.
     *
     * @return The position of the candy.
     */
    @Override
    public Position getPosition() {
        return cdposition;
    }

    /**
     * Sets the position of the candy.
     *
     * @param position The new position for the candy.
     */
    @Override
    public void setPosition(Position position) {
        cdposition = position;
    }

    /**
     * Checks if the candy is a bonus reward.
     *
     * @return True if the candy is a bonus reward, false otherwise.
     */
    @Override
    public boolean isBonusReward() {
        return isBonusReward;
    }

    /**
     * Adds the benefit of the candy to the player.
     * If the player's position matches the candy's position, the player's score is increased.
     *
     * @param player The player to whom the benefit will be added.
     */
    @Override
    public void addBenefit(Player player) {
        playerPosition = player.getPosition();
        if (playerPosition.equal(cdposition)) {
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
    private void addScore(Player player, int score) {
        this.score = score;
        player.addScoreToPlayer(score, isBonusReward);
    }

    /**
     * Loads the candy images from the resources directory.
     */
    private void getCandyImage() {
        try {
            String directory = System.getProperty("user.dir");
            cd1 = ImageIO.read(new File(directory + "/res/Rewards/candy1.png"));
            cd2 = ImageIO.read(new File(directory + "/res/Rewards/candy2.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Draws the candy on the given Graphics2D object.
     * The candy alternates its image between two states to create an animation effect.
     *
     * @param g1 The Graphics2D object on which the candy will be drawn.
     */
    @Override
    public void draw(Graphics2D g1) {
        stateCounter++;
        if (stateCounter >= 15) {
            org_State = !org_State;
            stateCounter = 0;
        }
        if (org_State)
            currentImage = cd1;
        else
            currentImage = cd2;

        g1.drawImage(currentImage, cdposition.getX_axis(), cdposition.getY_axis(), WindowConfig.tileSize, WindowConfig.tileSize, null);
    }



}
