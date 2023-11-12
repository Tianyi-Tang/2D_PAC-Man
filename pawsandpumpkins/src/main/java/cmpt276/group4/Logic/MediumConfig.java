package cmpt276.group4.Logic;

import java.util.ArrayList;
import java.util.List;

import cmpt276.group4.Position;

/**
 * Configures the game difficulty settings for the medium level.
 * This includes settings for the number of obstacles, enemies, and rewards,
 * as well as the layout of the game map.
 */

public class MediumConfig extends GameDifficultyConfig {
        // Static variables to store the coordinates for two medium level maze layouts

        private static final int[] mediumMaze1_arrayX = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 5, 6, 8,
                        9,
                        10, 11, 12, 13, 14, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15 };
        private static final int[] mediumMaze1_arrayY = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 1, 1, 1,
                        1,
                        9, 9, 9, 9, 9, 9, 9, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };
        private static final int[] mediumMaze2_arrayX = { 0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
                15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,
                13,12,11,10,9,8,7,6,5,4,3,2,1,0,
                0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                2,2,2,2,2,2,2,2,
                4,4,
                2,3,4,
                4,4,4,4,4,4,5,6,7,8,9,10,11,12,12,12,
                7,8,8,8,9,10,11,12,13,
                10,11,12,13,13,13,13,
        
        
        };
        private static final int[] mediumMaze2_arrayY = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,
                15,15,15,15,15,15,15,15,15,15,15,15,15,15,
                14,13,12,11,10,9,8,7,6,5,4,3,2,1,
                3,4,5,6,7,8,9,10,
                1,2,
                13,13,13,
                9,8,7,6,5,4,4,4,4,4,4,4,4,4,3,2,
                10,10,9,8,8,8,8,8,8,
                13,13,13,13,12,11,10,


        
        };

        // Counter to alternate between maze layouts
        private static int count = 0;

        /**
         * Constructs a MediumConfig object with predefined settings for a medium level
         * game.
         * It initializes the number of various game elements and selects a maze layout.
         */
        public MediumConfig() {
                super();
                numberOfObstacles = 5;
                numberOfSpiders = 9;
                numberOfBasicGhosts = 2;
                numberOfAdvancedGhosts = 0;
                numberOfRegularRewards = 6;
                numberOfBonusRewards = 3;

                switchMap(count, wallPositions, mediumMaze1_arrayX, mediumMaze1_arrayY, mediumMaze2_arrayX,
                                mediumMaze2_arrayY);
                count++;
        }

}
