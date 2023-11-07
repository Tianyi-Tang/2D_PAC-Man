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

    private ArrayList<Position> characterAvaliable_pos;
    private ArrayList<Position> obstacle_pos;

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


    public Position getRandomSafePosition() {
        ArrayList<Position> availableWithoutSpiders = new ArrayList<>(available);
    
        Iterator<Position> positionIterator = availableWithoutSpiders.iterator();
    
        while (positionIterator.hasNext()) {
            Position pos = positionIterator.next();
            for (Enemy enemy : enemies) {
                //if (enemy instanceof Spider && enemy.getEnemyPosition().equals(pos)) {
                    if (enemy.getEnemyPosition().equals(pos)) {
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

        characterAvaliable_pos = new ArrayList<Position>();
        obstacle_pos = new ArrayList<Position>();
        elements = new ArrayList<CharacterAvaliablePosition>();

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
            if(object.getPlayerAvaliable())
                characterAvaliable_pos.add(object.getPosition());
            else
                obstacle_pos.add(object.getPosition());

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
            //testing
            // Remove enemy's position from enemyAvaliable_pos only if enemy is an Spider
            // if (enemy instanceof Spider) {
            // Iterator<Position> it = enemyAvaliable_pos.iterator();
            // while (it.hasNext()) {
            //     Position p = it.next();
            //     if (p.equal(enemy.getEnemyPosition())) {
            //         System.out.println("removing from enepos ");
            //         it.remove();
            //         break; // Stop the loop once the position is found and removed
            //     }
            // }
            //}
        }
        else{
            System.out.println("no did not add");
            return false;
        }
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

    public boolean characterMovable(Position destination){
        for (Position position : characterAvaliable_pos) {
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

    public boolean isNotSpiderPosition(Position pos){
        for (Enemy enemy : enemies) {
                if (enemy instanceof Spider && enemy.getEnemyPosition().equals(pos)) {
                    return false;
                }
            }
            return true;
        }
        
}
