package cmpt276.group4.Logic;

import java.util.ArrayList;
import java.util.List;

import cmpt276.group4.Position;

public class HardConfig extends GameDifficultyConfig {

    // private List<Position> currentWallPositions;
    private static int count = 0;

     private static final int[] hardMaze1_arrayX = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14, 15,
            15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15 };
     private static final int[] hardMaze1_arrayY = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 13, 2, 2, 2, 6, 6, 10, 10, 10, 11,
            11, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };
     private static final int[] hardMaze2_arrayX = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 6, 7, 9, 10, 11, 12, 13, 14,
            15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15 };
     private static final int[] hardMaze2_arrayY = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 7, 7, 7, 7, 3, 3, 12, 12, 12, 12,
            12, 12, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };

    public HardConfig() {
        super();
        numberOfObstacles = 6;
        numberOfSpiders = 13;
        numberOfBasicGhosts = 3;
        numberOfAdvancedGhosts = 0;
        numberOfRegularRewards = 10;
        numberOfBonusRewards = 3;
        switchMap(count, wallPositions, hardMaze1_arrayX, hardMaze1_arrayY, hardMaze2_arrayX, hardMaze2_arrayY);
        count++;
    }

}
