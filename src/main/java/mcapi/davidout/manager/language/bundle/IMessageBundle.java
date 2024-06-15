package mcapi.davidout.manager.language.bundle;

import java.util.Map;

public interface IMessageBundle {
    String getName();
    String getPrefix();
    Map<String, String> getMessages();
}
