package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

public class DungeonApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Dungeon");

        LevelSelectScreen levelSelectScreen = new LevelSelectScreen(primaryStage);
        StartScreen startScreen = new StartScreen(primaryStage);
        startScreen.getController().setLevelSelectScreen(levelSelectScreen);
        levelSelectScreen.getController().setStartScreen(startScreen);

        startScreen.start();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
