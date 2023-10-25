package cmpt276.group4.Room;

import cmpt276.group4.Position;
import cmpt276.group4.Room.Obstacle;
import cmpt276.group4.Room.Door;
import cmpt276.group4.GameManager;

public class Room {
    private Position doorPosition;
    private Obstacle[] obstacle;
    private Door[] door;

    public Room(int roomLength, GameManager typeOfRoom) {
        this.doorPosition = new Position(0, 0); // Placeholder position
        this.obstacle = new Obstacle[roomLength]; 
        this.door = new Door[roomLength]; 

        // Initialize obstacle objects based on roomLength
        for (int i = 0; i < roomLength; i++) {
            obstacle[i] = new Obstacle();
        }

        // Initialize door objects based on roomLength
        for (int i = 0; i < roomLength; i++) {
            door[i] = new Door(IsOpen);
        }
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

