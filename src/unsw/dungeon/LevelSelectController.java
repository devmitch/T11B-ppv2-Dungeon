package unsw.dungeon;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LevelSelectController {

    private DungeonScreen dungeonScreen;
    private Stage stage;

    @FXML
    private ChoiceBox<FileEntry> levelChoiceBox;

    @FXML
    private Button selectLevelButton;

    public LevelSelectController(Stage primaryStage) {
        this.stage = primaryStage;
    }

    @FXML
    public void handleLevelChoicePicked(MouseEvent event) {
        setDungeonNames();
    }

    @FXML
    public void handleLoadLevel(ActionEvent event) throws IOException {
        FileEntry fileToLoad = levelChoiceBox.getValue();

        // this will attempt to load the level for the dungeon
        try {
            DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(fileToLoad.getPath());
            dungeonScreen = new DungeonScreen(stage, dungeonLoader);
            dungeonScreen.start();
        } catch (Exception e) {
            // display a message popup here instead
            System.out.println("The selected dungeon could not be loaded.");
        }
    }

    @FXML
    public void initialize() {
        // add all the names that are in the dungeon folder
        setDungeonNames();
    }

    private void setDungeonNames() {
        File dungeonFolder = new File("dungeons");
        ObservableList<FileEntry> observableDungeonNames = FXCollections.observableArrayList(getDungeonNames(dungeonFolder));
        levelChoiceBox.setItems(observableDungeonNames);
    }

    private ArrayList<FileEntry> getDungeonNames(File folder) {
        ArrayList<FileEntry> names = new ArrayList<>();
        for (File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                names.addAll(getDungeonNames(fileEntry));
            } else {
                names.add(new FileEntry(fileEntry));
            }
        }
        return names;
    }

}

class FileEntry {

    private File file;

    public FileEntry(File file) {
        this.file = file;
    }

    public String getPath() {
        return file.getPath();
    }

    @Override
    public String toString() {
        return file.getName();
    }

}
