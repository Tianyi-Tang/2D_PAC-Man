package cmpt276.group4.Time;

import java.util.Timer;
import java.util.TimerTask;

public class GameTime {
    private static GameTime instance = null;
    private int time = 0;
    private int timeCounter = 0;

    private Timer timer;

    // Private constructor to prevent instantiation
    private GameTime() {
        timer = new Timer();
    }

    public void setTimeInterval(TimeElapsedListener listener,int interval){
       TimerTask task = new TimerTask() {
        @Override
        public void run() {
            listener.update();
        }
       };
       timer.schedule(task, interval);
    }

    // Static method to get the singleton instance
    public static GameTime getInstance() {
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
