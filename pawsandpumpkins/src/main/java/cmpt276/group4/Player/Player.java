package cmpt276.group4.Player;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import cmpt276.group4.GameManager;
import cmpt276.group4.Position;
import cmpt276.group4.RecordUsedPlace;
import cmpt276.group4.Enemy.Enemy;
import cmpt276.group4.Logic.WindowConfig;
import cmpt276.group4.Reward.Reward;
import cmpt276.group4.WindowAndInput.MoveDirection;

/**
 * Class tha represent character that control by people
 */
public class Player implements KeyMovingObserver {
    private Position playerPosition;
    private Position destination;
    private static Player _instance = null;

    private boolean move_up, move_down, move_left, move_right = false;//directions player can move
    private MoveDirection direction = MoveDirection.Down; //the current direction playe move
    private boolean org_State = true;
    private int stateCounter = 0;

    private PlayerMovement movement;//check player move to avalibale position
    private int time_counter = 0;

    private int deductScore = 0;

    private int collectScore = 0;
    private int bonusReward_num = 0;
    private int generalReward_num =0;

   private  BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;//images for player moving 
   private  BufferedImage currentImage = null;// The image will draw on the window

   /**
    * constructor for the player initlization
    */
    Player(){
        playerPosition = new Position(1 * WindowConfig.tileSize, 1 * WindowConfig.tileSize);
        movement = new PlayerMovement();
        getPlayerImage();
        destination = new Position(0, 0);
    }

    /**
     * a static function to get player instance 
     * @return the instance of Player
     */
    public static synchronized Player getInstance(){
        if(_instance == null){
            _instance = new Player();
        }      
        return _instance;
    }

    /**
     * try to load the player image from resource
     */
    private void getPlayerImage(){
        try {
            //String directory = System.getProperty("user.dir");
            up1 = ImageIO.read(new File("res/Player/up1.png"));
            up2 = ImageIO.read(new File("res/Player/up2.png"));
            down1 = ImageIO.read(new File("res/Player/down1.png"));
            down2 = ImageIO.read(new File("res/Player/down2.png"));
            left1 = ImageIO.read(new File("res/Player/left1.png"));
            left2 = ImageIO.read(new File("res/Player/left2.png"));
            right1 = ImageIO.read(new File("res/Player/right1.png"));
            right2 = ImageIO.read(new File("res/Player/right2.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * get the player position
     * @return player position
     */
    public Position getPosition(){
        return playerPosition;
    }

    /**
     * When player catach by enemy, give punishment to player
     * @param deductScore how many socre need to be deduct
     */
    public void deductPoint(int deductScore){
        this.deductScore += deductScore;
        if(deductScore > collectScore){
            GameManager.getInstance().negativePoint();
        }
    }

    /**
     * When player get reward, add score to player and check player collect all general rewards or not
     * @param number how many score add to player
     * @param isBonusReward is this reward a bonus reward
     */
    public void addScoreToPlayer(int number, boolean isBonusReward){
        collectScore += number;
        if(isBonusReward)
            bonusReward_num ++;
        else{
            generalReward_num ++;
            GameManager.getInstance().collectReward(generalReward_num);
        }
            
    }

    /**
     * Get the totoal score player gain without count the deduct point for punishment
     * @return total score of player
     */
    public int getCollectScore(){
        return collectScore;
    }

    /**
     * Get the number of bonuse rewards dose player collect
     * @return number of bonuse rewards 
     */
    public int getBonusRewardNum(){
        return bonusReward_num;
    }

    /**
     * Get number of bonuse general rewards dose player collect
     * @return number of general rewards
     */
    public int getGeneralRewardNum(){
        return generalReward_num;
    }
    
    /**
     * How many point people deduct for punishment
     * @return deduct point
     */
    public int getDeductScore(){
        return deductScore;
    }

    /**
     * The total scores of player which count the deduct point for punishment
     * @return total scores
     */
    public int totalScore(){
        return collectScore - deductScore;
    }

    /**
     * Get information send from KeybaordListener and change player moving direction 
     * @param direction the direction player move to 
     * @param turnOn is people press or release keyboard
     */
    @Override
    public void observerUpdate(MoveDirection direction, boolean turnOn) {
        switch (direction) {
            case Up:
                move_up = turnOn;
                break;
            case Down:
                move_down = turnOn;
                break;
            case Left:
                move_left = turnOn;
                break;
            case Right:
                move_right = turnOn;
                break;
        }
    }

    /**
     * Logical update for player to change position base on keyboard input
     */
    public void update(){
        stateCounter++;
        time_counter ++;
        if(stateCounter >= 15){
            if(org_State)
                org_State = false;
            else
                org_State = true;
            stateCounter =0;
        }
        
        if(time_counter >= 10){
            if(move_up){
                direction = MoveDirection.Up;
                updateDestination(0, -48);
                updatePosition();
            }
            else if(move_down){
                direction = MoveDirection.Down;
                updateDestination(0, 48);
                updatePosition();
            }
            else if(move_right){
                direction = MoveDirection.Right;
                updateDestination(48, 0);
                updatePosition();
            }
            else if(move_left){
                direction = MoveDirection.Left;
                updateDestination(-48, 0);
                updatePosition();
            }
            time_counter =0;
        }
        
    }

    /**
     * Draw the player position on the map
     * @param g2 a Graphics2D that represent game window
     */
    public void draw(Graphics2D g2){
        switch (direction) {
            case Up:
                if(org_State)
                    currentImage = up1;
                else
                    currentImage = up2;
                break;
            case Down:
                if(org_State)
                    currentImage = down1;
                else
                    currentImage = down2;
                break;
            case Left:
                if(org_State)
                    currentImage = left1;
                else
                    currentImage = left2;
                break;
            case Right:
                if(org_State)
                    currentImage = right1;
                else
                    currentImage = right2;
                break;
        }

        g2.drawImage(currentImage, playerPosition.getX_axis(), playerPosition.getY_axis(), WindowConfig.tileSize, WindowConfig.tileSize,null); 
        
    }

    /**
     * Change the destination base on the keyboard input
     * @param x_increment the different of x-axis between player current position and  destination position
     * @param y_increment the different of y-axis between player current position and  destination position
     */
    private void updateDestination(int x_increment,int y_increment){
        destination.setPosition(playerPosition);
        destination.addOnX_axis(x_increment);
        destination.addOnY_axis(y_increment);
    }

    /**
     * If the destination is aviable position for player, then change the
     * destination position to player position and check the destination has
     * reward or enemy
     */
    private void updatePosition(){
        if(movement.isPositionAvailable(destination)){
            playerPosition.setPosition(destination);
            Reward reward = RecordUsedPlace.getInstance().playerGetReward();
            if(reward != null)
                reward.addBenefit(this);
            Enemy enemy = RecordUsedPlace.getInstance().playerMeetEnemy();
            if(enemy != null){
                GameManager.getInstance().enemyCatachPlayer(enemy.getMovable());
            }
        }
            
    }

    /**
     * Check whether player position is same as door position after
     * player meet the wining requirement
     */
    // private void outOfDoor(){
    //     if(checkDoor){
    //         for (Door door : doors) {
    //             if(playerPosition.equal(door.getPosition())){
    //                 wining = true;
    //                 GameManager.getInstance().leaveDoor();
    //             }
                    
    //         }
    //     }
    // }

    /**
     * Check whether player meeting the requirement of wining
     */
    // private void meetWiningRequirement(){
    //     if(generalReward_num == generalReward_require)
    //         checkDoor = true;
    // }



}
