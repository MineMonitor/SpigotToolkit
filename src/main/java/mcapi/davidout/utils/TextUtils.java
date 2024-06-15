package mcapi.davidout.utils;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtils {

    public static String formatColorCodes(String textToFormat) {
        if(textToFormat == null) {
            return "";
        }

        return ChatColor.translateAlternateColorCodes('&', textToFormat);
    }

    public static List<String> formatLore(List<String> lore) {
        List<String> returnedLore = new ArrayList<>();

        for(String currentLine : lore) {
            returnedLore.add(formatColorCodes(currentLine));
        }

        return returnedLore;
    }

    public static List<Integer> getVariableIndexes(String title, Pattern pattern) {
        String[] strings = title.split(" ");
        List<Integer> indexes = new ArrayList<>();

        for (int i = 0; i < strings.length; i++) {
            Matcher matcher = pattern.matcher(strings[i]);
            if (matcher.find()) {
                indexes.add(i);
            }
        }

        return indexes;
    }

    public static String removeVariables(List<Integer> variableIndexes, String s) {
        String[] words = s.split(" ");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            if (!variableIndexes.contains(i)) {
                sb.append(words[i]).append(" ");
            }
        }

        return sb.toString().trim();
    }
}