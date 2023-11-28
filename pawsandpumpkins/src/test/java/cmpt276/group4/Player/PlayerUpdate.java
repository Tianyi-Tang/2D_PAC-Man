package cmpt276.group4.Player;

public class PlayerUpdate {
    private final int playerMove = 10;
    private final int playImageChange = 15;
    private Player player;

    PlayerUpdate(Player player){
        this.player =player;
    }

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
