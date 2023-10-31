package cmpt276.group4.Room;

import cmpt276.group4.Room.RoomInitialization;
<<<<<<< HEAD
=======
import cmpt276.group4.GameManager;
>>>>>>> 0d25eb06c9c905178539e78e9f00913262d99eb5
import cmpt276.group4.Position;
public class RoomFactory {

    //creating actual object of obstacle and door and room in factory 
    //use enum instead of GameManger
    public Room createRoom(int roomLength) {
        return new Room(roomLength);
    }

    public Obstacle createObstacle(Position[] positions) {
        return new Obstacle(positions);
    }

    public Door createDoor(Boolean IsOpen) {
        return new Door(IsOpen);
    }
}

