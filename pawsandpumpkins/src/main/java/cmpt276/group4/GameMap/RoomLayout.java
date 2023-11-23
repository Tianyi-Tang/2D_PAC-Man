package cmpt276.group4.GameMap;

import java.util.ArrayList;

import cmpt276.group4.CharacterAvaliablePosition;
import cmpt276.group4.Position;
import cmpt276.group4.Logic.WindowConfig;
import cmpt276.group4.Room.Obstacle;


public class RoomLayout {
    private RecordUsedPlace record;

    private ArrayList<CharacterAvaliablePosition> elements;
    private ArrayList<Position> unAviablePositions;
    private static RoomLayout  instance;

    private int tileNum;
    private int wallNum;
    private int obstacleNum;

    public RoomLayout(){
        record = RecordUsedPlace.getInstance();
        elements = new ArrayList<CharacterAvaliablePosition>();
        unAviablePositions = new ArrayList<Position>();
    }

    public static synchronized RoomLayout getInstance(){
        if(instance == null)
            instance = new RoomLayout();
        return instance;
    }

    public boolean addElementInMap(CharacterAvaliablePosition element){
        if(element instanceof Obstacle){
             return placeObstacle(element);
        }
        else{
            return placeOtherItem(element);
        }
        
    }

    /**
     * Note the function is not complete, wait for record usedPlace provide more detail function
     * @param position
     * @return
     */
    private boolean placeObstacle(CharacterAvaliablePosition element){
        if(record.isPlaceAviable(element.getPosition())){
            addPositionToRecord(element.getPosition(), true);
            elements.add(element);
            obstacleNum ++;
            return true;
        }
        else
            return false;
    }

    private boolean placeOtherItem(CharacterAvaliablePosition element){
        if(record.isPlaceAviable(element.getPosition())){
            elements.add(element);
            if(element.getTakenPlace()){
                wallNum ++;
                addPositionToRecord(element.getPosition(), true);
            }
            else{
                tileNum ++;
            }
            return true;
        }
        return false;
    }

    private void addPositionToRecord(Position position,boolean isWall){
        record.removeFromAviable(position);
    }

    
    public ArrayList<CharacterAvaliablePosition> getElements(){
        return elements;
    }

    public int getTileNumber(){
        return tileNum;
    }

    public int getWallNumber(){
        return wallNum;
    }

    public int getObstaclesNumber(){
        return obstacleNum;
    }

    public boolean isPositionAviable(Position position){
        if(!outOfScreen(position))
            return false;
        for (Position unAviablePos  : unAviablePositions) {
            if(position.equal(unAviablePos))
                return false;
        }
        return true;
    }

    private boolean outOfScreen(Position position){
        if(position.getX_axis() < 0 || position.getY_axis() < 0)
            return false;
        else if(position.getX_axis() > WindowConfig.screenWidth - WindowConfig.tileSize || position.getY_axis() > WindowConfig.screenHeight - WindowConfig.tileSize)
            return false;
        else
            return true;
    }


}
