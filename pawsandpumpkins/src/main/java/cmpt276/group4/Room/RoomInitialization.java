package cmpt276.group4.Room;

import cmpt276.group4.GameManager;
import cmpt276.group4.Position;
import cmpt276.group4.RecordUsedPlace;
import cmpt276.group4.gameLevel;

//import cmpt276.group4.;

public class RoomInitialization {
    private int max_X;
    private int max_Y;
    private int wall,tombstone;
    private Obstacletype obstacletype;
    private Position position;
    
    // Dont know if this is correct? should i be entering type of room?


    // A function once called it will generate all tiles in the room

    // And for walls
    public Room initializeRoom(gameLevel gameLevel, RoomFactory rmFactory) {
        //this.gameLevel = gameLevel;
        System.out.println("Creating obstacle");
        switch (gameLevel) {
            case BASIC:
                wall = 3;
                tombstone = 3;
                break;
            case MEDIUM:
                wall = 6;
                tombstone = 6;
                break;
            case HARD:
                wall = 9;
                tombstone = 9;
                break;
        }      
        System.out.println("Creating obstacle");
        rmFactory.createObstacle(Obstacletype.WALL, position, wall);
        rmFactory.createObstacle(Obstacletype.TOMBSTONE, position, tombstone);

        

        //create obstcles
        
        //This shouldnt be here but when i remove it i get error
        //RoomFactory factory = new RoomFactory(); 
  
        return rmFactory.createRoom(max_X, max_Y);
    }


    public void setX(int x){
        max_X = x;
    }

    public void setY(int y){
        max_Y = y;
    }
}
