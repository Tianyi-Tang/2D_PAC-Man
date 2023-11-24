package cmpt276.group4.Player;

import cmpt276.group4.Movement;
import cmpt276.group4.Position;
import cmpt276.group4.GameMap.RoomEnvironment;
import cmpt276.group4.GameMap.RoomLayout;
import cmpt276.group4.Reward.Reward;

/**
 * Class the judge whether player can move to the center position
 */
public class PlayerMovement implements Movement {
    private RoomLayout roomLayout;
    private RoomEnvironment roomEnvironment;
    private Player player;

    private Reward reward;


    public void init(RoomLayout roomLayout, RoomEnvironment roomEnvironment,Player player){
        this.roomLayout = roomLayout;
        this.roomEnvironment = roomEnvironment;
        this.player = player;
    }

    /**
     * Check the target position is aviable to player or not 
     * @param position The target position for player moving
     * @return If this position is avaliable for player return true, else return false
     */
    @Override
    public boolean isPositionAvailable(Position position) {
        if(roomLayout.isPositionAviable(position)){
            moveTo(position);
            return true;
        }
        else
            return false;
    }

    private void moveTo(Position position){
        player.setPosition(position);
        reward = roomEnvironment.collectReward();
        if(reward != null)
            reward.addBenefit(player);
    }

    
}
