package cmpt276.group4.UI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.JFrame;

/**
 * Tests for the NumberPanel class.
 * Verifies that numbers are correctly set and displayed in various scenarios.
 */
class NumberPanelTest {

    private NumberPanel numberPanel;

    @BeforeEach
    void setUp() {
        numberPanel = new NumberPanel();

    }

    /**
     * Test setting positive numbers for different reward types and overall score.
     * Verifies that the numbers are set and displayed correctly.
     */
    @Test
    void testSetNumbersWith5PositiveValues() {
        numberPanel.init(true);
        numberPanel.setNumbers(123, 45, 6, 78, 910);

        int[][] numbersToDisplay = getPrivateFieldNumbersToDisplay();

        assertArrayEquals(new int[] { 1, 2, 3 }, numbersToDisplay[0], "Total rewards digits should match");
        assertArrayEquals(new int[] { 4, 5 }, numbersToDisplay[1], "Regular rewards digits should match");
        assertArrayEquals(new int[] { 6 }, numbersToDisplay[2], "Bonus rewards digits should match");
        assertArrayEquals(new int[] { 7, 8 }, numbersToDisplay[3], "Punishments digits should match");
        assertArrayEquals(new int[] { 9, 1, 0 }, numbersToDisplay[4], "Overall score digits should match");
    }

    /**
     * Test setting positive numbers including a time value.
     * Verifies that all six numbers including time are set and displayed correctly.
     */
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

    /**
     * Test setting negative numbers for different reward types and overall score.
     * Verifies that negative numbers are handled and displayed correctly.
     */
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

    /**
     * Test setting all numbers to zero.
     * Verifies that zero values are correctly displayed for all types of rewards
     * and overall score.
     */
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

    /**
     * Test the painting of the component when a background image is loaded.
     * Verifies that the panel is painted without errors when the background image
     * is present.
     */
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

    /**
     * Test the painting of the component when no background image is loaded.
     * Verifies that the panel is painted without errors even when the background
     * image is not present.
     */
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

    /**
     * Helper method to access and retrieve the private 'numbersToDisplay' field
     * using reflection.
     * 
     * @return The 'numbersToDisplay' field value of the NumberPanel instance.
     */
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
