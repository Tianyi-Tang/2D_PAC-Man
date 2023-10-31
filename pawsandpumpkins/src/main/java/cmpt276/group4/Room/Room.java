package cmpt276.group4.Room;

//import cmpt276.group4.GameManager;
import cmpt276.group4.Position;
import cmpt276.group4.WindowAndInput.GamePanel;

public class Room {
    private Position doorPosition;
    private Obstacle[] obstacle;
    private Door[] door;
    public int RoomSize;
    public int max_X;
    public int max_Y;

    
    //room size is it avaliable 
    public Room(int max_X, int max_Y) {

        if (GamePanel.maxScreenRow < max_X || GamePanel.maxScreenCol < max_Y){
            System.out.println("Screen too small");
        }
        else 
        RoomSize = max_X * max_Y; 
    }

    // Getter methods for attributes
    public Position getDoorPosition() {
        return doorPosition;
    }

    public Obstacle[] getObstacles() {
        return obstacle;
    }

    public Door[] getDoors() {
        return door;
    }

    // Setter methods for attributes if needed
    public void setDoorPosition(Position doorPosition) {
        this.doorPosition = doorPosition;
    }

    public void setObstacles(Obstacle[] obstacles) {
        this.obstacle = obstacles;
    }

    public void setDoors(Door[] doors) {
        this.door = doors;
    }
}

