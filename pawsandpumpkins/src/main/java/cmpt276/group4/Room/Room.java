package cmpt276.group4.Room;

import java.util.Timer;
import java.util.TimerTask;

import cmpt276.group4.GameManager;
//import cmpt276.group4.GameManager;
import cmpt276.group4.Position;
import cmpt276.group4.GameMap.RecordUsedPlace;
import cmpt276.group4.Logic.GameConfig;
import cmpt276.group4.Logic.WindowConfig;
import cmpt276.group4.WindowAndInput.GamePanel;

/**
 * The Room class represents a room in the game, containing information about its size,
 * center, coordinates, door position, obstacles, and doors.
 */
public class Room {
    private static Room instance;

    private Position doorPosition;
    private Obstacle[] obstacle;
    private Door[] doors;
    public int RoomSize;
    public int max_X;
    public int max_Y;
    public int roomCenterX;
    public int roomCenterY;
    public int RoomX1, RoomX2, RoomY1, RoomY2;

    /**
     * Constructor to initialize a room with the specified maximum X and Y coordinates.
     *
     * @param max_X Maximum X-coordinate of the room.
     * @param max_Y Maximum Y-coordinate of the room.
     */
    Room() {
        //Assuming centre of screen will always be even after division
        roomCenterX = GameConfig.getGameConfigInstance().getRoomColumn()/2;
        roomCenterY = GameConfig.getGameConfigInstance().getRoomRow()/2;

        max_X = WindowConfig.maxScreenCol;
        max_Y = WindowConfig.maxScreenRow;

            
        // X1 Y1 is a corner oposite of corner X2 Y2 and with these to points we
        // can generate a room to fill in the room with tiles
        RoomX1 = (roomCenterX -  (max_X/2));
        RoomX2 = (roomCenterX +  (max_X/2));
        RoomY1 = roomCenterY -  (max_Y/2);
        RoomY2 = roomCenterY +  (max_Y/2);
        generateAllPosition();
        createDoors();

    }

    public static synchronized Room getInstance(){
        if(instance == null)
            instance = new Room();
        return instance;
    }

    

    /**
     * Generates all positions in the room and adds them to the list of available positions.
     */
    private void generateAllPosition(){
        
        for (int x = RoomX1; x <  RoomX2; x++){
            for(int y = RoomY1; y < RoomY2; y++){
                Position position = new Position(x *WindowConfig.tileSize, y * WindowConfig.tileSize);
                RecordUsedPlace.getInstance().addAviable(position);
            }
        }
    }

    /**
     * Creates doors for the room.
     */
    private void createDoors(){
        doors = new Door[2];
        doors[0] = new Door(false, new Position(1 * WindowConfig.tileSize, 0));
        doors[1] = new Door(false, new Position(14 * WindowConfig.tileSize, 15 * WindowConfig.tileSize));
    }
        
    /**
     * Gets the position of the door in the room.
     *
     * @return The position of the door.
     */
    public Position getDoorPosition() {
        return doorPosition;
    }

    /**
     * Gets the obstacles in the room.
     *
     * @return Array of obstacles in the room.
     */
    public Obstacle[] getObstacles() {
        return obstacle;
    }

    /**
     * Gets the doors in the room.
     *
     * @return Array of doors in the room.
     */
    public Door[] getDoors() {
        return doors;
    }

    /**
     * Sets the position of the door in the room.
     *
     * @param doorPosition The position of the door.
     */
    public void setDoorPosition(Position doorPosition) {
        this.doorPosition = doorPosition;
    }

    /**
     * Sets the obstacles in the room.
     *
     * @param obstacles Array of obstacles to set in the room.
     */
    public void setObstacles(Obstacle[] obstacles) {
        this.obstacle = obstacles;
    }

}

