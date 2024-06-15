package mcapi.davidout.manager.gui;

import mcapi.davidout.utils.TextUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;


public abstract class IGui {

  private final UUID identifier;

  public IGui(UUID inventoryUUID) {
    this.identifier = inventoryUUID;
  }

  public IGui() {
    this.identifier = UUID.randomUUID();
  }

  public void openInventory(Player player, Object... arguments) {
    if (player == null) {
      return;
    }

    Inventory inventory = Bukkit.createInventory(new GuiHolder(this.identifier), this.getRows() * 9, TextUtils.formatColorCodes(this.getTitle(player, arguments)));
    Inventory customInventory = this.createInventory(inventory, player, arguments);

    if (customInventory != null) {
      inventory = customInventory;
    }

    updateInventory(customInventory);
    player.openInventory(inventory);
  }

  public void updateInventory(Inventory inventory) {

  }

  public void fillInventory(Inventory inventory, ItemStack itemStack) {
    for(int i = 0; i < (this.getRows() * 9); i++) {
      inventory.setItem(i, itemStack);
    }
  }



  public boolean isGui(Inventory inventory) {
    return inventory.getHolder() instanceof GuiHolder && ((GuiHolder)inventory).getInventoryUUID().equals(this.identifier);
  }

  public abstract String getTitle(Player p, Object... arguments);

  public abstract int getRows();

  public abstract Inventory createInventory(Inventory inventory, Player player, Object... objects);

  public abstract void onClick(InventoryClickEvent e);
  public void onClose(InventoryCloseEvent e) {

  }

  public UUID getUUID() {
    return this.identifier;
  }


}
