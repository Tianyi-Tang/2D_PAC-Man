package cmpt276.group4.Enemy;

import cmpt276.group4.gameLevel;
import cmpt276.group4.Logic.GameConfig;

public class EnemyInitialization {

    private gameLevel gameLevel;
    private int spider,basicGhost,advancedGhost;
    private GameConfig gc;



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

        eFactory.createEnemies(EnemyType.SPIDER, spider);
        eFactory.createEnemies(EnemyType.GHOST_BASIC, basicGhost);
        eFactory.createEnemies(EnemyType.GHOST_ADVANCED, advancedGhost);

    }

    public EnemyInitialization(EnemyFactory eFactory) {
        gc = GameConfig.getGameConfigInstance();
        spider = gc.getNumberOfSpiders();
        basicGhost = gc.getNumberOfBasicGhosts(); 
        advancedGhost = gc.getNumberOfAdvancedGhosts() ;
        System.out.println("num of spider, ghost "+ spider + " "+ basicGhost);

        eFactory.createEnemies(EnemyType.SPIDER, spider);
        eFactory.createEnemies(EnemyType.GHOST_BASIC, basicGhost);
        eFactory.createEnemies(EnemyType.GHOST_ADVANCED, advancedGhost);

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