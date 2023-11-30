package cmpt276.group4.Reward;

import cmpt276.group4.Position;
import cmpt276.group4.Player.Player;
import cmpt276.group4.Time.GameTime;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class PumpkinHeadTest {

    @Test
    void getScore_ReturnsCorrectScore() {
        PumpkinHead pumpkinHead = new PumpkinHead();
        assertEquals(5, pumpkinHead.getScore());
    }

    @Test
    void getPosition_AfterSetPosition_ReturnsNewPosition() {
        PumpkinHead pumpkinHead = new PumpkinHead();
        Position newPosition = new Position(10, 20);
        pumpkinHead.setPosition(newPosition);
        assertEquals(newPosition, pumpkinHead.getPosition());
    }

    @Test
    void setPosition_SetRightPosition() {
        var pumpkinHead = new PumpkinHead();
        var newPosition = new Position(10, 10);
        pumpkinHead.setPosition(newPosition);
        assertEquals(newPosition, pumpkinHead.getPosition());

    }

    @Test
    void isBonusReward_AlwaysReturnTure() {
        PumpkinHead pumpkinHead = new PumpkinHead();
        assertTrue(pumpkinHead.isBonusReward());
    }


    @Test
    void testAddScore() {
        Player mockPlayer = Mockito.mock(Player.class);
        PumpkinHead reward = new PumpkinHead();

        int initialScore = 10;
        reward.addScore(mockPlayer, initialScore);

        verify(mockPlayer).addScoreToPlayer(initialScore, reward.isBonusReward());
    }
    @Test
    void testAddBenefitWhenPlayerNotAtSamePosition() {
        Player mockPlayer = mock(Player.class);
        PumpkinHead reward = new PumpkinHead();

        Position rewardPosition = new Position(1, 1);
        Position playerPosition = new Position(2, 2);
        reward.setPosition(rewardPosition);

        when(mockPlayer.getPosition()).thenReturn(playerPosition);
        reward.addBenefit(mockPlayer);
        verify(mockPlayer, never()).addScoreToPlayer(anyInt(), anyBoolean());
    }




    @Test
    void testAddBenefitWhenNotAvailable() {
        Player mockPlayer = mock(Player.class);
        PumpkinHead reward = new PumpkinHead();
        Position samePosition = new Position(2, 2);
        reward.setPosition(samePosition);
        reward.setAvailable(false);
        when(mockPlayer.getPosition()).thenReturn(samePosition);
        reward.addBenefit(mockPlayer);
        verify(mockPlayer, never()).addScoreToPlayer(anyInt(), anyBoolean());
    }



    @Test
    void getAvailable_correctlyReturnAvailable() {
        var pumpkinHead = new PumpkinHead();
        assertTrue(pumpkinHead.getAvailable());

    }
    private boolean shouldBeDrawn(PumpkinHead pumpkinHead) {
        GameTime gameTime = GameTime.getInstance();
        boolean timeCondition = gameTime.getTime() % pumpkinHead.displayDuration >= pumpkinHead.displayDuration / 2;

        return pumpkinHead.org_State && timeCondition;
    }
    @Test
    void testDrawMethod() {
        PumpkinHead pumpkinHead = new PumpkinHead();
        Graphics2D mockedGraphics = mock(Graphics2D.class);

        pumpkinHead.draw(mockedGraphics);

        if (shouldBeDrawn(pumpkinHead)) {
            verify(mockedGraphics).drawImage(any(BufferedImage.class), anyInt(), anyInt(), anyInt(), anyInt(), isNull());
        } else {
            verify(mockedGraphics, never()).drawImage(any(BufferedImage.class), anyInt(), anyInt(), anyInt(), anyInt(), isNull());
        }
    }




}