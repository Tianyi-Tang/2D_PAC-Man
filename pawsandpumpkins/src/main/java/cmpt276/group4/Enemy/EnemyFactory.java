package cmpt276.group4.Enemy;
import java.util.ArrayList;
import java.util.List;


public class EnemyFactory {

    public List<Enemy> createEnemies(EnemyType type, int number) {
        System.out.println("Creating " + number + " " + type + " enemies.");  // Added for clarity
        List<Enemy> enemies = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            //System.out.println(i);
            switch (type) {
                case GHOST:
                    enemies.add(new Ghost());
                    System.out.println("Ghost created.");
                    break;
                case SPIDER:
                    enemies.add(new Spider());
                    System.out.println("Spider created.");
                    break;
                default:
                    throw new IllegalArgumentException("Invalid enemy type");
            }
        }
        return enemies;
    }
    
}


