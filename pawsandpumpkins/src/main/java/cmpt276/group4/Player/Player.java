package cmpt276.group4.Player;

import cmpt276.group4.Position;
import cmpt276.group4.WindowAndInput.MoveDirection;

public class Player implements KeyMovingObserver {
    private Position playerPosition;
    private static Player _instance = null;

    private PlayerMovement movement;

    Player(){
        System.out.println("create player");
    }

    public static Player getInstance(){
        if(_instance == null)
            _instance = new Player();
        return _instance;
    }

    public void setPosition(Position position){
        playerPosition = position;
    }

    public Position getPosition(){
        return playerPosition;
    }

    public void setPlayerMovement(PlayerMovement playerMovement){
        movement = playerMovement;
    }

    @Override
    public void update(MoveDirection direction) {
        if(direction == MoveDirection.Up){
            
        }
        else if(direction == MoveDirection.Down){

        }
        else if(direction == MoveDirection.Left){

        }
        else if(direction == MoveDirection.Right){

        }
    }


}
