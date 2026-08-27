package dev.vesper.vcc.fixes.leaks.iceberg;

//? if fabric {
import dev.vesper.eveningstarlib.EveningStarLib;
import dev.vesper.eveningstarlib.platform.fabric.events.LevelEvents;
import dev.vesper.vcc.mixin.accessors.CustomItemRenderAccessor;

public class ClearEntitiesOnUnload {
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
}
//?}
