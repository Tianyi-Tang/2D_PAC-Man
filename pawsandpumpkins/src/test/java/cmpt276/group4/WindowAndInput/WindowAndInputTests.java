package cmpt276.group4.WindowAndInput;


import javax.swing.JFrame;

public class WindowAndInputTests {
    
    public static void main(String[] args) {
        // Create a JFrame instance
        JFrame frame = new JFrame("Test Keyboard Listener");
        
        // Set the size of the frame
        frame.setSize(400, 400);
        
        // Set default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create an instance of keyboardListener and add it to the frame
        frame.addKeyListener(new keyboardListener());
        
        // Make the frame visible
        frame.setVisible(true);
    }
}
