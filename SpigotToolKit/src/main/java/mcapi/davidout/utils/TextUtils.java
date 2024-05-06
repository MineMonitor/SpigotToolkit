package mcapi.davidout.utils;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class TextUtils {

    public static String formatColorCodes(String textToFormat) {
        return ChatColor.translateAlternateColorCodes('&', textToFormat);
    }

    public static List<String> formatLore(List<String> lore) {
        List<String> returnedLore = new ArrayList<>();

        for(String currentLine : lore) {
            returnedLore.add(formatColorCodes(currentLine));
        }

        return returnedLore;
    }
}