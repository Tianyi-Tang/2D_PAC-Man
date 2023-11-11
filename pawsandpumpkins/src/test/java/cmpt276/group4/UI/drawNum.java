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

        NumberPanel firstline = new NumberPanel();
        NumberPanel numberPanel = new NumberPanel();
        numberPanel.setNumbers(0, new int[]{1, 2, 3, 4}, size/2+85, 329, 10,10); // First line
        numberPanel.setNumbers(1, 5678,  size/2+85, 352,10,10); // Second line
        numberPanel.setNumbers(2, 69691, size/2+85, 372,10,10); // Second line

                numberPanel.setNumbers(3, 5673, size/2+85, 410,10,10); // Second line

                        numberPanel.setNumbers(4, 65892, size/2+85, 450,10,10); // Second line

        // firstline.setNumber(7485); // Example number
        // firstline.setPositionAndSize(10, 10, 40, 40); // Example position and size

        frame.add(numberPanel);
        frame.setVisible(true);
        System.out.println("DONE");
    }
}
