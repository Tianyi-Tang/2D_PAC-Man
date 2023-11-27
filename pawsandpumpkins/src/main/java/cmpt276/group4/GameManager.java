package cmpt276.group4;

import cmpt276.group4.Logic.GameConfig;
import cmpt276.group4.Player.Player;
import cmpt276.group4.Room.Door;
import cmpt276.group4.WindowAndInput.PanelController;


/**
 * Class that controll the switch of different panel and end of game
 */
public class GameManager {    
    private GameStatus status;

    private int generalRewards_num;
    private boolean collectAllRewards;
    private Door[] doors;

    private boolean gameEnd;// end of game 
    private boolean playerWin;
    private PanelController controll;
    

    //level: BASIC, MEDIUM, HARD
    private static GameManager instance;// singleton

    public static void setInstance(GameManager instance) {
        GameManager.instance = instance;
    }

    private Player player;


    public static synchronized GameManager getInstance(){
        if(instance == null)
            instance = new GameManager();
        return instance;
    }

    public void init(PanelController controller){
        this.controll = controller;
    }


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

    public void setDoors(Door[] doors){
        this.doors = doors;
    }

    public boolean isPlayerCollectAllRewards(){
        return collectAllRewards;
    }


    public void collectReward(int generalReward){
        if(generalReward == generalRewards_num){
            collectAllRewards = true;
            for (Door door : doors) {
                door.turnOnDoor();
            }
        }
    }

    public void playerLeaveDoor(){
        if(collectAllRewards){
            status = GameStatus.Win;
            endOfGame();
        }
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

    /**
     * laoding the result panel to end of gmae
     */
    private  void endOfGame(){
        gameEnd =true;
        if(status == GameStatus.Win)
            playerWin = true;
        else
            playerWin = false;
        controll.transformToEndScreen();
    }

        /**
     * Player succesful go out of door and he win
     */
    // public void leaveDoor(){
    //     if(player.playerWin()){
    //         status = GameStatus.Win;
    //         endOfGame();
    //     }
    // }
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
