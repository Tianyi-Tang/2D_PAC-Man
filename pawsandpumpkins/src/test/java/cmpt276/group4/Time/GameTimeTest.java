package cmpt276.group4.Time;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTimeTest {



    @Test
    void testSingleton() {
        GameTime instance1 = GameTime.getInstance();
        GameTime instance2 = GameTime.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    void testTimeInterval() {

    }

    @Test
    void testCountTime() {
        GameTime gameTime = GameTime.getInstance();
        for (int i = 0; i < 60; i++) {
            gameTime.countTime();
        }
        assertEquals( 1, gameTime.getTime());
    }

    @Test
    void testGetTime() {
        GameTime gameTime = GameTime.getInstance();
        int expectedTime = gameTime.getTime();
        assertEquals(expectedTime, gameTime.getTime());
    }
}