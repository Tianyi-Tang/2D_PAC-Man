package cmpt276.group4;

import java.awt.Graphics2D;

public interface CharacterAvaliablePosition {
    Position position = new Position(0, 0);

    
    public Position getPosition();
    public boolean getPlayerAvaliable();
    public boolean getTakenPlace();

    public void draw(Graphics2D g2);
}
