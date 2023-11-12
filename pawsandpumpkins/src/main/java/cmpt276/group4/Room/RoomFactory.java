package cmpt276.group4.Room;

import cmpt276.group4.Room.RoomInitialization;

import java.lang.reflect.Array;
import java.util.List;

import cmpt276.group4.Position;
import cmpt276.group4.RecordUsedPlace;
public class RoomFactory {

    //creating actual object of obstacle and door and room in factory 
    //use enum instead of GameManger
    
    public Room createRoom(int max_X, int max_Y) {
        return new Room(max_X, max_Y);
    }

    public void createTombstones(Obstacletype type, Position position, int amount){
        while(amount > 0){
            Obstacle obstacle = new Tombstone(type,amount,position);
            RecordUsedPlace.getInstance().addElementToMap(obstacle);
            amount--;
        }
    }

    public void createWall(Obstacletype type, List<Position> positions, int amount){
        for (int i=0; i < amount && i < positions.size(); i++){
            RecordUsedPlace.getInstance().addElementToMap(new Wall(type,amount,positions.get(i)));
        }

    }

    public Door createDoor(Boolean IsOpen) {
        return new Door(IsOpen);
    }

    public void createTile(List<Position> positions){
        for (Position position : positions) {
            RecordUsedPlace.getInstance().addElementToMap(new Tile(position));
        };
    }
}

