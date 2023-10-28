package cmpt276.group4;

public interface CharacterAvaliablePosition {
    Position position = new Position(0, 0);

    
    public Position getPosition();
    public boolean getPlayerAvaliable();
    public boolean getEnemyAvaliable();
    public boolean getTakenPlace();
    boolean playerAvaliable = true;
    boolean enemyAvaliable = true;
    boolean takenPlace = false; // whether you can put item on it, for example reward
}
