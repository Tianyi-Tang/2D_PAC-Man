package cmpt276.group4.WindowAndInput;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import cmpt276.group4.Player.KeyMovingObserver;

public class keyboardListener implements KeyListener {
    private KeyMovingObserver player;

    public void addPlayer(KeyMovingObserver player){
        this.player = player;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        System.out.println("key pressed");
        switch (code) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                System.out.println("Player wants to move up");
                notify(MoveDirection.Up);
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                System.out.println("Player wants to move down");
                notify(MoveDirection.Down);
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                System.out.println("Player wants to move left");
                notify(MoveDirection.Left);
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                System.out.println("Player wants to move right");
                notify(MoveDirection.Right);
                break;
            default:
                System.out.println("Invalid input");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        notify(MoveDirection.StaySame);
    }

    private void notify(MoveDirection direction){
        if(player != null)
            player.observerUpdate(direction);
    }
    
}
