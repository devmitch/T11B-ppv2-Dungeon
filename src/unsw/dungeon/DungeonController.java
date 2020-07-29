package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;

import java.io.File;

/**
 * A JavaFX controller for the dungeon.
 * 
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    private DungeonControllerLoader dungeonControllerLoader;
    private StartScreen startScreen;

    @FXML
    private GridPane squares;

    @FXML
    private VBox itemsVBox;

    @FXML
    private VBox goalsVBox;

    @FXML
    private ListView<Entity> itemsListView;

    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        // dungeon.setController(this); for iteration 3
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
    }

    @FXML
    public void initialize() {
        Image ground = new Image((new File("images/dirt_0_new.png")).toURI().toString());

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);

        itemsListView.setItems(player.getItems());
        itemsListView.setCellFactory(new Callback<ListView<Entity>, ListCell<Entity>>() {
            @Override
            public ListCell<Entity> call(ListView<Entity> listViewEntity) {
                return new ListCell<Entity>() {
                    @Override
                    protected void updateItem(Entity entity, boolean empty) {
                        super.updateItem(entity, empty);
                        if (entity == null || empty) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            HBox root = new HBox();
                            root.setAlignment(Pos.CENTER);

                            // Create a label that the entity just has to bind to
                            Label label = new Label();
                            label.setAlignment(Pos.CENTER);
                            label.setFont(Font.font(24));
                            label.setPadding(new Insets(0, 10, 0, 0));

                            if (entity instanceof Sword) {
                                Sword sword = (Sword) entity;
                                
                                // Create an observer for the durability
                                
                                label.textProperty().bind(sword.getDurabilityProperty().asString());

                                // Create an image view for the sword
                                ImageView imageView = new ImageView(dungeonControllerLoader.getSwordImage());

                                root.getChildren().add(label);
                                root.getChildren().add(imageView);
                                
                            } else if (entity instanceof InvincibilityPotion) {
                                InvincibilityPotion potion = (InvincibilityPotion) entity;

                                // Create an observer for how many steps are left
                                label.textProperty().bind(potion.getStepsLeftProperty().asString());

                                // Create an image view for the potion
                                ImageView imageView = new ImageView(dungeonControllerLoader.getPotionImage());

                                root.getChildren().add(label);
                                root.getChildren().add(imageView);
                            } else if (entity instanceof Key) {
                                Key key = (Key) entity;

                                // Create an image view for the key
                                ImageView imageView = new ImageView(dungeonControllerLoader.getKeyImage());

                                root.getChildren().add(imageView);
                            }

                            setGraphic(root);
                        }
                    }
                };
            }
            
        });
        
    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
        if (!player.getStatus()) return;
        if (dungeon.completedGoal()) return;
        switch (event.getCode()) {
        case UP:
            player.move(Direction.UP);
            break;
        case DOWN:
            player.move(Direction.DOWN);
            break;
        case LEFT:
            player.move(Direction.LEFT);
            break;
        case RIGHT:
            player.move(Direction.RIGHT);
            break;
        case P:
            startScreen.start();
        default:
            break;
        }
    }

    public void newEntity(Entity entity) {
        if (entity instanceof Key) {
            Image keyImage = new Image((new File("images/key.png")).toURI().toString());
            ImageView view = new ImageView(keyImage);
            squares.getChildren().add(view);
            GridPane.setColumnIndex(view, entity.getX());
            GridPane.setRowIndex(view, entity.getY());
            trackPosition(entity, view);
        }
    }

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

    public void setDungeonControllerLoader(DungeonControllerLoader dungeonControllerLoader) {
        this.dungeonControllerLoader = dungeonControllerLoader;
    }

    public void setStartScreen(StartScreen startScreen) {
        this.startScreen = startScreen;
    }

    public GridPane getSquares() {
        return squares;
    }

}

