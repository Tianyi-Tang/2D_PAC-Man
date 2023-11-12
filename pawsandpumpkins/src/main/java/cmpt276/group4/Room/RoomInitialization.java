package cmpt276.group4.Room;

import cmpt276.group4.GameManager;
import cmpt276.group4.Position;
import cmpt276.group4.RecordUsedPlace;
import cmpt276.group4.gameLevel;
import cmpt276.group4.Logic.GameConfig;


//import cmpt276.group4.;

public class RoomInitialization {
    private int max_X;
    private int max_Y;
    private int wall,tombstone;
    private Obstacletype obstacletype;
    private Position position;
    private GameConfig gc;
    
    // Dont know if this is correct? should i be entering type of room?


    // A function once called it will generate all tiles in the room

    // And for walls
    public void initializeRoom(gameLevel gameLevel, RoomFactory rmFactory) {
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
        
        gc = GameConfig.getGameConfigInstance();
        
    }

    public void iRoom(RoomFactory rmFactory) {
        rmFactory.createRoom(max_X, max_Y);
    }

    public void iWalls(RoomFactory rmFactory){
        rmFactory.createObstacle(Obstacletype.WALL, position, wall);
    }
    public void iTombs(RoomFactory rmFactory){
        rmFactory.createObstacle(Obstacletype.TOMBSTONE, position, wall);
    }


    public void setX(int x){
        max_X = x;
    }

    public void setY(int y){
        max_Y = y;
    }
}
