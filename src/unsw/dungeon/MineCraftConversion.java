package unsw.dungeon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import javafx.stage.FileChooser;
import javafx.stage.Window;

public class MineCraftConversion {

    private JSONObject json;

    public MineCraftConversion(String filename) throws FileNotFoundException {
        this.json = new JSONObject(new JSONTokener(new FileReader(filename)));
    }

    public void convert(Window window) {

        FileChooser fileChooser = new FileChooser();
 
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialFileName("test.txt");

        File file = fileChooser.showSaveDialog(window);
        if (file != null) {
            writeToFile(file);
        }
    }

    private void writeToFile(File file) {
        try {
            FileWriter fw = new FileWriter(file);
            JSONArray jsonEntities = json.getJSONArray("entities");
            JSONObject playerObj = getPlayerObj();
            for (int i = 0; i < jsonEntities.length(); i++) {
                JSONObject obj = jsonEntities.getJSONObject(i);
                String type = obj.getString("type");
                int x = obj.getInt("x");
                int y = obj.getInt("y");
                if (type.equals("wall") || type.equals("enemy") || type.equals("wizard")) {
                    fw.write(type + ";" + (x-playerObj.getInt("x")) + ";" + (y-playerObj.getInt("y")) + "\n");
                }
            }
            fw.close();
        } catch (IOException e) {
            //uhh
        }
    }

    private JSONObject getPlayerObj() {
        JSONArray jsonEntities = json.getJSONArray("entities");
        for (int i = 0; i < jsonEntities.length(); i++) {
            JSONObject obj = jsonEntities.getJSONObject(i);
            String type = obj.getString("type");
            if (type.equals("player")) {
                return obj;
            }
        }
        return null; // uhhh
    }
}