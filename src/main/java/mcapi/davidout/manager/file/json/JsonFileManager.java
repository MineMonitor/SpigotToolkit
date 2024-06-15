package mcapi.davidout.manager.file.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import mcapi.davidout.manager.file.IFileManager;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonFileManager implements IFileManager {
    private final Gson gson;
    private final File baseFolder;

    public JsonFileManager(File baseFolder) {
        this.baseFolder = baseFolder;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public String formatPath(String filePath) {
        File file = new File(filePath);
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        String extension = (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
        fileName = (dotIndex == -1) ? fileName : fileName.substring(0, dotIndex);
        return this.baseFolder.getPath() + File.separator + fileName  + ".json";
    }

    @Override
    public <T> T loadFile(Class<T> fileClass, String path) throws IOException {
        try (FileReader reader = new FileReader(formatPath(path)  )) {
            return gson.fromJson(reader, fileClass);
        }
    }

    @Override
    public <T> boolean saveFile(T fileObject, String path) throws IOException {
        if(fileObject == null) {
            return false;
        }

        try (FileWriter writer = new FileWriter(formatPath(path)  )) {
            gson.toJson(fileObject, writer);
        }
        return true;
    }

    @Override
    public boolean deleteFile(String path) {
        File file = new File(formatPath(path) );
        return file.delete();
    }

    @Override
    public File getBaseFolder() {
        return this.baseFolder;
    }
}
