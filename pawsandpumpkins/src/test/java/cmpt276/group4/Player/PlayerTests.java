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

    @BeforeEach
    public void setUp(){
        player = Player.getInstance();
        mockGameManager = mock(GameManager.class);
        player.init(mockGameManager, RoomEnvironment.getInstance(), RoomLayout.getInstance());
        mockGameManager.setPlayer(player);
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
        for(int i=0;i < 10;i++)
            player.update();
        assertEquals(player.getPosition(), new Position(0, 48));
    }

    @Test
    public void failToMove(){
        player.observerUpdate(MoveDirection.Left, true);
        player.update();
        assertEquals(player.getPosition(), new Position(48, 48));
    }


    
}
