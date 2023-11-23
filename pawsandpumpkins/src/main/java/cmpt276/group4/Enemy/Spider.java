package cmpt276.group4.Enemy;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import cmpt276.group4.GameManager;
import cmpt276.group4.Position;
import cmpt276.group4.GameMap.RecordUsedPlace;
import cmpt276.group4.GameMap.RoomEnvironment;
import cmpt276.group4.Logic.WindowConfig;
import cmpt276.group4.WindowAndInput.GamePanel;

/**
 * Represents a Spider enemy in the game which is a fixed enemy.
 * This class handles the behavior and rendering of a spider-type enemy.
 */
public class Spider implements Enemy {

    private boolean movable = false;
    private Position enemyPosition = new Position(0, 0);
    SpiderType spideType;
    private BufferedImage currentImage;
    private RecordUsedPlace record;
    private RoomEnvironment roomEnvironment;
    private Position playerPosition;

    /**
     * Enum for different spider imgage
     */
    public enum SpiderType {
        type_spider_1,
        type_spider_2;
    }

    /**
     * Constructs a Spider enemy, choosing its type based on the number of enemies
     * already present.
     * The spider is positioned at a random position after checking vacancy.
     */
    Spider() {
        getPlayerPosition();
        record = RecordUsedPlace.getInstance();
        roomEnvironment = RoomEnvironment.getInstance();
        // get the list of enemy from recordUsedPlace and randomly picked one.
        switch ((roomEnvironment.getEnemyNumber()) % 2) {
            case 1:
                spideType = SpiderType.type_spider_1;
                break;

            default:
                spideType = SpiderType.type_spider_2;
                break;
        }
        // System.out.println("Spider.java: Creating spider");
        getEnemyImage();
        Position potentialPosition = new Position(0, 0);
        potentialPosition.equal(playerPosition);

        do {
            potentialPosition = record.getRandomFromAvailablePosition();
        } while (potentialPosition.equal(playerPosition));

        enemyPosition.setPosition(potentialPosition);
        roomEnvironment.addEnemy(this);

    }

    /**
     * Retrieves the current position of the player.
     */
    private void getPlayerPosition() {

        RecordUsedPlace record = RecordUsedPlace.getInstance();
        playerPosition = record.getPlayerPosition();
    }

    /**
     * Gets the current position of the spider.
     * 
     * @return The current position of the spider.
     */
    @Override
    public Position getPosition() {
        return enemyPosition;
    }

    /**
     * Checks if the spider has caught the player and updates the corresponding
     * flag.
     */
    @Override
    public void catchPlayer() {
        Position playerPosition = record.getPlayerPosition();
        if (playerPosition.equals(enemyPosition)) {
            GameManager.getInstance().enemyCatachPlayer(movable);
        } 
    }

    /**
     * Gets the current enemy position.
     * 
     * @return The current enemy position.
     */
    @Override
    public Position getEnemyPosition() {
        return enemyPosition;
    }

    /**
     * Sets a new position for this spider.
     * 
     * @param newPosition The new position to set.
     */
    @Override
    public void setEnemyPosition(Position newPosition) {
        enemyPosition = newPosition;
    }

    /**
     * Checks if the spider is movable.
     * 
     * @return false.
     */
    @Override
    public boolean isMovable() {
        return false;
    }

    /**
     * Draws the spider on the game panel.
     * 
     * @param g2 The Graphics2D object used for drawing.
     */
    @Override

    public void draw(Graphics2D g2) {
        g2.drawImage(currentImage, enemyPosition.getX_axis(), enemyPosition.getY_axis(), WindowConfig.tileSize,
                WindowConfig.tileSize, null);
    }

    /**
     * Loads the image for this spider based on its type.
     */
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

    @Override
    public boolean getMovable() {
        return false;
    }
}
