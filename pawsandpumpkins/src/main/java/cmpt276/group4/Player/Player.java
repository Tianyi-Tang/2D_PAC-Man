package cmpt276.group4.Player;

import java.awt.Color;
import java.awt.Graphics2D;

import cmpt276.group4.Position;
import cmpt276.group4.WindowAndInput.MoveDirection;

public class Player implements KeyMovingObserver {
    private Position playerPosition;
    private static Player _instance = null;
    private MoveDirection direction = MoveDirection.StaySame;

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
    public void observerUpdate(MoveDirection direction) {
        this.direction = direction;
    }

    public void update(){
        switch (direction) {
            case Up:
                playerPosition.addOnY_axis(-10);
                break;
            case Down:
                playerPosition.addOnY_axis(10);
                break;
            case Left:
                playerPosition.addOnX_axis(-10);
                break;
            case Right:
                playerPosition.addOnX_axis(10);
                break;
        }
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.white);
        g2.fillRect(playerPosition.getX_axis(), playerPosition.getY_axis(), 48, 48);
    }




}
