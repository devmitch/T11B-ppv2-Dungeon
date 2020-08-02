package unsw.dungeon;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class LevelSelectController {

    private DungeonScreen dungeonScreen;
    private LevelSelectScreen levelSelectScreen;
    private StartScreen startScreen;

    @FXML
    private ListView<FileEntry> levelListView;

    @FXML
    private Button selectLevelButton;

    @FXML
    private Label errorLabel;

    public LevelSelectController(DungeonScreen dungeonScreen) {
        this.dungeonScreen = dungeonScreen;
    }

    /**
     * Creates a screen a dungeon if that dungeon's json object can be loaded and there is a file
     * selected.
     * 
     * @param event
     * @throws IOException
     */
    @FXML
    public void handleLoadLevel(ActionEvent event) throws IOException {
        if (levelListView.getSelectionModel().getSelectedItem() == null) {
            return;
        }

        FileEntry fileToLoad = levelListView.getSelectionModel().getSelectedItem();

        // Load the dungeon screen for the selected dungeon.
        try {
            dungeonScreen.start(fileToLoad.getPath());
            dungeonScreen.setLevelSelectScreen(levelSelectScreen);
            errorLabel.setText("");
        } catch (Exception e) {
            errorLabel.setText(fileToLoad.toString() + " could not be loaded.");
        }
    }

    @FXML
    public void handleGoBack(ActionEvent event) {
        startScreen.start();
    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            startScreen.start();
        }
    } 

    @FXML
    public void initialize() {
        // add all the names that are in the dungeon folder
        setDungeonNames();
    }

    public void setStartScreen(StartScreen startScreen) {
        this.startScreen = startScreen;
    }

    public void setLevelSelectScreen(LevelSelectScreen levelSelectScreen) {
        this.levelSelectScreen = levelSelectScreen;
    }

    /**
     * Sets the list view elements to be the elements found in the dungeons folder.
     */
    private void setDungeonNames() {
        File dungeonFolder = new File("dungeons");
        ObservableList<FileEntry> observableDungeonNames = FXCollections.observableArrayList(getDungeonNames(dungeonFolder));
        levelListView.setItems(observableDungeonNames);
    }

    /**
     * Gets all of the file entries that are in the given folder.
     * 
     * @param folder 
     * @return the list of file entries.
     */
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

/**
 * This is a helper class for holding the paths to dungeons.
 */
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
        String name = file.getPath();
        name = name.substring(name.indexOf('/') + 1);
        int dotIndex = name.lastIndexOf('.');
        if (dotIndex > -1)
            return name.substring(0, dotIndex);
        return name;
    }

}
