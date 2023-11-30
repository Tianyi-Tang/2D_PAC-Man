package cmpt276.group4.WindowAndInput;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cmpt276.group4.Position;
import cmpt276.group4.gameLevel;
import cmpt276.group4.GameMap.RecordUsedPlace;
import cmpt276.group4.GameMap.RoomEnvironment;
import cmpt276.group4.GameMap.RoomLayout;
import cmpt276.group4.Logic.GameConfig;

/**
 * Class for loading pnale unit test
 */
public class LoadingPanelTest {
    public LoadingPanel loadingPanel;

    public InitialiseGameItem mockInialise;
    public RecordUsedPlace mockrecord;
    public RoomLayout mockroomLayout;
    public RoomEnvironment mockroomEnvironment;
    public PanelController mockpanelController;

    int aviablePos_num;
    int wall_num;
    int tombstone_num;
    int enemy_num;
    int reward_num;

    public GameConfig config;

    @BeforeEach
    public void setUp(){
        loadingPanel = new LoadingPanel();
        
        mockInialise = mock(InitialiseGameItem.class);
        mockrecord = mock(RecordUsedPlace.class);
        mockroomLayout = mock(RoomLayout.class);
        mockroomEnvironment = mock(RoomEnvironment.class);
        mockpanelController = mock(PanelController.class);

        
        config = GameConfig.getGameConfigInstance();
        config.passGameLevel(gameLevel.BASIC);
        getNumber(config);

        when(mockInialise.getConfig()).thenReturn(config);
        loadingPanel.init(mockInialise);
        loadingPanel.setKeySingleton(mockrecord, mockroomLayout, mockroomEnvironment,mockpanelController);
        setUpAllReturnNumber();
    }

    /**
     * Test loading panel's prcoess will increase after valid room gnerate
     */
    @Test
    public void loadingRoom(){
        updateTime(1);
        loadingVaild(1);
    }

    /**
     * Test loading panel's prcoess keep same if room is bigger than expect
     */
    @Test
    public void invailRoom(){
        when(mockrecord.getLengthOfAviable()).thenReturn(aviablePos_num + 30);
        updateTime(1);
        loadingVaild(0);
    }

    /**
     * Test loading panel's prcoess will increase after all tile is load 
     */
    @Test 
    public void loadingTile(){
        updateTime(2);
        loadingVaild(2);
    }

    /**
     * Test loading panel's prcoess keep same if tile number is bigger than expect
     */
    @Test 
    public void invaildTileNumber(){
        when(mockroomLayout.getTileNumber()).thenReturn(aviablePos_num + 13);
        updateTime(2);
        loadingVaild(1);
    }

    /**
     * Test loading panel's prcoess will increase after all wall is load 
     */
    @Test
    public void laodingWall(){
        updateTime(3);
        loadingVaild(3);
    }

     /**
     * Test loading panel's prcoess keep same if wall number is smaller than expect
     */
    @Test
    public void invaildWallNumber(){
        when(mockroomLayout.getWallNumber()).thenReturn(wall_num -5);
        updateTime(3);
        loadingVaild(2);
    }

    /**
     * Test loading panel's prcoess will increase after player is load 
     */
    @Test
    public void loadingPlayer(){
        updateTime(4);
        loadingVaild(4);
    }

     /**
     * Test loading panel's prcoess keep same if player position is null
     */
    @Test
    public void failLaodingPlayer(){
        when(mockroomEnvironment.getPlayerPosition()).thenReturn(null);
        updateTime(4);
        loadingVaild(3);
    }

    /**
     * Test loading panel's prcoess will increase after all obstcle is load 
     */
    @Test
    public void loadingObstcle(){
        updateTime(5);
        loadingVaild(5);
    }

     /**
     * Test loading panel's prcoess keep same if obstcle number is bigger than expect
     */
    @Test
    public void loadingMoreObstcle(){
        when(mockroomLayout.getObstaclesNumber()).thenReturn(tombstone_num +5);
        updateTime(5);
        loadingVaild(4);
    }

    /**
     * Test loading panel's prcoess will increase after all enemy is load 
     */
    @Test
    public void loadingEnemy(){
        updateTime(6);
        loadingVaild(6);
    }

     /**
     * Test loading panel's prcoess keep same if enemy number is less than expect
     */
    @Test
    public void loadLessEnemy(){
        when(mockroomEnvironment.getEnemyNumber()).thenReturn(enemy_num -5);
        updateTime(6);
        loadingVaild(5);
    }

    /**
     * Test loading panel's prcoess will increase after all reward is load 
     */
    @Test
    public void loadReward(){
        updateTime(7);
        loadingVaild(7);
        assertEquals(true, loadingPanel.allResourceLoading());
    }

     /**
     * Test loading panel's prcoess keep same if reward number is more than expect
     */
    @Test
    public void laodingMoreReward(){
        when(mockroomEnvironment.getRewardNumber()).thenReturn(reward_num + 5);
        updateTime(7);
        updateTime(6);
    }

    /**
     * Test loading panel can create time line
     * @throws InterruptedException if the thread sleep is not work
     */
    @Test
    public void loadingProcess() throws InterruptedException{
        loadingPanel.createTimeLine();
        Thread.sleep(500);
        verify(mockpanelController).transformToGameScreen();
    }
    /**
     * Test loading panel can not create time line, if requirement is not meet 
     * @throws InterruptedException if the thread sleep is not work
     */
    @Test
    public void failTocreatTimeLine() throws InterruptedException{
        loadingPanel.init(null);
        loadingPanel.createTimeLine();
        Thread.sleep(500);
        verify(mockpanelController,never()).transformToGameScreen();
    }

    /**
     * Test loading panel can not create time line, if there alreay have thread
     * @throws InterruptedException if the thread sleep is not work
     */
    @Test
    public void notCreatSecondTimeLine() throws InterruptedException{
        loadingPanel.createTimeLine();
        loadingPanel.createTimeLine();
        Thread.sleep(500);
        verify(mockpanelController,times(1)).transformToGameScreen();
    }

    /**
     * Check the how many of process should loading panel have
     * @param expectProcess number of process
     */
    private void loadingVaild(int expectProcess){
        assertEquals(expectProcess, loadingPanel.loadingProcess());
    }

    /**
     * How many update the loading panel should run
     * @param runingTime
     */
    private void updateTime(int runingTime){
        for(int i=0;i < runingTime;i++){
            loadingPanel.update();
        }
    }

    /**
     * Sett variable that need to used
     * @param config the game config will used
     */
    private void getNumber(GameConfig config){
        aviablePos_num = config.areaofRoom();
        wall_num = config.getNumberOfWall();
        tombstone_num = config.getNumberOfObstacles();
        enemy_num = config.getTotalEnemy();
        reward_num = config.getAllRewardNum();
    }

    /**
     * Set the return value of mock object
     */
    private void setUpAllReturnNumber(){
        when(mockrecord.getLengthOfAviable()).thenReturn(aviablePos_num);
        when(mockroomLayout.getTileNumber()).thenReturn(aviablePos_num);
        when(mockroomLayout.getWallNumber()).thenReturn(wall_num);
        when(mockroomEnvironment.getPlayerPosition()).thenReturn(new Position(0, 0));
        when(mockroomLayout.getObstaclesNumber()).thenReturn(tombstone_num);
        when(mockroomEnvironment.getEnemyNumber()).thenReturn(enemy_num);
        when(mockroomEnvironment.getRewardNumber()).thenReturn(reward_num);
    }

}

