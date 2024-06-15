package mcapi.davidout.manager.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
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

    public void openGui(IGui gui, Player player, Object ...args) {
        gui.openInventory(player, args);
    }


    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if(e.getView().getTopInventory() == null) {
            return;
        }

        Inventory topInventory = e.getView().getTopInventory();
        if (!(topInventory.getHolder() instanceof GuiHolder)) {
            return;
        }

        GuiHolder holder = (GuiHolder) topInventory.getHolder();
        IGui openGui = this.guis.stream().filter(gui ->
                gui.getUUID().equals(holder.getInventoryUUID())
        ).findFirst()
                .orElse(null);

        if(openGui == null || e.getClickedInventory() == null) {
            return;
        }

        openGui.onClick(e);
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if(e.getInventory() == null) {
            return;
        }

        Inventory topInventory = e.getInventory();
        if (!(topInventory.getHolder() instanceof GuiHolder)) {
            return;
        }

        GuiHolder holder = (GuiHolder) topInventory.getHolder();
        IGui openGui = this.guis.stream().filter(gui ->
                        gui.getUUID().equals(holder.getInventoryUUID())
                ).findFirst()
                .orElse(null);

        if(openGui == null) {
            return;
        }

        openGui.onClose(e);
    }




}
