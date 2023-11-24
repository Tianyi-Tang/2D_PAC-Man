package cmpt276.group4.Player;

import cmpt276.group4.GameManager;
import cmpt276.group4.GameMap.RoomEnvironment;

/**
 * Class that generate player
 */
public class PlayerGenerator {

    /**
     * Player will be first generate through the function
     * @return player
     */
    public static Player creatPlayer(GameManager manager, RoomEnvironment roomEnvironment){
        Player player = Player.getInstance();
        player.init(manager, roomEnvironment);
        return player;
    }
}
