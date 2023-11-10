package cmpt276.group4.Logic;

import java.util.ArrayList;
import java.util.List;

import cmpt276.group4.Position;
import cmpt276.group4.WindowAndInput.GamePanel;

public class GameConfig {
    private int windowColumn;
    private int windowRow;
    public GameConfig(int windowColumn, int windowRow, List<Position> wallPositions, int numberOfObstacles, int numberOfSpiders, int numberOfBasicGhosts, int numberOfAdvancedGhosts, int numberOfRewards) {
        this.windowColumn = windowColumn;
        this.windowRow = windowRow;
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

    public int numberofTiles(){
        return windowColumn * windowRow;
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
    //move it to tile class
    // public String getImageNameForPosition(Position p,List<Position> wallPositions) {
    //     boolean north = false, south = false, east = false, west = false;

    //     for (Position wallPosition : wallPositions) {
    //         if (wallPosition.equals(new Position(p.getX_axis(), p.getY_axis() - GamePanel.tileSize))) {
    //             north = true;
    //         }
    //         if (wallPosition.equals(new Position(p.getX_axis(), p.getY_axis() + GamePanel.tileSize))) {
    //             south = true;
    //         }
    //         if (wallPosition.equals(new Position(p.getX_axis() + GamePanel.tileSize, p.getY_axis()))) {
    //             east = true;
    //         }
    //         if (wallPosition.equals(new Position(p.getX_axis() - GamePanel.tileSize, p.getY_axis()))) {
    //             west = true;
    //         }
    //     }

    //     String imageName = "";
    //     if (north) imageName += "north_";
    //     if (east) imageName += "east_";
    //     if (south) imageName += "south_";
    //     if (west) imageName += "west_";

    //     imageName += ".png";

    //     return imageName;
    // }

}