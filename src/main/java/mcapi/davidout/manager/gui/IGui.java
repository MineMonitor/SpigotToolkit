package mcapi.davidout.manager.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import java.util.UUID;


public abstract class IGui {

  private final UUID identifier;

  public IGui(UUID inventoryUUID) {
    this.identifier = inventoryUUID;
  }

  public IGui() {
    this.identifier = UUID.randomUUID();
  }

  public void openInventory(Player p, Object... arguments) {
    Inventory inventory = this.createInventory(
            Bukkit.createInventory(new GuiHolder(this.identifier), this.getRows() * 9, this.getTitle(p, arguments)),
            p,
            arguments
    );

    p.openInventory(inventory);
  }

  public boolean isGui(Inventory inventory) {
    return inventory.getHolder() instanceof GuiHolder && ((GuiHolder)inventory).getInventoryUUID().equals(this.identifier);
  }

  public abstract String getTitle(Player p, Object... arguments);

  public abstract int getRows();

  public abstract Inventory createInventory(Inventory inventory, Player player, Object... objects);

  public abstract void onClick(InventoryClickEvent e);

  public UUID getUUID() {
    return this.identifier;
  }


}
