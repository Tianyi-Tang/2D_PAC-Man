package cmpt276.group4.WindowAndInput;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import cmpt276.group4.CharacterAvaliablePosition;
import cmpt276.group4.GameTime;
import cmpt276.group4.RecordUsedPlace;
import cmpt276.group4.Enemy.Enemy;
import cmpt276.group4.Enemy.Ghost;
import cmpt276.group4.Player.Player;

import cmpt276.group4.Reward.Reward;
import cmpt276.group4.UI.NumberPanel;
import cmpt276.group4.Reward.MangeBonusReward;


public class GamePanel extends JPanel implements Runnable {

    RecordUsedPlace record = RecordUsedPlace.getInstance();
    private NumberPanel numberPanel;

    //Screen Sitting 
    private static final int original_tileSize = 16;
    private static final int scale = 3;
    public static final int tileSize = original_tileSize * scale;

    public static final int maxScreenCol = 16;
    public static final int maxScreenRow = 16;
    public static final int screenWidth = maxScreenCol * tileSize;
    public static final int screenHeight = maxScreenRow * tileSize;

    final int FPS = 60;
    private double timeInterval = 1000000000 / FPS;
    private int enemyMoveCounter = 0;
    // Change the enemy's position every 30 frames (every half second at 60 FPS)
    private final int ENEMY_MOVE_INTERVAL = 30;
    private Player player;

    private Thread gameThread;
    private GameTime gameTime;

    private MangeBonusReward manageBonusReward;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

    }


    public void createTimeLine() {
        if (gameThread == null) {
            gameThread = new Thread(this);
            gameThread.start();
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
    }




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

    private void update() {
        Player.getInstance().update();

        if (enemyMoveCounter >= ENEMY_MOVE_INTERVAL) {
            for (Enemy enemy : record.getEnemyList()) {
                if (enemy instanceof Ghost) {
                    ((Ghost) enemy).ghostMoveNextPosition();
                }
            }
            enemyMoveCounter = 0;
        } else {
            enemyMoveCounter++;
        }
        gameTime.countTime();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        record.printTileAndObstacleCounts();
        Graphics2D g2 = (Graphics2D) g;
        System.out.println("avaliable "+record.getElemet().size());
        for (CharacterAvaliablePosition element : record.getElemet()) {
            element.draw(g2);
        }
        //System.out.println("avaliable "+record.getElemet().size());

        for (Reward reward : record.getRewardList()) {
            reward.draw(g2);

        }



    //Position wallPosition1 = new Position(5, 5);
    //Wall wall1 = new Wall(wallPosition1, 1);
    //RecordUsedPlace.getInstance().addWall(wall1);

    //Position wallPosition2 = new Position(10*tileSize, 10*tileSize);

    // for (Obstacle obstacle : record.getObstacles()) {
    // Create a new Wall object using the wall position
    //obstacle.draw(g2);
    // Draw the wall

    // }



        if(player != null)
            player.draw(g2);

        for (Enemy enemy : record.getEnemyList()) {
             //System.out.println("Enemy List size: "+ record.getEnemyList().size());

                enemy.draw(g2);
        }

        


        g2.dispose();
    }

}
