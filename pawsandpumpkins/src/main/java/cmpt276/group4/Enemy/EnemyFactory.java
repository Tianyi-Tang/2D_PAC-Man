package cmpt276.group4.Enemy;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory class for creating enemy objects in the game.
 * This class is responsible for generating various types of enemies based on
 * the specified enemy type.
 */
public class EnemyFactory {

    /**
     * Creates a list of enemies of a specified type.
     * The method generates the requested number of enemies according to the
     * provided type.
     * Currently supports creation of basic and advanced ghosts, and spiders.
     *
     * @param type   The type of enemy to be created. It should be one of the
     *               constants defined in {@link EnemyType}.
     * @param number The number of enemies to create.
     * @return A list of created enemies.
     * @throws IllegalArgumentException If an invalid enemy type is passed.
     */
    public List<Enemy> createEnemies(EnemyType type, int number) {
        if (type == null) {
            throw new IllegalArgumentException("Invalid enemy type");
        }
        List<Enemy> enemies = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            switch (type) {
                case GHOST_BASIC:
                case GHOST_ADVANCED:
                    enemies.add(new Ghost(type));
                    // uncomment for testing
                    // System.out.println("Ghost created.");
                    break;
                case SPIDER:
                    enemies.add(new Spider());
                    // uncomment for testing
                    // System.out.println("Spider created.");
                    break;
            }
        }
        return enemies;
    }

}
