package cmpt276.group4.Player;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import cmpt276.group4.Position;
import cmpt276.group4.RecordUsedPlace;
import cmpt276.group4.Reward.Reward;
import cmpt276.group4.WindowAndInput.GamePanel;
import cmpt276.group4.WindowAndInput.MoveDirection;

public class Player implements KeyMovingObserver {
    private Position playerPosition;
    private Position destination;
    private static Player _instance = null;

    private boolean move_up, move_down, move_left, move_right = false;
    private MoveDirection direction = MoveDirection.Down;
    private boolean org_State = true;
    private int stateCounter = 0;

    private PlayerMovement movement;
    private int time_counter = 0;

    private int deductScore = 0;

    private int collectScore = 0;
    private int bonusReward_num = 0;
    private int generalReward_num =0;

   private  BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
   private  BufferedImage currentImage = null;

    Player(){
        playerPosition = new Position(2 * GamePanel.tileSize, 2 * GamePanel.tileSize);
        movement = new PlayerMovement();
        getPlayerImage();
        destination = new Position(0, 0);
    }

    public static synchronized Player getInstance(){
        if(_instance == null){
            _instance = new Player();
        }      
        return _instance;
    }

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

    public Position getPosition(){
        return playerPosition;
    }

    public void setPlayerMovement(PlayerMovement playerMovement){
        movement = playerMovement;
    }

    public int totalScore(){
        return collectScore - deductScore;
    }

    public void deductPoint(int deductScore){
        this.deductScore += deductScore;
        if(gameEnd()){
            System.out.println("Game end");
        }
    }

    private boolean gameEnd(){
        if(deductScore > collectScore)
            return true;
        else 
            return false;
    }

    public void addScoreToPlayer(int number, boolean isBonusReward){
        collectScore += number;
        if(isBonusReward)
            bonusReward_num ++;
        else
            generalReward_num ++;
    }

    public int getCollectScore(){
        return collectScore;
    }

    public int getBonusRewardNum(){
        return bonusReward_num;
    }

    public int getGeneralRewardNum(){
        return generalReward_num;
    }

    public int totaltScore(){
        return collectScore - deductScore;
    }

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

        g2.drawImage(currentImage, playerPosition.getX_axis(), playerPosition.getY_axis(), GamePanel.tileSize, GamePanel.tileSize,null); 
        
    }


    private void updateDestination(int x_increment,int y_increment){
        destination.setPosition(playerPosition);
        destination.addOnX_axis(x_increment);
        destination.addOnY_axis(y_increment);
    }

    private void updatePosition(){
        if(movement.isPositionAvailable(destination)){
            playerPosition.setPosition(destination);
            Reward reward = RecordUsedPlace.getInstance().playerGetReward();
            if(reward != null)
                reward.addBenefit(this);
        }
            
    }




}
