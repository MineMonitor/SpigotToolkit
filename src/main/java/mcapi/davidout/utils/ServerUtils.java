package mcapi.davidout.utils;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.Objects;

public class ServerUtils {

    public static String getBukkitVersion() {
        String[] parts = Bukkit.getBukkitVersion().split("-|\\."); // Split by hyphen or dot
        return parts[0] + "." + parts[1];
    }

    public static boolean isVersion(String version) {
        return Objects.equals(version, getBukkitVersion());
    }

    public static boolean isVersion(double versionNumber) {
        String serverVersion = getBukkitVersion();

        try {
            return Double.parseDouble(
                    serverVersion
            ) == versionNumber;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static boolean versionGreaterThan(double versionNumber) {
        String serverVersion = getBukkitVersion();

        try {
            double serverVersionNumber = Double.parseDouble(serverVersion);
            return serverVersionNumber > versionNumber;
        } catch (NumberFormatException ex) {
            return false;
        }
    }


}
