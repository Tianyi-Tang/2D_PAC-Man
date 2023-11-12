package cmpt276.group4.WindowAndInput;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import cmpt276.group4.Position;
import cmpt276.group4.Player.KeyMovingObserver;

public class keyboardListener implements KeyListener, MouseListener, MouseMotionListener  {
    private KeyMovingObserver player;
    private Position mousePsition;
    private boolean mouseClicked;
    private boolean mousePressed;


    public Position getMousePosition() {
        return mousePsition;
    }

    // public void setMousePsition(Position ptrPsimousePsitiontion) {
    //     this.mousePsition = mousePsition;
    // }

    public boolean isMouseClicked() {
        return mouseClicked;
    }

    // public void setMouseClicked(boolean mouseClicked) {
    //     this.mouseClicked = mouseClicked;
    // }

    public boolean isMousePressed() {
        return mousePressed;
    }

    // public void setMousePressed(boolean mousePressed) {
    //     this.mousePressed = mousePressed;
    // }

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
                System.out.println("Yes");
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

    @Override
    public void mouseDragged(MouseEvent e) {
        mousePsition = new Position((int)(e.getPoint().getX()), (int)(e.getPoint().getY()));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePsition = new Position((int)(e.getPoint().getX()), (int)(e.getPoint().getY()));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        mousePressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        mouseClicked = true;
        mousePressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    public void clearMouseClick(){
        mouseClicked = false;
    }


    
}
