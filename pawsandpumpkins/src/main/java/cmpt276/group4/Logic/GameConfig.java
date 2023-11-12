package cmpt276.group4.Logic;

import java.util.ArrayList;
import java.util.List;

import cmpt276.group4.Position;
import cmpt276.group4.RecordUsedPlace;
import cmpt276.group4.gameLevel;
import cmpt276.group4.WindowAndInput.GamePanel;

/**
 * Configures and stores game settings including room dimensions, obstacles,
 * enemies, and rewards.
 * This class is designed to adjust game settings based on the selected game
 * level.
 */

public class GameConfig {
    private gameLevel level;
    private int roomColumn = 16;
    private int roomRow = 16;
    // obstacle
    protected List<Position> wallPositions;
    protected int numberOfObstacles;
    // enemies
    protected int numberOfSpiders;
    protected int numberOfBasicGhosts;
    protected int numberOfAdvancedGhosts;
    // rewards
    protected int numberOfRegularRewards;
    protected int numberOfBonusRewards;
    public static GameConfig instance;
    private GameDifficultyConfig gameLevelConfig;

    /**
     * Singleton pattern to ensure only one instance of GameConfig is created.
     * 
     * @return The single instance of GameConfig.
     */
    public static synchronized GameConfig getGameConfigInstance() {
        if (instance == null)
            instance = new GameConfig();
        return instance;
    }

    /**
     * Configures game settings based on the given game level.
     * 
     * @param level The game level to configure settings for.
     */
    public void passGameLevel(gameLevel level) {
        this.level = level;
        switch (level) {
            case BASIC:
                gameLevelConfig = new BasicConfig();
                break;
            case MEDIUM:
                gameLevelConfig = new MediumConfig();
                break;
            case HARD:
            default:
                gameLevelConfig = new HardConfig();
                break;
        }
        numberOfObstacles = gameLevelConfig.getNumberOfObstacles();
        numberOfSpiders = gameLevelConfig.getNumberOfSpiders();
        numberOfBasicGhosts = gameLevelConfig.getNumberOfBasicGhosts();
        numberOfAdvancedGhosts = gameLevelConfig.getNumberOfAdvancedGhosts();
        numberOfRegularRewards = gameLevelConfig.getNumberOfRegularRewards();
        numberOfBonusRewards = gameLevelConfig.getNumberOfBonusRewards();
        wallPositions = gameLevelConfig.getWallPositions();
        // Uncomment for testing:
        // System.out.println("number of spiders are: "+ numberOfSpiders);

    }

    /**
     * Checks if the game configuration has already been initialized.
     * 
     * @return true if the game configuration is initialized, false otherwise.
     */
    public boolean alreayInitialize() {
        if (wallPositions != null && numberOfRegularRewards != 0)
            return true;
        else {
            return false;
        }

    }

    /**
     * Constructs a new GameConfig instance with default settings.
     */
    public GameConfig() {
        wallPositions = new ArrayList<Position>();
        instance = this;
    }

    public int getRoomColumn() {
        return roomColumn;
    }

    public int getRoomRow() {
        return roomRow;
    }

    public int areaofRoom(){
        return roomRow * roomColumn;
    }

    public int getNumberOfRegularRewards() {
        return numberOfRegularRewards;
    }

    public int getNumberOfBonusRewards() {
        return numberOfBonusRewards;
    }

    public int getAllRewardNum(){
        return numberOfRegularRewards + numberOfBonusRewards;
    }

    public List<Position> getWallPositions() {
        return wallPositions;
    }

    public int getNumberOfWall(){
        return wallPositions.size();
    }

    public int getNumberOfObstacles() {
        return numberOfObstacles;
    }

    public int getNumberOfSpiders() {
        return numberOfSpiders;
    }

    public int getNumberOfBasicGhosts() {
        return numberOfBasicGhosts;
    }

    public int getNumberOfAdvancedGhosts() {
        return numberOfAdvancedGhosts;
    }

    public int getTotalGhosts(){
        return numberOfBasicGhosts + numberOfAdvancedGhosts + numberOfSpiders;
    }

    public gameLevel getGameLevel(){
        return level;
    }

}