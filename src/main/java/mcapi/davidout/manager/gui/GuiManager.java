package mcapi.davidout.manager.gui;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.*;

public class GuiManager implements IGuiManager, Listener {

    private final List<IGui> guis;
    public GuiManager() {
        this.guis = new ArrayList<>();
    }


    @Override
    public void registerGui(IGui gui) {
        this.guis.add(gui);
    }

    @Override
    public List<IGui> getGuis() {
        return this.guis;
    }


    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if(e.getView().getTopInventory() == null) {
            return;
        }

        Inventory topInventory = e.getView().getTopInventory();
        if (!(topInventory.getHolder() instanceof GuiHolder)) {
            return; // or handle the case when the holder is not a GuiHolder
        }

        GuiHolder holder = (GuiHolder) topInventory.getHolder();
        IGui openGui = this.guis.stream().filter(gui ->
                gui.getUUID().equals(holder.getInventoryUUID())
        ).findFirst()
                .orElse(null);

        if(openGui == null) {
            return;
        }


        openGui.onClick(e);
    }




}
