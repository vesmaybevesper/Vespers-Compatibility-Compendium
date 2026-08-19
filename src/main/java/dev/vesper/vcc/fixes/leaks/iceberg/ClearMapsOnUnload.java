package dev.vesper.vcc.fixes.leaks.iceberg;

import com.anthonyhilyard.iceberg.util.EntityCollector;
import dev.vesper.eveningstarlib.EveningStarLib;
import dev.vesper.eveningstarlib.platform.fabric.events.LevelEvents;
import dev.vesper.vcc.VCC;
import dev.vesper.vcc.util.ReflectionHelper;

import java.lang.invoke.VarHandle;
import java.util.Map;

public class ClearMapsOnUnload {
	public static void init(){
		if (!EveningStarLib.isModLoaded("iceberg")) return;

		try {
			VarHandle WRAPPED_LEVELS_MAP = ReflectionHelper.getFieldFromClass(EntityCollector.class, "wrappedLevelsMap", Map.class, true);
			//? <=1.21.1{
			/*VarHandle ENTITY_CACHE = ReflectionHelper.getFieldFromClass(EntityCollector.class, "entityCache", Map.class, true);
			*///?}

			LevelEvents.Unload.UNLOAD.register((LevelEvents.Unload event) ->{
				if (event.getLevel().isClientSide()){
					var map1 = (Map) WRAPPED_LEVELS_MAP.get();
					map1.clear();
					//? <=1.21.1{
					/*var  map2 = (Map) ENTITY_CACHE.get();
					map2.clear();
					*///?}
				}
			});
		} catch (Exception e) {
			VCC.LOGGER.warn("Iceberg classes not found, skipping Iceberg Maps fix");
		}

	}
}
