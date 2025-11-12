package vesper.vcc.leaks.jade;

import com.google.common.cache.Cache;
import dev.vesper.eveningstarlib.fabric.events.ClientRespawnEventCallback;
import dev.vesper.eveningstarlib.fabric.events.LevelEvents;
import net.fabricmc.loader.api.FabricLoader;
import snownee.jade.JadeClient;
import snownee.jade.impl.ObjectDataCenter;
import vesper.vcc.utils.Util;

import java.lang.invoke.VarHandle;

public class ClearAccessor {

    public static void init(){
        if (!FabricLoader.getInstance().isModLoaded("jade")) return;

        try {
            VarHandle HIDE_MOD_NAME = Util.ReflectionHelper.getFieldFromClass(JadeClient.class, "hideModName", Cache.class, true);

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
            Util.warn("Jade class not found, skipping Jade fix");
        }


    }
}
