package cmpt276.group4.WindowAndInput;
import java.awt.CardLayout;


import javax.swing.JFrame;
import javax.swing.JPanel;

import cmpt276.group4.GameManager;
import cmpt276.group4.GameStatus;
import cmpt276.group4.gameLevel;
import cmpt276.group4.GameMap.RecordUsedPlace;
import cmpt276.group4.GameMap.RoomEnvironment;
import cmpt276.group4.GameMap.RoomLayout;
import cmpt276.group4.Logic.GameConfig;
import cmpt276.group4.Player.Player;
import cmpt276.group4.Room.Room;
import cmpt276.group4.UI.NumberPanel;

/**
 * controlling the panel switching
 */
public class PanelController {
    private JFrame window;

    private static PanelController instance;

    private CardLayout layout;//manager to the different panel
    private JPanel cardContainer;

    private GameStatus status;
    private GameManager gameManager;

    private GamePanel gamePanel;
    private MainPanel mainPanel;
    private LoadingPanel loadPanel;
    private NumberPanel numberPanel;

    public PanelController(){
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
        createWindow();
    }


    public static synchronized PanelController getInstance(){
        if(instance == null)
            instance = new PanelController();
        return instance;
    }

    private void createWindow(){
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("paws and pumpkins");

        window.getContentPane().add(cardContainer);
        window.pack();
        window.setLocationRelativeTo(null);
    }

     /**
     * laoding the main menu to window and start of game
     */
    public void createMainWindow(GameManager gameManager, RecordUsedPlace record,RoomLayout roomLayout,RoomEnvironment roomEnvironment){
        status = GameStatus.MainPanel;
        layout.show(cardContainer, "main");
        window.setVisible(true);
        setKeySinglton(gameManager, record, roomLayout, roomEnvironment);
    }

    private void setKeySinglton(GameManager gameManager, RecordUsedPlace record,RoomLayout roomLayout,RoomEnvironment roomEnvironment){
        this.gameManager = gameManager;
        initalKeySinglton(record,roomLayout,roomEnvironment);
        initAllPanel(record, roomLayout, roomEnvironment);
    }

    private void initAllPanel(RecordUsedPlace record,RoomLayout roomLayout,RoomEnvironment roomEnvironment){
        mainPanel.init(this);
        loadPanel.setKeySingleton(record, roomLayout, roomEnvironment,this);
        gamePanel.setKeySingleton(roomLayout, roomEnvironment);
    }

    private void initalKeySinglton(RecordUsedPlace record,RoomLayout roomLayout, RoomEnvironment roomEnvironment){
        gameManager.init(this);
        roomLayout.init(record);
        roomEnvironment.init(record);
    }

    /**
     * Switch the main panel to laoding panel
     * @param level the difficulty of game 
     */
    public void transformToLoadingScreen(gameLevel level){
        loadPanel.init(new InitialiseGameItem(setingGameConfig(level)));
        status = GameStatus.LoadingPanel;
        layout.show(cardContainer, "load");
        loadPanel.createTimeLine();
    }

    private GameConfig setingGameConfig(gameLevel level){
        GameConfig config = GameConfig.getGameConfigInstance();
        config.passGameLevel(level);
        return config;
    }

     /**
     * Switch the loading panel to game panel
     */
    public void transformToGameScreen(){
        enterGame();
        layout.show(cardContainer, "game");
        gamePanel.createTimeLine();
        gamePanel.setPlayer(Player.getInstance());
        gamePanel.setDoors(Room.getInstance().getDoors());

        addKeyboardListener();
        status = GameStatus.GamePanel;
    }

    /**
     * switch the game panel to game end panel
     */
    public void transformToEndScreen(){
        if(gameManager.isGameEnd()){
            gamePanel.endGameLoop();
            layout.show(cardContainer, "gameEnd");
            initalNumberPanel();
        }
    }

    public GameStatus getGameStatu(){
        return status;
    }

    
    private void addKeyboardListener(){
        keyboardListener listener = new keyboardListener();
        gamePanel.addKeyListener(listener);
        gamePanel.requestFocusInWindow();
        listener.addPlayer(Player.getInstance());
    }

    /**
     * call the gameManagerment to get player
     */
    private void enterGame(){
        gameManager.setPlayer(Player.getInstance());
        gameManager.setNumberOfGeneralRewards(GameConfig.getGameConfigInstance());
        gameManager.setDoors(Room.getInstance().getDoors());
    }

    /**
     * inital 
     */
    private void initalNumberPanel(){
        Player player = Player.getInstance();
        numberPanel.init(gameManager.isPalyerWin());
        numberPanel.setNumbers(player.getCollectScore(), player.getGeneralRewardNum(), player.getBonusRewardNum() * 5, player.getDeductScore(), player.totalScore());
    }


}
