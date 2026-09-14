package dev.vesper.vcc.fixes.leaks.etf;

//? if fabric {
import dev.vesper.eveningstarlib.platform.fabric.events.ClientRespawnEventCallback;
import net.fabricmc.loader.api.FabricLoader;
//?}
import dev.vesper.vcc.VCC;
import dev.vesper.vcc.util.ReflectionHelper;
//? if neoforge {
/*import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.common.NeoForge;
*///?}
//? if fabric || (neoforge && >=26.1.2) {
import traben.entity_texture_features.features.ETFManager;
import traben.entity_texture_features.features.player.ETFPlayerEntity;
import traben.entity_texture_features.utils.ETFLruCache;
//?}

import java.lang.invoke.MethodType;

public class UpdateETFEntityOnRespawn {
	//? if fabric {
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
	//?} neoforge && >= 26.1.2{
	/*public UpdateETFEntityOnRespawn(){
		NeoForge.EVENT_BUS.addListener(this::updateEntityFromManager);
	}

	static {
		var dummy = ReflectionHelper.getMethodFromClass(ETFManager.class, "getInstance", MethodType.methodType(ETFManager.class), true);
		var dummy2 = ReflectionHelper.getFieldFromClass(ETFManager.class, "PLAYER_TEXTURE_MAP", ETFLruCache.class, false);
		var dummy3 = ReflectionHelper.getClass("traben.entity_texture_features.features.player.ETFPlayerTexture");
		var dummy4 = ReflectionHelper.getClass("traben.entity_texture_features.features.player.ETFPlayerEntity");
	}

	private void updateEntityFromManager(ClientPlayerNetworkEvent.Clone event){
		var etfTexture = ETFManager.getInstance().PLAYER_TEXTURE_MAP.get(event.getOldPlayer().getUUID());
		if (etfTexture == null) return;
		if (event.getNewPlayer() instanceof ETFPlayerEntity playerEntity){
			etfTexture.player = playerEntity;
		}
	}
	*///?}
}
