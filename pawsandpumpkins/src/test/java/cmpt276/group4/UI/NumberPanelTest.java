package cmpt276.group4.UI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cmpt276.group4.GameStatus;

import static org.junit.jupiter.api.Assertions.*;

class NumberPanelTest {

    private NumberPanel numberPanel;

    @BeforeEach
    void setUp() {
        numberPanel = new NumberPanel();
        
    }

    // @Test
    // void setNumbers_correctlyConvertsIntegersToArrayswhenWin() {
    //     numberPanel.init(GameStatus.Win); 
    //     int totalRewards = 123;
    //     int regular = 45;
    //     int bonus = 6;
    //     int punishments = 78;
    //     int overall = 910;

    //     numberPanel.setNumbers(totalRewards, regular, bonus, punishments, overall);

    //     // Retrieve the private field 'numbersToDisplay' using reflection
    //     int[][] numbersToDisplay = getPrivateFieldNumbersToDisplay();

    //     assertArrayEquals(new int[]{1, 2, 3}, numbersToDisplay[0], "Total rewards digits should match");
    //     assertArrayEquals(new int[]{4, 5}, numbersToDisplay[1], "Regular rewards digits should match");
    //     assertArrayEquals(new int[]{6}, numbersToDisplay[2], "Bonus rewards digits should match");
    //     assertArrayEquals(new int[]{7, 8}, numbersToDisplay[3], "Punishments digits should match");
    //     assertArrayEquals(new int[]{9, 1, 0}, numbersToDisplay[4], "Overall score digits should match");
    // }

    // @Test
    // void setNumbers_correctlyConvertsIntegersToArrayswhenGameOver() {
    //     numberPanel.init(GameStatus.GameOver); 
    //     int totalRewards = 123;
    //     int regular = 45;
    //     int bonus = 6;
    //     int punishments = 78;
    //     int overall = 910;

    //     numberPanel.setNumbers(totalRewards, regular, bonus, punishments, overall);

    //     // Retrieve the private field 'numbersToDisplay' using reflection
    //     int[][] numbersToDisplay = getPrivateFieldNumbersToDisplay();

    //     assertArrayEquals(new int[]{1, 2, 3}, numbersToDisplay[0], "Total rewards digits should match");
    //     assertArrayEquals(new int[]{4, 5}, numbersToDisplay[1], "Regular rewards digits should match");
    //     assertArrayEquals(new int[]{6}, numbersToDisplay[2], "Bonus rewards digits should match");
    //     assertArrayEquals(new int[]{7, 8}, numbersToDisplay[3], "Punishments digits should match");
    //     assertArrayEquals(new int[]{9, 1, 0}, numbersToDisplay[4], "Overall score digits should match");
    // }

    @Test
    void testSetNumbersWithPositiveValues() {
        numberPanel.init(GameStatus.Win); 
        numberPanel.setNumbers(123, 45, 6, 78, 910);

        int[][] numbersToDisplay = getPrivateFieldNumbersToDisplay();

        assertArrayEquals(new int[]{1, 2, 3}, numbersToDisplay[0], "Total rewards digits should match");
        assertArrayEquals(new int[]{4, 5}, numbersToDisplay[1], "Regular rewards digits should match");
        assertArrayEquals(new int[]{6}, numbersToDisplay[2], "Bonus rewards digits should match");
        assertArrayEquals(new int[]{7, 8}, numbersToDisplay[3], "Punishments digits should match");
        assertArrayEquals(new int[]{9, 1, 0}, numbersToDisplay[4], "Overall score digits should match");
    }

    @Test
    void testSetNumbersWithNegativeValues() {
        numberPanel.init(GameStatus.GameOver);
        numberPanel.setNumbers(-123, -45, -6, -78, -910);

        int[][] numbersToDisplay = getPrivateFieldNumbersToDisplay();

        assertArrayEquals(new int[]{10, 1, 2, 3}, numbersToDisplay[0], "Negative total rewards digits should match");
        assertArrayEquals(new int[]{10, 4, 5}, numbersToDisplay[1], "Negative regular rewards digits should match");
        assertArrayEquals(new int[]{10, 6}, numbersToDisplay[2], "Negative bonus rewards digits should match");
        assertArrayEquals(new int[]{10, 7, 8}, numbersToDisplay[3], "Negative punishments digits should match");
        assertArrayEquals(new int[]{10, 9, 1, 0}, numbersToDisplay[4], "Negative overall score digits should match");
    }

    @Test
    void testSetNumbersWithZero() {
        numberPanel.init(GameStatus.Win);
        numberPanel.setNumbers(0, 0, 0, 0, 0);

        int[][] numbersToDisplay = getPrivateFieldNumbersToDisplay();

        assertArrayEquals(new int[]{0}, numbersToDisplay[0], "Zero total rewards digits should match");
        assertArrayEquals(new int[]{0}, numbersToDisplay[1], "Zero regular rewards digits should match");
        assertArrayEquals(new int[]{0}, numbersToDisplay[2], "Zero bonus rewards digits should match");
        assertArrayEquals(new int[]{0}, numbersToDisplay[3], "Zero punishments digits should match");
        assertArrayEquals(new int[]{0}, numbersToDisplay[4], "Zero overall score digits should match");
    }




    private int[][] getPrivateFieldNumbersToDisplay() {
        // Use reflection to access the private field 'numbersToDisplay'
        try {
            java.lang.reflect.Field field = NumberPanel.class.getDeclaredField("numbersToDisplay");
            field.setAccessible(true);
            return (int[][]) field.get(numberPanel);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Reflection to access private field failed: " + e.getMessage());
            return null; // This line is unreachable, but required for compilation
        }
    }
}




