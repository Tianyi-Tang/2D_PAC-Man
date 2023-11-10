package cmpt276.group4.WindowAndInput;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JProgressBar;

import cmpt276.group4.GameManager;
import cmpt276.group4.RecordUsedPlace;
import cmpt276.group4.Logic.GameConfig;

public class LoadingPanel extends JPanel implements Runnable {

    private final int screenWidth = 48 * 16;
    private final int screenHeight = 48 * 16;

    private Thread loadingThread;

    final int FPS = 60;
    private double timeInterval = 1000000000/FPS;
    private JProgressBar progressBar;


    private RecordUsedPlace record;
    private boolean generateRoom, generateAllTile, generateAllObstacle,generateAllEnemies, generateAllRewards, generatePlayer =false;

    private GameConfig config;



    public LoadingPanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setLayout(null);
        
        progressBar = new JProgressBar(0,6);
        progressBar.setBounds(50, 256, 512, 30);
        progressBar.setStringPainted(true);
        progressBar.setOpaque(true);
        progressBar.setValue(0);
        progressBar.setVisible(true);

        this.add(progressBar);

    }

    public void createTimeLine(){
        if(loadingThread == null){
            loadingThread = new Thread(this);
            loadingThread.start();
            record = RecordUsedPlace.getInstance();
        }
    }

    @Override
    public void run() {
        double iteration = 0;
        double last_time = System.nanoTime();
        double currentTime;

        //call the gameManager to generate room

        
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
        initialLoading();
    }

    private void update(){
        if(!generateRoom){
            checkRoom();
        }
        else if(!generateAllTile){
            checkTitle();
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

    private void checkRoom(){
        if(GameManager.getInstance().roomAlreadyGenerate()){
            generateRoom = true;
            // call to generate title
        }
    }

    private void checkTitle(){
        if(record.getTileNumber() == config.numberofTiles()){
            generateAllTile = true;
            // call to generate Obstacle
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    private boolean allResourceLoading(){
        if(!generatePlayer)
            return false;
        else
            return true; 
    }

    private void initialLoading(){
        generateRoom = generateAllTile = generateAllObstacle =generateAllEnemies = generateAllRewards = generatePlayer =false;
        loadingThread = null;
    }


    
}
