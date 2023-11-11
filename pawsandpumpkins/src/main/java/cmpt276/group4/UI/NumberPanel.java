package cmpt276.group4.UI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class NumberPanel extends JPanel {
    private static final int NUM_IMAGES = 10;
    private BufferedImage[] digitImages = new BufferedImage[NUM_IMAGES];
    private BufferedImage backgroundImage; // Background image
    private int[][] numbersToDisplay; // Array of number lines
    private int imageWidth, imageHeight;
    private int[][] positions; // Positions for each line of numbers
    private String directory;

    public NumberPanel() {
        directory = System.getProperty("user.dir");
        //loadNumberImages();
        //loadBackgroundImage("path/to/your/background/image.png"); // Adjust path
        this.imageWidth = 8; // default width
        this.imageHeight = 8; // default height
        this.numbersToDisplay = new int[5][]; // Four lines of numbers
        this.positions = new int[5][2]; // Positions for each line
    }

    private void loadNumberImages() {
        
        for (int i = 0; i < NUM_IMAGES; i++) {
            try {
                System.out.println(directory + "\\pawsandpumpkins\\res\\n" + //
                        "um" + i + ".png");


                // digitImages[i] = ImageIO.read(new File(directory+"/res/num/" + i + ".png"));
                digitImages[i] = ImageIO.read(new File(directory + "/pawsandpumpkins/res/num/" + i + ".png"));
            } catch (IOException e) {
                e.printStackTrace(); // Or handle the exception as needed
            }
        }
    }

    private void loadBackgroundImage(String path) {
        try {
            //backgroundImage = ImageIO.read(new File(directory + "/pawsandpumpkins/res/pop_up/" + "game_welcome_page2.png"));
            backgroundImage = ImageIO.read(new File(directory + "/pawsandpumpkins/res/pop_up/" + "win.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setNumbers(int lineNumber, int[] numbers, int startX, int startY, int imageWidth, int imageHeight) {
        if (lineNumber >= 0 && lineNumber < numbersToDisplay.length) {
            numbersToDisplay[lineNumber] = numbers;
            positions[lineNumber][0] = startX;
            positions[lineNumber][1] = startY;
            this.imageHeight = imageHeight;
            this.imageWidth = imageWidth;
        }
        repaint();
    }
    public void setNumbers(int lineNumber, int numbers, int startX, int startY, int imageWidth, int imageHeight) {
        if (lineNumber >= 0 && lineNumber < numbersToDisplay.length) {
            numbersToDisplay[lineNumber] = intToArray(numbers);
            positions[lineNumber][0] = startX;
            positions[lineNumber][1] = startY;
            this.imageHeight = imageHeight;
            this.imageWidth = imageWidth;
        }
        repaint();
    }

    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw background image
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
        // Draw each line of numbers
        for (int i = 0; i < numbersToDisplay.length; i++) {
            if (numbersToDisplay[i] != null) {
                drawNumberLine(g, numbersToDisplay[i], positions[i][0], positions[i][1]);
            }
        }
    }

    private void drawNumberLine(Graphics g, int[] numberLine, int startX, int startY) {
        int x = startX;
        for (int number : numberLine) {
            if (number >= 0 && number < digitImages.length) {
                BufferedImage img = digitImages[number];
                if (img != null) {
                    g.drawImage(img, x, startY, imageWidth, imageHeight, this);
                    x += imageWidth; // Move to the right for the next digit
                }
            }
        }
    }
    private int[] intToArray(int number) {
        // Convert the number to a String to easily access individual digits
        String numberStr = Integer.toString(number);
        
        // Create an array of the same length as the number of digits
        int[] digits = new int[numberStr.length()];
        
        // Fill the array with the individual digits
        for (int i = 0; i < numberStr.length(); i++) {
            // Subtract '0' to convert char to int (ASCII manipulation)
            digits[i] = numberStr.charAt(i) - '0';
        }
        
        return digits;
    }
}
