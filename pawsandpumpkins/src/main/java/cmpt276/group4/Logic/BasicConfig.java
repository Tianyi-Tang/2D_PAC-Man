package cmpt276.group4.Logic;

import java.util.ArrayList;
import java.util.List;

import cmpt276.group4.Position;

public class BasicConfig extends GameDifficultyConfig {

    //private List<Position> currentWallPositions;
    private static int count = 0;
    private static final int[] easyMaze1_arrayX = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,
            13, 14, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15 };
    private static final int[] easyMaze1_arrayY = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15,
            15, 15, 15, 15, 15, 15, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };
    private static final int[] easyMaze2_arrayX = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 7, 7, 7, 7, 15, 15, 15, 15,
            15, 15, 15, 15, 15, 15, 15, 15 };
    private static final int[] easyMaze2_arrayY = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 1, 2, 3, 4, 5, 6, 7, 8, 0, 1, 2, 3,
            4, 5, 6, 7, 8, 9, 10, 11 };

    public BasicConfig() {
        super();
        numberOfObstacles = 5;
        numberOfSpiders = 3;
        numberOfBasicGhosts = 1;
        numberOfAdvancedGhosts = 0;
        numberOfRegularRewards = 5;
        numberOfBonusRewards = 1;

        switchMap(count, wallPositions, easyMaze1_arrayX, easyMaze1_arrayY, easyMaze2_arrayX, easyMaze2_arrayY);
        count++;
    }

}