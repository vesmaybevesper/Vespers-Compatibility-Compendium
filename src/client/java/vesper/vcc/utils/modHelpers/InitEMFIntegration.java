package vesper.vcc.utils.modHelpers;

import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRenderEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import traben.entity_model_features.models.animation.EMFAnimationEntityContext;
import vesper.vcc.utils.Util;

public class InitEMFIntegration {
    private static final Logger LOGGER = LoggerFactory.getLogger("VCC-EMF-Fix");
    private static boolean emfLoaded = false;

    public void init(){
        if (FabricLoader.getInstance().isModLoaded("entity_model_features")) {
            try {
                initializeEMFIntegration();
                emfLoaded = true;
                LOGGER.info("Successfully initialized Entity Model Features compatibility fix");
            } catch (Exception e) {
                LOGGER.error("Failed to initialize EMF compatibility fix", e);
            }
        } else {
            LOGGER.info("Entity Model Features not detected, compatibility fix will not be loaded");
        }
    }

    private void initializeEMFIntegration() {

    }
}
