package vesper.vcc.leaks.jei;

import dev.vesper.eveningstarlib.fabric.events.ClientRespawnEventCallback;
import dev.vesper.eveningstarlib.fabric.events.LevelEvents;
import mezz.jei.common.Internal;
import mezz.jei.library.runtime.JeiRuntime;
import vesper.vcc.mixin.client.accessors.jei.RecipeTransferManagerAccessor;

public class ClearCaches {
    public static void onLevelUnload(){
        LevelEvents.Unload.UNLOAD.register((LevelEvents.Unload event) -> {
            if (event.getLevel().isClientSide()){
                try {
                    var runTime = (JeiRuntime) Internal.getJeiRuntime();
                    if (runTime.getRecipeTransferManager() instanceof RecipeTransferManagerAccessor accessor){
                        accessor.getUnsupportedContainer().clear();
                    }
                } catch (Exception ignored) {}
            }
        });
    }

    public static void onRespawn(){
        ClientRespawnEventCallback.EVENT.register((gameMode, oldPlayer, newPlayer, connection) ->{
            try {
                var runTime = (JeiRuntime) Internal.getJeiRuntime();
                if (runTime.getRecipeTransferManager() instanceof RecipeTransferManagerAccessor accessor){
                    accessor.getUnsupportedContainer().clear();
                }
            } catch (Exception ignored) {}
        });
    }
}
