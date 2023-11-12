package cmpt276.group4.Room;

import cmpt276.group4.Room.RoomInitialization;

import java.util.List;

import cmpt276.group4.Position;
import cmpt276.group4.RecordUsedPlace;
public class RoomFactory {

    //creating actual object of obstacle and door and room in factory 
    //use enum instead of GameManger
    
    public Room createRoom(int max_X, int max_Y) {
        return new Room(max_X, max_Y);
    }

    public void createObstacle(Obstacletype type, Position position, int amount) {
        if (type == Obstacletype.WALL){
            while(amount > 0){
                System.out.println("Enter If loop");
                Obstacle obstacle = new Wall(type,amount,position);
                RecordUsedPlace.getInstance().addElementToMap(obstacle);
                amount--;
            }
        }
        else{
            while(amount > 0){
                System.out.println("Enter If loop");
                Obstacle obstacle = new Tombstone(type,amount,position);
                RecordUsedPlace.getInstance().addElementToMap(obstacle);
                amount--;
            }
        }

        //return obstacle;
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

