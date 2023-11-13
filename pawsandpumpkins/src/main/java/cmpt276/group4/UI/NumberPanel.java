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
 * with a pop up window (Win/ Gameover), typically used for showing game
 * rewards.
 */

public class NumberPanel extends JPanel {
    private static final int NUM_IMAGES = 10; // Number of digit images to display digits 0-9
    private BufferedImage[] digitImages = new BufferedImage[NUM_IMAGES];
    private BufferedImage backgroundImage; // Image for the pop up window
    private BufferedImage greyOut; // Image used for grey out effect
    private int[][] numbersToDisplay; // Array of numbers for each line to display
    private int imageWidth; // Width of each digit image
    private String directory; // Directory of images
    private int[] yAxisLineUp; // Y-axis positions for each line
    private String backgroundImgName; // Filename of the pop up winndow image
    private int xAxisLineUp; // X-axis position for the number
    private int digitXSize, digitYSize; // Size of the digit images
    private double popUpScale; // Scale factor for the pop-up window

    public enum Status {
        Win, GameOver // Game status types
    }

    /**
     * Constructor for NumberPanel.
     * Initializes the panel with default settings and loads number images.
     */
    public NumberPanel() {
        directory = System.getProperty("user.dir");
        loadNumberImages();

        this.imageWidth = 8;
        this.numbersToDisplay = new int[5][];
        digitXSize = 11;
        digitYSize = 11;
        xAxisLineUp = 420;
        yAxisLineUp = new int[] { 348, 365, 378, 412, 444 };
        popUpScale = 0.75;

    }

    /**
     * Initializes the panel with a specific background image based on the game's
     * win status.
     * 
     * @param isWin A boolean indicating if the game was won.
     */
    public void init(boolean isWin) {

        if (isWin) {
            backgroundImgName = "win.png";
        } else {
            backgroundImgName = "game_over.png";
        }

        loadBackgroundImage(backgroundImgName);

    }

    /**
     * Initializes the panel with a specific background image based on the game's
     * status.
     * 
     * @param status The status of the game (Win or Game Over).
     */
    public void init(GameStatus status) {

        if (status == GameStatus.Win) {
            backgroundImgName = "win.png";
        } else {
            backgroundImgName = "game_over.png";
        }

        loadBackgroundImage(backgroundImgName);

    }

    /**
     * Loads the digit images from the specified directory into the digitImages
     * array.
     * This method is responsible for reading the image files corresponding to each
     * digit (0-9) and storing them in an array for later use.
     */
    private void loadNumberImages() {

        for (int i = 0; i < NUM_IMAGES; i++) {
            try {
                digitImages[i] = ImageIO.read(new File(directory + "/res/num/" + i + ".png"));
            } catch (IOException e) {
                e.printStackTrace(); // Or handle the exception as needed
            }
        }
    }

    /**
     * Loads the background image and the grey-out image from the specified
     * directory.
     *
     * @param filename The filename of the background image to be loaded.
     */
    private void loadBackgroundImage(String filename) {
        try {

            backgroundImage = ImageIO.read(new File(directory + "/res/pop_up/" + filename));
            greyOut = ImageIO.read(new File(directory + "/res/pop_up/" + "grey.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the numbers to be displayed on the panel.
     * This method is used to specify the numerical values for each line in the
     * panel.
     *
     * @param totalRewards The total number of rewards.
     * @param regular      The number of regular rewards.
     * @param bonus        The number of bonus rewards.
     * @param punishments  The number of punishments.
     * @param overall      The overall score or number.
     */
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

    /**
     * Draws a line of numbers on the panel.
     * This method takes an array of integers and draws each corresponding image at
     * a specific position.
     *
     * @param g          The Graphics object used for drawing.
     * @param numberLine The array of numbers to be drawn.
     * @param startX     The starting X position for drawing the line.
     * @param startY     The starting Y position for drawing the line.
     */
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

    /**
     * Converts an integer to an array of its digits.
     * This method is used to prepare numerical values for graphical representation.
     *
     * @param number The number to be converted to an array of digits.
     * @return An array of integers representing each digit of the number.
     */
    private int[] intToArray(int number) {
        String numberStr = Integer.toString(number);

        int[] digits = new int[numberStr.length()];

        for (int i = 0; i < numberStr.length(); i++) {
            digits[i] = numberStr.charAt(i) - '0';
        }

        return digits;
    }

}
