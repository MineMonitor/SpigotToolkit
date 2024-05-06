package mcapi.davidout;

import mcapi.davidout.manager.file.CouldNotCreateFileException;
import mcapi.davidout.manager.file.json.JsonFileManager;
import mcapi.davidout.manager.file.yaml.YamlFileManager;
import mcapi.davidout.manager.language.MessageManager;
import mcapi.davidout.manager.language.bundle.MessageBundle;
import mcapi.davidout.manager.language.message.MessageKey;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Main {


    public static void main(String[] args) throws IOException {
        Config config = new Config("test", "1.0.0", 10);
        File file = new File("test-folder");
        file.mkdirs();


        JsonFileManager fileManager = new JsonFileManager(file);
        MessageManager manager = new MessageManager(fileManager);
        manager.loadMessageBundles();
        manager.setCurrentBundle("de");
        manager.addMessageBundle(getBundle());
        System.out.println(manager.getBundles().size());

        System.out.println(manager.getMessage("onEnable"));



    }

    public static MessageBundle getBundle() {
        HashMap<String, String> messages = new HashMap<>();
        messages.put("onEnable", "This plugin enabled");
        messages.put("onDisable", "This plugin disabled");
        return new MessageBundle("en", messages);
    }


}

class Config {

     public String pluginName;
     public String version;
     public int maxPlayers;

     public Config(String name, String version, int maxPlayers) {
         this.pluginName = name;
         this.version = version;
         this.maxPlayers = maxPlayers;
     }

}