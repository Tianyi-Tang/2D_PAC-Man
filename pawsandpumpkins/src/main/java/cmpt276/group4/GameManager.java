package cmpt276.group4;

import javax.swing.JFrame;

import cmpt276.group4.WindowAndInput.GamePanel;



public class GameManager {
    // typeOfRoom
    private int typeOfRoom;

    private JFrame window;
    private GamePanel gamePanel;

    // getter
    public int getTypeOfRoom() {
        return typeOfRoom;
    }

    // setter
    public void setTypeOfRoom(int i) {
        this.typeOfRoom = i;
    }

    public void createWindows(){
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        window.setTitle("paws and pumpkins");
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        gamePanel.createTimeLine();
    }

    //instance

    //enemyNum

    //gameRoom

    //totalRewardNum

    //initialgame

    //postGame

    //openDoor

    //endGame

    //EndGame

    //getAllReward

    //catchyByEnemy

    //getInstance
}
