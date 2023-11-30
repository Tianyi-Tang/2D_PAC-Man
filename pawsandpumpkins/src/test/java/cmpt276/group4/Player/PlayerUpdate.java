package cmpt276.group4.Player;

/**
 * Class that for player update it position and image
 */
public class PlayerUpdate {
    private final int playerMove = 10;
    private final int playImageChange = 15;
    private Player player;

    PlayerUpdate(Player player){
        this.player =player;
    }

    /**
     * Imitate the time pass by calling multiple time of player  
     * @param numberTime number of time you want to playe moving or change position
     * @param forPlayerMoving to obsever player moving or switch image
     */
    public void playerUpdate(int numberTime, boolean forPlayerMoving){
        int times;
        if(forPlayerMoving)
            times = playerMove * numberTime;
        else
            times = playImageChange * numberTime;

        for(int i=0;i < times;i++)
            player.update();
    }
}
