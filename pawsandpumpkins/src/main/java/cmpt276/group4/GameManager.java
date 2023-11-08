package cmpt276.group4;


import java.util.ArrayList;

import java.util.Timer;
import java.util.List;


import javax.swing.JFrame;

import cmpt276.group4.Enemy.Enemy;
import cmpt276.group4.Enemy.EnemyFactory;
import cmpt276.group4.Enemy.EnemyInitialization;
import cmpt276.group4.Enemy.EnemyType;
import cmpt276.group4.Player.Player;
import cmpt276.group4.Player.PlayerGenerator;
import cmpt276.group4.Reward.RewardFactory;
import cmpt276.group4.Reward.RewardInitialization;
import cmpt276.group4.Room.Room;
import cmpt276.group4.Room.RoomInitialization;
import cmpt276.group4.Room.Tile;
import cmpt276.group4.WindowAndInput.GamePanel;
import cmpt276.group4.WindowAndInput.keyboardListener;



public class GameManager {
    // typeOfRoom
    //level: BASIC, MEDIUM, HARD
    private gameLevel level = gameLevel.HARD;
    private int typeOfRoom;
    private static GameManager instance;

    private JFrame window;
    private GamePanel gamePanel;
    private keyboardListener listener;
    private Room room;

    private EnemyFactory enemyFactory;


    private EnemyInitialization enemyInitialization;
    private RewardFactory rewardFactory;

    private RewardInitialization rewardInitialization;

    private RecordUsedPlace record;
    private boolean existPlayer = false;

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

    public void createWindows(){
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        window.setTitle("paws and pumpkins");
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        listener = new keyboardListener();
        window.addKeyListener(listener);

        

        gamePanel = new GamePanel(this);
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
