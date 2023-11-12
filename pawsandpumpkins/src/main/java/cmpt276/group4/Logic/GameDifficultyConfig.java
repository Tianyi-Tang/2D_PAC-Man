package cmpt276.group4.Logic;

import java.util.ArrayList;
import java.util.List;
import cmpt276.group4.Position;

public abstract class GameDifficultyConfig {
    
    protected List<Position> wallPositions;
    protected int numberOfObstacles;
    protected int numberOfSpiders;
    protected int numberOfBasicGhosts;
    protected int numberOfAdvancedGhosts;
    protected int numberOfRegularRewards;
    protected int numberOfBonusRewards;

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



    public int getNumberOfRegularRewards() {
        return numberOfRegularRewards;
    }



    public int getNumberOfBonusRewards() {
        return numberOfBonusRewards;
    }



    public GameDifficultyConfig() {
        wallPositions = new ArrayList<>();
    }

    public GameDifficultyConfig( int numberOfObstacles, int numberOfSpiders, int numberOfBasicGhosts, int numberOfAdvancedGhosts, 
        int numberOfRegularRewards, int numberOfBonusRewards) {
            this.numberOfObstacles = numberOfObstacles;
            this.numberOfSpiders = numberOfSpiders;
            this.numberOfBasicGhosts = numberOfBasicGhosts;
            this.numberOfAdvancedGhosts = numberOfAdvancedGhosts;
            this.numberOfRegularRewards = numberOfRegularRewards;
            this.numberOfBonusRewards = numberOfBonusRewards;

            
        wallPositions = new ArrayList<>();
    }

    

    public List<Position> getWallPositions() {
        return wallPositions;
    }

    public void switchMap( int count, List<Position> pos,int[] Maze1_arrayX, int[] Maze1_arrayY, int[] Maze2_arrayX, int[] Maze2_arrayY){
        switch (count % 2) {
            case 1:
                initializeWallPosition(Maze1_arrayX, Maze1_arrayY, pos);
                break;

            default:
                initializeWallPosition(Maze2_arrayX, Maze2_arrayY, pos);
                break;
        }
    }
    //take the int x and y axis and convert them to position array
    public void initializeWallPosition(int[] arrayX, int[] arrayY, List<Position> currentWallPositions) {

        if (arrayX.length == arrayY.length) {
                for (int i = 0; i < arrayX.length; i++) {
                    currentWallPositions.add(new Position(arrayX[i], arrayY[i]));
                }
        }
    }



        //deep copy of wall positions
    // public void setWallPositions(List<Position> wallPositions) {
    //     if (this.wallPositions == null) {
    //         this.wallPositions = new ArrayList<>();
    //     }
    //     this.wallPositions.clear();
    //         if (wallPositions != null && !wallPositions.isEmpty()) {
    //         for (Position pos : wallPositions) {
    //             this.wallPositions.add(new Position(pos.getX_axis(), pos.getY_axis()));
    //         }
    //     }
    // }
    // Other getters and possibly setters for common attributes...
}
