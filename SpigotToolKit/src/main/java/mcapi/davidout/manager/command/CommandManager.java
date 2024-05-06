package mcapi.davidout.manager.command;

import mcapi.davidout.manager.command.ICommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.*;

public class CommandManager implements org.bukkit.command.CommandExecutor, org.bukkit.command.TabCompleter {
    private final JavaPlugin plugin;
    private final Map<String, ICommand> commandMap;

    public CommandManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.commandMap = new HashMap<>();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        ICommand icmd = commandMap.get(cmd.getName());
        if (icmd == null) {
            sender.sendMessage(ChatColor.RED + "[ERROR] Command could not be found");
            return false;
        }
        return icmd.executeCommand(sender, label, args);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        ICommand icmd = commandMap.get(cmd.getName());
        if (icmd == null) {
            return Collections.emptyList();
        }
        return icmd.autoComplete(sender, alias, args);
    }

    public boolean registerCommand(ICommand command) {
        String name = command.getName().toLowerCase();
        if (commandMap.containsKey(name)) {
            return false; // Command already registered
        }
        commandMap.put(name, command);
        PluginCommand bukkitCommand = plugin.getCommand(name);

        if (bukkitCommand != null) {
            bukkitCommand.setExecutor(this);
            bukkitCommand.setTabCompleter(this);
            bukkitCommand.setAliases(command.getAliasList());
            return false;
        }
        return true;
    }

    public boolean unregisterCommand(ICommand command) {
        return commandMap.remove(command.getName().toLowerCase()) != null;
    }

    public ICommand getCommandByName(String name) {
        return commandMap.get(name.toLowerCase());
    }

    public List<ICommand> getCommandList() {
        return new ArrayList<>(commandMap.values());
    }
}
