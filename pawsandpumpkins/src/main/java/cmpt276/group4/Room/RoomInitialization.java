package cmpt276.group4.Room;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.type.NullType;

import cmpt276.group4.GameManager;
import cmpt276.group4.Position;
import cmpt276.group4.RecordUsedPlace;
import cmpt276.group4.gameLevel;
import cmpt276.group4.Logic.GameConfig;


//import cmpt276.group4.;

public class RoomInitialization {
    private int max_X;
    private int max_Y;
    public int wall,tombstone;
    private Obstacletype obstacletype;
    private Position position;
    private GameConfig gc;
    public List<Position> wallPositionList;
    public List<Position> tilesPosition;
    
    public void initializeRoom(gameLevel gameLevel, RoomFactory rmFactory) {
        rmFactory.createRoom(max_X, max_Y);
        tilesPosition = RecordUsedPlace.getInstance().getAviablePosition();     
        gc = GameConfig.getGameConfigInstance();
        wallPositionList = gc.getWallPositions();     
        wall = wallPositionList.size();
        tombstone = gc.getNumberOfObstacles();
        
    }

    //public void iRoom(RoomFactory rmFactory) {
        //rmFactory.createRoom(max_X, max_Y);
    //}
    
    public void iTombs(RoomFactory rmFactory){
        rmFactory.createTombstones(Obstacletype.TOMBSTONE, position, tombstone);
    }

    public void iWalls(RoomFactory rmFactory){
        rmFactory.createWall(Obstacletype.WALL, wallPositionList, wall);
    }

    public void iTiles(RoomFactory rmFactory){
        rmFactory.createTile(tilesPosition);
    }
    


    public void setX(int x){
        max_X = x;
    }

    public void setY(int y){
        max_Y = y;
    }
}
