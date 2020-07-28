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
    
    public DungeonScreen(Stage stage, String filename) throws IOException {
        this.stage = stage;
        title = "The name's John, Dung John";

        // the dungeon controller is going to be made elsewhere
        this.dungeonLoader = new DungeonControllerLoader(filename);
        this.dungeonController = dungeonLoader.loadController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(dungeonController);

        Parent root = loader.load();
        scene = new Scene(root);
    }

    public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        // this will have to change when new ui elements are added
        scene.getRoot().requestFocus();
        stage.show();
    }

    public DungeonController getController() throws IOException {
        return dungeonController;
    }

}