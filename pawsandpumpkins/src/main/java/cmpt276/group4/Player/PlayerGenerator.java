package cmpt276.group4.Player;

import cmpt276.group4.GameManager;
import cmpt276.group4.GameMap.RoomEnvironment;
import cmpt276.group4.GameMap.RoomLayout;

/**
 * Class that generate player
 */
public class PlayerGenerator {

    /**
     * Player will be first generate through the function
     * @return player
     */
    public static Player creatPlayer(GameManager manager, RoomEnvironment roomEnvironment, RoomLayout roomLayout){
        Player player = Player.getInstance();
        player.init(manager, roomEnvironment,roomLayout);
        return player;
    }
}
