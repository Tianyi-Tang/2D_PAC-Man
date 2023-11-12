package cmpt276.group4.Room;

import java.util.Timer;
import java.util.TimerTask;

import cmpt276.group4.GameManager;
//import cmpt276.group4.GameManager;
import cmpt276.group4.Position;
import cmpt276.group4.RecordUsedPlace;
import cmpt276.group4.WindowAndInput.GamePanel;

public class Room {
    private Position doorPosition;
    private Obstacle[] obstacle;
    private Door[] doors;
    public int RoomSize;
    public int max_X;
    public int max_Y;
    public int roomCenterX;
    public int roomCenterY;
    public int RoomX1, RoomX2, RoomY1, RoomY2;

    
    //room size is it avaliable 
    public Room(int max_X, int max_Y) {
        if (GamePanel.maxScreenRow < max_X || GamePanel.maxScreenCol < max_Y){
            System.out.println("Screen too small");
        }
        else{
            //Assuming centre of screen will always be even after division
            roomCenterX = GamePanel.maxScreenRow/2;
            roomCenterY = GamePanel.maxScreenCol/2;

            // X1 Y1 is a corner oposite of corner X2 Y2 and with these to points we
            // can generate a room to fill in the room with tiles
            RoomX1 = (roomCenterX -  (max_X/2));
            RoomX2 = (roomCenterX +  (max_X/2));
            RoomY1 = roomCenterY -  (max_Y/2);
            RoomY2 = roomCenterY +  (max_Y/2);
            generateAllPosition();
            createDoors();
        } 
    }

    private void generateAllPosition(){
        
        for (int x = RoomX1; x < RoomX2; x++){
            for(int y = RoomY1; y <RoomY2; y++){
                Position position = new Position(x *GamePanel.tileSize, y * GamePanel.tileSize);
                RecordUsedPlace.getInstance().addAviable(position);
            }
        }
        // GameManager.getInstance().RecordUsedPlaceAviable();
    }

    private void createDoors(){
        doors = new Door[2];
        doors[0] = new Door(false, new Position(1 * GamePanel.tileSize, 0));
        doors[1] = new Door(false, new Position(14 * GamePanel.tileSize, 15 * GamePanel.tileSize));
    }
        

    // Getter methods for attributes
    public Position getDoorPosition() {
        return doorPosition;
    }

    public Obstacle[] getObstacles() {
        return obstacle;
    }

    public Door[] getDoors() {
        return doors;
    }

    // Setter methods for attributes if needed
    public void setDoorPosition(Position doorPosition) {
        this.doorPosition = doorPosition;
    }

    public void setObstacles(Obstacle[] obstacles) {
        this.obstacle = obstacles;
    }

}

