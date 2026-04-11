package vesper.vcc.leaks.jei;

import dev.vesper.eveningstarlib.fabric.events.ClientRespawnEventCallback;
import mezz.jei.common.Internal;
import mezz.jei.library.runtime.JeiRuntime;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLevelEvents;
import net.fabricmc.loader.api.FabricLoader;
import vesper.vcc.mixin.client.accessors.jei.RecipeTransferManagerAccessor;

public class ClearCachedInventories {
    public static void init(){
        if (!FabricLoader.getInstance().isModLoaded("jei")) return;

        ClientRespawnEventCallback.EVENT.register(((multiPlayerGameMode, localPlayer, localPlayer1, connection) -> {
            clear();
        }));

        ServerLevelEvents.UNLOAD.register((multiPlayerGameMode, serverLevel) -> {
            clear();
        });
    }

    private static void clear(){
        try {
            var runTime = (JeiRuntime) Internal.getJeiRuntime();
            if(runTime.getRecipeTransferManager() instanceof RecipeTransferManagerAccessor accessor){
                accessor.getUnsupportedContainer().clear();
            }
        } catch (Exception ignored) {}
    }
}
