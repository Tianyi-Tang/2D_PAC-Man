package cmpt276.group4;




import cmpt276.group4.Logic.GameConfig;
import cmpt276.group4.Player.Player;


/**
 * Class that controll the switch of different panel and end of game
 */
public class GameManager {    
    private GameStatus status;

    private int generalRewards_num;
    private boolean gameEnd = false;// end of game 
    private boolean playerWin = false;
    

    //level: BASIC, MEDIUM, HARD
    private static GameManager instance;// singleton

    private Player player;



    /**
     * constructor to set the defualt setting fot JFrame and laoding all panel
     */
    // public GameManager(){
    //     window = new JFrame();
    //     layout = new CardLayout();
    //     cardContainer = new JPanel(layout);
        
    //     gamePanel = new GamePanel();
    //     mainPanel = new MainPanel();
    //     loadPanel = new LoadingPanel();
    //     numberPanel = new NumberPanel();

    //     cardContainer.add(gamePanel,"game");
    //     cardContainer.add(mainPanel,"main");
    //     cardContainer.add(loadPanel,"load");
    //     cardContainer.add(numberPanel, "gameEnd");


    //     window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //     window.setResizable(false);
    //     window.setTitle("paws and pumpkins");

    //     window.getContentPane().add(cardContainer);
    //     window.pack();
    //     window.setLocationRelativeTo(null);
    // }

    public static synchronized GameManager getInstance(){
        if(instance == null)
            instance = new GameManager();
        return instance;
    }

    /**
     * Method for testing purposes to set the mock player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isGameEnd(){
        return gameEnd;
    }

    public boolean isPalyerWin(){
        return playerWin;
    }

    public void setNumberOfGeneralRewards(GameConfig config){
        generalRewards_num = config.getNumberOfRegularRewards();
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
     * Play have negative point and end the game 
     */
    public void negativePoint(){
        if(player.totalScore() < 0){
            status = GameStatus.GameOver;
            endOfGame();
        }
    }

    public void collectAllRewards(){
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
     * laoding the result panel to end of gmae
     */
    private void endOfGame(){
        gameEnd =true;
        if(status == GameStatus.Win)
            playerWin = true;
        else
            playerWin = false;
        PanelController.getInstance().transformToEndScreen();
    }

        /**
     * laoding the main menu to window and start of game
     */
    // public void createMainWindow(){
    //     status = GameStatus.MainPanel;
    //     layout.show(cardContainer, "main");

    //     window.setVisible(true);
    // }



    // /**
    //  * lading the result panel to window and it is end of game
    //  */
    // public void createNumberPanel(){
    //     status = GameStatus.Win;

    //     layout.show(cardContainer, "gameEnd");
    //     window.setVisible(true);
    // }

       /**
     * Switch the main panel to laoding panel
     * @param level the difficulty of game 
     */
    // public void transformToLoadingScreen(gameLevel level){
    //     if(status == GameStatus.MainPanel){
    //         layout.show(cardContainer, "load");
    //         loadPanel.gameLevelSending(level);
    //         status = GameStatus.LoadingPanel;
    //     }
    // }

    /**
     * Switch the loading panel to game panel
     */
    // public void transformToGameScreen(){
    //     if(status == GameStatus.LoadingPanel){
    //         layout.show(cardContainer, "game");
    //         gamePanel.createTimeLine();
    //         player = Player.getInstance();
    //         gamePanel.setPlayer(player);
    //         addKeyboardListener();
    //         status = GameStatus.GamePanel;
    //     }
    // }

}
