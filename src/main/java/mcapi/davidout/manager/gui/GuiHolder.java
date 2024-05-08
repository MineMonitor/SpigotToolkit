package mcapi.davidout.manager.gui;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.UUID;

public class GuiHolder implements InventoryHolder {

    private final UUID uuid;

    public GuiHolder(UUID inventoryUUID) {
        this.uuid = inventoryUUID;
    }

    public UUID getInventoryUUID() {
        return this.uuid;
    }

    @Override
    public Inventory getInventory() {
        return null;
    }
}
