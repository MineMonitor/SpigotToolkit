package mcapi.davidout.manager.language;

import mcapi.davidout.manager.file.IFileManager;
import mcapi.davidout.manager.language.bundle.IMessageBundle;
import mcapi.davidout.manager.language.bundle.MessageBundle;
import mcapi.davidout.manager.language.bundle.SavedBundle;
import mcapi.davidout.manager.language.message.MessageKey;
import mcapi.davidout.utils.TextUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

;
public class MessageManager implements IMessageManager {

    private static MessageManager instance;
    public static MessageManager getInstance() {
        return instance;
    }

    private final List<IMessageBundle> messageBundles;
    private final IFileManager fileManager;
    private IMessageBundle currentBundle;

    public MessageManager(IFileManager fileManager) {
        this.fileManager = fileManager;
        this.messageBundles = new ArrayList<>();
        instance = this;
    }

    @Override
    public IMessageBundle getCurrentBundle() {
        return currentBundle;
    }

    @Override
    public List<IMessageBundle> getBundles() {
        return messageBundles;
    }

    @Override
    public void setCurrentBundle(String bundleName) {
        IMessageBundle bundle = messageBundles.stream().filter(iMessageBundle -> iMessageBundle.getName().equalsIgnoreCase(bundleName)).findFirst().orElse(null);
        if(bundle == null) return;
        this.currentBundle = bundle;
    }


    @Override
    public String getMessage(MessageKey key) {
        return getMessageFromBundle(currentBundle, key);
    }

    @Override
    public String getMessage(String key) {
        return getMessageFromBundle(currentBundle, key);
    }

    @Override
    public String getMessageFromBundle(IMessageBundle bundle, MessageKey key) {
        return getMessageFromBundle(bundle, key.getKey());
    }

    @Override
    public String getMessageFromBundle(IMessageBundle bundle, String key) {
        if(bundle == null) {
            return "No bundle selected";
        }

        return
                bundle.getMessages().entrySet().stream().
                filter(entry -> entry.getKey().equalsIgnoreCase(key)).findFirst().map(Map.Entry::getValue).orElse("&cCould not find the message.");
    }

    @Override
    public void addMessageBundle(IMessageBundle bundle) {
        messageBundles.add(bundle);
        if(currentBundle != null) return;
        currentBundle = bundle;
    }

    @Override
    public boolean loadMessageBundles() {
        File baseFolder = fileManager.getBaseFolder();

        if (baseFolder == null || !baseFolder.exists()) {
            return false;
        }

        File[] yamlFiles = baseFolder.listFiles((dir, name) -> name.endsWith(".json"));

        if (yamlFiles == null) {
            return false;
        }

        messageBundles.clear();

        for (File file : yamlFiles) {
            try {
                String bundleName = file.getName().replace(".json", "");
                SavedBundle savedBundle = fileManager.loadFile(SavedBundle.class, file.getName());

                if (savedBundle == null) {
                    System.err.println("Could not load file: " + file.getName());
                    continue;
                }

                Map<String, String> messages = savedBundle.messages;

                Optional<IMessageBundle> existingBundle = messageBundles.stream()
                        .filter(bundle -> bundle.getName().equalsIgnoreCase(bundleName))
                        .findFirst();

                existingBundle.ifPresent(bundle -> {
                    System.out.println("Removing existing bundle: " + bundle.getName());
                    messageBundles.remove(bundle);
                });

                System.out.println("Adding new bundle: " + bundleName);
                messageBundles.add(new MessageBundle(bundleName, messages));
            } catch (IOException e) {
                System.err.println("Error loading message bundles: " + e.getMessage());
                e.printStackTrace();
            }
        }

        currentBundle = messageBundles.isEmpty() ? null : messageBundles.get(0);
        return true;
    }

    @Override
    public boolean saveMessageBundles()  {
        for (IMessageBundle bundle : this.messageBundles) {
            try {
                fileManager.saveFile(new SavedBundle(bundle.getName(), bundle.getMessages()), bundle.getName());
            } catch (Exception ex) {
                ex.printStackTrace();
                return false;
            }
        }
        return true;
    }
}

