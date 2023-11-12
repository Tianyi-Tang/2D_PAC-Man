package cmpt276.group4.WindowAndInput;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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
    private BufferedImage mainPanel_img;


    public MainPanel(){
        loadingImage();
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

    private void loadingImage(){
        try {
            mainPanel_img = ImageIO.read(new File("res/pop_up/game_welcome_page2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(mainPanel_img != null)
            g.drawImage(mainPanel_img,0,0,GamePanel.screenWidth,GamePanel.screenHeight,this);
    }

    private void VisibleDifficultButton(){
        easyLevelButton.setVisible(true);
        middleLevelButton.setVisible(true);
        hardLevelButton.setVisible(true);
    }
    
}
