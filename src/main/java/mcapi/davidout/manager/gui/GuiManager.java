package mcapi.davidout.manager.gui;

import mcapi.davidout.utils.TextUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.regex.Pattern;

public class GuiManager implements IGuiManager, EventListener {

    private List<IGui> guis;
    private Pattern VARIABLE_PATTERN;

    public GuiManager() {
        this.guis = new ArrayList<>();
        this.VARIABLE_PATTERN = Pattern.compile("\\{\\w+\\}");
    }

    @Override
    public List<IGui> getGuis() {
        return guis;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Inventory clickedInvetory = e.getClickedInventory();
        HumanEntity entity = e.getWhoClicked();

        if (entity.getOpenInventory().getTopInventory().equals(entity.getInventory())) {
            return;
        }

        IGui gui = guis.stream().filter(iGui -> namesMatch(
                formatTitle(    iGui.getTitle() ),
                formatTitle(    clickedInvetory.getTitle()  )
                )
        ).findFirst().orElse(null);

        if(gui == null) {
            return;
        }

        this.openInventory(entity, gui);
    }

    private String formatTitle(String title) {
        return TextUtils.formatColorCodes(title);
    }

    private boolean namesMatch(String originalName, String nameToCheck) {
        List<Integer> variableIndexes = TextUtils.getVariableIndexes(originalName, VARIABLE_PATTERN);

        return TextUtils.removeVariables(
                variableIndexes, originalName
        ).equals(
                TextUtils.removeVariables(
                        variableIndexes, nameToCheck
                )
        );
    }

    private void openInventory(HumanEntity entity, IGui gui) {
        Inventory inv = Bukkit.createInventory(null, gui.getRows() * 9, formatTitle(gui.getTitle()));
    }
}
