package cmpt276.group4.GameMap;

import java.util.ArrayList;

import cmpt276.group4.CharacterAvaliablePosition;
import cmpt276.group4.Position;
import cmpt276.group4.Logic.WindowConfig;

public class RoomLayout {

    private ArrayList<CharacterAvaliablePosition> elements;
    private ArrayList<Position> unAviablePositions;
    private static RoomLayout  instance;

    public RoomLayout(){
        elements = new ArrayList<CharacterAvaliablePosition>();
        unAviablePositions = new ArrayList<Position>();
    }

    public static RoomLayout getInstance(){
        if(instance == null)
            instance = new RoomLayout();
        return instance;
    }

    public void addElementInMap(CharacterAvaliablePosition element){
        elements.add(element);
        isPositionUnAviable(element);
    }

    private void isPositionUnAviable(CharacterAvaliablePosition element){
        if(!element.getPlayerAvaliable()){
            unAviablePositions.add(element.getPosition());
        }
    }
    
    public ArrayList<CharacterAvaliablePosition> getElements(){
        return elements;
    }

    public boolean isPositionAviable(Position position){
        if(!outOfScreen(position))
            return false;
        for (Position unAviablePos  : unAviablePositions) {
            if(position == unAviablePos)
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
