package cmpt276.group4;


import java.awt.CardLayout;

import java.util.ArrayList;

import java.util.Timer;
import java.util.concurrent.TimeUnit;
import java.util.List;


import javax.swing.JFrame;
import javax.swing.JPanel;

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
import cmpt276.group4.Room.Tile;
import cmpt276.group4.Room.Tombstone;
import cmpt276.group4.Room.Wall;
import cmpt276.group4.UI.NumberPanel;
import cmpt276.group4.Room.Obstacle;
import cmpt276.group4.WindowAndInput.GamePanel;
import cmpt276.group4.WindowAndInput.LoadingPanel;
import cmpt276.group4.WindowAndInput.MainPanel;
import cmpt276.group4.WindowAndInput.keyboardListener;



public class GameManager {
    // typeOfRoom
    //level: BASIC, MEDIUM, HARD
    private GameStatus status;
    private boolean gameEnd = false;

    private gameLevel level = gameLevel.HARD;
    private int typeOfRoom;
    private static GameManager instance;

    private JFrame window;

    private CardLayout layout;
    private JPanel cardContainer;
    private GamePanel gamePanel;
    private MainPanel mainPanel;
    private LoadingPanel loadPanel;
    private NumberPanel numberPanel;


    private keyboardListener listener;
    private Room room;

    private EnemyFactory enemyFactory;

    private RoomInitialization initialization_room;

    private EnemyInitialization enemyInitialization;
    private RewardFactory rewardFactory;

    private RewardInitialization rewardInitialization;

    private RecordUsedPlace record;
    private boolean existPlayer = false;


    public GameManager(){
        window = new JFrame();
        layout = new CardLayout();
        cardContainer = new JPanel(layout);
        
        gamePanel = new GamePanel();
        mainPanel = new MainPanel();
        loadPanel = new LoadingPanel();
        numberPanel = new NumberPanel();

        cardContainer.add(gamePanel,"game");
        cardContainer.add(mainPanel,"main");
        cardContainer.add(loadPanel,"load");
        cardContainer.add(numberPanel, "gameEnd");


        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("paws and pumpkins");

        window.getContentPane().add(cardContainer);
        window.pack();
        window.setLocationRelativeTo(null);


        
    }

    // getter
    public int getTypeOfRoom() {
        return typeOfRoom;
    }

    // setter
    public void setTypeOfRoom(int i) {
        this.typeOfRoom = i;
    }

    public static synchronized GameManager getInstance(){
        if(instance == null)
            instance = new GameManager();
        return instance;
    }

    public void createMainWindow(){
        status = GameStatus.MainPanel;
        layout.show(cardContainer, "main");

        window.setVisible(true);
    }

    public void createWindows(){
        
        status = GameStatus.GamePanel;
        layout.show(cardContainer, "game");

        window.setVisible(true);

        listener = new keyboardListener();
        window.addKeyListener(listener);

        GameConfig gameConfig=new GameConfig();
        gameConfig.passGameLevel(gameLevel.BASIC);
       
        
        initialization_room = new RoomInitialization();
        RoomFactory roomfactory = new RoomFactory();

        initialization_room.iRoom(roomfactory);

        //RoomFactory roomfactory = new RoomFactory();
        initialization_room.initializeRoom(gameLevel.MEDIUM, roomfactory);

        
        initialization_room.initializeRoom(gameLevel.MEDIUM, roomfactory);


        record =  RecordUsedPlace.getInstance();
        
    


        initialization_room.iTiles(roomfactory);
        initialization_room.iWalls(roomfactory);
        initialization_room.iTombs(roomfactory);
        
      
        enemyFactory = new EnemyFactory();
        enemyInitialization = new EnemyInitialization(enemyFactory); 

        rewardFactory = new RewardFactory();
        rewardInitialization = new RewardInitialization(rewardFactory);
        rewardInitialization.generateReward();

    }

    public void createNumberPanel(){
        status = GameStatus.Win;


        
        layout.show(cardContainer, "gameEnd");
        window.setVisible(true);
    }

    public void RecordUsedPlaceAviable(){
        if(existPlayer ==false){
            existPlayer = true;
            creatPlayer();
        }
    }


    private void creatPlayer(){
        Player player = PlayerGenerator.creatPlayer();
        listener.addPlayer(player);
        gamePanel.setPlayer(player);
        RecordUsedPlace.getInstance().setPlayer(player);
        gamePanel.createTimeLine();
    }

    public void enemyCatachPlayer(boolean moveable){
        if(moveable == true){
            status = GameStatus.GameOver;
            endOfGame();
            // end game
        }
        else{
            // game continues
        }
    }

    public void transformToLoadingScreen(gameLevel level){
        if(status == GameStatus.MainPanel){
            this.level = level;
            layout.show(cardContainer, "load");
            loadPanel.gameLevelSending(level);
            status = GameStatus.LoadingPanel;
        }
    }

    public void transformToGameScreen(){
        if(status == GameStatus.LoadingPanel){
            layout.show(cardContainer, "game");
            gamePanel.createTimeLine();
            gamePanel.setPlayer(Player.getInstance());
            addKeyboardListener();
            status = GameStatus.GamePanel;
        }
    }

    private void addKeyboardListener(){
        listener = new keyboardListener();
        window.addKeyListener(listener);
        System.out.println("sounds good");
        listener.addPlayer(Player.getInstance());
    }

    private void endOfGame(){
        if(status == GameStatus.GamePanel.GameOver){

        }
        else{

        }
    }

    public boolean isGameEnd(){
        if(status == GameStatus.GameOver || status == GameStatus.Win)
            return true;
        else
            return false;
    }

    // Call this method in game update loop to check for mouse input
    // public void handleMouseInput() {
    //     if (listener.isMouseClicked()) {
    //         // Handle mouse click using inputListener.getMousePosition()
    //         System.out.println(String.format("MOUSE CLICKED AT POSITION x:%d y: %d", listener.getMousePosition().getX_axis(), listener.getMousePosition().getY_axis()));
    //         listener.clearMouseClick(); // Clear the click once it's handled
    //     }
    //     //  Handle mouse position and pressed state
    // }

    //instance

    //enemyNum

    //gameRoom

    //totalRewardNum

    //initialgame

    //postGame

    //openDoor

    //endGame

    //EndGame

    //getAllReward

    //catchyByEnemy

    //getInstance
}
