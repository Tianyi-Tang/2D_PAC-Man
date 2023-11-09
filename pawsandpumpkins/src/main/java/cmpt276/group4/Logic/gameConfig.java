package cmpt276.group4.Logic;

import java.util.List;

import cmpt276.group4.Position;

public class gameConfig {
    private int windowWidth;
    private int windowHeight;
    private List<Position> wallPositions;
    private List<Position> obstaclePositions;
    private int numberOfSpiders;
    private int numberOfBasicGhosts;
    private int numberOfAdvancedGhosts;
    private int numberOfRewards;

    public int getWindowWidth() {
        return windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }

    public List<Position> getWallPositions() {
        return wallPositions;
    }

    public List<Position> getObstaclePositions() {
        return obstaclePositions;
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

    public int getNumberOfRewards() {
        return numberOfRewards;
    }

    public void GameConfig(int windowWidth, int windowHeight, List<Position> wallPositions,
                      List<Position> obstaclePositions, int numberOfSpiders,
                      int numberOfBasicGhosts, int numberOfAdvancedGhosts, int numberOfRewards) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.wallPositions = wallPositions;
        this.obstaclePositions = obstaclePositions;
        this.numberOfSpiders = numberOfSpiders;
        this.numberOfBasicGhosts = numberOfBasicGhosts;
        this.numberOfAdvancedGhosts = numberOfAdvancedGhosts;
        this.numberOfRewards = numberOfRewards;
    }

}