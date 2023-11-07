package cmpt276.group4.Player;

import cmpt276.group4.Movement;
import cmpt276.group4.Position;
import cmpt276.group4.RecordUsedPlace;
import cmpt276.group4.WindowAndInput.GamePanel;

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
        if(RecordUsedPlace.getInstance().characterMovable(position)){
            if(position.getX_axis() < 0 || position.getY_axis() < 0)
                return false;
            if(position.getX_axis() > GamePanel.screenWidth - GamePanel.tileSize || position.getY_axis() > GamePanel.screenHeight - GamePanel.tileSize)
                return false;
            return true;
        }
        else
            return false;
    }
    
}
