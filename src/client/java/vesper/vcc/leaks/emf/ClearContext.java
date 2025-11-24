package vesper.vcc.leaks.emf;

import net.fabricmc.loader.api.FabricLoader;
import vesper.vcc.events.RenderLivingEvent;
import vesper.vcc.utils.Util;

import java.lang.reflect.Field;

public class ClearContext {

    public static void init(){
        if (!FabricLoader.getInstance().isModLoaded("entity_model_features")) return;

        try {
            Class<?> emfIterationContext = Class.forName("traben.entity_model_features.models.animation.EMFAnimationEntityContext.IterationContext");
            Field emfHeldIteration = emfIterationContext.getDeclaredField("emf$heldIteration");

            RenderLivingEvent.POST.register((renderer, matrices) -> {
                try {
                   emfHeldIteration.set(renderer, (Object) null);
                } catch (IllegalAccessException e) {
                    Util.error("Failed to clear EMF context");
                }
            });

        } catch (ClassNotFoundException e) {
            Util.warn("EMF class not found, skipping EMF fix");
        } catch (NoSuchFieldException e) {
            Util.error("Failed to initialize EMF fix");
        }
    }
}
