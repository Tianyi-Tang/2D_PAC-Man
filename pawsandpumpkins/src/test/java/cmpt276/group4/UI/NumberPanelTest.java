package cmpt276.group4.UI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cmpt276.group4.GameStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.awt.Graphics;

import javax.swing.JFrame;

class NumberPanelTest {

    private NumberPanel numberPanel;

    @BeforeEach
    void setUp() {
        numberPanel = new NumberPanel();

    }

    @Test
    void testSetNumbersWithPositiveValues() {
        numberPanel.init(true);
        numberPanel.setNumbers(123, 45, 6, 78, 910);

        int[][] numbersToDisplay = getPrivateFieldNumbersToDisplay();

        assertArrayEquals(new int[] { 1, 2, 3 }, numbersToDisplay[0], "Total rewards digits should match");
        assertArrayEquals(new int[] { 4, 5 }, numbersToDisplay[1], "Regular rewards digits should match");
        assertArrayEquals(new int[] { 6 }, numbersToDisplay[2], "Bonus rewards digits should match");
        assertArrayEquals(new int[] { 7, 8 }, numbersToDisplay[3], "Punishments digits should match");
        assertArrayEquals(new int[] { 9, 1, 0 }, numbersToDisplay[4], "Overall score digits should match");
    }

    @Test
    void testSetNumbersWith6PositiveValues() {
        numberPanel.init(true);
        numberPanel.setNumbers(123, 45, 6, 78, 910, 1000);

        int[][] numbersToDisplay = getPrivateFieldNumbersToDisplay();

        assertArrayEquals(new int[] { 1, 2, 3 }, numbersToDisplay[0], "Total rewards digits should match");
        assertArrayEquals(new int[] { 4, 5 }, numbersToDisplay[1], "Regular rewards digits should match");
        assertArrayEquals(new int[] { 6 }, numbersToDisplay[2], "Bonus rewards digits should match");
        assertArrayEquals(new int[] { 7, 8 }, numbersToDisplay[3], "Punishments digits should match");
        assertArrayEquals(new int[] { 9, 1, 0 }, numbersToDisplay[4], "Overall score digits should match");
        assertArrayEquals(new int[] { 1, 0, 0, 0 }, numbersToDisplay[5], "Time should match");

    }

    @Test
    void testSetNumbersWithNegativeValues() {
        numberPanel.init(false);
        numberPanel.setNumbers(-123, -45, -6, -78, -910);

        int[][] numbersToDisplay = getPrivateFieldNumbersToDisplay();

        assertArrayEquals(new int[] { 10, 1, 2, 3 }, numbersToDisplay[0], "Negative total rewards digits should match");
        assertArrayEquals(new int[] { 10, 4, 5 }, numbersToDisplay[1], "Negative regular rewards digits should match");
        assertArrayEquals(new int[] { 10, 6 }, numbersToDisplay[2], "Negative bonus rewards digits should match");
        assertArrayEquals(new int[] { 10, 7, 8 }, numbersToDisplay[3], "Negative punishments digits should match");
        assertArrayEquals(new int[] { 10, 9, 1, 0 }, numbersToDisplay[4], "Negative overall score digits should match");
    }

    @Test
    void testSetNumbersWithZero() {
        numberPanel.init(true);
        numberPanel.setNumbers(0, 0, 0, 0, 0);

        int[][] numbersToDisplay = getPrivateFieldNumbersToDisplay();

        assertArrayEquals(new int[] { 0 }, numbersToDisplay[0], "Zero total rewards digits should match");
        assertArrayEquals(new int[] { 0 }, numbersToDisplay[1], "Zero regular rewards digits should match");
        assertArrayEquals(new int[] { 0 }, numbersToDisplay[2], "Zero bonus rewards digits should match");
        assertArrayEquals(new int[] { 0 }, numbersToDisplay[3], "Zero punishments digits should match");
        assertArrayEquals(new int[] { 0 }, numbersToDisplay[4], "Zero overall score digits should match");
    }

    @Test
    void testPaintComponent() {
        NumberPanel numberPanel = new NumberPanel();
        numberPanel.init(true);
        numberPanel.setNumbers(123, 45, 6, 78, 910);

        JFrame frame = new JFrame();
        frame.add(numberPanel);
        frame.setSize(400, 400);
        frame.setVisible(true);

        numberPanel.repaint();
        // vereify theree's no error thwne running.
        frame.dispose();

    }

    @Test
    void testPaintComponentWhenNoBackgroundLoad() {
        NumberPanel numberPanel = new NumberPanel();

        numberPanel.setNumbers(123, 45, 6, 78, 910);

        JFrame frame = new JFrame();
        frame.add(numberPanel);
        frame.setSize(400, 400);
        frame.setVisible(true);

        numberPanel.repaint();
        // vereify theree's no error thwne running.
        frame.dispose();
    }

    private int[][] getPrivateFieldNumbersToDisplay() {
        // Use reflection to access the private field 'numbersToDisplay'
        try {
            java.lang.reflect.Field field = NumberPanel.class.getDeclaredField("numbersToDisplay");
            field.setAccessible(true);
            return (int[][]) field.get(numberPanel);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Reflection to access private field failed: " + e.getMessage());
            return null;
        }
    }

}
