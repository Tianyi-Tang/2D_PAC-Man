package cmpt276.group4.Logic;

import java.util.ArrayList;
import java.util.List;

import cmpt276.group4.GameManager;
import cmpt276.group4.Position;
import cmpt276.group4.gameLevel;
import cmpt276.group4.WindowAndInput.GamePanel;

public class gameConfig {
    private int windowWidth;
    private int windowHeight;
    public gameConfig(int windowWidth, int windowHeight, List<Position> wallPositions, int numberOfObstacles,
            int numberOfSpiders, int numberOfBasicGhosts, int numberOfAdvancedGhosts, int numberOfRewards) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.wallPositions = wallPositions;
        this.numberOfObstacles = numberOfObstacles;
        this.numberOfSpiders = numberOfSpiders;
        this.numberOfBasicGhosts = numberOfBasicGhosts;
        this.numberOfAdvancedGhosts = numberOfAdvancedGhosts;
        this.numberOfRewards = numberOfRewards;
    }

    private List<Position> wallPositions;

    private int numberOfObstacles;
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

    public int getNumberOfRewards() {
        return numberOfRewards;
    }

    public void GameConfig(gameLevel level) {
        this.windowWidth = GamePanel.tileSize * 16;
        this.windowHeight = GamePanel.tileSize * 16;
        this.wallPositions = new ArrayList<>(); 

        switch (level) {
            case BASIC:
                this.numberOfObstacles = 5;
                this.numberOfSpiders = 3;
                this.numberOfBasicGhosts = 1;
                this.numberOfAdvancedGhosts = 0;
                this.numberOfRewards = 8;
                break;
            case MEDIUM:
                this.numberOfObstacles = 7;
                this.numberOfSpiders = 5;
                this.numberOfBasicGhosts = 2;
                this.numberOfAdvancedGhosts = 1;
                this.numberOfRewards = 10;
                break;
            case HARD:
                this.numberOfObstacles = 10; // Example value
                this.numberOfSpiders = 7;
                this.numberOfBasicGhosts = 3;
                this.numberOfAdvancedGhosts = 2;
                this.numberOfRewards = 12;
                break;
            default:
                throw new IllegalArgumentException("Unknown game level: " + level);
        }
    }

    public String getImageNameForPosition(Position p) {
        boolean north = false, south = false, east = false, west = false;

        for (Position wallPosition : wallPositions) {
            if (wallPosition.equals(new Position(p.getX_axis(), p.getY_axis() - GamePanel.tileSize))) {
                north = true;
            }
            if (wallPosition.equals(new Position(p.getX_axis(), p.getY_axis() + GamePanel.tileSize))) {
                south = true;
            }
            if (wallPosition.equals(new Position(p.getX_axis() + GamePanel.tileSize, p.getY_axis()))) {
                east = true;
            }
            if (wallPosition.equals(new Position(p.getX_axis() - GamePanel.tileSize, p.getY_axis()))) {
                west = true;
            }
        }

        String imageName = "";
        if (north) imageName += "north_";
        if (east) imageName += "east_";
        if (south) imageName += "south_";
        if (west) imageName += "west_";

        imageName += ".png";

        return imageName;
    }

}