package cmpt276.group4.WindowAndInput;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class MainPanel extends JPanel implements Runnable {
    
    private final int screenWidth = 48 * 16;
    private final int screenHeight = 48 * 16;

    private Thread main_thread;



    public MainPanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

    public void createTimeLine(){
        if(main_thread == null){
            main_thread = new Thread(this);
            main_thread.start();
        }
    }

    @Override
    public void run() {

        while (main_thread != null) {
            
        }
        
    }
    
}
