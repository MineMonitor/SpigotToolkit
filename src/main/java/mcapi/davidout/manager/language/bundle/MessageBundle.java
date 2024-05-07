package mcapi.davidout.manager.language.bundle;

import java.util.HashMap;
import java.util.Map;

public class MessageBundle implements IMessageBundle {

    private final String name;
    private final Map<String, String> messages;

    public MessageBundle() {
        this.name = "";
        this.messages = new HashMap<>();
    }

    public MessageBundle(String name, Map<String, String> messages) {
        this.name = name;
        this.messages = messages;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public Map<String, String> getMessages() {
        return messages;
    }
}
