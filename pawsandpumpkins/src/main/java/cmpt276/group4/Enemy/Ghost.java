package cmpt276.group4.Enemy;
import cmpt276.group4.Position;

public class Ghost implements Enemy {
    EnemyMovement enemyMovement;
    Position playerPosition;
    boolean findPlayer;

    public Ghost() {
        this.enemyMovement = new EnemyMovement();
        this.playerPosition = new Position(5, 5); // Placeholder position
        this.findPlayer = false;
        //System.out.println("Ghost created.");
    }

    public void moveTowardPlayer() {
        System.out.println("Ghost is moving towards the player.");
        
        enemyMovement.moveTo(playerPosition);
    }

    public boolean playerAround() {
        System.out.println("Checking if player is around.");
        return findPlayer;
    }

    @Override
    public void catchPlayer() {
        System.out.println("Ghost caught the player!");
    }
}
