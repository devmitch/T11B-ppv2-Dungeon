package unsw.dungeon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class StartController {
    
    private TutorialScreen tutorialScreen;
    private LevelSelectScreen levelSelectScreen;
    private BuilderScreen builderScreen;

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

    @FXML
    public void handleLevelBuilder(ActionEvent event) {
        builderScreen.start();
    }

    public void setLevelSelectScreen(LevelSelectScreen levelSelectScreen) {
        this.levelSelectScreen = levelSelectScreen;
    }

    public void setTutorialScreen(TutorialScreen tutorialScreen) {
        this.tutorialScreen = tutorialScreen;
    }
    
    public void setBuilderScreen(BuilderScreen builderScreen) {
        this.builderScreen = builderScreen;
    }

}