package mcapi.davidout.manager.language;

import mcapi.davidout.manager.language.bundle.IMessageBundle;
import mcapi.davidout.manager.language.message.MessageKey;

import java.util.List;

public interface IMessageManager {

    IMessageBundle getCurrentBundle();
    List<IMessageBundle> getBundles();
    void setCurrentBundle(String bundleName);
    String getMessage(MessageKey key);
    String getMessage(String key);

    String getMessageFromBundle(IMessageBundle bundle, MessageKey key);
    String getMessageFromBundle(IMessageBundle bundle, String key);

    void addMessageBundle(IMessageBundle bundle);

    boolean loadMessageBundles();
    boolean saveMessageBundles();
}
