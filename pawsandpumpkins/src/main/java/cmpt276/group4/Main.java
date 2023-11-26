package cmpt276.group4;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

import cmpt276.group4.WindowAndInput.PanelController;

public class Main {
    public static void main( String[] args )
    {
        PanelController controller = PanelController.getInstance();
        controller.createMainWindow();


    }
}
