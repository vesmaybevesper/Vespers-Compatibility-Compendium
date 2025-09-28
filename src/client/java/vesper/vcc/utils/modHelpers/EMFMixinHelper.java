package vesper.vcc.utils.modHelpers;

import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;

public class EMFMixinHelper {

    public static void register(){
        LivingEntityFeatureRendererRegistrationCallback.EVENT.register(EMFMixinHelper::onRegister);
    }

    private static void onRegister(EntityType<? extends LivingEntity> entityType, LivingEntityRenderer<?,?> renderer, LivingEntityFeatureRendererRegistrationCallback.RegistrationHelper registrationHelper, EntityRendererFactory.Context context) {
    }
}
