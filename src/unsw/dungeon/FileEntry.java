package unsw.dungeon;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileEntry {

    private File file;

    public FileEntry(File file) {
        this.file = file;
    }

    public String getPath() {
        return file.getPath();
    }

    public String getContents() {
        try {
            byte[] data = Files.readAllBytes(Paths.get(getPath()));
            return new String(data);
        } catch (Exception e) {
            return "";
        }
    }

    public String getName() {
        return file.getName().substring(0, file.getName().lastIndexOf("."));
    }

    @Override
    public String toString() {
        String name = file.getPath();
        name = name.substring(name.indexOf('/') + 1);
        int dotIndex = name.lastIndexOf('.');
        if (dotIndex > -1)
            return name.substring(0, dotIndex);
        return name;
    }
    
}