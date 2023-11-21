package cmpt276.group4.Player;

import static org.junit.Assert.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cmpt276.group4.Position;
import cmpt276.group4.WindowAndInput.GamePanel;

/**
 * Test class for player
 */
public class PlayerTests {
    public Player player;
    public int number;

    @BeforeEach
    public void setUp(){
        player = Player.getInstance();
    }

    @Test
    public void initalPosition(){
        assertEquals(new Position(GamePanel.tileSize, GamePanel.tileSize), player.getPosition());
    }
}
