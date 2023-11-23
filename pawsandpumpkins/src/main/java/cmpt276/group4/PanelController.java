package cmpt276.group4;
import java.awt.CardLayout;


import javax.swing.JFrame;
import javax.swing.JPanel;

import cmpt276.group4.Logic.GameConfig;
import cmpt276.group4.Player.Player;
import cmpt276.group4.Room.Room;
import cmpt276.group4.UI.NumberPanel;
import cmpt276.group4.WindowAndInput.GamePanel;
import cmpt276.group4.WindowAndInput.LoadingPanel;
import cmpt276.group4.WindowAndInput.MainPanel;
import cmpt276.group4.WindowAndInput.keyboardListener;

/**
 * controlling the panel switching
 */
public class PanelController {
     private JFrame window;

    private static PanelController instance;

    private CardLayout layout;//manager to the different panel
    private JPanel cardContainer;

    private GameStatus status;

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


        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("paws and pumpkins");

        window.getContentPane().add(cardContainer);
        window.pack();
        window.setLocationRelativeTo(null);
    }


    public static synchronized PanelController getInstance(){
        if(instance == null)
            instance = new PanelController();
        return instance;
    }

     /**
     * laoding the main menu to window and start of game
     */
    public void createMainWindow(){
        status = GameStatus.MainPanel;
        layout.show(cardContainer, "main");

        window.setVisible(true);
    }

    /**
     * Switch the main panel to laoding panel
     * @param level the difficulty of game 
     */
    public void transformToLoadingScreen(gameLevel level){
        if(status == GameStatus.MainPanel){
            layout.show(cardContainer, "load");
            loadPanel.gameLevelSending(level);
            status = GameStatus.LoadingPanel;
        }
    }

     /**
     * Switch the loading panel to game panel
     */
    public void transformToGameScreen(){
        if(status == GameStatus.LoadingPanel){
            enterGame();
            layout.show(cardContainer, "game");
            gamePanel.createTimeLine();
            gamePanel.setPlayer(Player.getInstance());
            gamePanel.setDoors(Room.getInstance().getDoors());

            addKeyboardListener();
            status = GameStatus.GamePanel;
        }
    }

    /**
     * switch the game panel to game end panel
     */
    public void transformToEndScreen(){
        if(GameManager.getInstance().isGameEnd()){
            layout.show(cardContainer, "gameEnd");
            initalNumberPanel();
        }
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
        GameManager manager = GameManager.getInstance();
        manager.setPlayer(Player.getInstance());
        manager.setNumberOfGeneralRewards(GameConfig.getGameConfigInstance());
        manager.setDoors(Room.getInstance().getDoors());


    }

    /**
     * inital 
     */
    private void initalNumberPanel(){
        Player player = Player.getInstance();
        numberPanel.init(GameManager.getInstance().isPalyerWin());
        numberPanel.setNumbers(player.getCollectScore(), player.getGeneralRewardNum(), player.getBonusRewardNum() * 5, player.getDeductScore(), player.totalScore());
    }


}
