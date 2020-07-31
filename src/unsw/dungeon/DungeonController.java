package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import java.io.File;

/**
 * A JavaFX controller for the dungeon.
 * 
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    private DungeonControllerLoader dungeonControllerLoader;
    private LevelSelectScreen levelSelectScreen;

    @FXML
    private GridPane squares;

    @FXML
    private ListView<Entity> itemsListView;

    @FXML
    private ListView<GoalType> goalsListView;

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

        // Set the items for the tool bar
        itemsListView.setItems(player.getItems());
        itemsListView.setCellFactory(listViewEntity -> new ListCell<Entity>() {
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
                    label.setPadding(new Insets(0, 12, 0, 0));

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
                        // Create an image view for the key
                        ImageView imageView = new ImageView(dungeonControllerLoader.getKeyImage());

                        root.getChildren().add(imageView);
                    }

                    setGraphic(root);
                }
            }
        });
     
        // Set the goals for the goal progress bar.
        goalsListView.setItems(dungeonControllerLoader.getDungeonGoals());
        goalsListView.setCellFactory(listViewEntity -> new ListCell<GoalType>() {
            @Override
            protected void updateItem(GoalType goalType, boolean empty) {
                super.updateItem(goalType, empty);
                if (goalType == null || empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    HBox root = new HBox();
                    root.setAlignment(Pos.CENTER);

                    // Picture of if the goal is completed
                    ImageView tickImageView = new ImageView(dungeonControllerLoader.getTickImage());
                    tickImageView.visibleProperty().bind(goalType.getIsSatisfiedProperty());

                    if (goalType instanceof ExitGoalType) {

                        Label label = new Label();
                        label.setPadding(new Insets(0, 12, 0, 12));
                        label.setText("Reach the ");

                        // picture of treasure
                        ImageView imageView = new ImageView(dungeonControllerLoader.getExitImage());

                        root.getChildren().addAll(tickImageView, label, imageView);

                    } else if (goalType instanceof TreasureGoalType) {
                        TreasureGoalType treasureGoalType = (TreasureGoalType) goalType;

                        Label label = new Label();
                        label.setPadding(new Insets(0, 12, 0, 12));
                        label.setText("Pickup ");

                        // how much treasure is needed
                        Label label2 = new Label();
                        label2.setPadding(new Insets(0, 12, 0, 0));

                        label2.textProperty().bind(treasureGoalType.getCurrentTreasureProperty().asString());

                        // picture of treasure
                        ImageView imageView = new ImageView(dungeonControllerLoader.getTreasureImage());

                        root.getChildren().addAll(tickImageView, label, label2, imageView);

                    } else if (goalType instanceof EnemyGoalType) {
                        EnemyGoalType enemyGoalType = (EnemyGoalType) goalType;

                        
                        Label label = new Label();
                        label.setPadding(new Insets(0, 12, 0, 0));
                        label.textProperty().bind(enemyGoalType.getEnemiesLeftProperty().asString());

                        ImageView imageView = new ImageView(dungeonControllerLoader.getEnemyImage());

                        Label label2 = new Label();
                        label2.setPadding(new Insets(0, 12, 0, 12));
                        label2.setText("Kill ");

                        root.getChildren().addAll(tickImageView, label2, label, imageView);

                    } else if (goalType instanceof SwitchGoalType) {
                        SwitchGoalType switchGoalType = (SwitchGoalType) goalType;

                        Label label = new Label();
                        label.setPadding(new Insets(0, 0, 0, 12));
                        label.setText("Place ");

                        Label label2 = new Label();
                        label2.setPadding(new Insets(0, 12, 0, 12));
                        label2.textProperty().bind(switchGoalType.getSwitchesLeftProperty().asString());

                        ImageView boulderView = new ImageView(dungeonControllerLoader.getBoulderImage());

                        Label label3 = new Label();
                        label3.setText(" on ");

                        ImageView switchView = new ImageView(dungeonControllerLoader.getFloorSwitchImage());
                        
                        root.getChildren().addAll(tickImageView, label, label2, boulderView, label3, switchView);
                    }

                    setGraphic(root);
                }
            }
        });
        
    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            getConfirmationToExit("Are you sure you want to leave?", "");
        }

        if (!player.getStatus() || dungeon.completedGoal()) return;
        
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
        default:
            break;
        }
    }

    @FXML
    public void handleExitGame(ActionEvent event) {
        getConfirmationToExit("Are you sure you want to leave?", "");
    }

    private void getConfirmationToExit(String headerText, String contentText) {
        Alert alert = new Alert(AlertType.CONFIRMATION, contentText, ButtonType.YES, ButtonType.NO);
        alert.setTitle("Exit Confirmation");
        alert.setHeaderText(headerText);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            levelSelectScreen.start();
        }
        alert.close();
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

    public void setLevelSelectScreen(LevelSelectScreen levelSelectScreen) {
        this.levelSelectScreen = levelSelectScreen;
    }

    public GridPane getSquares() {
        return squares;
    }

}

