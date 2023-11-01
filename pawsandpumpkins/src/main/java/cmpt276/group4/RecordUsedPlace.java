package cmpt276.group4;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import cmpt276.group4.Enemy.Enemy;
import cmpt276.group4.Player.Player;
import cmpt276.group4.Reward.Reward;

public class RecordUsedPlace {
    // for reward to check 
    private ArrayList<CharacterAvaliablePosition> elements;
    private ArrayList<Position> available;

    private ArrayList<Position> playerAvaliable_pos;
    private ArrayList<Position> enemyAvaliable_pos;

    private ArrayList<Enemy> enemies;
    private ArrayList<Reward> rewards;
    private Player player;
    private Iterator<Position> iterator;
    public static RecordUsedPlace instance;
    static int counter = 0;

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
        //for testing please dont delete below yet. Rosemary
        // available.add(new Position(0, 0));
        // available.add(new Position(48, 48));
        // available.add(new Position(48+GamePanel.tileSize, 48));
        // available.add(new Position(48, 48+GamePanel.tileSize));
        // available.add(new Position(48-GamePanel.tileSize, 48));
        // available.add(new Position(48, 48-GamePanel.tileSize));

        playerAvaliable_pos = new ArrayList<Position>();
        enemyAvaliable_pos = new ArrayList<Position>();
        elements = new ArrayList<CharacterAvaliablePosition>();
         //for testing please dont delete below yet. Rosemary
        // enemyAvaliable_pos.add(new Position(0, 0));
        // enemyAvaliable_pos.add(new Position(48, 48));
        // enemyAvaliable_pos.add(new Position(48+GamePanel.tileSize, 48));
        // enemyAvaliable_pos.add(new Position(48, 48+GamePanel.tileSize));
        // enemyAvaliable_pos.add(new Position(48-GamePanel.tileSize, 48));
        // enemyAvaliable_pos.add(new Position(48, 48-GamePanel.tileSize));

        enemies = new ArrayList<Enemy>();
        player = Player.getInstance();
    }

    private void initalAvailableArray(){
        available = new ArrayList<Position>();

        // add all aviable area in this function
    }

    public boolean addAviable(Position position){
        if(isPlaceAviable(position))
            return false;
        else{
            available.add(position);
            return true;
        }
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

            elements.add(object);
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

    public ArrayList<CharacterAvaliablePosition>  getElemet(){
        return elements;
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

    public void removeReward(Reward reward){
        available.add(reward.getPosition());
        rewards.remove(reward);
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

    public Reward playerGetReward(){
        for (Reward reward : rewards) {
            if(player.getPosition().equal(reward.getPosition()))
                return reward;
        }
        return null;
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
            //RemoveFromAviable(position);
        }
    }


    private void RemoveFromAviable(Position takePosition){
        iterator = available.iterator();
        while (iterator.hasNext()) {
            Position position = iterator.next();
            if(position.equal(takePosition)){
                iterator.remove(); 
                break;
            }         
        }
    }
    
    public List<Enemy> getEnemyList(){
        return enemies;
    }
}
