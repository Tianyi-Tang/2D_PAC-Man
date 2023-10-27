package cmpt276.group4.Room;

import cmpt276.group4.Room.RoomInitialization;
import cmpt276.group4.GameManager;
import cmpt276.group4.Position;
public class RoomFactory {

    public Room createRoom(int roomLength, GameManager typeOfRoom) {
        return new Room(roomLength, typeOfRoom);
    }

    public Obstacle createObstacle(boolean playerAccess, boolean enemyTraversability, Position[] positions) {
        return new Obstacle(playerAccess, enemyTraversability, positions);
    }

    public Door createDoor(Boolean IsOpen) {
        return new Door(IsOpen);
    }
}

