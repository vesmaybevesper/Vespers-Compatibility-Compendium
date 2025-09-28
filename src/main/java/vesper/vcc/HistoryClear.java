/*package vesper.vcc;

import dev.emi.emi.runtime.EmiHistory;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.network.ServerPlayerEntity;


import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

*//*Adapted from All The Leaks under MIT*//*
public class HistoryClear {
    public static final MethodHandle CLEAR_HISTORY;

    static {
        CLEAR_HISTORY = vesper.vcc.mixin.leaks.emi.Util.ReflectionHelper.getMethodFromClass(EmiHistory.class, "clear", MethodType.methodType(void.class), true);
    }

    public HistoryClear(){
        ServerPlayerEvents.AFTER_RESPAWN.register(this::clearHistory);
    }

    private void clearHistory(ServerPlayerEntity serverPlayerEntity, ServerPlayerEntity serverPlayerEntity1, boolean b){
        if (FabricLoader.getInstance().isModLoaded("emi")) {
            try {
                CLEAR_HISTORY.invoke();
                System.out.println("clear ran");
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    }
}*/
