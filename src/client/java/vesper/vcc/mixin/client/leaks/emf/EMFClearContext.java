package vesper.vcc.mixin.client.leaks.emf;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import traben.entity_model_features.models.animation.EMFAnimationEntityContext;
import vesper.vcc.utils.Util;

@Mixin(LivingEntityRenderer.class)
public class EMFClearContext {
    @Inject(method = "render(Lnet/minecraft/entity/Entity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At("RETURN"))
    private void afterRender(Entity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci){
        if (FabricLoader.getInstance().isModLoaded("entity_model_features")){
            LivingEntityRenderer<?, ?> renderer = (LivingEntityRenderer<?, ?>) (Object) this;
            var field = Util.ReflectionHelper.getFieldFromClass(LivingEntityRenderer.class, "emf$heldIteration", EMFAnimationEntityContext.IterationContext.class, false);
            if (field != null){
                try {
                    field.set(renderer, (Object) null);
                } catch (Exception e) {
                    Util.log("Failed to clear EMF context: " + e.getMessage());
                }
            }
        }
    }
}
