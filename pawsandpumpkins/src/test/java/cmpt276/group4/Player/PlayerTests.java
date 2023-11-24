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

    @Test
    public void initalPosition(){
        assertEquals(new Position( WindowConfig.tileSize, WindowConfig.tileSize), player.getPosition());
    }

    @Test
    public void addScoreToPlayer(){
        player.addScoreToPlayer(4, true);
        assertEquals(player.getCollectScore(), 4);
    }

    @Test
    public void deductPoint(){
        player.addScoreToPlayer(15, true);
        player.deductPoint(10);
        assertEquals(player.totalScore(), 5);
    }

    @Test
    public void negativePoint(){
        mockGameManager.setPlayer(player);
        player.deductPoint(4);
        verify(mockGameManager).negativePoint();
    }

    @Test
    public void deductPointInShortTime(){
        player.deductPoint(5);
        player.deductPoint(10);
        assertEquals(player.getDeductScore(), 5);
    }

    @Test
    public void addGeneralRewards(){
        player.addScoreToPlayer(5, false);
        player.addScoreToPlayer(2, false);
        assertEquals(player.getGeneralRewardNum(), 2);
    }

    @Test
    public void addBonusReward(){
        player.addScoreToPlayer(6, true);
        assertEquals(player.getBonusRewardNum(), 1);
    }

    @Test
    public void playerMovingLeft(){
        player.observerUpdate(MoveDirection.Left, true);
        runUpdatemultipleTime(directionUpdateTime);
        assertEquals(player.getPosition(), new Position(0, 48));
    }

    @Test
    public void failToMove(){
        player.observerUpdate(MoveDirection.Left, true);
        player.update();
        assertEquals(player.getPosition(), new Position(48, 48));
    }

    @Test
    public void initialmovingImage(){
        player.draw(mockGraphic);
        BufferedImage expectImage = loadImage("res/Player/down1.png");
        assertImagesEqual(expectImage,player.getCurrentImage());
    }

    @Test
    public void movingImageUp(){
        player.observerUpdate(MoveDirection.Up, true);
        runUpdatemultipleTime(directionUpdateTime);
        player.draw(mockGraphic);
        BufferedImage expecImage = loadImage("res/Player/up1.png");
        assertImagesEqual(expecImage, player.getCurrentImage());
    }

    @Test
    public void imageSwitch(){
        player.observerUpdate(MoveDirection.Left, true);
        runUpdatemultipleTime(imageChangingTime);
        player.draw(mockGraphic);
        BufferedImage expecImage = loadImage("res/Player/left2.png");
        assertImagesEqual(expecImage, player.getCurrentImage());
    }

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

    private void runUpdatemultipleTime(int index){
        for(int i=0;i < index;i++)
            player.update();
    }

    private BufferedImage loadImage(String path){
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }


    private void  assertImagesEqual(BufferedImage expectImage, BufferedImage actualImage){
        int[] expectedPixels = expectImage.getRGB(0, 0, expectImage.getWidth(), expectImage.getHeight(), null, 0, expectImage.getWidth());
        int[] actualPixels = actualImage.getRGB(0, 0, actualImage.getWidth(), actualImage.getHeight(), null, 0, actualImage.getWidth());

        assertArrayEquals(expectedPixels, actualPixels);
    }


    
}
