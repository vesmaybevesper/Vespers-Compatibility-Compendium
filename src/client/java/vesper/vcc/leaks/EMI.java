package vesper.vcc.leaks;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

public class EMI {
    private static final Logger LOGGER = LoggerFactory.getLogger("VCC/EMI");
    private static boolean initialized = false;

    public static void initialize() {
        if (initialized || !FabricLoader.getInstance().isModLoaded("emi")) {
            return;
        }

        try {
            // Use reflection to avoid direct class references
            Class<?> emiHistoryClass = Class.forName("dev.emi.emi.runtime.EmiHistory");
            Method clearMethod = emiHistoryClass.getDeclaredMethod("clear");

            // Register events
            ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
                try {
                    clearMethod.invoke(null);
                } catch (Exception e) {
                    LOGGER.error("Error clearing EMI history on join", e);
                }
            });

            ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> {
                try {
                    clearMethod.invoke(null);
                } catch (Exception e) {
                    LOGGER.error("Error clearing EMI history on disconnect", e);
                }
            });

            initialized = true;
            LOGGER.info("EMI history clearing registered successfully");

        } catch (ClassNotFoundException e) {
            LOGGER.debug("EMI classes not found, skipping EMI fix");
        } catch (Exception e) {
            LOGGER.error("Failed to initialize EMI fix", e);
        }
    }
}
