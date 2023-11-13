package cmpt276.group4.Player;

import cmpt276.group4.WindowAndInput.MoveDirection;

/**
 * The class that lister to keyboard changing
 */
public interface KeyMovingObserver {
    /**
     * When people press or release the moving key, the function will be called and
     * send information to the class that are listen to
     * @param direction
     * @param turnOn
     */
    public void observerUpdate(MoveDirection direction, boolean turnOn);
}
