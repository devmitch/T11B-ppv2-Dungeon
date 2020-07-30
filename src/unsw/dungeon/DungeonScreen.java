package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonScreen {
    
    private Stage stage;
    private String title;
    private DungeonControllerLoader dungeonLoader;
    private DungeonController dungeonController;
    private Scene scene;
    
    public DungeonScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "The name's John, Dung John";
    }

    public void start(String filename) throws IOException {
        loadDungeon(filename);

        stage.setTitle(title);
        stage.setScene(scene);
        dungeonController.getSquares().requestFocus();
        stage.show();
    }

    public void setLevelSelectScreen(LevelSelectScreen levelSelectScreen) {
        dungeonController.setLevelSelectScreen(levelSelectScreen);  
    }

    private void loadDungeon(String filename) throws IOException {
        this.dungeonLoader = new DungeonControllerLoader(filename);
        this.dungeonController = dungeonLoader.loadController();
        this.dungeonController.setDungeonControllerLoader(dungeonLoader);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(dungeonController);

        Parent root = loader.load();
        scene = new Scene(root);
    }

}