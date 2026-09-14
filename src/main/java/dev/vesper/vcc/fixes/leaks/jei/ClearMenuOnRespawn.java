package dev.vesper.vcc.fixes.leaks.jei;

//? if fabric
import dev.vesper.eveningstarlib.platform.fabric.events.ClientRespawnEventCallback;
import dev.vesper.vcc.VCC;
import dev.vesper.vcc.util.ReflectionHelper;
import net.minecraft.world.inventory.GrindstoneMenu;
//? if neoforge {
/*import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.common.NeoForge;
*///?}

import java.lang.invoke.VarHandle;

public class ClearMenuOnRespawn {
	//? if fabric {
	public static void init(){
		try {
			var clazz = ReflectionHelper.getClass("mezz.jei.library.plugins.vanilla.grindstone.GrindstoneRecipeMaker");
			VarHandle GRINDSTONE_MENU = ReflectionHelper.getFieldFromClass(clazz, "GRINDSTONE_MENU", GrindstoneMenu.class, true);

			ClientRespawnEventCallback.EVENT.register(((multiPlayerGameMode, localPlayer, localPlayer1, connection) -> {
				GRINDSTONE_MENU.set((Object) null);
			}));
		} catch (Exception e) {
			VCC.LOGGER.warn("JEI classes not found, skipping JEI fix");
		}
	}
	//?} neoforge && >=26.1.2{
	/*public ClearMenuOnRespawn(){
		NeoForge.EVENT_BUS.addListener(this::clearMenuOnPlayerClone);
	}

	public static final VarHandle GRINDSTONE_MENU;
		static {
		var clazz = ReflectionHelper.getClass("mezz.jei.library.plugins.vanilla.grindstone.GrindstoneRecipeMaker");
		GRINDSTONE_MENU = ReflectionHelper.getFieldFromClass(clazz, "GRINDSTONE_MENU", GrindstoneMenu.class, true);
	}

	private void clearMenuOnPlayerClone(ClientPlayerNetworkEvent.Clone event){
			GRINDSTONE_MENU.set((Object) null);
	}
	*///?}
}
