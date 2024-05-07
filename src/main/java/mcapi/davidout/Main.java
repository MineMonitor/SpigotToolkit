package mcapi.davidout;

import mcapi.davidout.manager.file.CouldNotCreateFileException;
import mcapi.davidout.manager.file.json.JsonFileManager;
import mcapi.davidout.manager.file.yaml.YamlFileManager;
import mcapi.davidout.manager.language.MessageManager;
import mcapi.davidout.manager.language.bundle.MessageBundle;
import mcapi.davidout.manager.language.message.MessageKey;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {

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