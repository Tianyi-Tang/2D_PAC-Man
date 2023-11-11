package cmpt276.group4.UI;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Dimension;

public class drawNum {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Number Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int size = 16*48; // Size for a square window
        frame.setSize(new Dimension(size, size)); // Set the frame to be a square
        frame.getContentPane().setBackground(Color.BLACK); // Set background color to black

        NumberPanel numberPanel = new NumberPanel();
        //numberPanel.initialize();
        numberPanel.setNumber(7485); // Example number
        numberPanel.setPositionAndSize(10, 10, 100, 100); // Example position and size

        frame.add(numberPanel);
        frame.setVisible(true);
        System.out.println("DONE");
    }
}
