package cmpt276.group4.Logic;

import java.util.ArrayList;
import java.util.List;
import cmpt276.group4.Position;
import cmpt276.group4.WindowAndInput.GamePanel;

/**
 * Represents the configuration of game difficulty.
 * This abstract class defines the common attributes and functionalities
 * related to different game difficulty settings such as the number of various
 * entities (obstacles, spiders, ghosts, rewards) and their positions.
 */

public abstract class GameDifficultyConfig {

    protected List<Position> wallPositions;
    protected int numberOfObstacles;
    protected int numberOfSpiders;
    protected int numberOfBasicGhosts;
    protected int numberOfAdvancedGhosts;
    protected int numberOfRegularRewards;
    protected int numberOfBonusRewards;

    /**
     * Gets the number of obstacles in the game.
     * 
     * @return The number of obstacles.
     */
    public int getNumberOfObstacles() {
        return numberOfObstacles;
    }

    /**
     * Gets the number of spiders in the game.
     * 
     * @return The number of spiders.
     */
    public int getNumberOfSpiders() {
        return numberOfSpiders;
    }

    /**
     * Gets the number of basic ghosts in the game.
     * 
     * @return The number of basic ghosts.
     */

    public int getNumberOfBasicGhosts() {
        return numberOfBasicGhosts;
    }

    /**
     * Gets the number of advanced ghosts in the game.
     * 
     * @return The number of advanced ghosts.
     */

    public int getNumberOfAdvancedGhosts() {
        return numberOfAdvancedGhosts;
    }

    /**
     * Gets the number of regular rewards in the game.
     * 
     * @return The number of regular rewards.
     */

    public int getNumberOfRegularRewards() {
        return numberOfRegularRewards;
    }

    /**
     * Gets the number of bonus rewards in the game.
     * 
     * @return The number of bonus rewards.
     */

    public int getNumberOfBonusRewards() {
        return numberOfBonusRewards;
    }

    /**
     * Default constructor initializing the list of wall positions.
     */

    public GameDifficultyConfig() {
        wallPositions = new ArrayList<>();
    }

    /**
     * Constructs a new GameDifficultyConfig with the specified parameters.
     * 
     * @param numberOfObstacles      The number of obstacles.
     * @param numberOfSpiders        The number of spiders.
     * @param numberOfBasicGhosts    The number of basic ghosts.
     * @param numberOfAdvancedGhosts The number of advanced ghosts.
     * @param numberOfRegularRewards The number of regular rewards.
     * @param numberOfBonusRewards   The number of bonus rewards.
     */

    public GameDifficultyConfig(int numberOfObstacles, int numberOfSpiders, int numberOfBasicGhosts,
            int numberOfAdvancedGhosts,
            int numberOfRegularRewards, int numberOfBonusRewards) {
        this.numberOfObstacles = numberOfObstacles;
        this.numberOfSpiders = numberOfSpiders;
        this.numberOfBasicGhosts = numberOfBasicGhosts;
        this.numberOfAdvancedGhosts = numberOfAdvancedGhosts;
        this.numberOfRegularRewards = numberOfRegularRewards;
        this.numberOfBonusRewards = numberOfBonusRewards;

        wallPositions = new ArrayList<>();
    }

    /**
     * Gets the list of wall positions in the game.
     * 
     * @return A list of wall positions.
     */

    public List<Position> getWallPositions() {
        return wallPositions;
    }

    /**
     * Switches the map configuration based on the given count.
     * 
     * @param count        The count used to determine which map configuration to
     *                     use.
     * @param pos          The list of positions to be populated.
     * @param Maze1_arrayX The x-coordinates for the first maze configuration.
     * @param Maze1_arrayY The y-coordinates for the first maze configuration.
     * @param Maze2_arrayX The x-coordinates for the second maze configuration.
     * @param Maze2_arrayY The y-coordinates for the second maze configuration.
     */
    public void switchMap(int count, List<Position> pos, int[] Maze1_arrayX, int[] Maze1_arrayY, int[] Maze2_arrayX,
            int[] Maze2_arrayY) {
        switch (count % 2) {
            case 1:
                initializeWallPosition(Maze1_arrayX, Maze1_arrayY, pos);
                break;

            default:
                initializeWallPosition(Maze2_arrayX, Maze2_arrayY, pos);
                break;
        }
    }

    /**
     * Initializes the positions of walls based on the provided x and y coordinates.
     * 
     * @param arrayX               An array of x-coordinates for the wall positions.
     * @param arrayY               An array of y-coordinates for the wall positions.
     * @param currentWallPositions The list where wall positions will be stored.
     */
    public void initializeWallPosition(int[] arrayX, int[] arrayY, List<Position> currentWallPositions) {

        if (arrayX.length == arrayY.length) {
            for (int i = 0; i < arrayX.length; i++) {
                currentWallPositions.add(new Position(arrayX[i]*GamePanel.tileSize, arrayY[i]*GamePanel.tileSize));
            }
        }
    }

}
