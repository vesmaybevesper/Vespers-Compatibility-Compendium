package dev.vesper.vcc.fixes.leaks.jade;

import com.google.common.cache.Cache;
import dev.vesper.eveningstarlib.platform.fabric.events.ClientRespawnEventCallback;
import dev.vesper.eveningstarlib.platform.fabric.events.LevelEvents;
import dev.vesper.vcc.VCC;
import dev.vesper.vcc.util.ReflectionHelper;
import net.fabricmc.loader.api.FabricLoader;
//? <=1.21.1{
/*import snownee.jade.JadeClient;
import snownee.jade.impl.ObjectDataCenter;
*///?}

import java.lang.invoke.VarHandle;

public class ClearAccessorOnUnload {
//? <=1.21.1{
	/*public static void init(){
		if (!FabricLoader.getInstance().isModLoaded("jade")) return;

		try {
			VarHandle HIDE_MOD_NAME = ReflectionHelper.getFieldFromClass(JadeClient.class, "hideModName", Cache.class, true);

			LevelEvents.Unload.UNLOAD.register((LevelEvents.Unload event) -> {
				if (event.getLevel().isClientSide()){
					ObjectDataCenter.set(null);
					var cache = (Cache) HIDE_MOD_NAME.get();
					cache.invalidateAll();
				}
			});

			ClientRespawnEventCallback.EVENT.register(((multiPlayerGameMode, localPlayer, localPlayer1, connection) -> {
				ObjectDataCenter.set(null);
			}));

		} catch (Exception e) {
			VCC.LOGGER.warn("Jade class not found, skipping Jade fix");
		}
	}
*///?}
}
