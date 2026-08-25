package dev.vesper.vcc.fixes.leaks.jei;

//? if fabric {
import dev.vesper.eveningstarlib.platform.fabric.events.ClientRespawnEventCallback;
import dev.vesper.vcc.VCC;
import dev.vesper.vcc.util.ReflectionHelper;
import net.minecraft.world.inventory.GrindstoneMenu;

import java.lang.invoke.VarHandle;

public class ClearMenuOnRespawn {
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
}
//?}
