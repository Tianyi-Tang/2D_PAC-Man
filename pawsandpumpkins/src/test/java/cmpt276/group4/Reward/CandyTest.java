package cmpt276.group4.Reward;

import cmpt276.group4.Logic.WindowConfig;
import cmpt276.group4.Position;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CandyTest {

    @Test
    void getScore_ReturnCorrectScore() {
        var candy = new Candy();
        assertEquals(1,candy.getScore());
    }

    @Test
    void getPosition() {
        var candy = new Candy();
        Position newPosition = new Position(10, 20); // Replace with actual Position class.
        candy.setPosition(newPosition);
        assertEquals(newPosition, candy.getPosition());
    }

    @Test
    void setPosition() {
        var candy = new Candy();
        Position newPosition = new Position(10, 20);
        candy.setPosition(newPosition);
        assertEquals(newPosition, candy.getPosition());
    }


    @Test
    void isBonusReward_AlwaysReturnFalse() {
        var candy = new Candy();
        assertFalse(candy.isBonusReward());
    }


    @Test
    void addBenefit_CorrectlyAddSore() {


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