package mcapi.davidout.utils;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ResourceUtils {

    public static boolean copyFileFromResources(JavaPlugin plugin, String resourcePath, String destinationPath) throws IOException {
        InputStream inputStream = plugin.getResource(resourcePath);
        if (inputStream == null) {
            throw new IOException("Resource not found: " + resourcePath);
        }

        // Create the destination directory if it doesn't exist
        Path destinationDirectory = Paths.get(destinationPath).getParent();
        if (!Files.exists(destinationDirectory)) {
            Files.createDirectories(destinationDirectory);
        }

        // Copy the resource to the destination file
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(destinationPath))) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
        }

        return true;
    }
}
