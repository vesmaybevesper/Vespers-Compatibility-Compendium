package vesper.vcc.leaks.etf;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import traben.entity_texture_features.features.player.ETFPlayerTexture;
import traben.entity_texture_features.utils.ETFEntity;
import vesper.vcc.events.RenderLivingEvent;
import vesper.vcc.utils.Util;
import java.lang.invoke.VarHandle;

public class ClearCachedEntity {
    private static final Logger LOGGER = LoggerFactory.getLogger("VCC/ETF");

    public static void init() {
        /*if (!FabricLoader.getInstance().isModLoaded("entity_texture_features")) return;

        //Class<?> etfEntity = Class.forName("traben.entity_texture_features.mixin.mixins.entity.renderer.MixinLivingEntityRenderer");
        VarHandle etfEntity = Util.ReflectionHelper.getFieldFromClass(LivingEntityRenderer.class, "etf$heldEntity", ETFEntity.class, false);
        //Field etfHeldEntity = etfEntity.getDeclaredField("etf$heldEntity");
        //Class<?> etfPlayer = Class.forName("traben.entity_texture_features.mixin.mixins.entity.renderer.MixinPlayerEntityRenderer");
        VarHandle etfPlayer = Util.ReflectionHelper.getFieldFromClass(PlayerRenderer.class, "etf$ETFPlayerTexture", ETFPlayerTexture.class, false);
        // Field etfPlayerTexture = etfPlayer.getDeclaredField("etf$ETFPlayerTexture");

        RenderLivingEvent.POST.register((renderer, matrices, vertexConsumers, light, tickDelta) -> {
            try {
                etfEntity.set(renderer, null);
                if (renderer instanceof PlayerRenderer) {
                    etfPlayer.set(renderer, null);
                }
            } catch (IllegalArgumentException e) {
                Util.warn("Failed to clear ETF cache");
            }
        });*/
    }
}
