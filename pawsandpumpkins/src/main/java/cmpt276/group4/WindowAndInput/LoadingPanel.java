package cmpt276.group4.WindowAndInput;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class LoadingPanel extends JPanel {

    private final int screenWidth = 48 * 16;
    private final int screenHeight = 48 * 16;

    public LoadingPanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }


    
}
