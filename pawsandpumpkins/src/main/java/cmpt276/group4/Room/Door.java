package cmpt276.group4.Room;

import java.awt.Graphics2D;

import cmpt276.group4.CharacterAvaliablePosition;
import cmpt276.group4.GameManager;
import cmpt276.group4.Position;
import cmpt276.group4.Player.Player;

/**
 * The Door class represents a door in the game, implementing the CharacterAvaliablePosition interface.
 */
public class Door implements CharacterAvaliablePosition{

    private boolean open;
    private Position position;

    /**
     * Constructor to initialize a door with the specified open status and position.
     *
     * @param isOpen   True if the door is open, false if closed.
     * @param position The position of the door.
     */
    public Door(boolean isOpen, Position position) {
        this.open = isOpen;
        this.position = position;
    }

    /**
     * Gets whether the player can access the door.
     *
     * @return True if the player can access the door, false otherwise.
     */
    @Override
    public boolean getPlayerAvaliable() {
        return true;
    }

    public void turnOnDoor(){
        if(GameManager.getInstance().isPlayerCollectAllRewards())
            open = true;
    }

    public void playerLeaveRoom(){
        if(open){
            if(Player.getInstance().getPosition().equal(position))
                GameManager.getInstance().playerLeaveDoor();
        }
    }

    /**
     * Gets the position of the door.
     *
     * @return The position of the door.
     */
    @Override
    public Position getPosition() {
        return position;
    }

     /**
     * Gets whether the position is already taken.
     *
     * @return True if the position is taken, false otherwise.
     */
    @Override
    public boolean getTakenPlace() {
        return true;
    }

    /**
     * Draws the door on the graphics context.
     *
     * @param g2 The Graphics2D context on which to draw the door.
     */
    @Override
    public void draw(Graphics2D g2) {
        
    }
}
