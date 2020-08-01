package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TutorialScreen implements BackTrackScreen {
    
    private Stage stage;
    private String title;
    private TutorialController controller;
    private Scene scene;

    private DungeonScreen dungeonScreen;

    public TutorialScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Tutorial";

        dungeonScreen = new DungeonScreen(stage);
        controller = new TutorialController(this, dungeonScreen);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TutorialView.fxml"));
        loader.setController(controller);

        Parent root = loader.load();
        scene = new Scene(root);
    }

    public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public void setStartScreen(StartScreen startScreen) {
        controller.setStartScreen(startScreen);
    }

}