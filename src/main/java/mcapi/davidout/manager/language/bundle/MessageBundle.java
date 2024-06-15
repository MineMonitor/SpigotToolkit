package mcapi.davidout.manager.language.bundle;

import java.util.HashMap;
import java.util.Map;

public class MessageBundle implements IMessageBundle {

    private final String name;
    private final String prefix;
    private final Map<String, String> messages;

    public MessageBundle() {
        this.name = "";
        this.messages = new HashMap<>();
        this.prefix = "";
    }

    public MessageBundle(String name, Map<String, String> messages, String prefix) {
        this.name = name;
        this.messages = messages;
        this.prefix = prefix;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPrefix() {
        return prefix;
    }

    @Override
    public Map<String, String> getMessages() {
        return messages;
    }
}
