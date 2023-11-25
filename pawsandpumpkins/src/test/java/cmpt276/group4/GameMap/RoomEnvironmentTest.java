package cmpt276.group4.GameMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cmpt276.group4.Position;
import cmpt276.group4.Enemy.Ghost;
import cmpt276.group4.Logic.WindowConfig;
import cmpt276.group4.Player.Player;

public class RoomEnvironmentTest {
    public RoomEnvironment roomEnvironment;
    public RecordUsedPlace record;
    public Player player;

    @BeforeEach
    public void setUp(){
        roomEnvironment = new RoomEnvironment();

        record = new RecordUsedPlace();
        player = new Player();
        roomEnvironment.init(record);
        roomEnvironment.setPlayer(player);
    }

    @Test
    public void addGhostEnemy(){
        Position enemyPosition = new Position(WindowConfig.tileSize *2, WindowConfig.tileSize);
        record.addAviable(enemyPosition);
    }


}
