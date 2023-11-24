package cmpt276.group4.Player;

import cmpt276.group4.Movement;
import cmpt276.group4.Position;
import cmpt276.group4.GameMap.RoomLayout;

/**
 * Class the judge whether player can move to the center position
 */
public class PlayerMovement implements Movement {
    private RoomLayout roomLayout;

    public PlayerMovement(){
        roomLayout = RoomLayout.getInstance();
    }
    
    /**
     * Check the target position is aviable to player or not 
     * @param position The target position for player moving
     * @return If this position is avaliable for player return true, else return false
     */
    @Override
    public boolean isPositionAvailable(Position position) {
        if(roomLayout.isPositionAviable(position)){
            return true;
        }
        else
            return false;
    }
    
}
