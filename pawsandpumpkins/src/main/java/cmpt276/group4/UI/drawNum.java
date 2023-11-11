package cmpt276.group4.UI;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class drawNum {
    private static final int NUM_IMAGES = 10; // Assuming you have images for numbers 0 to 9
    private BufferedImage[] numberImages = new BufferedImage[NUM_IMAGES];

    public void loadNumberImages() {
        for (int i = 0; i < NUM_IMAGES; i++) {
            try {
                numberImages[i] = ImageIO.read(new File("res/num/" + i + ".png"));
            } catch (IOException e) {
                e.printStackTrace(); // Or handle the exception as needed
            }
        }
    }

    public BufferedImage getNumberImage(int number) {
        if (number >= 0 && number < NUM_IMAGES) {
            return numberImages[number];
        }
        return null; // Or handle invalid number
    }

    
}
