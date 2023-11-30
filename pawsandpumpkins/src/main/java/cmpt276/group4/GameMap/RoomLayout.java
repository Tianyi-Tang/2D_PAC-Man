package cmpt276.group4.GameMap;

import java.util.ArrayList;

import cmpt276.group4.CharacterAvaliablePosition;
import cmpt276.group4.Position;
import cmpt276.group4.Logic.WindowConfig;
import cmpt276.group4.Room.Tombstone;

/**
 * THe class that store the all room item and unavaliable position
 * room item means wall, tile, tombstone
 */
public class RoomLayout {
    private RecordUsedPlace record;

    private ArrayList<CharacterAvaliablePosition> elements;
    private ArrayList<Position> unAviablePositions;
    private static RoomLayout instance;

    private int tileNum;
    private int wallNum;
    private int obstacleNum;

    /**
     * Constructor for the room layout
     */
    public RoomLayout() {
        elements = new ArrayList<CharacterAvaliablePosition>();
        unAviablePositions = new ArrayList<Position>();
    }

    /**
     * Passing the ReocrdUsedPlcae will use in room layout
     * 
     * @param record the record use place object that room layout will use
     */
    public void init(RecordUsedPlace record) {
        this.record = record;
    }

    /**
     * Get the instance of room layout
     * 
     * @return the instance of room layout
     */
    public static synchronized RoomLayout getInstance() {
        if (instance == null)
            instance = new RoomLayout();
        return instance;
    }

    public boolean addElementInMap(CharacterAvaliablePosition element) {
        if (element instanceof Tombstone) {
            return placeObstacle(element);
        } else {
            return placeOtherItem(element);
        }
    }

    /**
     * Note the function is not complete, wait for record usedPlace provide more
     * detail function
     * 
     * @param position
     * @return
     */
    private boolean placeObstacle(CharacterAvaliablePosition element) {
        if (record.canPlaceEnemyAndObstacle(element.getPosition()) && record.isPlaceAviable(element.getPosition())) {
            addPositionToRecord(element.getPosition());
            elements.add(element);
            obstacleNum++;
            return true;
        } else
            return false;
    }

    /**
     * Check the item that are not the tombstone can be add to the room layout
     * 
     * @param element the
     * @return if item can be add into room layout return true, else return false
     */
    private boolean placeOtherItem(CharacterAvaliablePosition element) {
        if (record.isPlaceAviable(element.getPosition())) {
            elements.add(element);
            if (element.getTakenPlace()) {
                wallNum++;
                addPositionToRecord(element.getPosition());
            } else {
                tileNum++;
            }
            return true;
        }
        return false;
    }

    /**
     * Add the object that will taken the position and reject character move
     * to that position
     * 
     * @param position the posititon of that object
     * @param isWall   is that object is wall or tombstone
     */
    private void addPositionToRecord(Position position) {
        record.removeFromAviable(position);
        unAviablePositions.add(position);
        record.addObstcalePosition(position);
    }

    /**
     * Get the room item store in room layout
     * room item can be wall, tombstone or tile
     * 
     * @return
     */
    public ArrayList<CharacterAvaliablePosition> getElements() {
        return elements;
    }

    /**
     * Get the number of tile store in room layout
     * 
     * @return mumber of tile
     */
    public int getTileNumber() {
        return tileNum;
    }

    /**
     * Get the number of wall store in room layout
     * 
     * @return number of wall
     */
    public int getWallNumber() {
        return wallNum;
    }

    /**
     * Get the number of tombstone store in room layout
     * 
     * @return number of tombstone
     */
    public int getObstaclesNumber() {
        return obstacleNum;
    }

    /**
     * Check if the position is available for character
     * 
     * @param position position that want to check
     * @return if position is available for character return true, else return false
     */
    public boolean isPositionAviable(Position position) {
        if (!outOfScreen(position))
            return false;
        for (Position unAviablePos : unAviablePositions) {
            if (position.equal(unAviablePos))
                return false;
        }
        return true;
    }

    /**
     * Check if the position is out of screen
     * 
     * @param position the position what to check
     * @return if position is not out of screen return true, else return false
     */
    private boolean outOfScreen(Position position) {
        if (position.getX_axis() < 0 || position.getY_axis() < 0)
            return false;
        else if (position.getX_axis() > WindowConfig.screenWidth - WindowConfig.tileSize
                || position.getY_axis() > WindowConfig.screenHeight - WindowConfig.tileSize)
            return false;
        else
            return true;
    }

}
