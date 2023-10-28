package cmpt276.group4.Enemy;


public class Spider implements Enemy {

    boolean movable = false;
    @Override
    public void catchPlayer() {
        System.out.println("Spider caught the player!");
    }
}
