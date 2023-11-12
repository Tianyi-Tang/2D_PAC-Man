package cmpt276.group4;


import java.awt.CardLayout;

import java.util.ArrayList;

import java.util.Timer;
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
        
        RoomInitialization initialization_room = new RoomInitialization();
        initialization_room.setX(12);
        initialization_room.setY(12);

        RoomFactory roomfactory = new RoomFactory();
        initialization_room.initializeRoom(gameLevel.BASIC, roomfactory);
        room = initialization_room.initializeRoom(gameLevel.BASIC, roomfactory);


        record =  RecordUsedPlace.getInstance();
       
        
        
        //Position wallPosition1 = new Position(10, 10);
        //Obstacle wall1 = new Obstacle(wallPosition1, 1);
      
        //RecordUsedPlace.getInstance().addObstacle(wall1);


        //for (Position obstaclePosition : record.getObstacles()) {
        //    System.out.println("Obstacle Position - X: " + obstaclePosition.getX_axis() + ", Y: " + obstaclePosition.getY_axis());
        //}

        //Position wallPosition2 = record.getRandomFromAvailablePosition();
        //System.out.println("wallposition " + wallPosition2);
        //Obstacle wall2 = new Wall(wallPosition2, 1);
        //System.out.println("wallposition " + wallPosition2);
        //RecordUsedPlace.getInstance().addElementToMap(wall2);

        // put tile to all aviable position 
        ArrayList<Position> tilesPosition = RecordUsedPlace.getInstance().getAviablePosition();
        for (Position position : tilesPosition) {
            //System.out.println("number of Aviable:");
            RecordUsedPlace.getInstance().addElementToMap(new Tile(position));
        }

        //walls being created
        //Position wallPosition1 = record.getRandomFromAvailablePosition();
        //Obstacle wall1 = new Wall(wallPosition1);
        //RecordUsedPlace.getInstance().addElementToMap(wall1);
      
    

        GameConfig gameConfig=new GameConfig();
        gameConfig.passGameLevel(gameLevel.HARD);

        rewardFactory = new RewardFactory();
        rewardInitialization = new RewardInitialization(rewardFactory);
        rewardInitialization.generateReward();

        enemyFactory = new EnemyFactory();
        enemyInitialization = new EnemyInitialization(enemyFactory); 

        

        






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

    

    public boolean isGameEnd(){
        if(status == GameStatus.GamePanel && gameEnd == true)
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
