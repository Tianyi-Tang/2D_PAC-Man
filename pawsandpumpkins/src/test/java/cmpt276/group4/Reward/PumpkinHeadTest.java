package cmpt276.group4.Reward;

import cmpt276.group4.Position;
import cmpt276.group4.Player.Player;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

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
        var newPosition = new Position(10,10);
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

        // Verify if player's score is updated
        verify(mockPlayer).addScoreToPlayer(initialScore, reward.isBonusReward());
    }
    @Test
    void getAvailable_correctlyReturnAvailable(){
        var pumpkinHead = new PumpkinHead();
        assertTrue(pumpkinHead.getAvailable());

    }

    @Test
    void draw() {
    }
}