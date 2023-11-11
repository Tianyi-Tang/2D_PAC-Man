package cmpt276.group4.WindowAndInput;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import cmpt276.group4.GameManager;
import cmpt276.group4.gameLevel;

public class MainPanel extends JPanel implements Runnable {
    
    private final int screenWidth = 48 * 16;
    private final int screenHeight = 48 * 16;

    private Thread mainThread;
    private JButton startButton;
    private JButton easyLevelButton;
    private JButton middleLevelButton;
    private JButton hardLevelButton;


    public MainPanel(){
        startButton = new JButton();
        startButton.setText("Start Game");
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                VisibleDifficultButton();
                startButton.setVisible(false);
                super.mouseClicked(e);

            }
        });
        startButton.setBounds(7* GamePanel.tileSize,7* GamePanel.tileSize,GamePanel.tileSize *2,GamePanel.tileSize);
        this.setLayout(null);

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

        this.add(startButton);
        createButton();
    }

    private void createButton(){
        easyLevelButton = new JButton();
        easyLevelButton.setText("Easy");
        easyLevelButton.setVisible(false);
        easyLevelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GameManager.getInstance().transformToLoadingScreen(gameLevel.BASIC);
                super.mouseClicked(e);
            }
        });

        middleLevelButton = new JButton();
        middleLevelButton.setText("Middle");
        middleLevelButton.setVisible(false);
        middleLevelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GameManager.getInstance().transformToLoadingScreen(gameLevel.MEDIUM);
                super.mouseClicked(e);
            }
        });

        hardLevelButton = new JButton();
        hardLevelButton.setText("Hard");
        hardLevelButton.setVisible(false);
        hardLevelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 GameManager.getInstance().transformToLoadingScreen(gameLevel.HARD);
                super.mouseClicked(e);
            }
        });

        easyLevelButton.setBounds(7* GamePanel.tileSize,7* GamePanel.tileSize,GamePanel.tileSize *2,GamePanel.tileSize);
        middleLevelButton.setBounds(7* GamePanel.tileSize, 8* GamePanel.tileSize , GamePanel.tileSize *2, GamePanel.tileSize);
        hardLevelButton.setBounds(7* GamePanel.tileSize, 9* GamePanel.tileSize, GamePanel.tileSize *2, GamePanel.tileSize);

        this.add(easyLevelButton);
        this.add(middleLevelButton);
        this.add(hardLevelButton);
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

    private void VisibleDifficultButton(){
        easyLevelButton.setVisible(true);
        middleLevelButton.setVisible(true);
        hardLevelButton.setVisible(true);
    }
    
}
