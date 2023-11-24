package cmpt276.group4.Player;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;


import javax.imageio.ImageIO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cmpt276.group4.GameManager;
import cmpt276.group4.Position;
import cmpt276.group4.GameMap.RoomEnvironment;
import cmpt276.group4.GameMap.RoomLayout;
import cmpt276.group4.Logic.WindowConfig;
import cmpt276.group4.WindowAndInput.MoveDirection;

/**
 * Unit test for Player
 */
public class PlayerTests {
    public Player player;
    public GameManager mockGameManager;
    Graphics2D mockGraphic;
    public final int directionUpdateTime =10;
    public final int imageChangingTime = 15;

    /**
     * Set up the player before testing
     */
    @BeforeEach
    public void setUp(){
        player = Player.getInstance();

        mockGameManager = mock(GameManager.class);
        player.init(mockGameManager, RoomEnvironment.getInstance(), RoomLayout.getInstance());
        mockGameManager.setPlayer(player);
        mockGraphic = mock(Graphics2D.class);
    }

    /**
     * Test the inital posititon of player is always in (1,1) in the map
     */
    @Test
    public void initalPosition(){
        assertEquals(new Position( WindowConfig.tileSize, WindowConfig.tileSize), player.getPosition());
    }

    /**
     * Check the score is successfully adding to player
     */
    @Test
    public void addScoreToPlayer(){
        player.addScoreToPlayer(4, true);
        assertEquals(player.getCollectScore(), 4);
    }
    /**
     * Check will player score can be deducted 
     */
    @Test
    public void deductPoint(){
        player.addScoreToPlayer(15, true);
        player.deductPoint(10);
        assertEquals(player.totalScore(), 5);
    }

    /**
     * Test player will call the negativePoint function in GameManger after player has negative point
     */
    @Test
    public void negativePoint(){
        mockGameManager.setPlayer(player);
        player.deductPoint(4);
        verify(mockGameManager).negativePoint();
    }

    /**
     * Test after player first deduct score, the second deduction will not success within 0.5 second
     */
    @Test
    public void deductPointInShortTime(){
        player.deductPoint(5);
        player.deductPoint(10);
        assertEquals(player.getDeductScore(), 5);
    }

    /**
     * Test player can record how many general reward it collect and call the collectReward
     * function in GameManager
     */
    @Test
    public void addGeneralRewards(){
        player.addScoreToPlayer(5, false);
        player.addScoreToPlayer(2, false);
        verify(mockGameManager).collectReward(player.getGeneralRewardNum());
        assertEquals(player.getGeneralRewardNum(), 2);
    }

    /**
     * Test player can record how many bonus reward it collect
     */
    @Test
    public void addBonusReward(){
        player.addScoreToPlayer(6, true);
        assertEquals(player.getBonusRewardNum(), 1);
    }

    /**
     * Test player can not immediately move after the observerUpdate pass the
     * Move direction
     */
    @Test
    public void playerFailToMoving(){
        player.observerUpdate(MoveDirection.Up, true);
        assertEquals(player.getPosition(), new Position(48, 48));
    }

    /**
     * Test observerUpdate will make Player move base on the direction input after
     * run several time of update 
     */
    @Test
    public void playerMovingLeft(){
        player.observerUpdate(MoveDirection.Left, true);
        runUpdatemultipleTime(directionUpdateTime);
        assertEquals(player.getPosition(), new Position(0, 48));
    }

    /**
     * Test player will continues moving as long as the observerUpdate not receive
     * the moving direction turn off
     */
    @Test
    public void PlayerContinuesPressKey(){
        player.observerUpdate(MoveDirection.Down, true);
        runUpdatemultipleTime(directionUpdateTime * 2);
        assertEquals(player.getPosition(), new Position(48, 144));
    }

    /**
     * Test the intial image of player is image of player moving down
     */
    @Test
    public void initialmovingImage(){
        player.draw(mockGraphic);
        BufferedImage expectImage = loadImage("res/Player/down1.png");
        assertImagesEqual(expectImage,player.getCurrentImage());
    }

    /**
     * Test player will update image base on direction Input after 
     * several time of update 
     */
    @Test
    public void movingImageUp(){
        player.observerUpdate(MoveDirection.Up, true);
        runUpdatemultipleTime(directionUpdateTime);
        player.draw(mockGraphic);
        BufferedImage expecImage = loadImage("res/Player/up1.png");
        assertImagesEqual(expecImage, player.getCurrentImage());
    }

    /**
     * Test player will main its' direction adn move image, even the observerUpdate
     * turn off the move direction
     */
    @Test
    public void imageAfterReleaseKey(){
        player.observerUpdate(MoveDirection.Right, true);
        runUpdatemultipleTime(directionUpdateTime);
        player.draw(mockGraphic);

        player.observerUpdate(MoveDirection.Right, false);
        player.draw(mockGraphic);
        BufferedImage expecImage = loadImage("res/Player/right1.png");
        assertImagesEqual(expecImage, player.getCurrentImage());
    }

    /**
     * Test the player image will switch another version of moveing direction when player 
     * maintain direction during several update
     */
    @Test
    public void imageSwitch(){
        player.observerUpdate(MoveDirection.Left, true);
        runUpdatemultipleTime(imageChangingTime);
        player.draw(mockGraphic);
        BufferedImage expecImage = loadImage("res/Player/left2.png");
        assertImagesEqual(expecImage, player.getCurrentImage());
    }

    /**
     * Method that imitate the time passing, by continues calling the update function
     * in player
     * @param index how many time you want to call the update
     */
    private void runUpdatemultipleTime(int index){
        for(int i=0;i < index;i++)
            player.update();
    }

    /**
     * Provide the buffedImage laoding base on path of image
     * @param path the path of image which you want to load 
     * @return the loading image 
     */
    private BufferedImage loadImage(String path){
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    /**
     * Check Player provide image is smae as the expectation
     * @param expectImage the expect image that player will provide
     * @param actualImage the acutal image that player provide
     */
    private void  assertImagesEqual(BufferedImage expectImage, BufferedImage actualImage){
        int[] expectedPixels = expectImage.getRGB(0, 0, expectImage.getWidth(), expectImage.getHeight(), null, 0, expectImage.getWidth());
        int[] actualPixels = actualImage.getRGB(0, 0, actualImage.getWidth(), actualImage.getHeight(), null, 0, actualImage.getWidth());

        assertArrayEquals(expectedPixels, actualPixels);
    }


    
}
