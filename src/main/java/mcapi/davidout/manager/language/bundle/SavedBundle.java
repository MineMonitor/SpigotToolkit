package mcapi.davidout.manager.language.bundle;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SavedBundle {

    public String name;
    public String prefix;
    public Map<String, String> messages;

    public SavedBundle(String name, Map<String, String> messages, String prefix) {
        this.name = name;
        this.messages = messages;
        this.prefix = prefix;
    }

    public SavedBundle() {
        this.messages = new HashMap<>();
        this.name = UUID.randomUUID().toString();
        this.prefix = "";
    }
}

