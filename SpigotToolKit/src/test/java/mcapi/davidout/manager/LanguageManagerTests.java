package mcapi.davidout.manager;

import mcapi.davidout.manager.file.IFileManager;
import mcapi.davidout.manager.file.json.JsonFileManager;
import mcapi.davidout.manager.language.MessageManager;
import mcapi.davidout.manager.language.bundle.IMessageBundle;
import mcapi.davidout.manager.language.bundle.MessageBundle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

import static org.junit.Assert.assertTrue;

public class LanguageManagerTests {

    private File baseFolder;
    private IFileManager fileManager;
    private MessageManager messageManager;
    private IMessageBundle bundle;

    @BeforeEach
    void setUp() {
        this.baseFolder = new File("test-folder");
        if(!baseFolder.exists()) {
            baseFolder.mkdirs();
        }

        this.fileManager = new JsonFileManager(baseFolder);
        this.messageManager = new MessageManager(this.fileManager);
        this.bundle = getBundle();
    }

    @Test
    void saveMessageBundles() {
        messageManager.addMessageBundle(bundle);
        boolean created = messageManager.saveMessageBundles();

        Assertions.assertTrue(created);
        File[] filteredFiles = Arrays.stream(Objects.requireNonNull(baseFolder.listFiles()))
                .filter(file -> file.getName().startsWith(bundle.getName()))
                .toArray(File[]::new);

        Assertions.assertEquals(1, filteredFiles.length);
    }

    @Test
    void loadMessageBundles() {
        boolean loaded = messageManager.loadMessageBundles();
        Assertions.assertTrue(loaded);
        Assertions.assertEquals(1, messageManager.getBundles().size());
    }

    public MessageBundle getBundle() {
        HashMap<String, String> messages = new HashMap<>();
        messages.put("onEnable", "This plugin enabled");
        messages.put("onDisable", "This plugin disabled");
        return new MessageBundle("en", messages);
    }
}
