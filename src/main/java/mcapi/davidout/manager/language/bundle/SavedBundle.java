package mcapi.davidout.manager.language.bundle;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SavedBundle {

    public String name;
    public Map<String, String> messages;

    public SavedBundle(String name, Map<String, String> messages) {
        this.name = name;
        this.messages = messages;
    }

    public SavedBundle() {
        this.messages = new HashMap<>();
        this.name = UUID.randomUUID().toString();
    }
}

