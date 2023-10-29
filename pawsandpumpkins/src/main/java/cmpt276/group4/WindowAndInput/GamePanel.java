package cmpt276.group4.WindowAndInput;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;


import javax.swing.JPanel;

import cmpt276.group4.RecordUsedPlace;
import cmpt276.group4.Enemy.Enemy;
import cmpt276.group4.Enemy.Ghost;
import cmpt276.group4.Player.Player;


public class GamePanel extends JPanel implements Runnable {

    RecordUsedPlace record = RecordUsedPlace.getInstance();
    
    //Screen Sitting 
    final int original_tileSize = 16;
    final int scale = 3;
    final int tileSize = original_tileSize * scale;

    final int maxScreenCol = 16;
    final int maxScreenRow = 16;
    final int screenWidth = maxScreenCol * tileSize;
    final int screenHeight = maxScreenRow * tileSize;

    final int FPS = 60;
    private double timeInterval = 1000000000/FPS;
    private int enemyMoveCounter = 0;
    // Change the enemy's position every 30 frames (every half second at 60 FPS)
    private final int ENEMY_MOVE_INTERVAL = 30; 
    

    private Thread gameThread;

    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

    public void createTimeLine(){
        if(gameThread == null){
            gameThread = new Thread(this);
            gameThread.start();
        }
    }

    @Override
    public void run() {
        double iteration = 0;
        double last_time = System.nanoTime();
        double currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            iteration += (currentTime - last_time) / timeInterval;
            last_time = currentTime;

            if(iteration >=1){
                update();
                repaint();
                iteration -= 1;
            }
        }
    }

    public void update(){
        Player.getInstance().update();
        if (enemyMoveCounter >= ENEMY_MOVE_INTERVAL) {
            for (Enemy enemy : record.getEnemyList()) {
                if (enemy instanceof Ghost) {
                    ((Ghost)enemy).ghostMoveNextPosition();
                }
            }
            enemyMoveCounter = 0; // Reset the counter
        } else {
            enemyMoveCounter++;
        }
        
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        
        Player.getInstance().draw(g2);
        

        for (Enemy enemy : record.getEnemyList()) {
        enemy.draw(g2);
        }
        testDrawObstacle(g2);

        g2.dispose();
        

    }

    private void testDrawObstacle(Graphics2D g2){
        g2.setColor(Color.white);
        g2.fillRect(48, 96, 48, 48);
    }

}
