package cmpt276.group4;


import java.awt.CardLayout;



import javax.swing.JFrame;
import javax.swing.JPanel;



import cmpt276.group4.Player.Player;



import cmpt276.group4.UI.NumberPanel;

import cmpt276.group4.WindowAndInput.GamePanel;
import cmpt276.group4.WindowAndInput.LoadingPanel;
import cmpt276.group4.WindowAndInput.MainPanel;
import cmpt276.group4.WindowAndInput.keyboardListener;


/**
 * Class that controll the switch of different panel and end of game
 */
public class GameManager {    
    private GameStatus status;
    private boolean gameEnd = false;// end of game 

    //level: BASIC, MEDIUM, HARD
    private static GameManager instance;// singleton

    private JFrame window;

    private CardLayout layout;//manager to the different panel
    private JPanel cardContainer;

    //panels in the game
    private GamePanel gamePanel;
    private MainPanel mainPanel;
    private LoadingPanel loadPanel;
    private NumberPanel numberPanel;

    private Player player;

    private keyboardListener listener;

    /**
     * constructor to set the defualt setting fot JFrame and laoding all panel
     */
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

    public static synchronized GameManager getInstance(){
        if(instance == null)
            instance = new GameManager();
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
     * lading the result panel to window and it is end of game
     */
    public void createNumberPanel(){
        status = GameStatus.Win;

        layout.show(cardContainer, "gameEnd");
        window.setVisible(true);
    }

    /**
     * Enemy catch player and base on the type of enemy give different result
     * @param moveable is enemy moveable
     */
    public void enemyCatachPlayer(boolean moveable){
        if(moveable == true){
            status = GameStatus.GameOver;
            endOfGame();
        }
        else{
            player.deductPoint(5);
        }
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
            layout.show(cardContainer, "game");
            gamePanel.createTimeLine();
            player = Player.getInstance();
            gamePanel.setPlayer(player);
            addKeyboardListener();
            status = GameStatus.GamePanel;
        }
    }


    private void addKeyboardListener(){
        listener = new keyboardListener();
        gamePanel.addKeyListener(listener);
        gamePanel.requestFocusInWindow();
        listener.addPlayer(Player.getInstance());
    }

    /**
     * Play have negative point and end the game 
     */
    public void negativePoint(){
        if(player.totalScore() < 0){
            status = GameStatus.GameOver;
            endOfGame();
        }
    }

    /**
     * Player succesful go out of door and he win
     */
    public void leaveDoor(){
        if(player.playerWin()){
            status = GameStatus.Win;
            endOfGame();
        }
    }

    /**
     * Chcek the game is end or not
     * @return If true, it mean the game end; else the game still contius
     */
    public boolean isGameEnd(){
        if(status == GameStatus.GameOver || status == GameStatus.Win)
            return true;
        else
            return false;
    }

    /**
     * laoding the result panel to end of gmae
     */
    private void endOfGame(){
        layout.show(cardContainer, "gameEnd");
        numberPanel.init(status);
        numberPanel.setNumbers(player.getCollectScore(), player.getGeneralRewardNum(), player.getBonusRewardNum() * 5, player.getDeductScore(), player.totalScore());
    }

}
