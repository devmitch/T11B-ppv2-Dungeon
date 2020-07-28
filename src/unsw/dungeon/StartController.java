package unsw.dungeon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class StartController {
    
    // this is going to be intermediate
    private LevelSelectScreen levelSelectScreen;

    public StartController() {
        super();
    }

    @FXML
    public void handleTutorial(ActionEvent event) {
        System.out.println("Load the tutorial here!");
    }

    @FXML
    public void handleLevelSelect(ActionEvent event) {
        levelSelectScreen.start();
    }

    public void setLevelSelectScreen(LevelSelectScreen levelSelectScreen) {
        this.levelSelectScreen = levelSelectScreen;
    }

}