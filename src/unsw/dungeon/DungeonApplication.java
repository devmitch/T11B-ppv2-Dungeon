package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Dungeon");

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("itemtest.json");
        DungeonScreen dungeonScreen = new DungeonScreen(primaryStage, dungeonLoader);

        StartScreen startScreen = new StartScreen(primaryStage);
        startScreen.getController().setDungeonScreen(dungeonScreen);

        startScreen.start();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
