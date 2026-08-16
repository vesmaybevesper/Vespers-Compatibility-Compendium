package dev.vesper.vcc.fixes.leaks.etf;

import dev.vesper.eveningstarlib.platform.fabric.events.ClientRespawnEventCallback;
import dev.vesper.vcc.VCC;
import net.fabricmc.loader.api.FabricLoader;
import traben.entity_texture_features.features.ETFManager;
import traben.entity_texture_features.features.player.ETFPlayerEntity;

public class UpdateETFEntityOnRespawn {
	public static void init(){
		if (!FabricLoader.getInstance().isModLoaded("entity_texture_features")) return;

		try {
			ClientRespawnEventCallback.EVENT.register((gameMode, oldPlayer, newPlayer, connection) -> {
				var etfTexture = ETFManager.getInstance().PLAYER_TEXTURE_MAP.get(oldPlayer.getUUID());
				if (etfTexture == null) return;
				if (newPlayer instanceof ETFPlayerEntity playerEntity){
					etfTexture.player = playerEntity;
				}
			});
		} catch (Exception e) {
			VCC.LOGGER.error("Error updating ETF entity on respawn", e);
		}
	}
}
