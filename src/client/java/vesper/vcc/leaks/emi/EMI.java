package vesper.vcc.leaks.emi;

import dev.vesper.eveningstarlib.fabric.events.ClientRespawnEventCallback;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.Method;

public class EMI {
    private static final Logger LOGGER = LoggerFactory.getLogger("VCC/EMI");

    public static void initialize() {
        if (!FabricLoader.getInstance().isModLoaded("emi")) {
            return;
        }

        try {
            Class<?> emiHistoryClass = Class.forName("dev.emi.emi.runtime.EmiHistory");
            Method clearMethod = emiHistoryClass.getDeclaredMethod("clear");

            ClientRespawnEventCallback.EVENT.register((gameMode, oldPlayer, newPlayer, connection) -> {
                try {
                    clearMethod.invoke(null);
                } catch (Exception e) {
                    LOGGER.error("Error clearing EMI history on respawn", e);
                }
            });

        } catch (ClassNotFoundException e) {
            LOGGER.warn("EMI classes not found, skipping EMI fix");
        } catch (Exception e) {
            LOGGER.error("Failed to initialize EMI fix", e);
        }
    }
}
