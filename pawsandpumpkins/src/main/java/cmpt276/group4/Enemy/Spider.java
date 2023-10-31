package cmpt276.group4.Enemy;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import cmpt276.group4.Position;
import cmpt276.group4.RecordUsedPlace;
import cmpt276.group4.Player.Player;
import cmpt276.group4.WindowAndInput.GamePanel;

public class Spider implements Enemy {

    private boolean movable = false;
    private Position enemyPosition = new Position(0, 0);
    // private boolean org_State = true;
    // private BufferedImage spider1, spider2;
    SpiderType spideType;
    private BufferedImage currentImage;
    private RecordUsedPlace record;

    public enum SpiderType {
        type_spider_1,
        type_spider_2;
    }

    Spider() {
        record = RecordUsedPlace.getInstance();
        //get the list of enemy from recordUsedPlace and randomly picked one.
        switch ((record.getEnemyList().size()) % 2) {
            case 1:
                spideType = SpiderType.type_spider_1;
                break;

            default:
                spideType = SpiderType.type_spider_2;
                break;
        }
        System.out.println("Spider.java: Creating spider");
        getEnemyImage();
        enemyPosition = new Position(0, 0);
        record.addEnemy(this);

    }

    void deleteImage() {
        // not done yet
    }

    void deductScore(Player player, int score) {
        // not done yet
    }

    @Override
    public void catchPlayer() {
        RecordUsedPlace record = RecordUsedPlace.getInstance();
        Position playerPosition = record.getPlayerPosition();

        if (playerPosition == enemyPosition) {
            System.out.println("Bagel stepped on spider!");
        }
    }

    @Override
    public Position getEnemyPosition() {
        return enemyPosition;
    }

    @Override
    public void setEnemyPosition(Position newPosition) {
        enemyPosition = newPosition;
    }

    @Override
    public boolean isMovable() {
        return movable;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(currentImage, enemyPosition.getX_axis(), enemyPosition.getY_axis(), GamePanel.tileSize,
                GamePanel.tileSize, null);
    }

    private void getEnemyImage() {
        try {
            String directory = System.getProperty("user.dir");
            switch (spideType) {
                case type_spider_1:
                    currentImage = ImageIO.read(new File(directory + "/res/Enemy/spider1.png"));
                    break;

                default:
                    currentImage = ImageIO.read(new File(directory + "/res/Enemy/spider2.png"));
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
