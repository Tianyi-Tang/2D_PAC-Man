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
import cmpt276.group4.WindowAndInput.KeyInput.MovingKey;

public class keyboardListenerTest {
    public keyboardListener listener;
    public Player mockPlayer;
    public KeyEvent mockevent;
    public KeyInput keyInput;


    @BeforeEach
    public void setUp(){
        listener = new keyboardListener();
        mockPlayer = mock(Player.class);
        listener.addPlayer(mockPlayer);
        mockevent = mock(KeyEvent.class);
        keyInput = new KeyInput();
        keyInput.setup(listener);
    }

    @Test
    public void preessUpArraw(){
        keyInput.inputKey(MovingKey.upArrow, true);
        verifyPressKey(MovingKey.upArrow);
    }

    @Test
    public void relasedW(){
        keyInput.inputKey(MovingKey.W, false);
        verifyRelaseKey(MovingKey.W);
    }

    @Test
    public void pressA(){
        keyInput.inputKey(MovingKey.A, true);
        verifyPressKey(MovingKey.A);
    }

    @Test
    public void realseLeftArraw(){
        keyInput.inputKey(MovingKey.LeftArrow, false);
        verifyRelaseKey(MovingKey.LeftArrow);
    }

    @Test
    public void pressS(){
        keyInput.inputKey(MovingKey.S, true);
        verifyPressKey(MovingKey.S);
    }

    @Test
    public void realseDownArraw(){
        keyInput.inputKey(MovingKey.DownArrow, false);
        verifyRelaseKey(MovingKey.DownArrow);
    }

    @Test
    public void preessRightArraw(){
        keyInput.inputKey(MovingKey.RightArrow, true);
        verifyPressKey(MovingKey.RightArrow);
    }

    @Test
    public void relasedD(){
        keyInput.inputKey(MovingKey.D, false);
        verifyRelaseKey(MovingKey.D);
    }

    @Test
    public void otherKeyPress(){
        keyInput.inputKey(MovingKey.other, true);
        verify(mockPlayer, Mockito.never()).observerUpdate(null, true);
    }

    @Test
    public void otherKeyRealse(){
        keyInput.inputKey(MovingKey.other, false);
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

    

    private void verifyPressKey(MovingKey key){
        int Key = keyInput.keyCode(key);
        verify(mockPlayer).observerUpdate(movingDirection(Key), true);
    }

    private void verifyRelaseKey(MovingKey key){
        int Key = keyInput.keyCode(key);
        verify(mockPlayer).observerUpdate(movingDirection(Key), false);
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
