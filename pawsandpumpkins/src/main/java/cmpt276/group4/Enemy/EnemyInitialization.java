package cmpt276.group4.Enemy;

import java.util.List;

import cmpt276.group4.gameLevel;

public class EnemyInitialization {

    private gameLevel gameLevel;
    private int spider,basicGhost,advancedGhost;

    public EnemyInitialization(int enemyNum) {
        // this.enemyNum = enemyNum;
        System.out.println("Enemy Initialization with " + enemyNum + " enemies.");
    }

    public EnemyInitialization(gameLevel gameLevel, EnemyFactory eFactory) {
        this.gameLevel = gameLevel;
        switch (gameLevel) {
            case BASIC:
                spider = 3;
                basicGhost = 1; 
                advancedGhost = 0 ;
                break;
            case MEDIUM:
                spider = 9;
                basicGhost = 2; 
                advancedGhost = 0 ;
                break;
            case HARD:
                spider = 13;
                basicGhost = 3; 
                advancedGhost = 0 ;
                break;
        }
        // GHOST_AVDANCED,
        // GHOST_BASIC,
        // SPIDER
        eFactory.createEnemies(EnemyType.SPIDER, spider);
        eFactory.createEnemies(EnemyType.GHOST_BASIC, basicGhost);
        eFactory.createEnemies(EnemyType.GHOST_ADVANCED, advancedGhost);
        System.out.println("Enemy Initialization with " + (spider + basicGhost
        + advancedGhost) + " enemies.");
    }

    public gameLevel getGameLevel() {
        return gameLevel;
    }
    public int getSpider() {
        return spider;
    }
    public int getBasicGhost() {
        return basicGhost;
    }
    public int getAdvancedGhost() {
        return advancedGhost;
    }

}