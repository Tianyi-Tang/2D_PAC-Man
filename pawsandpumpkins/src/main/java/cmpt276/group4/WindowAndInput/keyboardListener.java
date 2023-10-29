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
        switch (code) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                notify(MoveDirection.Up,true);
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                notify(MoveDirection.Down,true);
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                notify(MoveDirection.Left,true);
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                notify(MoveDirection.Right,true);
                break;
            default:
                System.out.println("Invalid input");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
         switch (code) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                notify(MoveDirection.Up,false);
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                notify(MoveDirection.Down,false);
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                notify(MoveDirection.Left,false);
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                notify(MoveDirection.Right,false);
                break;
            default:
                System.out.println("Invalid input");
                break;
        }
    }

    private void notify(MoveDirection direction, boolean turnOn){
        if(player != null)
            player.observerUpdate(direction,turnOn);
    }
    
}
