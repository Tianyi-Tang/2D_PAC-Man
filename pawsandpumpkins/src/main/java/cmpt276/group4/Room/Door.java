package cmpt276.group4.Room;

public class Door {
    // open variable
    private boolean open;

    // getter for open
    public boolean getOpen() {
        return open;
    }

    // setter for open
    public void setOpen(boolean i) {
        this.open = i;
    }

    // this function opens door by setting open to true
    private void openDoor() {
        this.open = true;
    }

    // if player available is true call private openDoor()
    public void meetCondition() {

    }
}
