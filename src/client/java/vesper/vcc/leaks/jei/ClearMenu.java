package vesper.vcc.leaks.jei;

import dev.vesper.eveningstarlib.fabric.events.ClientRespawnEventCallback;
import net.minecraft.world.inventory.GrindstoneMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vesper.vcc.utils.Util;

import java.lang.invoke.VarHandle;

public class ClearMenu {
    private static final Logger LOGGER = LoggerFactory.getLogger("VCC/JEI");

    public static void onRespawn(){

        try {
            var clazz = Util.ReflectionHelper.getClass("mezz.jei.library.plugins.vanilla.grindstone.GrindstoneRecipeMaker");
            VarHandle GRINDSTONE_MENU = Util.ReflectionHelper.getFieldFromClass(clazz, "GRINDSTONE_MENU", GrindstoneMenu.class, true);

            ClientRespawnEventCallback.EVENT.register(((multiPlayerGameMode, localPlayer, localPlayer1, connection) -> {
                GRINDSTONE_MENU.set((Object) null);
            }));
        } catch (Exception e) {
            LOGGER.warn("JEI classes not found, skipping JEI fix");
        }
    }
}
