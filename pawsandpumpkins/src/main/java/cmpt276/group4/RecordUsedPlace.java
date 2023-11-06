package cmpt276.group4;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import cmpt276.group4.Enemy.Enemy;
import cmpt276.group4.Enemy.Spider;
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
    private Iterator<Position> iterator_pos;
    private Iterator<Reward> iterator_reward;
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


    public Position getRandomFromAvailablePositioAvoidSpider() {
        // Assuming Position has an 'equal' method correctly overridden.
        // Copy the list of available positions to avoid modifying the original list.
        ArrayList<Position> availableWithoutSpiders = new ArrayList<>(available);
    
        // Use an iterator to avoid ConcurrentModificationException
        Iterator<Position> positionIterator = availableWithoutSpiders.iterator();
    
        while (positionIterator.hasNext()) {
            Position pos = positionIterator.next();
            for (Enemy enemy : enemies) {
                // Assuming 'enemy' is of type Spider or has a method to check if it's a spider.
                if (enemy instanceof Spider && enemy.getEnemyPosition().equals(pos)) {
                    // Remove the position if there is a spider on it
                    positionIterator.remove();
                    break; // No need to check the other spiders for this position
                }
            }
        }
    
        // Now 'availableWithoutSpiders' contains positions not occupied by spiders.
        if (availableWithoutSpiders.isEmpty()) {
            System.out.println("No available positions without spiders");
            return null;
        }
    
        // Return a random position from the list of positions without spiders.
        Random random = new Random();
        return availableWithoutSpiders.get(random.nextInt(availableWithoutSpiders.size()));
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
    }

    public void setPlayer(Player player){
        this.player = player;
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
        iterator_reward = rewards.iterator();
        Reward rewardInList;
        while (iterator_reward.hasNext()) {
            rewardInList = iterator_reward.next();
            if(rewardInList == reward)
                iterator_reward.remove();
        }
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
        //Edit by rosemary: use this for testing ghost functions
        // for (Position position : available) {
        //     if(destination.equal(position))
        //         return true;
        // }

        //This is the official one
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
            RemoveFromAviable(position);
        }
    }


    private void RemoveFromAviable(Position takePosition){
        iterator_pos = available.iterator();
        Position position;
        while (iterator_pos.hasNext()) {
            position = iterator_pos.next();
            if(position.equal(takePosition)){
                iterator_pos.remove(); 
                break;
            }         
        }
    }
    
    public List<Enemy> getEnemyList(){
        return enemies;
    }
}
