package mcapi.davidout.utils;

import org.bukkit.Bukkit;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ServerUtilsTest {

    @Test
    void normalizeVersionTest() {
        String version = "1.8.8.R0.1.SNAPSHOT";
        Assertions.assertEquals(version, ServerUtils.normalizeVersion(version));
        Assertions.assertEquals("1.8.8", ServerUtils.normalizeVersion(version, 3));
        Assertions.assertEquals("1.8", ServerUtils.normalizeVersion(version, 2));
    }


}
