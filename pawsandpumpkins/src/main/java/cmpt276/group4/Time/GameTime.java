package cmpt276.group4.Time;

import java.util.Timer;
import java.util.TimerTask;

public class GameTime {
    private static GameTime instance = null;
    private int time = 0;
    private int timeCounter = 0;

    private Timer timer;

    /**
     * Private constructor for GameTime class.
     * Initializes a new Timer object.
     * This constructor is part of the Singleton pattern, ensuring only one instance of GameTime exists.
     */
    private GameTime() {
        timer = new Timer();
    }

    /**
     * Sets the time interval for a TimerTask and starts it.
     * When the time interval elapses, the specified TimeElapsedListener is notified.
     *
     * @param listener The TimeElapsedListener that should be notified when the time interval elapses.
     * @param interval The time interval in milliseconds after which the listener should be notified.
     */
    public void setTimeInterval(TimeElapsedListener listener, int interval) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                listener.arriveTime();
            }
        };
        timer.schedule(task, interval);
    }

    /**
     * Static method to get the singleton instance of GameTime.
     * If the instance is null, a new instance is created.
     *
     * @return The singleton instance of GameTime.
     */
    public static synchronized GameTime getInstance() {
        if (instance == null) {
            instance = new GameTime();
        }
        return instance;
    }

    /**
     * Increments the time counter.
     * When the time counter reaches 60, it resets to 0 and increments the time.
     */
    public void countTime() {
        timeCounter++;
        if (timeCounter >= 60) {
            time++;
            timeCounter = 0;
        }
    }

    /**
     * Returns the current time value.
     *
     * @return The current time.
     */
    public int getTime() {
        return time;
    }
}
