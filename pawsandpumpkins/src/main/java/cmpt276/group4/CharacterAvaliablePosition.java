package cmpt276.group4;

import java.awt.Graphics2D;

public interface CharacterAvaliablePosition {
    Position position = new Position(0, 0);

    
    public Position getPosition();

    /**
     * Can player go to this position
     * @return If ture, then player can go to this position
     */
    public boolean getPlayerAvaliable();

    /**
     * Is the position can't allow put wall, obstcale, enemy and reward on it
     * @return If true, mean these object can't place in this position; else we can put things on it
     */
    public boolean getTakenPlace();

    public void draw(Graphics2D g2);
}
