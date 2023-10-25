package cmpt276.group4.Room;

import cmpt276.group4.GameManager;

public class RoomInitialization {
    private int roomLength;
    //How do i make this private and use it with out error?
    public GameManager typeOfRoom;
    
    // Getter for roomLength
    public int getRoomLength() {
        return roomLength;
    }

    // Setter for room Length
    public void setRoomLength(int x) {
        this.roomLength = x;
    }


    // Dont know if this is correct? should i be entering type of room?
    public Room createRoom(GameManager typeOfRoom) {
        RoomFactory factory = new RoomFactory();
        return factory.createRoom(roomLength, typeOfRoom);
    }
}
