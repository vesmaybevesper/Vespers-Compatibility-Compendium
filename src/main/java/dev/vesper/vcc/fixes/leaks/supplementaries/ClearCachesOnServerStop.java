package dev.vesper.vcc.fixes.leaks.supplementaries;

//? if fabric {
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;
//? <=1.21.1{
/*import net.mehvahdjukaar.supplementaries.common.block.tiles.EndermanSkullBlockTile;
import net.mehvahdjukaar.supplementaries.common.items.crafting.WeatheredMapRecipe;
*///?}

public class ClearCachesOnServerStop {
	//? <=1.21.1{
	/*public static void init(){
		if (!FabricLoader.getInstance().isModLoaded("supplementaries")) return;

		ServerLifecycleEvents.SERVER_STOPPING.register(server -> {
			WeatheredMapRecipe.onWorldUnload();
			EndermanSkullBlockTile.clearCache();
		});
	}
	*///?}
}
//?}
