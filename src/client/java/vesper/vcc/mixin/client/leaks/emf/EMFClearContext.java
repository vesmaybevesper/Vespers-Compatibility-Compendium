package vesper.vcc.mixin.client.leaks.emf;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import traben.entity_model_features.models.animation.EMFAnimationEntityContext;
import vesper.vcc.utils.Util;

@Mixin(LivingEntityRenderer.class)
public class EMFClearContext {
    @Inject(method = "render(Lnet/minecraft/world/entity/Entity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At("TAIL"))
    private void afterRender(CallbackInfo ci){
        if (!FabricLoader.getInstance().isModLoaded("entity_model_features")){return;}
            LivingEntityRenderer<?, ?> renderer = (LivingEntityRenderer<?, ?>) (Object) this;
            try {
                var field = Util.ReflectionHelper.getFieldFromClass(LivingEntityRenderer.class, "emf$heldIteration", EMFAnimationEntityContext.IterationContext.class, false);
                if (field != null){
                    try {
                        field.set(renderer, (Object) null);
                    } catch (Exception e) {
                        Util.error("Failed to clear EMF context: " + e.getMessage());
                    }
                }
            } catch (Exception e) {
                Util.warn("EMF classes not found, skipping EMF fix");
            }
    }
}
