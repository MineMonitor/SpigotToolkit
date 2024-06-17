package mcapi.davidout.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemCreator {

    private static ItemStack normalizeItemStack(ItemStack itemStack) {
        return (itemStack == null) ? new ItemStack(Material.AIR) : itemStack;
    }

    private static boolean canUseItemStack(ItemStack itemStack) {
        return normalizeItemStack(itemStack).getType() != Material.AIR;
    }

    public static ItemStack editItem(ItemStack itemStack, String displayName) {
        if(!canUseItemStack(itemStack)) {
            return normalizeItemStack(itemStack);
        }

        if(displayName == null) {
            return itemStack;
        }

        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(TextUtils.formatColorCodes(displayName));
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public static ItemStack editItem(ItemStack itemStack, List<String> lore) {
        if(!canUseItemStack(itemStack)) {
            return normalizeItemStack(itemStack);
        }

        if(lore == null || lore.isEmpty()) {
            return itemStack;
        }

        ItemMeta meta = itemStack.getItemMeta();
        meta.setLore(
                TextUtils.formatLore(lore)
        );
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public static ItemStack editItem(ItemStack itemStack, String displayName, List<String> lore) {
        itemStack = editItem(itemStack, displayName);
        if(lore == null || lore.isEmpty()) {
            return itemStack;
        }
        return editItem(itemStack, lore);
    }

    public static ItemStack editItem(ItemStack itemStack, String displayName, String ...lore) {
        return editItem(itemStack, displayName, Arrays.asList(lore));
    }

    public static ItemStack createItem(Material material) {
        return new ItemStack(material);
    }


    public static ItemStack createItem(Material material, int amount) {
        return new ItemStack(material, amount);
    }


    public static ItemStack createItem(Material material, String displayName) {
        ItemStack item = createItem(material);
        return editItem(item, displayName);
    }

    public static ItemStack createItem(Material material,String displayName, List<String> lore) {
       return editItem(
               createItem(material)
               , displayName,  lore);
    }

    public static ItemStack createItem(Material material, String displayName, String... lore) {
       return editItem(
               createItem(material)
               , displayName, lore);
    }

    public static Material getTypeItem(String materialName, String legacyMaterialName) {
        return (ServerUtils.versionLowerThan("1.13")) ? Material.getMaterial(legacyMaterialName) : Material.getMaterial(materialName);
    }

   public static ItemStack getTypeItem(String materialName, String legacyMaterialName, int number) {
        Material mat = getTypeItem(materialName, legacyMaterialName);
       return (ServerUtils.versionLowerThan("1.13")) ? new ItemStack(mat, 1, (short) number) : new ItemStack(mat);
   }

    public static ItemStack getTypeItem(Material material, String legacyMaterialName, int number) {
        return getTypeItem(material.name(), legacyMaterialName, number);
    }

    public static ItemStack getTypeItem(String materialName, Material legacyMaterial, int number) {
        return getTypeItem(materialName, legacyMaterial.name(), number);
    }



}
