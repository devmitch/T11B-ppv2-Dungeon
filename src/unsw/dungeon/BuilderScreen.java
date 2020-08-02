package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BuilderScreen {
    
    private Stage stage;
    private String title;
    private BuilderController builderController;
    private Scene scene;
    
    public BuilderScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "DungJohn Builder";
        this.builderController = new BuilderController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BuilderView.fxml"));
        loader.setController(builderController);

        Parent root = loader.load();
        scene = new Scene(root);
    }

    public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        builderController.getSquares().requestFocus();
        stage.show();
    }

    public void setStartScreen(StartScreen startScreen) {
        builderController.setStartScreen(startScreen);
    }
}