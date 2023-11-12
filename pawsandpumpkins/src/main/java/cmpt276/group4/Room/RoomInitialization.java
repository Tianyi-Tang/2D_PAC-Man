package cmpt276.group4.Room;

import java.util.ArrayList;
import java.util.List;

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
    public List<Position> wallPositionList;
    public List<Position> tilesPosition;
    
    // Dont know if this is correct? should i be entering type of room?


    // A function once called it will generate all tiles in the room

    // And for walls
    public void initializeRoom(gameLevel gameLevel, RoomFactory rmFactory) {
        GameConfig config = GameConfig.getGameConfigInstance();
        max_X = config.getRoomColumn();
        max_Y = config.getRoomRow();
        tilesPosition = RecordUsedPlace.getInstance().getAviablePosition();     
        gc = GameConfig.getGameConfigInstance();
        wallPositionList = gc.getWallPositions();     
        wall = wallPositionList.size();
        tombstone = gc.getNumberOfObstacles();

        System.out.println("wall: " + wall);
        System.out.println("tombstone: " + tombstone);
        
    }

    public void iRoom(RoomFactory rmFactory) {
        rmFactory.createRoom(max_X, max_Y);
    }
    
    public void iTombs(RoomFactory rmFactory){
        rmFactory.createObstacle(Obstacletype.TOMBSTONE, position, tombstone);
    }

    public void iWalls(RoomFactory rmFactory){
        rmFactory.createObstacle(Obstacletype.WALL,position, wall);
    }

    public void iTiles(RoomFactory rmFactory){
        rmFactory.createTile(tilesPosition);
    }
   
}