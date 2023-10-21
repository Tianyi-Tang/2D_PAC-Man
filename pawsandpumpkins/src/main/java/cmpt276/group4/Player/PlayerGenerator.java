package cmpt276.group4.Player;

import cmpt276.group4.Position;

public class PlayerGenerator {

    public Player creatPlayer(Position position){
        Player player = Player.getInstance();

        player.setPosition(position);
        player.setPlayerMovement(new PlayerMovement());
        return player;
    }
}
