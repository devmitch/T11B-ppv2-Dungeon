package unsw.dungeon;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Pagination;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TutorialController {

    private TutorialScreen tutorialScreen;
    private StartScreen startScreen;
    private DungeonScreen dungeonScreen;

    @FXML
    private Pagination pagination;

    private ArrayList<FileEntry> informationList;

    public TutorialController(TutorialScreen tutorialScreen, DungeonScreen dungeonScreen) {
        this.tutorialScreen = tutorialScreen;
        this.dungeonScreen = dungeonScreen;
    }

    @FXML
    public void initialize() {

        // Get the tutorial messages, and sort them by tutorial names
        informationList = getTutorialInformation();
        Collections.sort(informationList, (f1, f2) -> {
            return f1.getName().compareTo(f2.getName());
        });

        pagination.setPageCount(informationList.size());
        pagination.setPageFactory((pageIndex) -> {

            GridPane gridPane = new GridPane();
            gridPane.setHgap(12);
            gridPane.setVgap(12);

            FileEntry entry = informationList.get(pageIndex);

            Label tutorialInfoLabel = new Label(entry.getContents());
            tutorialInfoLabel.setWrapText(true);
            tutorialInfoLabel.setTextAlignment(TextAlignment.CENTER);
            
            Button startTutorialButton = new Button("PRACTICE");
            startTutorialButton.setOnAction(new EventHandler<ActionEvent>(){
                public void handle(ActionEvent event) {
                    String dungeon = "dungeons/" + entry.getName() + ".json";
                    
                    // Load the dungeon here, that can come back to this screen when it is completed
                    try {
                        dungeonScreen.start(dungeon);
                        dungeonScreen.setBackTrackScreen(tutorialScreen);
                    } catch (Exception e) {
                        
                    }
                }
            });

            gridPane.add(tutorialInfoLabel, 0, 0);
            gridPane.add(startTutorialButton, 0, 1);

            GridPane.setHalignment(tutorialInfoLabel, HPos.CENTER);
            GridPane.setHalignment(startTutorialButton, HPos.CENTER);

            return gridPane;
        });

    }

    @FXML
    public void handleGoBack() {
        startScreen.start();
    }

    private ArrayList<FileEntry> getTutorialInformation() {
        ArrayList<FileEntry> information = new ArrayList<>();

        File tutorialFolder = new File("src/resources/tutorials");

        for (File fileEntry : tutorialFolder.listFiles()) {
            information.add(new FileEntry(fileEntry));
        }

        return information;
    }

    public void setStartScreen(StartScreen startScreen) {
        this.startScreen = startScreen;
    }
}