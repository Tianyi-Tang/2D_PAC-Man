package cmpt276.group4.WindowAndInput;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import cmpt276.group4.gameLevel;

import cmpt276.group4.GameMap.RecordUsedPlace;
import cmpt276.group4.GameMap.RoomEnvironment;
import cmpt276.group4.GameMap.RoomLayout;
import cmpt276.group4.Logic.GameConfig;
import cmpt276.group4.Logic.WindowConfig;
import cmpt276.group4.Room.RoomFactory;
import cmpt276.group4.Room.RoomInitialization;


/**
 * Panel that laoding all resources in the game 
 */
public class LoadingPanel extends JPanel implements Runnable {

    private final int screenWidth = 48 * 16;
    private final int screenHeight = 48 * 16;

    private Thread loadingThread;

    final int FPS = 60;// how many update within one sconde
    private double timeInterval = 1000000000/FPS;//timer interval for each update
    private JProgressBar progressBar;
    private int progress;

    private RecordUsedPlace record;
    private RoomLayout roomLayout;
    private RoomEnvironment roomEnvironment;
    private GameConfig config;//object contain the information about game 
    private boolean generateconfi,generateRoom, generateAllTile, generateWall, generatePlayer,generateObstacle,generateAllEnemies, generateAllRewards =false;
    //boolean value to check each resource is generate

    private InitialiseGameItem initialiseItem;
    private BufferedImage background_img;

    /**
     * constructor for loading panel to sthe the process bar and background
     */
    public LoadingPanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setLayout(null);
        loadingImage();
        
        progressBar = new JProgressBar(0,6);
        progressBar.setBounds(128, 512, 512, 30);
        progressBar.setStringPainted(true);
        progressBar.setOpaque(true);
        progressBar.setValue(0);
        progressBar.setVisible(true);
        progress = 0;

        this.add(progressBar);
    }

    public void init(InitialiseGameItem initialiseGameItem, GameConfig gameConfig){
        config = gameConfig;
        initialiseItem = initialiseGameItem;
        initialiseItem.setRoomInitialize(new RoomInitialization(), new RoomFactory());
        createTimeLine();
    }

    public void setKeySingleton(RecordUsedPlace record, RoomLayout roomLayout, RoomEnvironment roomEnvironment){
        this.record = record;
        this.roomLayout = roomLayout;
        this.roomEnvironment = roomEnvironment;
    }

    /**
     * Create the gamme loop for the laoding panel
    */
    private void createTimeLine(){
        if(loadingThread == null){
            loadingThread = new Thread(this);
            loadingThread.start();
        }
    }

    /**
     * Loading the background image
     */
    private void loadingImage(){
        try {
            background_img = ImageIO.read(new File("res/pop_up/game_welcome_page2.png"));
        } catch (Exception e) {
            
        }
    }

    public int loadingProcess(){
        return progress;
    }

    /**
     * Continues check all resource is loading
     * after all resource loading finished the loop and switch the game panel
     */
    @Override
    public void run() {
        double iteration = 0;
        double last_time = System.nanoTime();
        double currentTime;

        while (loadingThread != null) {
            currentTime = System.nanoTime();
            iteration += (currentTime - last_time) / timeInterval;
            last_time = currentTime;

            if(iteration >=1){
                if(allResourceLoading())
                    break;
                update();
                repaint();
                iteration -= 1;
            }
        }
        
        initialLoading();
        startGame();
    }

    /**
     * Check and laod resource by dependcy order
     */
    public void update(){
        progressBar.setValue(progress);
        if(!generateconfi){
            checkConfig();
        }
        else if(!generateRoom){
            checkRoom();
        }
        else if(!generateAllTile){
            checkTitle();
        }
        else if(!generateWall){
            checkWall();
        }
        else if(!generatePlayer){
            checkPlayer();
        }
        else if(!generateObstacle){
            checkObstacle();
        }
        else if(!generateAllEnemies){
            checkEnemy();
        }
        else{
            checkRewards();
        }
    }

    private boolean keySingltonReady(){
        if(record != null && roomLayout != null && roomEnvironment != null)
            return true;
        else
            return false;
    }

    /**
     * Check the game config is successful build
     */
    private void checkConfig(){
        if(config.alreayInitialize() && keySingltonReady()  && initialiseItem.getConfig()){
            generateconfi = true;
            initialiseItem.createRoom();
            progress ++;
        }
    }

    /**
     * Check the room is generate and send position in this room to record use place 
     * Once it success loadin, loading the tile in room
     */
    private void checkRoom(){
        if(record.getLengthOfAviable() == config.areaofRoom()){
            generateRoom = true;
            initialiseItem.createTile();
            progress ++;
        }
    }

    /**
     * Check tile in the room is all loading
     * Once it success loadin, loading the wall in room
     */
    private void checkTitle(){
        System.out.println(config.areaofRoom());
        if(roomLayout.getTileNumber() == config.areaofRoom()){
            generateAllTile = true;
            initialiseItem.createWall();;
            progress ++;
        }
    }

    /**
     * Check the wall in the room is all loading
     * Once it success loadin, loading the player 
     */
    private void checkWall(){
        if(roomLayout.getWallNumber() == config.getNumberOfWall()){
            generateWall = true;
            initialiseItem.createPlayer(roomLayout, roomEnvironment);
            progress ++;
        }
    }

    /**
     * checking the player is aviable and 
     * Once it success loadin,set wining requirement to player and loading the obstacle
     */
    private void checkPlayer(){
        if(roomEnvironment.getPlayerPosition() != null){
            generatePlayer = true;
            initialiseItem.createObstacle();
            progress ++;
        }
    }

    /**
     * checkning all obstacle is appear in the room
     * Once meet the requirement, loading the enemy
     */
    private void checkObstacle (){
        if(roomLayout.getObstaclesNumber() == config.getNumberOfObstacles()){
            generateObstacle = true;
            initialiseItem.createEnemy();
            progress ++;
        }
    }

    /**
     * check all enemy is generate in the room
     * Once meet the requirement, loading the rewards
     */
    private void checkEnemy(){
        if(roomEnvironment.getEnemyNumber() == config.getTotalGhosts()){
            generateAllEnemies = true;
            initialiseItem.createReward();
            progress ++;
        }
    }

    /**
     * check all reward load to the room
     */
    private void checkRewards(){
        if(roomEnvironment.getRewardNumber() == config.getAllRewardNum()){
            generateAllRewards = true;
            progress ++;
        }
    }

    /**
     * Draw the background to the panel
     *@param Graphics draw on the panel
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(background_img != null)
            g.drawImage(background_img,0,0,WindowConfig.screenWidth, WindowConfig.screenHeight,this);
    }

    /**
     * check whether the loading panel finished the tast
     * @return if all resoucres is laoding, return true; else return false
     */
    public boolean allResourceLoading(){
        if(!generateAllRewards)
            return false;
        else
            return true; 
    }

    /**
     * inital the loading panel
     */
    private void initialLoading(){
        generateRoom = generateAllTile =generateAllEnemies = generateObstacle  = generateAllRewards = generatePlayer =false;
        loadingThread = null;
        System.out.println("Success!!");
    }

    /**
     * swtich to game panel
     */
    private void startGame(){
        PanelController.getInstance().transformToGameScreen();
    }


    
}
