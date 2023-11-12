package cmpt276.group4.UI;

import javax.swing.JFrame;

import cmpt276.group4.GameStatus;

import java.awt.Color;
import java.awt.Dimension;

public class NumberPanelTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Number Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int size = 16 * 48; // Size for a square window
        frame.setSize(new Dimension(size, size)); // Set the frame to be a square
        frame.getContentPane().setBackground(Color.BLACK); // Set background color to black

        
        NumberPanel numberPanel = new NumberPanel();
        numberPanel.init(GameStatus.GameOver);
        numberPanel.setNumbers(123,3435,12412,12542,23432);

      

        frame.add(numberPanel);
        frame.setVisible(true);

    }
}
