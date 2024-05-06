package mcapi.davidout.manager.language.message;

public enum Message implements MessageKey {

    NoPermission("NoPermission"),
    CouldNotSaveFile("CouldNotSaveFile");

    private final String key;

    Message(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return this.key;
    }


}
