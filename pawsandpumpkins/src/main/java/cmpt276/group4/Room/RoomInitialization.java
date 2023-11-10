package cmpt276.group4.Room;

import cmpt276.group4.GameManager;
import cmpt276.group4.gameLevel;

public class RoomInitialization {
    private int max_X;
    private int max_Y;
    // Dont know if this is correct? should i be entering type of room?


    // A function once called it will generate all tiles in the room

    // And for walls
    public Room createRoom() {
        RoomFactory factory = new RoomFactory();
        return factory.createRoom(max_X, max_Y);
    }

    public void setX(int x){
        max_X = x;
    }

    public void setY(int y){
        max_Y = y;
    }
}
