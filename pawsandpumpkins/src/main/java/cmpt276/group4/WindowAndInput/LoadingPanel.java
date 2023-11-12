package cmpt276.group4.WindowAndInput;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

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
import cmpt276.group4.Room.RoomFactory;
import cmpt276.group4.Room.RoomInitialization;

public class LoadingPanel extends JPanel implements Runnable {

    private final int screenWidth = 48 * 16;
    private final int screenHeight = 48 * 16;

    private Thread loadingThread;

    final int FPS = 60;
    private double timeInterval = 1000000000/FPS;
    private JProgressBar progressBar;

    private RoomInitialization room_initialization;
    private RoomFactory factory;

    private RecordUsedPlace record;
    private GameConfig config;
    private boolean generateconfi,generateRoom, generateAllTile, generateAllObstacle,generateAllEnemies, generateAllRewards, generatePlayer =false;


    public LoadingPanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setLayout(null);
        
        progressBar = new JProgressBar(0,6);
        progressBar.setBounds(50, 256, 512, 30);
        progressBar.setStringPainted(true);
        progressBar.setOpaque(true);
        progressBar.setValue(0);
        progressBar.setVisible(true);

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
    }

    private void update(){
        if(!generateconfi){
            checkConfig();
        }
        else if(!generateRoom){
            checkRoom();
        }
        else if(!generateAllTile){
            System.out.println("tile");
            checkTitle();
        }
        else if(!generateAllObstacle){
             System.out.println("obstacle");
            checkObstacle();
        }
        else if(!generateAllEnemies){
            System.out.println("enemies");
            checkEnemy();
        }
        else if(!generateAllRewards){
            System.out.println("reward");
            checkRewards();
        }
        else{
            checkPlayer();
        }
    }

    private void checkConfig(){
        if(config.alreayInitialize()){
            generateconfi = true;
            createRoom();
        }
    }

    private void checkRoom(){
        System.out.println("avibale:"+ record.getLengthOfAviable());
        if(record.getLengthOfAviable() == config.areaofRoom()){
            generateRoom = true;
            createTitle();
        }
    }

    private void checkTitle(){
        if(record.getTileNumber() == config.areaofRoom()){
            generateAllTile = true;
            createObstacle();
        }
    }

    private void checkObstacle (){
        if(record.getObstaclesNumber() == config.getNumberOfObstacles()){
            generateAllObstacle = true;
            createEnemy();
        }
    }

    private void checkEnemy(){
        if(record.getLengthOfEnemies() == config.getTotalGhosts()){
            generateAllEnemies = true;
            createReward();
        }
    }

    private void checkRewards(){
        if(record.getLengthOfRewards() == config.getAllRewardNum()){
            generateAllRewards = true;
            createPlayer();
        }
    }

    private void checkPlayer(){
        if(record.getPlayerPosition() != null){
            generatePlayer = true;
        }
    }

    private void createRoom(){
        room_initialization = new RoomInitialization();
        factory = new RoomFactory();
        room_initialization.initializeRoom(config.getGameLevel(), factory);
        room_initialization.iRoom(factory);
    }

    private void createTitle(){
        room_initialization.iTiles(factory);
    }

    private void  createObstacle(){
        room_initialization.iWalls(factory);
    }

    private void createEnemy(){
        new EnemyInitialization(new EnemyFactory());
    }


    private void createReward(){
        RewardInitialization reward_initialization = new RewardInitialization(new RewardFactory());
        reward_initialization.generateReward();
    }

    private void createPlayer(){
        record.setPlayer(PlayerGenerator.creatPlayer());
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    private boolean allResourceLoading(){
        if(!generatePlayer)
            return false;
        else
            return true; 
    }

    private void initialLoading(){
        generateRoom = generateAllTile = generateAllObstacle =generateAllEnemies = generateAllRewards = generatePlayer =false;
        loadingThread = null;
        System.out.println("Success!!");
    }


    
}
