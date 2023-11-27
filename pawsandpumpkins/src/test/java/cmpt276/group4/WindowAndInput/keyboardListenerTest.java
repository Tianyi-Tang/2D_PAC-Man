package cmpt276.group4.WindowAndInput;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import cmpt276.group4.Player.Player;

public class keyboardListenerTest {
    public keyboardListener listener;
    public Player mockPlayer;
    public KeyEvent mockevent;

    public enum MovingKey{
        W,A,S,D,upArrow,DownArrow,LeftArrow,RightArrow,other
    }

    @BeforeEach
    public void setUp(){
        listener = new keyboardListener();
        mockPlayer = mock(Player.class);
        listener.addPlayer(mockPlayer);
        mockevent = mock(KeyEvent.class);
    }

    @Test
    public void preessUpArraw(){
        verifyKey(MovingKey.upArrow,true);
    }

    @Test
    public void relasedW(){
        verifyKey(MovingKey.W, false);
    }

    @Test
    public void pressA(){
        verifyKey(MovingKey.A, true);
    }

    @Test
    public void realseLeftArraw(){
        verifyKey(MovingKey.LeftArrow, false);
    }

    @Test
    public void pressS(){
        verifyKey(MovingKey.S, true);
    }

    @Test
    public void realseDownArraw(){
        verifyKey(MovingKey.DownArrow, false);
    }

    @Test
    public void preessRightArraw(){
        verifyKey(MovingKey.RightArrow, true);
    }

    @Test
    public void relasedD(){
        verifyKey(MovingKey.D, false);
    }

    @Test
    public void otherKeyPress(){
        verifyKey(MovingKey.other, true);
        listener.keyPressed(mockevent);
        verify(mockPlayer, Mockito.never()).observerUpdate(null, true);
    }

    @Test
    public void otherKeyRealse(){
        verifyKey(MovingKey.other, false);
        listener.keyReleased(mockevent);
        verify(mockPlayer, Mockito.never()).observerUpdate(null, false);
    }

    /**
     * no error emit 
     */
    @Test
    public void notSitePlayer(){
        listener = new keyboardListener();
        when(mockevent.getKeyCode()).thenReturn(keyCode(MovingKey.A));
        listener.keyPressed(mockevent);
    }

    

    private void verifyKey(MovingKey key, boolean press){
        int code = keyCode(key);
        when(mockevent.getKeyCode()).thenReturn(code);
        if(code == 80)
            return;
        if(press)
            verifyPressKey(movingDirection(code));
        else
            verifyRelaseKey(movingDirection(code));
    }

    private void verifyPressKey(MoveDirection moveDirection){
        listener.keyPressed(mockevent);
        verify(mockPlayer).observerUpdate(moveDirection, true);
    }

    private void verifyRelaseKey(MoveDirection moveDirection){
        listener.keyReleased(mockevent);
        verify(mockPlayer).observerUpdate(moveDirection, false);
    }

    private MoveDirection movingDirection(int code){
        if(code == 87 || code == 38)
            return MoveDirection.Up;
        else if(code == 65 || code == 37)
            return MoveDirection.Left;
        else if(code == 68 || code == 39)
            return MoveDirection.Right;
        else 
            return MoveDirection.Down;
    }

    private int keyCode(MovingKey key){
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
