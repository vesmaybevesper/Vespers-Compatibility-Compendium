package dev.vesper.vcc.fixes.leaks.iceberg;

import dev.vesper.eveningstarlib.EveningStarLib;
//? if fabric
import dev.vesper.eveningstarlib.platform.fabric.events.LevelEvents;
//? if neoforge {
/*import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.level.LevelEvent;
*///?}
import dev.vesper.vcc.mixin.accessors.CustomItemRenderAccessor;

public class ClearEntitiesOnUnload {
	//? if fabric {
	public static void init(){
		if (!EveningStarLib.isModLoaded("iceberg")) return;

		LevelEvents.Unload.UNLOAD.register((LevelEvents.Unload event) ->{
			if (event.getLevel().isClientSide()){
				CustomItemRenderAccessor.setEntity(null);
				CustomItemRenderAccessor.setArmorStand(null);
				CustomItemRenderAccessor.setHorse(null);
				//? >=1.21.1
				CustomItemRenderAccessor.setWolf(null);
			}
		});
	}
	//?} neoforge && >=26.1.2{
	/*public ClearEntitiesOnUnload(){
		NeoForge.EVENT_BUS.addListener(this::clearEntitiesOnLevelUnload);
	}

	private void clearEntitiesOnLevelUnload(LevelEvent.Unload event) {
		if (event.getLevel().isClientSide()){
			CustomItemRenderAccessor.setWolf(null);
			CustomItemRenderAccessor.setHorse(null);
			CustomItemRenderAccessor.setEntity(null);
			CustomItemRenderAccessor.setArmorStand(null);
		}
	}
	*///?}
}
