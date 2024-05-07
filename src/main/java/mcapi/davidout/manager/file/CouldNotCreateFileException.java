package mcapi.davidout.manager.file;

public class CouldNotCreateFileException extends Exception {
    public CouldNotCreateFileException() {
        super("An error occured while creating the file.");
    }

    public CouldNotCreateFileException(String message) {
        super(message);
    }

    public CouldNotCreateFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public CouldNotCreateFileException(Throwable cause) {
        super("An error occured while creating the file. Error: ", cause);
    }
}
