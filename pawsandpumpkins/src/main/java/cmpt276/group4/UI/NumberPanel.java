package cmpt276.group4.UI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import cmpt276.group4.GameStatus;
/**
 * The NumberPanel class is responsible for displaying a panel of numbers
 * with a pop up window (Win/ Gameover), typically used for showing game statistics.
 */

public class NumberPanel extends JPanel {
    private static final int NUM_IMAGES = 10;
    private BufferedImage[] digitImages = new BufferedImage[NUM_IMAGES];
    private BufferedImage backgroundImage;
    private BufferedImage greyOut; // Background image
    private int[][] numbersToDisplay; // Array of number lines
    private int imageWidth;
    private int[][] positions; // Positions for each line of numbers
    private String directory;
    private int[] yAxisLineUp;
    private String backgroundImgName;
    private int xAxisLineUp;
    private int digitXSize, digitYSize;
    private double popUpScale;

    public enum Status {
        Win, GameOver
    }

    public NumberPanel() {
        directory = System.getProperty("user.dir");
        loadNumberImages();

        this.imageWidth = 8;
        this.numbersToDisplay = new int[5][]; 
        this.positions = new int[5][2]; 
        digitXSize = 11;
        digitYSize = 11;
        xAxisLineUp = 420;
        yAxisLineUp = new int[] { 335, 351, 365, 397, 427 };
        popUpScale = 0.75;

    }

    // Need to change to gaatestatus
    public void init(boolean isWin) {

        if (isWin) {
            backgroundImgName = "win.png";
        } else {
            backgroundImgName = "game_over.png";
        }

        loadBackgroundImage(backgroundImgName);

        
    }

    public void init(GameStatus status) {

        if (status == GameStatus.Win) {
            backgroundImgName = "win.png";
        } else {
            backgroundImgName = "game_over.png";
        }

        loadBackgroundImage(backgroundImgName);

    }

    private void loadNumberImages() {

        for (int i = 0; i < NUM_IMAGES; i++) {
            try {
                //digitImages[i] = ImageIO.read(new File(directory + "/pawsandpumpkins/res/num/" + i + ".png"));
                digitImages[i] = ImageIO.read(new File(directory + "/res/num/" + i + ".png"));
            } catch (IOException e) {
                e.printStackTrace(); // Or handle the exception as needed
            }
        }
    }

    private void loadBackgroundImage(String filename) {
        try {
            // backgroundImage = ImageIO.read(new File(directory + "/pawsandpumpkins/res/pop_up/" + filename));
            // greyOut = ImageIO.read(new File(directory + "/pawsandpumpkins/res/pop_up/" + "grey.png"));
            backgroundImage = ImageIO.read(new File(directory + "/res/pop_up/" + filename));
            greyOut = ImageIO.read(new File(directory + "/res/pop_up/" + "grey.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setNumbers(int totalRewards, int regular, int bonus, int punishments, int overall) {
        int[] nums = { totalRewards, regular, bonus, punishments, overall };
        for (int i = 0; i < nums.length; i++) {
            numbersToDisplay[i] = intToArray(nums[i]);
        }
        repaint();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            g.drawImage(greyOut, 0, 0, this.getWidth(), this.getHeight(), this);
            g.drawImage(backgroundImage, 85, 90, (int) (this.getWidth() * popUpScale),
                    (int) (this.getHeight() * popUpScale), this);

        }
        // Draw each line of numbers
        for (int i = 0; i < numbersToDisplay.length; i++) {
            if (numbersToDisplay[i] != null) {
                drawNumberLine(g, numbersToDisplay[i], xAxisLineUp, yAxisLineUp[i]);
            }
        }
    }

    private void drawNumberLine(Graphics g, int[] numberLine, int startX, int startY) {
        int x = startX;
        for (int number : numberLine) {
            if (number >= 0 && number < digitImages.length) {
                BufferedImage img = digitImages[number];
                if (img != null) {
                    g.drawImage(img, x, startY, digitXSize, digitYSize, this);
                    x += (imageWidth + 1); // Move to the right for the next digit

                }
            }
        }

    }

    private int[] intToArray(int number) {
        String numberStr = Integer.toString(number);

        int[] digits = new int[numberStr.length()];

        for (int i = 0; i < numberStr.length(); i++) {
            digits[i] = numberStr.charAt(i) - '0';
        }

        return digits;
    }

}
