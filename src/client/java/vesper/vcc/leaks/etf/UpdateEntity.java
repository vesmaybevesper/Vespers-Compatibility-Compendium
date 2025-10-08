package vesper.vcc.leaks.etf;

import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import traben.entity_texture_features.features.ETFManager;
import traben.entity_texture_features.features.player.ETFPlayerEntity;
import vesper.vcc.events.ClientRespawnEvent;

public class UpdateEntity {
    private static final Logger LOGGER = LoggerFactory.getLogger("VCC/ETF");

    public static void init(){
        if (!FabricLoader.getInstance().isModLoaded("entity_texture_features")) {
            return;
        }

        try {
            ClientRespawnEvent.EVENT.register((gameMode, oldPlayer, newPlayer, connection) -> {
                var etfTexture = ETFManager.getInstance().PLAYER_TEXTURE_MAP.get(oldPlayer.getUUID());
                if (etfTexture == null) return;
                if (newPlayer instanceof ETFPlayerEntity playerEntity){
                    etfTexture.player = playerEntity;
                }
            });
        } catch (Exception e) {
            LOGGER.error("Error updating ETF entity on respawn", e);
        }
    }
}
