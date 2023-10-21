package cmpt276.group4.Player;

import cmpt276.group4.Movement;
import cmpt276.group4.Position;

public class PlayerMovement implements Movement {

    PlayerMovement(){
        System.out.println("PlayerMovement is create");
    }

    @Override
    public boolean moveTo(Position position) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'moveTo'");
    }

    @Override
    public boolean moveable(Position position) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'moveable'");
    }
    
}
