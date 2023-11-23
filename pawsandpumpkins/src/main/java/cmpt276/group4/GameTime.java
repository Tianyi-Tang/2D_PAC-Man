package cmpt276.group4;

public class GameTime {
    private static GameTime instance = null;
    private int time = 0;
    private int timeCounter = 0;

    // Private constructor to prevent instantiation
    private GameTime() {}

    // Static method to get the singleton instance
    public static synchronized GameTime getInstance() {
        if (instance == null) {
            instance = new GameTime();
        }
        return instance;
    }

    public void countTime() {
        timeCounter++;
        if (timeCounter >= 60) {
            time++;
            timeCounter = 0;
        }
    }

    public int getTime() {
        return time;
    }
}
