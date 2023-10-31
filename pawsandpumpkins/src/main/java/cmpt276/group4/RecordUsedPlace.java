package cmpt276.group4;
import java.util.ArrayList;
import java.util.List;

import cmpt276.group4.Enemy.Enemy;
import cmpt276.group4.Player.Player;
import cmpt276.group4.Reward.Reward;

public class RecordUsedPlace {
    // for reward to check 
    private ArrayList<Position> alreadyUsed;
    private ArrayList<Position> available;

    private ArrayList<Position> playerAvaliable_pos;
    private ArrayList<Position> enemyAvaliable_pos;

    private ArrayList<Enemy> enemies;
    private ArrayList<Reward> rewards;
    private Player player;
    public static RecordUsedPlace instance;

    public RecordUsedPlace(){
        alreadyUsed = new ArrayList<Position>();
        initalAvailableArray();

        playerAvaliable_pos = new ArrayList<Position>();
        enemyAvaliable_pos = new ArrayList<Position>();
        enemies = new ArrayList<Enemy>();
        player = Player.getInstance();
    }

    private void initalAvailableArray(){
        available = new ArrayList<Position>();

        // add all aviable area in this function
    }

    public static RecordUsedPlace getInstance(){
        if(instance ==null)
            instance = new RecordUsedPlace();
        return instance;
    }

    public boolean addElementToMap(CharacterAvaliablePosition object){
        if(isPlaceAviable(object.getPosition())){
            if(object.getEnemyAvaliable())
                enemyAvaliable_pos.add(object.getPosition());
            if(object.getPlayerAvaliable())
                playerAvaliable_pos.add(object.getPosition());

            elementTakenPlace(object.getTakenPlace(), object.getPosition());
            return true;
        }
        else 
            return false;
    }

    public void addEnemy(Enemy enemy){
        enemies.add(enemy);
        elementTakenPlace(false, enemy.getEnemyPosition());
    }

    public void addReward(Reward reward){
        rewards.add(reward);
        elementTakenPlace(false, reward.getPosition());
    }

    public Position getPlayerPosition(){
        return player.getPosition();
    }

    public boolean catchPlayer(Position enemy_pos){
        if(enemy_pos.equal(player.getPosition()))
            return true;
        else
            return false;
    }

    public ArrayList<Position> getAviablePosition(){
        return available;
    }

    public boolean enemyMovable(Position destination){
        for (Position position : enemyAvaliable_pos) {
            if(destination.equal(position))
                return true;
        }
        return false;
    }

    public boolean playerMovable(Position destination){
        for (Position position : playerAvaliable_pos) {
            if(destination.equal(position))
                return true;
        }
        return false;
    }

    private boolean isPlaceAviable(Position planingPosition){
        for (Position position : alreadyUsed) {
            if(position.equal(planingPosition))
                return false;
        }
        return true;
    }

    private void elementTakenPlace(boolean takenPlace, Position position){
        if(takenPlace){
            alreadyUsed.add(position);
            RemoveFromAviable(position);
        }

    }


    private void RemoveFromAviable(Position takePosition){
        for (Position position : available) {
            if(position.equal(takePosition))
                available.remove(position);
        }
    }
    
    public List<Enemy> getEnemyList(){
        return enemies;
    }
}
