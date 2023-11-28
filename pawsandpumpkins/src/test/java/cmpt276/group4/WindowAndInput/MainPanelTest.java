package cmpt276.group4.WindowAndInput;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


import java.awt.Component;
import java.awt.Container;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cmpt276.group4.gameLevel;

public class MainPanelTest {
    public MainPanel panel;
    public PanelController mockcontroller;
    public ActionEvent click;

    @BeforeEach
    public void setUp(){
        panel = new MainPanel();
        mockcontroller = mock(PanelController.class);
        panel.init(mockcontroller);
    }

    @Test
    public void numberOfButton(){
        ArrayList<JButton> buttons = findButton(panel);
        JButton button = buttons.get(0);
        button.doClick();
        assertEquals(true,button.isVisible());
        
    }

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
