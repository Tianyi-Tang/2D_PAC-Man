package cmpt276.group4.WindowAndInput;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import cmpt276.group4.Player.KeyMovingObserver;

public class keyboardListener implements KeyListener {
    private KeyMovingObserver player;


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        System.out.println("key pressed");

        if(code == KeyEvent.VK_W){
            //player.update(MoveDirection.Up);
        }
        else if(code == KeyEvent.VK_S){
            //player.update(MoveDirection.Down);
        }
        else if(code == KeyEvent.VK_A){
            //player.update(MoveDirection.Left);
        }
        else if(code == KeyEvent.VK_D){
            //player.update(MoveDirection.Right);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("key release");
    }
    
}
