/*package vesper.vcc.leaks.supplementaries;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.mehvahdjukaar.supplementaries.common.block.tiles.EndermanSkullBlockTile;
import net.mehvahdjukaar.supplementaries.common.items.crafting.WeatheredMapRecipe;
import net.mehvahdjukaar.supplementaries.common.misc.map_data.ColoredMapHandler;
import net.mehvahdjukaar.supplementaries.common.worldgen.WaySignStructure;

public class ClearRemaining {
    public static void init(){
        ServerLifecycleEvents.SERVER_STOPPED.register((server) -> {
            WeatheredMapRecipe.onWorldUnload();
            WaySignStructure.clearCache();
            EndermanSkullBlockTile.clearCache();
            ColoredMapHandler.clearIdCache();
        });
    }
}*/
