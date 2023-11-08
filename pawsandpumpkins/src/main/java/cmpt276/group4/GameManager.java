package cmpt276.group4;


import java.awt.CardLayout;
import java.util.ArrayList;

import java.util.Timer;
import java.util.List;


import javax.swing.JFrame;
import javax.swing.JPanel;

import cmpt276.group4.Enemy.Enemy;
import cmpt276.group4.Enemy.EnemyFactory;
import cmpt276.group4.Enemy.EnemyInitialization;
import cmpt276.group4.Enemy.EnemyType;
import cmpt276.group4.Player.Player;
import cmpt276.group4.Player.PlayerGenerator;

import cmpt276.group4.Reward.RewardFactory;
import cmpt276.group4.Reward.RewardInitialization;

import cmpt276.group4.Room.Obstacle;

import cmpt276.group4.Room.Room;
import cmpt276.group4.Room.RoomFactory;
import cmpt276.group4.Room.RoomInitialization;
import cmpt276.group4.Room.Tile;
import cmpt276.group4.Room.Wall;
import cmpt276.group4.WindowAndInput.GamePanel;
import cmpt276.group4.WindowAndInput.MainPanel;
import cmpt276.group4.WindowAndInput.keyboardListener;



public class GameManager {
    // typeOfRoom
    //level: BASIC, MEDIUM, HARD
    private GameStatus status = GameStatus.gamePanel;

    private gameLevel level = gameLevel.HARD;
    private int typeOfRoom;
    private static GameManager instance;

    private JFrame window;

    private CardLayout layout;
    private JPanel cardContainer;
    private GamePanel gamePanel;
    private MainPanel mainPanel;

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
        cardContainer = new JPanel();

        gamePanel = new GamePanel(this);
        mainPanel = new MainPanel();

        cardContainer.add(gamePanel,"game");
        cardContainer.add(mainPanel,"main");
    }

    // getter
    public int getTypeOfRoom() {
        return typeOfRoom;
    }

    // setter
    public void setTypeOfRoom(int i) {
        this.typeOfRoom = i;
    }

    public static GameManager getInstance(){
        if(instance == null)
            instance = new GameManager();
        return instance;
    }

    public void createMainWindow(){
        JFrame mainWindow = new JFrame();
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setResizable(false);

        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);

        mainWindow.add(mainPanel);
        mainWindow.pack();
    }

    public void createWindows(){
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        window.setTitle("paws and pumpkins");
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        listener = new keyboardListener();
        window.addKeyListener(listener);


        window.add(gamePanel);
        window.pack();

        gamePanel.addMouseListener(listener);    
        gamePanel.addMouseMotionListener(listener); 
        RoomInitialization initialization_room = new RoomInitialization();
        initialization_room.setX(12);
        initialization_room.setY(12);
        room = initialization_room.createRoom();


        record = new RecordUsedPlace();
        enemyFactory = new EnemyFactory();
        enemyInitialization = new EnemyInitialization(level, enemyFactory); // Initializing 1 enemies
        //enemyFactory.createEnemies(EnemyType.GHOST_BASIC, enemyInitialization.getEnemyNum());
        
        Position wallPosition1 = new Position(10, 10);
        wallPosition1.setX_axis(1000);
        wallPosition1.setY_axis(1000);
        Wall wall1 = new Wall(wallPosition1, 1);
        RecordUsedPlace.getInstance().addWall(wall1);

        Position wallPosition2 = new Position(10, 10);
        Wall wall2 = new Wall(wallPosition2, 1);
        RecordUsedPlace.getInstance().addWall(wall2);
        

        System.out.println("Pass");
        // put tile to all aviable position 
        ArrayList<Position> tilesPosition = RecordUsedPlace.getInstance().getAviablePosition();
        for (Position position : tilesPosition) {
            RecordUsedPlace.getInstance().addElementToMap(new Tile(position));
        }

        rewardFactory = new RewardFactory();
        rewardInitialization = new RewardInitialization(level,rewardFactory);



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

    // Call this method in game update loop to check for mouse input
    public void handleMouseInput() {
        if (listener.isMouseClicked()) {
            // Handle mouse click using inputListener.getMousePosition()
            System.out.println(String.format("MOUSE CLICKED AT POSITION x:%d y: %d", listener.getMousePosition().getX_axis(), listener.getMousePosition().getY_axis()));
            listener.clearMouseClick(); // Clear the click once it's handled
        }
        //  Handle mouse position and pressed state
    }

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
