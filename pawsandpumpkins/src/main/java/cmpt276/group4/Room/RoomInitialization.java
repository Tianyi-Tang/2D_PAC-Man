package cmpt276.group4.Room;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.type.NullType;

import cmpt276.group4.GameManager;
import cmpt276.group4.Position;
import cmpt276.group4.RecordUsedPlace;
import cmpt276.group4.gameLevel;
import cmpt276.group4.Logic.GameConfig;


/**
 * RoomInitialization class is responsible for initializing rooms,
 * including walls, tombstones, and tiles.
 */
public class RoomInitialization {
    private int max_X;
    private int max_Y;
    public int wall,tombstone;
    private Obstacletype obstacletype;
    private Position position;
    private GameConfig gc;
    public List<Position> wallPositionList;
    public List<Position> tilesPosition;
    
    /**
     * Initializes the room based on the specified game level and room factory.
     *
     * @param gameLevel  The game level for which the room is being initialized.
     * @param rmFactory  The room factory used to create the room and its components.
     */
    public void initializeRoom(gameLevel gameLevel, RoomFactory rmFactory) {
        GameConfig config = GameConfig.getGameConfigInstance();
        max_X = config.getRoomColumn();
        max_Y = config.getRoomRow();
        tilesPosition = RecordUsedPlace.getInstance().getAviablePosition();     
        gc = GameConfig.getGameConfigInstance();
        wallPositionList = gc.getWallPositions();     
        wall = wallPositionList.size();
        tombstone = gc.getNumberOfObstacles();
    }

    /**
     * Creates and returns an instance of the room using the specified room factory.
     *
     * @param rmFactory  The room factory used to create the room.
     * @return An instance of the room.
     */
    public Room iRoom(RoomFactory rmFactory) {
        return rmFactory.createRoom();
    }


    /**
     * Creates tombstones in the room using the specified room factory.
     *
     * @param rmFactory  The room factory used to create tombstones.
     */
    public void iTombs(RoomFactory rmFactory){
        rmFactory.createTombstones(Obstacletype.TOMBSTONE, position, tombstone);
    }

    /**
     * Creates walls in the room using the specified room factory.
     *
     * @param rmFactory  The room factory used to create walls.
     */
    public void iWalls(RoomFactory rmFactory){
        rmFactory.createWall(Obstacletype.WALL, wallPositionList, wall);
    }


    /**
     * Creates tiles in the room using the specified room factory.
     *
     * @param rmFactory  The room factory used to create tiles.
     */
    public void iTiles(RoomFactory rmFactory){
        rmFactory.createTile(tilesPosition);
    }

}