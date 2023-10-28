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
        switch (code) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                System.out.println("Player wants to move up");
                // player.update(MoveDirection.Up);
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                System.out.println("Player wants to move down");
                // player.update(MoveDirection.Down);
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                System.out.println("Player wants to move left");
                // player.update(MoveDirection.Left);
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                System.out.println("Player wants to move right");
                // player.update(MoveDirection.Right);
                break;
            default:
                System.out.println("Invalid input");
                break;
        }

        // if(code == KeyEvent.VK_W){
        //     //player.update(MoveDirection.Up);
        // }
        // else if(code == KeyEvent.VK_S){
        //     //player.update(MoveDirection.Down);
        // }
        // else if(code == KeyEvent.VK_A){
        //     //player.update(MoveDirection.Left);
        // }
        // else if(code == KeyEvent.VK_D){
        //     //player.update(MoveDirection.Right);
        // }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("key release");
    }
    
}
