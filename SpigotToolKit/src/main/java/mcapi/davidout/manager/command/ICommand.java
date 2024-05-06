package mcapi.davidout.manager.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public interface ICommand {

    String getName();

    List<String> getAliasList();
    boolean executeCommand(CommandSender commandSender, String s, String[] arguments);
    List<String> autoComplete(CommandSender commandSender, String s, String[] strings);

}
