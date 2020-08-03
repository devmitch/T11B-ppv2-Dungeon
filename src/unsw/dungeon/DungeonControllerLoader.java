package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.File;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities;

    //Images
    private Image playerImage;
    private Image wallImage;
    private Image boulderImage;
    private Image exitImage;
    private Image treasureImage;
    private Image keyImage;
    private Image closedDoorImage;
    private Image openDoorImage;
    private Image floorSwitchImage;
    private Image portalImage;
    private Image enemyImage;
    private Image wizardImage;
    private Image swordImage;
    private Image invincibilityPotionImage;
    private Image phasePotionImage;


    private Image tickImage;

    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        playerImage = new Image((new File("images/human_new.png")).toURI().toString());
        wallImage = new Image((new File("images/brick_brown_0.png")).toURI().toString());
        boulderImage = new Image((new File("images/boulder.png")).toURI().toString());
        exitImage = new Image((new File("images/exit.png")).toURI().toString());
        treasureImage = new Image((new File("images/gold_pile.png")).toURI().toString());
        keyImage = new Image((new File("images/key.png")).toURI().toString());
        closedDoorImage = new Image((new File("images/closed_door.png")).toURI().toString());
        openDoorImage = new Image((new File("images/open_door.png")).toURI().toString());
        floorSwitchImage = new Image((new File("images/pressure_plate.png")).toURI().toString());
        portalImage = new Image((new File("images/portal.png")).toURI().toString());
        double enemyImageChoice = Math.random();
        if (enemyImageChoice < 0.5) {
            enemyImage = new Image((new File("images/deep_elf_master_archer.png")).toURI().toString());
        } else {
            enemyImage = new Image((new File("images/hound.png")).toURI().toString());
        }
        wizardImage = new Image((new File("images/gnome.png")).toURI().toString());
        swordImage = new Image((new File("images/greatsword_1_new.png")).toURI().toString());
        invincibilityPotionImage = new Image((new File("images/brilliant_blue_new.png")).toURI().toString());
        phasePotionImage = new Image((new File("images/bubbly.png")).toURI().toString());
        tickImage = new Image((new File("images/tick.png")).toURI().toString()); 
    }

    @Override
    public void onLoad(Player player) {
        ImageView view = new ImageView(playerImage);
        view.opacityProperty().bind(player.getOpacityProperty());
        addEntity(player, view);
    }

    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        addEntity(wall, view);
    }

    @Override
    public void onLoad(Boulder boulder) {
        ImageView view = new ImageView(boulderImage);
        addEntity(boulder, view);
    }

    @Override
    public void onLoad(Exit exit) {
        ImageView view = new ImageView(exitImage);
        addEntity(exit, view);
    }

    @Override
    public void onLoad(Treasure treasure) {
        ImageView view = new ImageView(treasureImage);
        addEntity(treasure, view);
    }

    @Override
    public void onLoad(Key key) {
        ImageView view = new ImageView(keyImage);
        addEntity(key, view);
    }

    @Override
    public void onLoad(Door door) {
        door.setOpenDoorImage(openDoorImage);
        door.setClosedDoorImage(closedDoorImage);
        ImageView view = new ImageView(closedDoorImage);
        view.imageProperty().bind(door.getImageProperty());
        addEntity(door, view);
    }

    @Override
    public void onLoad(FloorSwitch floorSwitch) {
        ImageView view = new ImageView(floorSwitchImage);
        addEntity(floorSwitch, view);
    }

    @Override
    public void onLoad(Portal portal) {
        ImageView view = new ImageView(portalImage);
        addEntity(portal, view);
    }

    @Override
    public void onLoad(Enemy enemy) {
        ImageView view = new ImageView(enemyImage);
        addEntity(enemy, view);
    }

    @Override
    public void onLoad(Wizard wizard) {
        ImageView view = new ImageView(wizardImage);
        addEntity(wizard, view);
    }

    @Override
    public void onLoad(Sword sword) {
        ImageView view = new ImageView(swordImage);
        addEntity(sword, view);
    }

    @Override
    public void onLoad(InvincibilityPotion potion) {
        ImageView view = new ImageView(invincibilityPotionImage);
        addEntity(potion, view);
    }

    @Override
    public void onLoad(PhasePotion potion) {
        ImageView view = new ImageView(phasePotionImage);
        addEntity(potion, view);
    }


    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entities.add(view);
    }


    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });
        entity.status().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldStatus, Boolean newStatus) {
                if (!newStatus) {
                    node.setVisible(false);
                } else {
                    node.setVisible(true);
                }
            }
        });
    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities);
    }

    public Image getPlayerImage() {
        return playerImage;
    }

    public Image getWallImage() {
        return wallImage;
    }

    public Image getBoulderImage() {
        return boulderImage;
    }

    public Image getExitImage() {
        return exitImage;
    }

    public Image getTreasureImage() {
        return treasureImage;
    }

    public Image getKeyImage() {
        return keyImage;
    }

    public Image getClosedDoorImage() {
        return closedDoorImage;
    }

    public Image getFloorSwitchImage() {
        return floorSwitchImage;
    }

    public Image getPortalImage() {
        return portalImage;
    }

    public Image getEnemyImage() {
        return enemyImage;
    }

    public Image getWizardImage() {
        return wizardImage;
    }

    public Image getSwordImage() {
        return swordImage;
    }

    public Image getInvincibilityPotionImage() {
        return invincibilityPotionImage;
    }

    public Image getPhasePotionImage() {
        return phasePotionImage;
    }

    public Image getTickImage() {
        return tickImage;
    }

}
