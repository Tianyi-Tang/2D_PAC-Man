package cmpt276.group4.Player;

import cmpt276.group4.Position;

public class PlayerGenerator {

    public static Player creatPlayer(){
        Player player = Player.getInstance();

        player.setPosition(new Position(96, 96));
        player.setPlayerMovement(new PlayerMovement());
        return player;
    }
}
