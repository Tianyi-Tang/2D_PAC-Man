package cmpt276.group4.WindowAndInput;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MainPanel extends JPanel implements Runnable {
    
    private final int screenWidth = 48 * 16;
    private final int screenHeight = 48 * 16;

    private Thread mainThread;
    private JButton startButton;



    public MainPanel(){
        startButton = new JButton();
        startButton.setText("Start");
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Has been click");
                super.mouseClicked(e);
            }
        });
        startButton.setBounds(2* GamePanel.tileSize,3* GamePanel.tileSize,GamePanel.tileSize,GamePanel.tileSize);
        this.setLayout(null);

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

        this.add(startButton);
    }

    public void createTimeLine(){
        if(mainThread == null){
            mainThread = new Thread(this);
            mainThread.start();
        }
    }

    @Override
    public void run() {

        while (mainThread != null) {
            
        }
        
    }
    
}
