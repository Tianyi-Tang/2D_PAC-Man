package cmpt276.group4.WindowAndInput;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.awt.event.KeyEvent;


public class KeyInput {
    private keyboardListener listener;
    public KeyEvent mockevent = mock(KeyEvent.class);

    public enum MovingKey{
        W,A,S,D,upArrow,DownArrow,LeftArrow,RightArrow,other
    }

    public void setup(keyboardListener listener){
        this.listener = listener;
    }

    public void inputKey(MovingKey key, boolean press){
        int code = keyCode(key);
        when(mockevent.getKeyCode()).thenReturn(code);
        if(code == 80)
            return;
        if(press)
            listener.keyPressed(mockevent);
        else
            listener.keyReleased(mockevent);
    }

    public int keyCode(MovingKey key){
        int code;
        if(key == MovingKey.W)
            code = 87;
        else if(key == MovingKey.upArrow)
            code = 38;
        else if(key == MovingKey.A)
            code = 65;
        else if(key == MovingKey.LeftArrow)
            code = 37;
        else if(key == MovingKey.S)
            code = 83;
        else if(key == MovingKey.DownArrow)
            code = 40;
        else if(key == MovingKey.D)
            code = 68;
        else if(key == MovingKey.RightArrow)
            code = 39;
        else
            code = 80;
        return code;
    }
}
