package cmpt276.group4;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cmpt276.group4.Enemy.Enemy;
import cmpt276.group4.Player.Player;
import cmpt276.group4.Reward.Reward;

public class RecordUsedPlace {
    // for reward to check 
    private ArrayList<Position> available;

    private ArrayList<Position> playerAvaliable_pos;
    private ArrayList<Position> enemyAvaliable_pos;

    private ArrayList<Enemy> enemies;
    private ArrayList<Reward> rewards;
    private Player player;
    public static RecordUsedPlace instance;

    public Position getRandomFromAvailablePosition(){
    //return a random position from variable available
        if (available == null || available.isEmpty()) {
            System.out.println("No available used place to choose from");
            return null; 
        }
        Random random = new Random();
        return available.get(random.nextInt(available.size()));
    }

    public RecordUsedPlace(){
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

    public boolean addEnemy(Enemy enemy){
        if(isPlaceAviable(enemy.getEnemyPosition())){
            enemies.add(enemy);
            elementTakenPlace(false, enemy.getEnemyPosition());
            return true;
        }
        else
            return false;
    }

    public boolean addReward(Reward reward){
        if(isPlaceAviable(reward.getPosition())){
            rewards.add(reward);
            elementTakenPlace(false, reward.getPosition());
            return true;
        }
        else
            return false;
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
        for (Position position : available) {
            if(position.equal(planingPosition))
                return true;
        }
        return false;
    }

    private void elementTakenPlace(boolean takenPlace, Position position){
        if(takenPlace){
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
