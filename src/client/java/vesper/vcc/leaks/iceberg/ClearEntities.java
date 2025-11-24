/*
package vesper.vcc.leaks.iceberg;

import dev.vesper.eveningstarlib.fabric.events.LevelEvents;
import net.fabricmc.loader.api.FabricLoader;
import vesper.vcc.mixin.client.accessors.iceberg.CustomItemRenderAccessor;

public class ClearEntities {
    public static void init(){
        if (!FabricLoader.getInstance().isModLoaded("iceberg")) return;

        LevelEvents.Unload.UNLOAD.register((LevelEvents.Unload event) ->{
            if (event.getLevel().isClientSide()){
                CustomItemRenderAccessor.setEntity(null);
                CustomItemRenderAccessor.setHorse(null);
                CustomItemRenderAccessor.setArmorStand(null);
                CustomItemRenderAccessor.setWolf(null);
            }
        });
    }
}
*/
