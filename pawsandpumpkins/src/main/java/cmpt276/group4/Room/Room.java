package cmpt276.group4.Room;

import cmpt276.group4.Position;

public class Room {
    private Position doorPosition;
    private Obstacle[] obstacle;
    private Door[] door;

    public Room(int roomLength) {
        this.obstacle = obstacle;
        this.door = door;
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

