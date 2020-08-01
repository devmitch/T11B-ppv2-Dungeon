package unsw.dungeon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class StartController {
    
    private TutorialScreen tutorialScreen;
    private LevelSelectScreen levelSelectScreen;

    public StartController() {
        super();
    }

    @FXML
    public void handleTutorial(ActionEvent event) {
        tutorialScreen.start();
    }

    @FXML
    public void handleLevelSelect(ActionEvent event) {
        levelSelectScreen.start();
    }

    public void setLevelSelectScreen(LevelSelectScreen levelSelectScreen) {
        this.levelSelectScreen = levelSelectScreen;
    }

    public void setTutorialScreen(TutorialScreen tutorialScreen) {
        this.tutorialScreen = tutorialScreen;
    }

}