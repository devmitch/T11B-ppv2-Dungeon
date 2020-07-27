package unsw.dungeon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class StartController {
    
    // this is going to be intermediate
    private DungeonScreen dungeonScreen;

    public StartController() {
        super();
    }

    @FXML
    public void handleTutorial(ActionEvent event) {
        System.out.println("Load the tutorial here!");
    }

    @FXML
    public void handleLevelSelect(ActionEvent event) {
        dungeonScreen.start();
    }

    public void setDungeonScreen(DungeonScreen dungeonScreen) {
        this.dungeonScreen = dungeonScreen;
    }

}