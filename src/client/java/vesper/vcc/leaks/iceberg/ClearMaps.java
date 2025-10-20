package vesper.vcc.leaks.iceberg;

import com.anthonyhilyard.iceberg.util.EntityCollector;
import dev.vesper.eveningstarlib.fabric.events.LevelEvents;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vesper.vcc.utils.Util;

import java.lang.invoke.VarHandle;
import java.util.Map;

public class ClearMaps {
    private static final Logger LOGGER = LoggerFactory.getLogger("VCC/Iceberg");
    public static void init(){
        if (!FabricLoader.getInstance().isModLoaded("iceberg")) return;

        try {
            VarHandle WRAPPED_LEVELS_MAP = Util.ReflectionHelper.getFieldFromClass(EntityCollector.class, "wrappedLevelsMap", Map.class, true);
            VarHandle ENTITY_CACHE = Util.ReflectionHelper.getFieldFromClass(EntityCollector.class, "entityCache", Map.class, true);

            LevelEvents.Unload.UNLOAD.register((LevelEvents.Unload event) ->{
                if (event.getLevel().isClientSide()){
                    var map1 = (Map) WRAPPED_LEVELS_MAP.get();
                    map1.clear();
                    var map2 = (Map) ENTITY_CACHE.get();
                    map2.clear();
                }
            });
        } catch (Exception e) {
            LOGGER.warn("Iceberg classes not found, skipping Iceberg Maps fix");
        }
    }
}
