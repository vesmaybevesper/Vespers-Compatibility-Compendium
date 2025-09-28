package vesper.vcc.mixin.client.leaks.emf;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import traben.entity_model_features.models.animation.EMFAnimationEntityContext;
import vesper.vcc.utils.Util;

@Mixin(LivingEntityRenderer.class)
public class EMFClearCachedEntity {
    @Inject(method = "render(Lnet/minecraft/entity/Entity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At("RETURN"))
    private void clearCache(CallbackInfo ci){
        if (FabricLoader.getInstance().isModLoaded("entity_model_features")) {
            LivingEntityRenderer<?, ?> renderer = (LivingEntityRenderer<?, ?>) (Object) this;
            var field = Util.ReflectionHelper.getFieldFromClass(LivingEntityRenderer.class, "emf$heldIteration", EMFAnimationEntityContext.IterationContext.class, false);
            field.set(renderer, (Object) null);
        }
    }
}