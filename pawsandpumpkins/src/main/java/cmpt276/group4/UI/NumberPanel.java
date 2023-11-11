package cmpt276.group4.UI;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Dimension;

public class NumberPanel extends JPanel {
    private static final int NUM_IMAGES = 10; // Assuming you have images for numbers 0 to 9
    private BufferedImage[] digitImages = new BufferedImage[NUM_IMAGES];
    private int numberToDisplay;
    private int imageWidth, imageHeight;
    private int startX, startY;
    String directory;

    public NumberPanel() {
        loadNumberImages(); // Load images when an instance is created
        this.imageWidth = 8; // default width
        this.imageHeight = 8; // default height
        this.startX = 50; // default start X
        this.startY = 50; // default start Y
    }

    private void loadNumberImages() {
        directory = System.getProperty("user.dir");
        for (int i = 0; i < NUM_IMAGES; i++) {
            try {
                System.out.println(directory + "\\pawsandpumpkins\\res\\n" + //
                        "um" + i + ".png");
                // digitImages[i] = ImageIO.read(new File(directory+"/pawsandpumpkins/res/num/"
                // + "one.png"));

                // digitImages[i] = ImageIO.read(new File(directory+"/res/num/" + i + ".png"));
                digitImages[i] = ImageIO.read(new File(directory + "/pawsandpumpkins/res/num/" + i + ".png"));
            } catch (IOException e) {
                e.printStackTrace(); // Or handle the exception as needed
            }
        }
    }

    public void initialize() {
        loadNumberImages();
    }

    public void setNumber(int number) {
        this.numberToDisplay = number;
        repaint(); // Request the panel to be repainted with the new number
    }

    public void setPositionAndSize(int startX, int startY, int width, int height) {
        this.startX = startX;
        this.startY = startY;
        this.imageWidth = width;
        this.imageHeight = height;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawNumber(g, numberToDisplay);
    }

    // private void drawNumber(Graphics g, int number) {
    // String numberStr = String.valueOf(number);
    // int x = startX;

    // for (char c : numberStr.toCharArray()) {
    // int digit = Character.getNumericValue(c);
    // if (digit >= 0 && digit < digitImages.length) {
    // g.drawImage(digitImages[digit], x, startY, imageWidth, imageHeight, this);
    // x += imageWidth; // Move to the right for the next digit
    // }
    // }
    // }
    // private void drawNumber(Graphics g, int number) {
    // System.out.println("in drae number: "+ number);
    // String numberStr = String.valueOf(number);
    // int x = startX;

    // for (char c : numberStr.toCharArray()) {
    // int digit = Character.getNumericValue(c);
    // System.out.println("for loop");
    // if (digit >= 0 && digit < digitImages.length) {
    // System.out.println("first if loop");
    // BufferedImage img = digitImages[digit];
    // System.out.println("truing to read digit img: "+ digit);
    // if (img != null) {
    // System.out.println("there's image read");
    // // Print the size of each image for debugging
    // System.out.println("Digit: " + digit + ", Width: " + img.getWidth() + ",
    // Height: " + img.getHeight());

    // g.drawImage(img, x, startY, imageWidth, imageHeight, this);
    // x += imageWidth; // Move to the right for the next digit
    // }
    // }
    // }
    // }
    private void drawNumber(Graphics g, int number) {
        String numberStr = String.valueOf(number);
        int x = startX;

        for (char c : numberStr.toCharArray()) {
            int digit = Character.getNumericValue(c);
            if (digit >= 0 && digit < digitImages.length) {
                BufferedImage img = digitImages[digit];
                if (img != null) {
                    // int drawWidth = img.getWidth(); // Use natural image width
                    // int drawHeight = img.getHeight(); // Use natural image height
                    int drawWidth = 60; // Use natural image width
                    int drawHeight = 60; // Use natural image height

                    g.drawImage(img, x, startY, drawWidth, drawHeight, this);
                    x += drawWidth; // Move right by the width of the image
                }
            }
        }
    }

}
