package mcapi.davidout.manager.command;

import java.util.List;

public interface ICommandManager {

    boolean registerCommand(ICommand command);
    boolean unregisterCommand(ICommand command);

    List<ICommand> getCommandList();
}
