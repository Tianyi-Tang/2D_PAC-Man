package cmpt276.group4.Enemy;
import java.awt.Color;
import java.awt.Graphics2D;

import cmpt276.group4.Position;
import cmpt276.group4.RecordUsedPlace;

public class Spider implements Enemy {

    private boolean movable = false;
    private Position enemyPosition = new Position(0, 0);
    private boolean org_State = true;
    @Override
    public void catchPlayer() {
        RecordUsedPlace record = RecordUsedPlace.getInstance();
        Position playerPosition = record.getPlayerPosition();
    
        if (playerPosition == enemyPosition) {
            System.out.println("Bagel stepped on spider!"); 
        }
    }

    @Override
    public Position getEnemyPosition() {
        return enemyPosition;
    }

    @Override
    public void setEnemyPosition(Position newPosition) {
        enemyPosition = newPosition;
    }

    @Override
    public boolean isMovable() {
        return movable;
    }

    @Override
    public void draw(Graphics2D g2){
        g2.setColor(Color.white);
        g2.fillRect(enemyPosition.getX_axis(), enemyPosition.getY_axis(), 48, 48);
    }
}
