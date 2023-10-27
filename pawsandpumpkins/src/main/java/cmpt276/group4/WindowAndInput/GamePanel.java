package cmpt276.group4.WindowAndInput;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


public class GamePanel extends JPanel implements Runnable {
    
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

    private Thread gameThread;
    private keyboardListener listener = new keyboardListener();

    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(listener);
        this.setFocusable(true);
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

    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
    }

}
