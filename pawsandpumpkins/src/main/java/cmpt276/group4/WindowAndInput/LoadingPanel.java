package cmpt276.group4.WindowAndInput;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class LoadingPanel extends JPanel implements Runnable {

    private final int screenWidth = 48 * 16;
    private final int screenHeight = 48 * 16;

    private Thread loadingThread;

    final int FPS = 60;
    private double timeInterval = 1000000000/FPS;

    public LoadingPanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

    public void createTimeLine(){
        if(loadingThread == null){
            loadingThread = new Thread(this);
            loadingThread.start();
        }
    }

    @Override
    public void run() {
        double iteration = 0;
        double last_time = System.nanoTime();
        double currentTime;

        while (loadingThread != null) {
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

    private void update(){

    }

    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);
    }


    
}
