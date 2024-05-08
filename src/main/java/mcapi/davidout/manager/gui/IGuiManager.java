package mcapi.davidout.manager.gui;

import org.bukkit.entity.Player;

import java.util.List;
import java.util.Objects;

public interface IGuiManager {

    public void registerGui(IGui gui);
    List<IGui> getGuis();


}
