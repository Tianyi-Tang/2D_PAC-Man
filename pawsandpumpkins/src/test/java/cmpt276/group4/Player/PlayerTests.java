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
    public PlayerUpdate playerUpdate;

    /**
     * Set up the player before testing
     */
    @BeforeEach
    public void setUp(){
        player = new Player();

        mockGameManager = mock(GameManager.class);
        player.init(mockGameManager, RoomEnvironment.getInstance(), RoomLayout.getInstance());
        mockGameManager.setPlayer(player);
        mockGraphic = mock(Graphics2D.class);

        playerUpdate = new PlayerUpdate(player);
    }

    /**
     * Test the inital posititon of player is always in (1,1) in the map
     */
    @Test
    public void initalPosition(){
        assertEquals(new Position( WindowConfig.tileSize, WindowConfig.tileSize), player.getPosition());
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
        player.addScoreToPlayer(15, false);
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
     * Test player can not immediately move after the observerUpdate pass the
     * Move direction
     */
    @Test
    public void playerFailToMoving(){
        player.observerUpdate(MoveDirection.Up, true);
        assertEquals(player.getPosition(), new Position(48, 48));
    }

    /**
     * Test ObserverUpdate will return error if the pass value for direction is null
     */
    @Test
    public void sendINullToObserverUpdate(){
        keyInputPlayerUpdate(null, true, 1,true);
    }

    /**
     * Test ObserverUpdate will make player position change when the other key is press
     */
    @Test
    public void sendOtherToObserverUpdate(){
        Position initalPosition = player.getPosition();
        keyInputPlayerUpdate(MoveDirection.Other, true, 1,true);
        assertEquals(initalPosition, player.getPosition());
    }

    /**
     * Test observerUpdate will make Player move base on the direction input after
     * run several time of update 
     */
    @Test
    public void playerMovingLeft(){
        keyInputPlayerUpdate(MoveDirection.Left, true, 1, true);
        assertEquals(player.getPosition(), new Position(0, 48));
    }

    /**
     * Test player will continues moving as long as the observerUpdate not receive
     * the moving direction turn off
     */
    @Test
    public void PlayerContinuesPressKey(){
        keyInputPlayerUpdate(MoveDirection.Down, true, 2, true);
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
        keyInputPlayerUpdate(MoveDirection.Up, true, 1, true);
        player.draw(mockGraphic);

        BufferedImage expecImage = loadImage("res/Player/up1.png");
        assertImagesEqual(expecImage, player.getCurrentImage());
    }

    /**
     * Test player will main its' direction adn move image, even the observerUpdate
     * turn off the move direction and switch image
     */
    @Test
    public void imageAfterReleaseKey(){
        keyInputPlayerUpdate(MoveDirection.Right,true,1,true);
        player.draw(mockGraphic);
        keyInputPlayerUpdate(MoveDirection.Right,false,1,true);
        player.draw(mockGraphic);
        BufferedImage expecImage = loadImage("res/Player/right2.png");
        assertImagesEqual(expecImage, player.getCurrentImage());
    }

    /**
     * Test playe will still switch iamge even there is not key press at all
     */
    @Test
    public void noPressKey(){
        keyInputPlayerUpdate(null,true,2,true);
        player.draw(mockGraphic);
        BufferedImage expecImage = loadImage("res/Player/down2.png");
        assertImagesEqual(expecImage, player.getCurrentImage());
    }

    /**
     * Test the player image will switch another version of moveing direction when player 
     * maintain direction during several update
     */
    @Test
    public void imageSwitch(){
        keyInputPlayerUpdate(MoveDirection.Left,true,1,false);
        player.draw(mockGraphic);
        BufferedImage expecImage = loadImage("res/Player/left2.png");
        assertImagesEqual(expecImage, player.getCurrentImage());
    }

    /**
     * The fucniton to imitate after user press or release the key and player updater 
     * several time to moving or chang its image
     * @param direction the Moving Direction of player
     * @param pressKey is the key press or release
     * @param updateTime how many time you want to player to move or change image
     * @param forPlayermoving is to view player to new position or siwtch image
     */
    private void keyInputPlayerUpdate(MoveDirection direction, boolean pressKey, int updateTime, boolean forPlayermoving){
        player.observerUpdate(direction, pressKey);
        playerUpdate.playerUpdate(updateTime, forPlayermoving);
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
