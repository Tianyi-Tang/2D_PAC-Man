package cmpt276.group4.Enemy;

import java.util.List;

public class EnemyInitialization {
    int enemyNum;
    EnemyType type;

    public EnemyInitialization(EnemyType type, int enemyNum) {
        this.type = type;
        this.enemyNum = enemyNum;
        System.out.println("Enemy Initialization with " + enemyNum + " " + type + " enemies.");
    }

    public List<Enemy> createEnemies() {
        EnemyFactory enemyFactory = new EnemyFactory();
        return enemyFactory.createEnemies(type, enemyNum);
    }
}