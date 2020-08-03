package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LevelSelectScreen implements BackTrackScreen {
    
    private Stage stage;
    private String title;
    private LevelSelectController controller;
    private Scene scene;

    private DungeonScreen dungeonScreen;

    public LevelSelectScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Level Select";

        dungeonScreen = new DungeonScreen(stage);
        controller = new LevelSelectController(dungeonScreen);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LevelSelectView.fxml"));
        loader.setController(controller);

        
        controller.setLevelSelectScreen(this);

        Parent root = loader.load();
        scene = new Scene(root);
    }

    public void start() {
        controller.setDungeonNames();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public LevelSelectController getController() {
        return controller;
    }

    public void setStartScreen(StartScreen startScreen) {
        controller.setStartScreen(startScreen);
    }

}