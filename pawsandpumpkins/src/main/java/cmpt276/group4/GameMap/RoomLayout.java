package cmpt276.group4.GameMap;

import java.util.ArrayList;

import cmpt276.group4.CharacterAvaliablePosition;
import cmpt276.group4.Position;

public class RoomLayout {

    ArrayList<CharacterAvaliablePosition> elements;
    ArrayList<Position> unAviablePositions;

    public void addElementInMap(CharacterAvaliablePosition element){
        elements.add(element);
        isPositionUnAviable(element);
    }

    private void isPositionUnAviable(CharacterAvaliablePosition element){
        if(!element.getPlayerAvaliable()){
            unAviablePositions.add(element.getPosition());
        }
    }
    


}
