package cmpt276.group4.Enemy;

import cmpt276.group4.gameLevel;
import cmpt276.group4.GameMap.RecordUsedPlace;
import cmpt276.group4.Logic.GameConfig;

/**
 * This class is responsible for initializing enemies in the game.
 * It retrieves enemy configuration from {@link GameConfig} and creates the
 * specified number and types of enemies using an {@link EnemyFactory}.
 */
public class EnemyInitialization {

    private gameLevel gameLevel;
    private int spider, basicGhost, advancedGhost;
    private GameConfig gc;


    /**
     * Constructs an EnemyInitialization object using specified
     * {@link EnemyFactory}.
     * It initializes the enemy count based on the configuration provided by
     * {@link GameConfig}.
     * 
     * @param eFactory The factory used to create enemy instances.
     * @param reocrd the recordused place that asking for passing
     */
    public EnemyInitialization(EnemyFactory eFactory, RecordUsedPlace record) {
        gc = GameConfig.getGameConfigInstance();
        spider = gc.getNumberOfSpiders();
        basicGhost = gc.getNumberOfBasicGhosts();
        advancedGhost = gc.getNumberOfAdvancedGhosts();
        //Uncomment for testing
        //System.out.println("num of spider, ghost: " + spider + ", " + basicGhost);

        eFactory.createEnemies(EnemyType.SPIDER, spider,record);
        eFactory.createEnemies(EnemyType.GHOST_BASIC, basicGhost,record);
        eFactory.createEnemies(EnemyType.GHOST_ADVANCED, advancedGhost,record);
    }

    /**
     * Returns the game level configuration.
     * 
     * @return The current game level.
     */
    public gameLevel getGameLevel() {
        return gameLevel;
    }

    /**
     * Returns the number of spiders initialized.
     * 
     * @return The number of spiders.
     */
    public int getSpider() {
        return spider;
    }

    /**
     * Returns the number of basic ghosts initialized.
     * 
     * @return The number of basic ghosts.
     */
    public int getBasicGhost() {
        return basicGhost;
    }

    /**
     * Returns the number of advanced ghosts initialized.
     * 
     * @return The number of advanced ghosts.
     */
    public int getAdvancedGhost() {
        return advancedGhost;
    }
}
