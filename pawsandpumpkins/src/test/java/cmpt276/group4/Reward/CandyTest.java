package cmpt276.group4.Reward;

import cmpt276.group4.Player.Player;
import cmpt276.group4.Position;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

class CandyTest {

    @Test
    void getScore_ReturnCorrectScore() {
        Candy candy = new Candy();
        assertEquals(1,candy.getScore());
    }

    @Test
    void getPosition() {
        Candy candy = new Candy();
        Position newPosition = new Position(10, 20); // Replace with actual Position class.
        candy.setPosition(newPosition);
        assertEquals(newPosition, candy.getPosition());
    }

    @Test
    void setPosition() {
        Candy candy = new Candy();
        Position newPosition = new Position(10, 20);
        candy.setPosition(newPosition);
        assertEquals(newPosition, candy.getPosition());
    }


    @Test
    void isBonusReward_AlwaysReturnFalse() {
        Candy candy = new Candy();
        assertFalse(candy.isBonusReward());
    }


    @Test
    void addBenefit_CorrectlyAddSoreWhenReachDifferentPosition() {
        Player mockPlayer = mock(Player.class);
        var reward = new Candy();

        Position rewardPosition = new Position(1, 1);
        Position playerPosition = new Position(2, 2);
        reward.setPosition(rewardPosition);

        when(mockPlayer.getPosition()).thenReturn(playerPosition);

        reward.addBenefit(mockPlayer);


        verify(mockPlayer, never()).addScoreToPlayer(anyInt(), anyBoolean());


    }
    @Test
    void addBenefit_CorrectlyAddSoreWhenReachSamePosition() {
        Player mockPlayer = mock(Player.class);
        var reward = new Candy();

        Position rewardPosition = new Position(2, 2);
        Position playerPosition = new Position(2, 2);
        reward.setPosition(rewardPosition);

        when(mockPlayer.getPosition()).thenReturn(playerPosition);

        reward.addBenefit(mockPlayer);


        verify(mockPlayer).addScoreToPlayer(reward.getScore(), reward.isBonusReward());


    }
    @Test
    void addSore_CorrectlyAddSore(){
        var candy = new Candy();
        Player mockPlayer = Mockito.mock(Player.class);
        candy.addScore(mockPlayer,10);
        verify(mockPlayer).addScoreToPlayer(10, candy.isBonusReward());
    }



    @Test
    void testDrawMethod() {
        Candy candy = new Candy();
        Graphics2D mockedGraphics = mock(Graphics2D.class);
        Position cdposition = new Position(1, 1);
        candy.setPosition(cdposition);

        candy.draw(mockedGraphics);

        verify(mockedGraphics).drawImage(any(BufferedImage.class), eq(1), eq(1), eq(48), eq(48), isNull());
    }
    }