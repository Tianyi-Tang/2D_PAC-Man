package cmpt276.group4.WindowAndInput;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.awt.Font;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import cmpt276.group4.CharacterAvaliablePosition;

import cmpt276.group4.GameManager;

import cmpt276.group4.Enemy.Enemy;
import cmpt276.group4.Enemy.Ghost;
import cmpt276.group4.GameMap.RecordUsedPlace;
import cmpt276.group4.GameMap.RoomEnvironment;
import cmpt276.group4.GameMap.RoomLayout;
import cmpt276.group4.Logic.WindowConfig;
import cmpt276.group4.Player.Player;

import cmpt276.group4.Reward.Reward;
import cmpt276.group4.Room.Door;
import cmpt276.group4.Time.GameTime;

/**
 * GamePanel is a custom JPanel class that serves as the main game area for a
 * tile-based game.
 * It handles the rendering of the game elements, including the player, enemies,
 * rewards, and the game environment.
 * This class is responsible for the game loop and updating the game state.
 * It uses double buffering for smooth rendering.
 *
 * <p>
 * The game area is defined by a grid of tiles with configurable dimensions.
 * The class includes functionality for updating the positions and states of
 * various game entities,
 * such as the player character, enemies, and collectible rewards.
 * It also keeps track of and displays the player's score.
 * </p>
 *
 * <p>
 * Instances of this class should be created to initialize and display the main
 * game area.
 * The class also includes methods for starting the game thread and updating the
 * game elements.
 * </p>
 * </pre>
 *
 */
public class GamePanel extends JPanel implements Runnable {
    // Screen Sitting
    private RoomLayout roomLayout;
    private RoomEnvironment roomEnvironment =  RoomEnvironment.getInstance();

    

    // Define the desired width and height for the pause button
    private static final int PAUSE_BUTTON_WIDTH = 50; // example width
    private static final int PAUSE_BUTTON_HEIGHT = 50; // example height

    final int FPS = 60;
    private double timeInterval = 1000000000 / FPS;
    private int enemyMoveCounter = 0;
    // Change the enemy's position every 30 frames (every half second at 60 FPS)
    private final int ENEMY_MOVE_INTERVAL = 30;
    private Player player;
    private Thread gameThread;
    private GameTime gameTime;
    private BufferedImage pauseButtonImage;

    private Door[] doors;

    /**
     * constructor for GamePanel to build up defualt seting
     */
    public GamePanel() {
        this.setPreferredSize(new Dimension(WindowConfig.screenWidth, WindowConfig.screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        loadPauseButtonImage();
    }

    public void setKeySingleton(RoomLayout roomLayout, RoomEnvironment roomEnvironment){
        this.roomLayout = roomLayout;
        this.roomEnvironment = roomEnvironment;
    }

    /**
     * Create the game loop for GamePanel
     */
    public void createTimeLine() {
        if (gameThread == null) {
            gameThread = new Thread(this);
            gameThread.start();
        }
    }

    /**
     * Set the player in the GamePanel
     * @param player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setDoors(Door[] doors){
        this.doors = doors;
    }

    /**
     * The main game loop. This method is run continuously by the game thread and
     * controls the timing of game updates and rendering.
     */
    @Override
    public void run() {
        double iteration = 0;
        double last_time = System.nanoTime();
        double currentTime;
        gameTime = GameTime.getInstance();
        while (gameThread != null) {
            currentTime = System.nanoTime();
            iteration += (currentTime - last_time) / timeInterval;
            last_time = currentTime;
            if (iteration >= 1) {
                update();
                repaint();
                iteration -= 1;
            }
        }
    }

    /**
     * Updates the state of the game. This method is called at each iteration of the
     * game loop and is responsible for
     * updating the positions and states of all game elements, including the player,
     * enemies, and rewards.
     * It also manages the timing of enemy movements and game time counting.
     */
    private void update() {
        player.update();

        if(doors != null){
            for (Door door : doors) {
                door.playerLeaveRoom();
            }
        }

        for (Enemy enemy : roomEnvironment.getEnemies()) {
            enemy.catchPlayer();            
        }
        
        if (enemyMoveCounter >= ENEMY_MOVE_INTERVAL) {
            for (Enemy enemy : roomEnvironment.getEnemies()) {
                enemy.action();
            }
            enemyMoveCounter = 0;
        } else {
            enemyMoveCounter++;
        }
        gameTime.countTime();
    }

    /**
     * Renders the game elements onto the panel. This method is responsible for
     * drawing all the visual components of the game,
     * including the player, enemies, rewards, and other game environment elements.
     * It is called every time the panel needs to be repainted.
     *
     * @param g The Graphics object used for drawing.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        synchronized (roomLayout.getElements()) {
            for (CharacterAvaliablePosition element : roomLayout.getElements()) {
                element.draw(g2);
            }
        }

        for (Reward reward : roomEnvironment.getRewards()) {
            reward.draw(g2);

        }

        if (player != null) {
            player.draw(g2); // Existing player drawing code

            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.BOLD, 20));
            int score = player.totalScore();
            g2.drawString("Score: " + score, 10, 30);
        }
        for (Enemy enemy : roomEnvironment.getEnemies()) {

            enemy.draw(g2);
        }
        if (pauseButtonImage != null) {
            int buttonX = WindowConfig.screenWidth - PAUSE_BUTTON_WIDTH - 10;
            int buttonY = 10;

            g.drawImage(pauseButtonImage, buttonX, buttonY, PAUSE_BUTTON_WIDTH, PAUSE_BUTTON_HEIGHT, this);

        }
        g2.dispose();
    }

    public void endGameLoop(){
        if(GameManager.getInstance().isGameEnd()){
            gameThread = null;
        }
    }

    // /**
    //  * Restarts the game by stopping the current game thread. This method can be
    //  * used to reset the game state
    //  * and prepare the panel for a new game session.
    //  */
    // public void restartGamePanel() {
    //     gameThread = null;
    // }

    private void loadPauseButtonImage() {
        try {
            String directory = System.getProperty("user.dir");
            pauseButtonImage = ImageIO.read(new File(directory + "/res/pop_up/pause_button.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
