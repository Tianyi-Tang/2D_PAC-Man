package cmpt276.group4.WindowAndInput;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import cmpt276.group4.Logic.GameConfig;

public class LoadingPanel extends JPanel implements Runnable {

    private final int screenWidth = 48 * 16;
    private final int screenHeight = 48 * 16;

    private Thread loadingThread;

    final int FPS = 60;
    private double timeInterval = 1000000000/FPS;

    private boolean generateRoom, generateAllTile, generateAllObstacle,generateAllEnemies, generateAllRewards, generatePlayer =false;
    private GameConfig config;

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
                if(allResourceLoading())
                    break;
                update();
                repaint();
                iteration -= 1;
            }
        }
    }

    private void update(){
        if(!generateRoom){

        }
        else if(!generateAllTile){

        }
        else if(!generateAllObstacle){

        }
        else if(!generateAllEnemies){

        }
        else if(!generateAllRewards){

        }
        else{

        }
    }

    private void checkTitle(){
    }

    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);
    }

    private boolean allResourceLoading(){
        if(generatePlayer)
            return false;
        else 
            return true;
    }


    
}
