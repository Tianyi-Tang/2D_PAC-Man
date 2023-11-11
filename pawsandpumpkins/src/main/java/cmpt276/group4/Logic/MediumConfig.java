package cmpt276.group4.Logic;

import java.util.ArrayList;
import java.util.List;

import cmpt276.group4.Position;

public class MediumConfig extends GameDifficultyConfig {

    // private List<Position> currentWallPositions;
    private static int count = 0;

    private static final int[] mediumMaze1_arrayX = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 5, 6, 8, 9,
            10, 11, 12, 13, 14, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15 };
    private static final int[] mediumMaze1_arrayY = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 1, 1, 1, 1,
            9, 9, 9, 9, 9, 9, 9, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };
    private static final int[] mediumMaze2_arrayX = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 3, 4, 5, 7, 8,
            10, 11, 12, 14, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15 };
    private static final int[] mediumMaze2_arrayY = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 14, 14, 14,
            14, 6, 6, 3, 3, 3, 2, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };

    public MediumConfig() {
        super();
        numberOfObstacles = 5;
        numberOfSpiders = 9;
        numberOfBasicGhosts = 2;
        numberOfAdvancedGhosts = 0;
        numberOfRegularRewards = 6;
        numberOfBonusRewards = 3;

        switchMap(count, wallPositions, mediumMaze1_arrayX, mediumMaze1_arrayY, mediumMaze2_arrayX, easyMaze2_arrayY);
        count++;
    }

}
