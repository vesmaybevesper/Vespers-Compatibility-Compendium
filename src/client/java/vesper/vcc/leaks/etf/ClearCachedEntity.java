package vesper.vcc.leaks.etf;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vesper.vcc.events.RenderLivingEvent;
import vesper.vcc.utils.Util;

import java.lang.reflect.Field;

public class ClearCachedEntity {
    private static final Logger LOGGER = LoggerFactory.getLogger("VCC/ETF");

    public static void init() {
        if (!FabricLoader.getInstance().isModLoaded("entity_texture_features")) return;

        try {
            Class<?> etfEntity = Class.forName("traben.entity_texture_features.mixin.mixins.entity.renderer.MixinLivingEntityRenderer");
            Field etfHeldEntity = etfEntity.getDeclaredField("etf$heldEntity");
            Class<?> etfPlayer = Class.forName("traben.entity_texture_features.mixin.mixins.entity.renderer.MixinPlayerEntityRenderer");
            Field etfPlayerTexture = etfPlayer.getDeclaredField("etf$ETFPlayerTexture");

            RenderLivingEvent.POST.register((renderer, matrices, vertexConsumers, light, tickDelta) -> {
                try {
                    etfHeldEntity.set(renderer, null);
                    if (renderer instanceof PlayerRenderer) {
                        etfPlayerTexture.set(renderer, null);
                    }
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    Util.error("Failed to clear ETF cache");
                }
            });
        } catch (NoSuchFieldException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
