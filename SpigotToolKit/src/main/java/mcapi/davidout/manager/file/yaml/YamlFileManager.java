package mcapi.davidout.manager.file.yaml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import mcapi.davidout.manager.file.CouldNotCreateFileException;
import mcapi.davidout.manager.file.IFileManager;

import java.io.File;
import java.io.IOException;

public class YamlFileManager implements IFileManager {

    private final ObjectMapper objectMapper;
    private final File baseFolder;

    public YamlFileManager(File baseFolder) {
        this.baseFolder = baseFolder;
        this.createBaseFolder();
        this.objectMapper = new ObjectMapper(new YAMLFactory());
    }

    private void createBaseFolder() {
        if (this.baseFolder.exists()) return;
        boolean created = baseFolder.mkdirs();
    }

    public String formatPath(String filePath) {
        File file = new File(filePath);
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        String extension = (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
        fileName = (dotIndex == -1) ? fileName : fileName.substring(0, dotIndex);
        return this.baseFolder.getPath() + File.separator + fileName + ".yaml";
    }


    @Override
    public <T> T loadFile(Class<T> fileClass, String path) throws IOException {
        return objectMapper.readValue(new File(formatPath(path) ), fileClass);
    }

    @Override
    public <T> boolean saveFile(T fileObject, String path) throws IOException {
        objectMapper.writeValue(new File(formatPath(path) ), fileObject);
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
