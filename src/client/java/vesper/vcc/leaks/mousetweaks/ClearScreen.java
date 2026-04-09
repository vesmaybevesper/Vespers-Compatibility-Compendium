package vesper.vcc.leaks.mousetweaks;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLevelEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.inventory.Slot;
import vesper.vcc.VCC;
import vesper.vcc.utils.Util;
import yalter.mousetweaks.IGuiScreenHandler;
import yalter.mousetweaks.Main;

import java.lang.invoke.VarHandle;

public class ClearScreen {
    private static final VarHandle HANDLER;
    private static final VarHandle OLD_SELECTED_SLOT;
    private static final VarHandle OPEN_SCREEN;
    static {
        OPEN_SCREEN = Util.ReflectionHelper.getFieldFromClass(Main.class, "openScreen", Screen.class, true);
        OLD_SELECTED_SLOT = Util.ReflectionHelper.getFieldFromClass(Main.class, "oldSelectedSlot", Slot.class, true);
        HANDLER = Util.ReflectionHelper.getFieldFromClass(Main.class, "handler", IGuiScreenHandler.class, true);
    }
    public static void init(){
        if (!FabricLoader.getInstance().isModLoaded("")) return;

        try {
            ServerLevelEvents.UNLOAD.register((server, world) -> {
                if (world.isClientSide()){
                    OPEN_SCREEN.set((Object) null);
                    OLD_SELECTED_SLOT.set((Object) null);
                    HANDLER.set((Object) null);
                }
            });
        } catch (NoSuchFieldError e) {
            VCC.LOGGER.warn("Unable to find Mouse Tweaks fields");
        }
    }
}
