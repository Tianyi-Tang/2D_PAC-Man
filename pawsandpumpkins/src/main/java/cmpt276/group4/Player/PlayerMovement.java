package cmpt276.group4.Player;

import cmpt276.group4.Movement;
import cmpt276.group4.Position;

public class PlayerMovement implements Movement {

    PlayerMovement(){
        System.out.println("PlayerMovement is create");
    }

    @Override
    public boolean moveTo(Position position) {
        return isPositionAvailable(position);
    }

    @Override
    public boolean isPositionAvailable(Position position) {
        if(position.getX_axis() == 48 && position.getY_axis() == 96)
            return false;
        else
            return true;

        // if(RecordUsedPlace.getInstance().playerMovable(position))
        //     return true;
        // else
        //     return false;
    }
    
}
