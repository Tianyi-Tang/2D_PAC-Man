package cmpt276.group4.Room;

import cmpt276.group4.GameManager;

public class RoomInitialization {
    private int roomLength;
    //private GameManager typeOfRooom;
    
    // Getter for roomLength
    public int getRoomLength() {
        return roomLength;
    }

    // Setter for room Length
    public void setRoomLength(int x) {
        this.roomLength = x;
    }

    // Dont know if this is correct? should i be entering type of room?
    public void createRoom() {
        System.out.println("Create new room");
    }
}
