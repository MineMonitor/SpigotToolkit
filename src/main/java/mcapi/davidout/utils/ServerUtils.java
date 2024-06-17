package mcapi.davidout.utils;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Objects;

public class ServerUtils {

    public static String getBukkitVersion(int numbers) {
        return normalizeVersion(Bukkit.getBukkitVersion(), numbers);
    }


    public static String normalizeVersion(String version, int numbers) {
        String[] parts = version.split("-|\\.");
        StringBuilder s = new StringBuilder(parts[0]);

        for (int i = 1; i < parts.length; i++) {
            if (i >= numbers && numbers != -1) {
                break;
            }
            s.append(".").append(parts[i]);
        }

        return s.toString();
    }

    public static String normalizeVersion(String version) {
        return normalizeVersion(version, -1);
    }

    public static boolean isVersion(String version) {
        return normalizeVersion(getBukkitVersion(version.split("\\.").length)).equalsIgnoreCase(version);
    }

    public static boolean versionGreaterThan(String versionNumber) {
        String[] serverVersionNumbers = normalizeVersion(Bukkit.getBukkitVersion(), versionNumber.split("\\.").length).split("\\.");
        String[] versionNumbers = versionNumber.split("\\.");

        for (int i = 0; i < versionNumbers.length; i++) {
            if (i >= serverVersionNumbers.length) {
                return false;
            }

            int requestedVersionPart = Integer.parseInt(versionNumbers[i]);
            int serverVersionPart = Integer.parseInt(serverVersionNumbers[i]);

            if (serverVersionPart < requestedVersionPart) {
                return false;
            } else if (serverVersionPart > requestedVersionPart) {
                return true;
            }
        }

        return false;
    }

    public static boolean versionLowerThan(String versionNumber) {
        return !versionGreaterThan(versionNumber) && !isVersion(versionNumber);
    }




}
