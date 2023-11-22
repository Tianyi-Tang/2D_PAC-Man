package cmpt276.group4.GameMap;

import java.util.ArrayList;

import cmpt276.group4.CharacterAvaliablePosition;
import cmpt276.group4.Position;
import cmpt276.group4.Logic.WindowConfig;
import cmpt276.group4.Room.RoomItemType;

public class RoomLayout {

    private ArrayList<CharacterAvaliablePosition> elements;
    private ArrayList<Position> unAviablePositions;
    private static RoomLayout  instance;

    private int tileNum;
    private int wallNum;
    private int obstacleNum;

    public RoomLayout(){
        elements = new ArrayList<CharacterAvaliablePosition>();
        unAviablePositions = new ArrayList<Position>();
    }

    public static synchronized RoomLayout getInstance(){
        if(instance == null)
            instance = new RoomLayout();
        return instance;
    }

    public void addElementInMap(CharacterAvaliablePosition element,RoomItemType type){
        elements.add(element);
        
        if(type == RoomItemType.Tile)
            tileNum ++;
        else{
            unAviablePositions.add(element.getPosition());
            if(type == RoomItemType.Wall)
                wallNum ++;
            else
                obstacleNum++;
        }
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
