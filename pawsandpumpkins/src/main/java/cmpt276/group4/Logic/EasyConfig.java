package cmpt276.group4.Logic;

import java.util.ArrayList;
import java.util.List;

import cmpt276.group4.Position;

public class EasyConfig extends GameConfig {
     private List<Position> currentWallPositions;
     private static int count = 0;

    
    int[] easyMaze1_arrayX = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,
            13, 14, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15 };
    int[] easyMaze1_arrayY = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15,
            15, 15, 15, 15, 15, 15, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };
    int[] easyMaze2_arrayX = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 7, 7, 7, 7, 15, 15, 15, 15,
            15, 15, 15, 15, 15, 15, 15, 15 };
    int[] easyMaze2_arrayY = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 1, 2, 3, 4, 5, 6, 7, 8, 0, 1, 2, 3,
            4, 5, 6, 7, 8, 9, 10, 11 };


    
    public EasyConfig() {
            
        super( 3, 3, 1, 5, 10, 2);         
        currentWallPositions = new ArrayList<Position>();
        //switch between 2 maps
            switch (count%2) {
                case 1:
                    initializeWallPosition(easyMaze1_arrayX,easyMaze1_arrayY,currentWallPositions);
                    break;
            
                default:
                    initializeWallPosition(easyMaze2_arrayX,easyMaze2_arrayY,currentWallPositions);
                    break;
            }
            setWallPositions(currentWallPositions);
            count++;
    }


}
