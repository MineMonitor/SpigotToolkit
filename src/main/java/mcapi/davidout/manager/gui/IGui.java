package mcapi.davidout.manager.gui;

import org.bukkit.inventory.Inventory;


public abstract class IGui {

  public abstract String getTitle();
  public abstract int getRows();

  public abstract void createInventory(Object... args);

}
