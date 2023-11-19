package cmpt276.group4.Player;

import org.junit.jupiter.api.BeforeEach;

/**
 * Test class for player
 */
public class PlayerTests {
    Player player;
    @BeforeEach
    void intialPlayer(){
        player = Player.getInstance();
    }
}
