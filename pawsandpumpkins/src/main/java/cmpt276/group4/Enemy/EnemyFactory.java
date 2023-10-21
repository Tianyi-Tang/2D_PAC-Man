package cmpt276.group4.Enemy;
import java.util.ArrayList;
import java.util.List;


public class EnemyFactory {
    public List<Enemy> createEnemies(EnemyType type, int number) {
        List<Enemy> enemies = new ArrayList<>();

        for (int i = 0; i < number; i++) {
            switch (type) {
                case GHOST:
                    enemies.add((Enemy)(new Ghost()));
                    break;
                case SPIDER:
                    enemies.add((Enemy)(new Spider()));
                    break;
                default:
                    throw new IllegalArgumentException("Unknown enemy type");
            }
        }

        System.out.println(number + " " + type + " enemies created.");
        return enemies;
    }
}