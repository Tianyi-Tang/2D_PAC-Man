package cmpt276.group4.WindowAndInput;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;


import java.awt.Component;
import java.awt.Container;


import java.util.ArrayList;

import javax.swing.JButton;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Class for unit test MainPanel 
 */
public class MainPanelTest {
    public MainPanel panel;
    public PanelController mockcontroller;

    public ArrayList<JButton> buttons;

    /*
     * set up the main panel and get button in the pnael
     */
    @BeforeEach
    public void setUp(){
        panel = new MainPanel();
        mockcontroller = mock(PanelController.class);
        panel.init(mockcontroller);
        buttons = findButton(panel);
    }

    /**
     * Test the start button will invisiable after you click it 
     */
    @Test
    public void clickStartButton(){
        JButton button = buttons.get(0);
        panel.clickStartButton();
        assertEquals(false,button.isVisible());
    }

    /**
     * Test before click start button the difficult button is invisible 
     * and after click the start button the button appear
     */
    @Test
    public void easyLevelButtonAppear(){
        JButton button = buttons.get(1);
        assertEquals(false, button.isVisible());
        panel.clickStartButton();
        assertEquals(true, button.isVisible());
    }


    /**
     * Get array of button that in the panel
     * @param container the Panel you have 
     * @return array of button that in the panel
     */
    private  ArrayList<JButton> findButton(Container container){
        ArrayList<JButton> buttons = new ArrayList<JButton>();
        Component[] components = container.getComponents();
        for (Component component : components) {
            if(component instanceof JButton)
                buttons.add((JButton) component);
        }
        return buttons;
    }

}
