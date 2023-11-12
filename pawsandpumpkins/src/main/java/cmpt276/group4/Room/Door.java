package cmpt276.group4.Room;

import java.awt.Graphics2D;

import cmpt276.group4.CharacterAvaliablePosition;
import cmpt276.group4.Position;

public class Door implements CharacterAvaliablePosition{
    // open variable
    private boolean open;
    private Position position;

    public Door(boolean isOpen, Position position) {
        this.open = isOpen;
        this.position = position;
    }

    @Override
    public boolean getPlayerAvaliable() {
        return true;
    }

    @Override
    public Position getPosition() {
        return position;
    }
    @Override
    public boolean getTakenPlace() {
        return true;
    }
    @Override
    public void draw(Graphics2D g2) {
        
    }
}
