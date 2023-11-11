package cmpt276.group4.Logic;

import java.util.ArrayList;
import java.util.List;

import cmpt276.group4.Position;
import cmpt276.group4.RecordUsedPlace;
import cmpt276.group4.gameLevel;
import cmpt276.group4.WindowAndInput.GamePanel;

public class GameConfig {
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

    public static synchronized GameConfig getGameConfigInstance() {
        if (instance == null)
            instance = new GameConfig();
        return instance;
    }

    public void passGameLevel(gameLevel level) {
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

    }

    public GameConfig() {
        wallPositions = new ArrayList<Position>();
    }

    public int getRoomColumn() {
        return roomColumn;
    }

    public int getRoomRow() {
        return roomRow;
    }

    public int getNumberOfRegularRewards() {
        return numberOfRegularRewards;
    }

    public int getNumberOfBonusRewards() {
        return numberOfBonusRewards;
    }

    public int numberofTiles() {
        return roomColumn * roomRow;
    }

    public List<Position> getWallPositions() {
        return wallPositions;
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

    // public GameConfig(int numberOfObstacles, int numberOfSpiders, int numberOfBasicGhosts, int numberOfAdvancedGhosts,
    //         int numberOfRegularRewards, int numberOfBonusRewards) {
    //     this.numberOfObstacles = numberOfObstacles;
    //     this.numberOfSpiders = numberOfSpiders;
    //     this.numberOfBasicGhosts = numberOfBasicGhosts;
    //     this.numberOfAdvancedGhosts = numberOfAdvancedGhosts;
    //     this.numberOfRegularRewards = numberOfRegularRewards;
    //     this.numberOfBonusRewards = numberOfBonusRewards;

    // }
}