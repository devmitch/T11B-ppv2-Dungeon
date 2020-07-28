package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LevelSelectScreen {
    
    private Stage stage;
    private String title;
    private LevelSelectController controller;
    private Scene scene;

    public LevelSelectScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Level Select";

        controller = new LevelSelectController(stage);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LevelSelectView.fxml"));
        loader.setController(controller);

        Parent root = loader.load();
        scene = new Scene(root);
    }

    public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public LevelSelectController getController() {
        return controller;
    }

}