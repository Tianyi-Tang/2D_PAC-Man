package cmpt276.group4.Room;

import cmpt276.group4.Room.RoomInitialization;
import cmpt276.group4.Position;
public class RoomFactory {

    //creating actual object of obstacle and door and room in factory 
    //use enum instead of GameManger
    public Room createRoom(int max_X, int max_Y) {
        return new Room(max_X, max_Y);
    }

    public Obstacle createObstacle(Position position, int type) {
        return new Obstacle(position, type);
    }

    public Door createDoor(Boolean IsOpen) {
        return new Door(IsOpen);
    }

    public Tile creaTile(Position position){
        return new Tile(position);
    }
}

