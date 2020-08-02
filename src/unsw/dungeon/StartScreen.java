package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartScreen {
    
    private Stage stage;
    private String title;
    private StartController controller;
    private Scene scene;

    public StartScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "The name's John, Dung John";

        controller = new StartController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StartView.fxml"));
        loader.setController(controller);

        LevelSelectScreen levelSelectScreen = new LevelSelectScreen(stage);
        levelSelectScreen.setStartScreen(this);

        controller.setLevelSelectScreen(levelSelectScreen);

        BuilderScreen builderScreen = new BuilderScreen(stage);
        builderScreen.setStartScreen(this);
        controller.setBuilderScreen(builderScreen);

        Parent root = loader.load();
        scene = new Scene(root);
    }

    public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

}