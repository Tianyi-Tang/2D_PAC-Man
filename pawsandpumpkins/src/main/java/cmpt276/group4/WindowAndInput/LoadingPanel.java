package cmpt276.group4.WindowAndInput;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import cmpt276.group4.GameManager;
import cmpt276.group4.RecordUsedPlace;
import cmpt276.group4.gameLevel;
import cmpt276.group4.Enemy.EnemyFactory;
import cmpt276.group4.Enemy.EnemyInitialization;
import cmpt276.group4.Logic.GameConfig;
import cmpt276.group4.Player.Player;
import cmpt276.group4.Player.PlayerGenerator;
import cmpt276.group4.Reward.RewardFactory;
import cmpt276.group4.Reward.RewardInitialization;
import cmpt276.group4.Room.Room;
import cmpt276.group4.Room.RoomFactory;
import cmpt276.group4.Room.RoomInitialization;

public class LoadingPanel extends JPanel implements Runnable {

    private final int screenWidth = 48 * 16;
    private final int screenHeight = 48 * 16;

    private Thread loadingThread;

    final int FPS = 60;
    private double timeInterval = 1000000000/FPS;
    private JProgressBar progressBar;
    private int progress;

    private RoomInitialization room_initialization;
    private RoomFactory factory;
    private Room room;

    private RecordUsedPlace record;
    private GameConfig config;
    private boolean generateconfi,generateRoom, generateAllTile, generateWall, generatePlayer,generateObstacle,generateAllEnemies, generateAllRewards =false;
    private BufferedImage background_img;


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

    public void gameLevelSending(gameLevel level){
        config = GameConfig.getGameConfigInstance();
        config.passGameLevel(level);
        createTimeLine();
    }

    private void createTimeLine(){
        if(loadingThread == null){
            loadingThread = new Thread(this);
            loadingThread.start();
            record = RecordUsedPlace.getInstance();
        }
    }

    private void loadingImage(){
        try {
            background_img = ImageIO.read(new File("res/pop_up/game_welcome_page2.png"));
        } catch (Exception e) {
            
        }
    }

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

    private void update(){
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

    private void checkConfig(){
        if(config.alreayInitialize()){
            generateconfi = true;
            createRoom();
        }
    }

    private void checkRoom(){
        if(record.getLengthOfAviable() == config.areaofRoom()){
            generateRoom = true;
            createTitle();
            progress ++;
        }
    }

    private void checkTitle(){
        System.out.println(config.areaofRoom());
        if(record.getTileNumber() == config.areaofRoom()){
            generateAllTile = true;
            createWall();
            progress ++;
        }
    }

    private void checkWall(){
        if(record.getWallNumber() == config.getNumberOfWall()){
            generateWall = true;
            createPlayer();
            progress ++;
        }
    }

    private void checkPlayer(){
        if(record.getPlayerPosition() != null){
            Player.getInstance().setRoom(room);
            generatePlayer = true;
            createObstacle();
            progress ++;
        }
    }

    private void checkObstacle (){
        System.out.println("number of obstacle:" + record.getObstaclesNumber() +"; should be:" +config.getNumberOfObstacles());
        if(record.getObstaclesNumber() == config.getNumberOfObstacles()){
            generateObstacle = true;
            createEnemy();
            progress ++;
        }
    }

    private void checkEnemy(){
        if(record.getLengthOfEnemies() == config.getTotalGhosts()){
            generateAllEnemies = true;
            createReward();
            progress ++;
        }
    }

    private void checkRewards(){
        if(record.getLengthOfRewards() == config.getAllRewardNum()){
            generateAllRewards = true;
            createPlayer();
            progress ++;
        }
    }

    private void createRoom(){
        room_initialization = new RoomInitialization();
        factory = new RoomFactory();
        room_initialization.initializeRoom(config.getGameLevel(), factory);
        room =room_initialization.iRoom(factory);
    }

    private void createTitle(){
        room_initialization.iTiles(factory);
    }

    private void createWall(){
        room_initialization.iWalls(factory);
    }

    private void createPlayer(){
        record.setPlayer(PlayerGenerator.creatPlayer());
    }

    private void  createObstacle(){
        room_initialization.iTombs(factory);
    }

    private void createEnemy(){
        new EnemyInitialization(new EnemyFactory());
    }


    private void createReward(){
        RewardInitialization reward_initialization = new RewardInitialization(new RewardFactory());
        reward_initialization.generateReward();
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(background_img != null)
            g.drawImage(background_img,0,0,GamePanel.screenWidth, GamePanel.screenHeight,this);
        else
            System.out.println("error");
    }

    private boolean allResourceLoading(){
        if(!generateAllRewards)
            return false;
        else
            return true; 
    }

    private void initialLoading(){
        generateRoom = generateAllTile = generateObstacle = generateObstacle =generateAllEnemies = generateAllRewards = generatePlayer =false;
        loadingThread = null;
        System.out.println("Success!!");
    }

    private void startGame(){
        GameManager.getInstance().transformToGameScreen();
    }


    
}
