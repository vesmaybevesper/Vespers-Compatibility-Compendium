package vesper.vcc.leaks.etf;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import traben.entity_texture_features.features.player.ETFPlayerTexture;
import vesper.vcc.events.RenderLivingEvent;
import vesper.vcc.utils.Util;
import java.lang.invoke.VarHandle;

public class ClearCachedEntity {
    private static final Logger LOGGER = LoggerFactory.getLogger("VCC/ETF");

    public static void init() {
        if (!FabricLoader.getInstance().isModLoaded("entity_texture_features")) return;

        try {
            //Class<?> ETF$HELDENTITY = Class.forName("traben.entity_texture_features.mixin.mixins.entity.renderer.MixinLivingEntityRenderer");
            //VarHandle ETF$HELDENTITY = Util.ReflectionHelper.getFieldFromClass(LivingEntityRenderer.class, "etf$heldEntity", ETFEntityRenderState.class, false);
            //Field etfHeldEntityField = ETF$HELDENTITY.getDeclaredField("etf$heldEntity");
            //Class<?> ETF$PLAYERTEXTURE = Class.forName("traben.entity_texture_features.mixin.mixins.entity.renderer.MixinPlayerEntityRenderer");
            VarHandle ETF$PLAYERTEXTURE = Util.ReflectionHelper.getFieldFromClass(AvatarRenderer.class, "etf$ETFPlayerTexture", ETFPlayerTexture.class, false);
            //Field etfPlayerTexture = ETF$PLAYERTEXTURE.getDeclaredField("etf$ETFPlayerTexture");


            RenderLivingEvent.POST.register((renderer, matrices) -> {
                try {
                    //ETF$HELDENTITY.set(renderer, null);
                    if (renderer instanceof AvatarRenderer) {
                        ETF$PLAYERTEXTURE.set(renderer, null);
                    }
                } catch (IllegalArgumentException e) {
                    Util.warn("Failed to clear ETF cache");
                }
            });
        } catch (SecurityException e) {
            Util.error("Error Finding ETF Fields, skipping fixes");
        }



    }
}
