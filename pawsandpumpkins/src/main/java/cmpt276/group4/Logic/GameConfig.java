package cmpt276.group4.Logic;

import java.util.ArrayList;
import java.util.List;

import cmpt276.group4.Position;
import cmpt276.group4.RecordUsedPlace;
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

    public void setWallPositions(List<Position> wallPositions) {
        if (this.wallPositions == null) {
            this.wallPositions = new ArrayList<>();
        }
        this.wallPositions.clear();
            if (wallPositions != null && !wallPositions.isEmpty()) {
            for (Position pos : wallPositions) {
                this.wallPositions.add(new Position(pos.getX_axis(), pos.getY_axis()));
            }
        }
    }
    public static synchronized GameConfig getGameConfigInstance(){
        if(instance ==null)
            instance = new GameConfig();
        return instance;
    }
    

    public GameConfig(int numberOfObstacles, int numberOfSpiders, int numberOfBasicGhosts, int numberOfAdvancedGhosts,
            int numberOfRegularRewards, int numberOfBonusRewards) {
        this.numberOfObstacles = numberOfObstacles;
        this.numberOfSpiders = numberOfSpiders;
        this.numberOfBasicGhosts = numberOfBasicGhosts;
        this.numberOfAdvancedGhosts = numberOfAdvancedGhosts;
        this.numberOfRegularRewards = numberOfRegularRewards;
        this.numberOfBonusRewards = numberOfBonusRewards;

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

    public void initializeWallPosition(int[] arrayX, int[] arrayY, List<Position> currentWallPositions) {

        if (arrayX.length == arrayY.length) {
            for (int i = 0; i < arrayX.length; i++) {
                currentWallPositions.add(new Position(arrayX[i], arrayY[i]));
            }
        } else {
            // different lengths
            System.out.println("Error: arrayX and arrayY have different lengths.");
        }
    }

}