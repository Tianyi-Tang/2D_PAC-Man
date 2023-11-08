package cmpt276.group4;
public class Main {
    public static void main( String[] args )
    {
        GameManager manager = GameManager.getInstance();
        // manager.createMainWindow();
        manager.createWindows();
        System.out.println("Hello World" );
    }
}
