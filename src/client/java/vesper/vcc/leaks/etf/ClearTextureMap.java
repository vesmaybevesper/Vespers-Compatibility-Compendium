/*
package vesper.vcc.leaks.etf;

import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import traben.entity_texture_features.features.ETFManager;
import vesper.vcc.events.ClientRespawnEvent;
import vesper.vcc.utils.Util;

import java.lang.reflect.Method;

public class ClearTextureMap {
    private static final Logger LOGGER = LoggerFactory.getLogger("VCC/ETF");

    public static void init() {
        if (!FabricLoader.getInstance().isModLoaded("entity_texture_features")) {
            return;
        }

        try {
            Class<?> etfManager = Class.forName("traben.entity_texture_features.features.ETFManager");
            Method etfInstance = etfManager.getDeclaredMethod("getInstance");

            ClientRespawnEvent.EVENT.register((gameMode, oldPlayer, newPlayer, clientConnection) -> {
                try {
                    var runTime = (ETFManager) etfInstance.invoke();
                    runTime.PLAYER_TEXTURE_MAP.clear();
                } catch (Exception e) {
                    Util.error("Failed to clear ETF context");
                }
            });
        } catch (NoSuchFieldException | NoSuchMethodException | ClassNotFoundException e) {
            Util.warn("ETF class not found, skipping ETF fix");
        }
    }
}*/
