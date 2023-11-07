package cmpt276.group4.WindowAndInput;

import javax.swing.JPanel;

public class MainPanel extends JPanel {
    private final int original_tileSize = 16;
    private final  int scale = 3;
    public final int tileSize = original_tileSize * scale;

    private final int maxScreenCol = 16;
    private final int maxScreenRow = 16;
    private final int screenWidth = maxScreenCol * tileSize;
    private final int screenHeight = maxScreenRow * tileSize;

    
    
}
