package cmpt276.group4.Room;

import cmpt276.group4.Room.RoomInitialization;
import cmpt276.group4.WindowAndInput.GamePanel;

import java.lang.reflect.Array;
import java.util.List;

import cmpt276.group4.Position;
import cmpt276.group4.gameLevel;
import cmpt276.group4.GameMap.RoomLayout;

public class RoomFactory {

    /**
     * Creates and returns an instance of the room with the specified dimensions.
     *
     * @param max_X The maximum X-coordinate of the room.
     * @param max_Y The maximum Y-coordinate of the room.
     * @return An instance of the room.
     */
    public Room createRoom() {
        return Room.getInstance();
    }


    /**
     * Creates tombstones with the specified type, position, and amount.
     *
     * @param type     The type of tombstone obstacle.
     * @param position The position of the tombstone.
     * @param amount   The number of tombstones to create.
     */
    public void createTombstones( Position position, int amount){
        while(amount > 0){
            Obstacle obstacle = new Tombstone(amount,position);
            RoomLayout.getInstance().addElementInMap(obstacle);
            amount--;
        }
    }

    /**
     * Creates walls with the specified type, positions, and amount.
     *
     * @param type      The type of wall obstacle.
     * @param positions The list of positions for the walls.
     * @param amount    The number of walls to create.
     */
    public void createWall( List<Position> positions, int amount){
        for (int i=0; i < amount && i < positions.size(); i++){
            RoomLayout.getInstance().addElementInMap(new Wall(amount,positions.get(i)));
        }
    }

    /**
     * Creates tiles at the specified positions.
     *
     * @param positions The list of positions for the tiles.
     */
    public void createTile(List<Position> positions){
        for (Position position : positions) {
            RoomLayout.getInstance().addElementInMap(new Tile(position));
        };
    }
}

