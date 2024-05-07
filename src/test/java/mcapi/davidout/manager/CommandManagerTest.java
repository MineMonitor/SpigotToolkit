package mcapi.davidout.manager;

import mcapi.davidout.manager.command.CommandManager;
import mcapi.davidout.manager.command.ICommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CommandManagerTest {

    private CommandManager commandManager;
    private JavaPlugin mockPlugin;

    @BeforeEach
    public void setUp() {
        mockPlugin = mock(JavaPlugin.class);
        commandManager = new CommandManager(mockPlugin);
    }

    @Test
    public void testRegisterCommand() {
        ICommand mockCommand = mock(ICommand.class);
        when(mockCommand.getName()).thenReturn("testcommand");
        assertTrue(commandManager.registerCommand(mockCommand));
        assertNotNull(commandManager.getCommandByName("testcommand"));
    }

    @Test
    public void testUnregisterCommand() {
        ICommand mockCommand = mock(ICommand.class);
        when(mockCommand.getName()).thenReturn("testcommand");
        commandManager.registerCommand(mockCommand);
        assertTrue(commandManager.unregisterCommand(mockCommand));
        assertNull(commandManager.getCommandByName("testcommand"));
    }

    // Add more tests for other methods as needed
}

