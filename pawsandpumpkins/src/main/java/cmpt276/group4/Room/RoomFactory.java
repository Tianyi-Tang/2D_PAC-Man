package cmpt276.group4.Room;

import cmpt276.group4.Room.RoomInitialization;

public class RoomFactory {

    public Room createRoom(int roomLength) {
        Room room = createRoom(roomLength);
        return room;
    }

    public Obstacle createObstacle() {
        return new Obstacle();
    }

    public Door createDoor() {
        return new Door();
    }



}
