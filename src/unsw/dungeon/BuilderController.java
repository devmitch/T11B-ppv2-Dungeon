package unsw.dungeon;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.util.Pair;

public class BuilderController {
    private Image playerImage;
    private Image wallImage;
    private Image boulderImage;
    private Image exitImage;
    private Image treasureImage;
    private Image keyImage;
    private Image closedDoorImage;
    private Image floorSwitchImage;
    private Image portalImage;
    private Image enemyImage;
    private Image wizardImage;
    private Image swordImage;
    private Image invincibilityPotionImage;
    private Image phasePotionImage;
    private Image deleteImage;
    private Image groundImage;

    private Builder builder;
    private StartScreen startScreen;

    @FXML
    private GridPane squares;

    @FXML
    private ListView<BuilderEntity> itemsListView;

    @FXML
    private TextField goalsText;

    public BuilderController() {
        playerImage = new Image((new File("images/human_new.png")).toURI().toString());
        wallImage = new Image((new File("images/brick_brown_0.png")).toURI().toString());
        boulderImage = new Image((new File("images/boulder.png")).toURI().toString());
        exitImage = new Image((new File("images/exit.png")).toURI().toString());
        treasureImage = new Image((new File("images/gold_pile.png")).toURI().toString());
        keyImage = new Image((new File("images/key.png")).toURI().toString());
        closedDoorImage = new Image((new File("images/closed_door.png")).toURI().toString());
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
        deleteImage = new Image((new File("images/delete.png")).toURI().toString(), 32, 32, false, false);
        groundImage = new Image((new File("images/dirt_0_new.png")).toURI().toString());

        builder = new Builder(15, 15);
    }

    public GridPane getSquares() {
        return squares;
    }

    @FXML
    public void initialize() {
        // Image ground = new Image((new
        // File("images/dirt_0_new.png")).toURI().toString());

        // Add the ground first
        for (int x = 0; x < builder.getWidth(); x++) {
            for (int y = 0; y < builder.getHeight(); y++) {
                ImageView toAdd = new ImageView(groundImage);
                toAdd.setOnMouseClicked(this::handleBuilderPlace);
                squares.add(toAdd, x, y);
            }
        }

        // add all the entities to itemsListView
        ObservableList<BuilderEntity> entityList = FXCollections.observableArrayList();
        entityList.add(new BuilderEntity("delete"));
        entityList.add(new BuilderEntity("player"));
        entityList.add(new BuilderEntity("enemy"));
        entityList.add(new BuilderEntity("wizard"));
        entityList.add(new BuilderEntity("wall"));
        entityList.add(new BuilderEntity("sword"));
        entityList.add(new BuilderEntity("treasure"));
        entityList.add(new BuilderEntity("switch"));
        entityList.add(new BuilderEntity("boulder"));
        entityList.add(new BuilderEntity("phase"));
        entityList.add(new BuilderEntity("invincibility"));
        entityList.add(new BuilderEntity("key", -1)); //maybe make constructor to pass in "true"
        entityList.add(new BuilderEntity("door", -1));
        entityList.add(new BuilderEntity("exit"));
        entityList.add(new BuilderEntity("portal", -1));
        itemsListView.setItems(entityList);
        itemsListView.getSelectionModel().select(0); // selects wall as default
        itemsListView.setCellFactory(param -> new ListCell<BuilderEntity>() {
            @Override
            protected void updateItem(BuilderEntity entity, boolean empty) {
                super.updateItem(entity, empty);
                if (empty || entity == null) {
                    // setText(null);
                    setGraphic(null);
                } else {
                    // setText(entity.getType());
                    setGraphic(getImageView(entity.getType()));
                }
            }
        });

    }

    private ImageView getImageView(String type) {
        switch (type) {
            case "delete":
                return new ImageView(deleteImage);
            case "player":
                return new ImageView(playerImage);
            case "enemy":
                return new ImageView(enemyImage);
            case "wizard":
                return new ImageView(wizardImage);
            case "wall":
                return new ImageView(wallImage);
            case "sword":
                return new ImageView(swordImage);
            case "treasure":
                return new ImageView(treasureImage);
            case "switch":
                return new ImageView(floorSwitchImage);
            case "boulder":
                return new ImageView(boulderImage);
            case "phase":
                return new ImageView(phasePotionImage);
            case "invincibility":
                return new ImageView(invincibilityPotionImage);
            case "key":
                return new ImageView(keyImage);
            case "door":
                return new ImageView(closedDoorImage);
            case "exit":
                return new ImageView(exitImage);
            case "portal":
                return new ImageView(portalImage);
        }
        return null;
    }

    @FXML
    public void handleBuilderSelect(MouseEvent event) {
        // dont actually need this
        String type = itemsListView.getSelectionModel().getSelectedItem().getType();
        System.out.println("Selected " + type);
    }

    @FXML
    public void handleBuilderPlace(MouseEvent event) {
        Node source = (Node) event.getSource();
        ImageView image = (ImageView) source;
        int x = GridPane.getColumnIndex(source);
        int y = GridPane.getRowIndex(source);
        BuilderEntity entity = itemsListView.getSelectionModel().getSelectedItem();
        //String type = itemsListView.getSelectionModel().getSelectedItem().getType(); // get item selected in listview
        if (entity.getType().equals("delete")) {
            // delete entity
            builder.removeEntity(x, y);
            if (image.getImage() != groundImage) { // not a ground tile
                squares.getChildren().remove(source);
            }
        } else if (entity.hasId()) {
            // entity with id
            TextInputDialog dialog = new TextInputDialog("0");
            dialog.setTitle("Entity ID required");
            dialog.setContentText("Please enter the entity ID:");

            try {
                int id = 0;
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                    id = Integer.parseInt(result.get());
                } else {
                    throw new NumberFormatException(); // could just return from here? idk
                }
                builder.addEntity(entity.getType(), x, y, id);
                addEntityImageWithId(entity, x, y, id);
            } catch (NumberFormatException e) {
                Alert alert = new Alert(AlertType.ERROR, "You did not enter a number", ButtonType.OK);
                alert.setTitle("Error");
                alert.showAndWait();
                alert.close();

            }
        } else {
            // entity without id
            builder.addEntity(entity.getType(), x, y);
            addEntityImage(entity, x, y);
        }
        event.consume();
    }

    private void addEntityImageWithId(BuilderEntity entity, int x, int y, int id) {
        ImageView toAdd = getImageView(entity.getType());
        toAdd.setOnMouseClicked(this::handleBuilderPlace);
        Tooltip tp = new Tooltip("id: " + id);
        Tooltip.install(toAdd, tp);
        squares.add(toAdd, x, y);
    }

    private void addEntityImage(BuilderEntity entity, int x, int y) {
        ImageView toAdd = getImageView(entity.getType());
        toAdd.setOnMouseClicked(this::handleBuilderPlace);
        squares.add(toAdd, x, y);
    }

    @FXML
    public void handleSaveDungeon(ActionEvent event) {
        System.out.println("saving...!");

        FileChooser fileChooser = new FileChooser();
 
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialFileName("myDungeon.json");

        File file = fileChooser.showSaveDialog(squares.getScene().getWindow());
        if (file != null) {
            saveToFile(builder.getJSON().toString(2), file);
        }
    }

    private void saveToFile(String content, File file) {
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(content);
            fw.close();
        } catch (IOException e) {
            //uhh
        }
    }

    @FXML
    public void handleSetGoals(ActionEvent event) {
        System.out.println("setting goals...!");

        TextInputDialog dialog = new TextInputDialog(builder.getGoalString());
        dialog.setTitle("Set goals");
        dialog.setContentText("Please enter goals:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            builder.setGoalString(result.get());
            builder.printGoalString();
        }
    }

    @FXML
    public void handleSetSize(ActionEvent event) {
        // https://code.makery.ch/blog/javafx-dialogs-official/
        System.out.println("setting size...!");

        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Login Dialog");
        ButtonType setButtonType = new ButtonType("Set", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(setButtonType, ButtonType.CANCEL);
        GridPane grid = new GridPane();


        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField width = new TextField("0");
        width.setPromptText("Width");
        TextField height = new TextField("0");
        height.setPromptText("Height");

        grid.add(new Label("Width:"), 0, 0);
        grid.add(width, 1, 0);
        grid.add(new Label("Height:"), 0, 1);
        grid.add(height, 1, 1);

        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == setButtonType) {
                return new Pair<>(width.getText(), height.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();
        result.ifPresent(pair -> {
            System.out.println("width=" + pair.getKey() + ", height=" + pair.getValue());
            int newWidth = 0;
            int newHeight = 0;
            try {
                newWidth = Integer.parseInt(pair.getKey());
                newHeight = Integer.parseInt(pair.getValue());
                squares.getChildren().clear();
                resizeBuilder(newWidth, newHeight);
            } catch (NumberFormatException e) {
                System.out.println("NANs");

            }
        });
    }

    private void resizeBuilder(int newWidth, int newHeight) {
        builder.resize(newWidth, newHeight);
        BuilderTile newTiles[][] = builder.getTiles();
        for (int y = 0; y < builder.getHeight(); y++) {
            for (int x = 0; x < builder.getWidth(); x++) {
                ImageView toAdd = new ImageView(groundImage);
                toAdd.setOnMouseClicked(this::handleBuilderPlace);
                squares.add(toAdd, x, y);
                for (BuilderEntity entity : newTiles[x][y].getEntities()) {
                    if (entity.hasId()) {
                        addEntityImageWithId(entity, x, y, entity.getId());
                    } else {
                        addEntityImage(entity, x, y);
                    }
                }
            }
        }
    }



    public void setStartScreen(StartScreen startScreen) {
        this.startScreen = startScreen;
    }

    @FXML
    public void handleExitGame(ActionEvent event) {
        getConfirmationToExit("Are you sure you want to leave? Any unsaved changes will be lost!", "");
    }

    private void getConfirmationToExit(String headerText, String contentText) {
        Alert alert = new Alert(AlertType.CONFIRMATION, contentText, ButtonType.YES, ButtonType.NO);
        alert.setTitle("Exit Confirmation");
        alert.setHeaderText(headerText);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            startScreen.start();
        }
        alert.close();
    }

    @FXML
    public void handleLoadDungeon(ActionEvent event) {

        // create a dialog to get a file path
        FileChooser fileChooser = new FileChooser();
 
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialFileName("");

        File file = fileChooser.showOpenDialog(squares.getScene().getWindow());
        if (file == null)
            return;

        // load the contents into a json object
        JSONObject json = readDungeonFromFile(file);
        if (json == null)
            return;
        
        // get the width and height of the dungeon, resize the builder.
        resizeBuilder(json.getInt("width"), json.getInt("height"));

        // get the entities, add them to the builder
        addEntitiesToDungeon(json.getJSONArray("entities"));

        // convert the goals into a string
        builder.setGoalString(goalsToString(json.getJSONObject("goal-condition")));
    }

    private void addEntitiesToDungeon(JSONArray entities) {
        for (int i = 0; i < entities.length(); i++) {

            JSONObject object = entities.getJSONObject(i);
            String type = object.getString("type");
            int x = object.getInt("x");
            int y = object.getInt("y");
            
            if (object.has("id")) {
                BuilderEntity entity = new BuilderEntity(type, object.getInt("id"));
                builder.addEntity(type, x, y, object.getInt("id"));
                addEntityImageWithId(entity, x, y, entity.getId());
            } else {
                BuilderEntity entity = new BuilderEntity(type);
                builder.addEntity(type, x, y);
                addEntityImage(entity, x, y);
            }
        }
    }

    private JSONObject readDungeonFromFile(File file) {
        try {
            return new JSONObject(new JSONTokener(new FileReader(file.getPath())));
        } catch (Exception e) {
            return null;
        }
    }

    private String goalsToString(JSONObject goal) {
        String goalType = goal.getString("goal");

        if (goalType.equals("AND") || goalType.equals("OR")) {
            JSONArray subGoals = goal.getJSONArray("subgoals");
            String goalConjunction = "(";
            for (int i = 0; i < subGoals.length(); i++) {
                goalConjunction += goalsToString(subGoals.getJSONObject(i));
                if (i != subGoals.length() - 1) {
                    goalConjunction += " " + goalType + " ";
                }
            }
            return goalConjunction + ")";
        } else {
            return "(" + goalType + ")";
        }
    }

}