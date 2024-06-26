package cmpt276.group4.Logic;

import java.util.ArrayList;
import java.util.List;
import cmpt276.group4.Position;

/**
 * Configures the game difficulty settings for the hard level.
 * This includes settings for the number of obstacles, enemies, and rewards,
 * as well as the layout of the game map.
 */

public class HardConfig extends GameDifficultyConfig {

       // Static variables to store the coordinates for two hard level maze layouts

       private static final int[] hardMaze1_arrayX = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 4, 5, 6, 7, 8,
                     10, 11, 12, 13, 14, 15,
                     15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15 };
       private static final int[] hardMaze1_arrayY = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 13, 2, 2,
                     2, 6, 6, 10, 10, 10, 11,
                     11, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };

       private static final int[] hardMaze2_arrayX = {  0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
              15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,
              13,12,11,10,9,8,7,6,5,4,3,2,1,0,
              0,0,0,0,0,0,0,0,0,0,0,0,0,0,
              3,2,3,2,2,2,3,2,2,2,2,3,4,5,
              7,8,9,9,9,4,5,6,6,6,7,
              13,13,13,12,11,11,11,11,12,13,13,8,8,9,10,11,11,12,13,13,13,13,
              7,8,9,10,11,8



              };
       private static final int[] hardMaze2_arrayY = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
              1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,
              15,15,15,15,15,15,15,15,15,15,15,15,15,15,
              14,13,12,11,10,9,8,7,6,5,4,3,2,1,
              2,3,3,6,7,8,8,10,11,12,13,13,13,13,
              2,2,2,3,4,5,5,5,6,7,7,
              1,2,3,3,3,4,5,6,6,6,7,10,9,9,9,9,10,10,10,11,12,13,
              12,12,12,12,12,14



              };
       private static int count = 0;

       /**
        * Constructs a HardConfig object with predefined settings for a hard level
        * game.
        * It initializes the number of various game elements and selects a maze layout.
        */

       public HardConfig() {
              super();
              numberOfObstacles = 5;
              numberOfSpiders = 5;
              numberOfBasicGhosts = 3;
              numberOfAdvancedGhosts = 0;
              numberOfRegularRewards = 10;
              numberOfBonusRewards = 3;
              switchMap(count, wallPositions, hardMaze1_arrayX, hardMaze1_arrayY, hardMaze2_arrayX, hardMaze2_arrayY);
              count++;
       }

}
