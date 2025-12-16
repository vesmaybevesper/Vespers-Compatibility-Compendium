package vesper.vcc.mixin.client.effectivewakes;

import com.goby56.wakes.WakesClient;
import com.goby56.wakes.config.WakesConfig;
import com.goby56.wakes.render.WakeColor;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vesper.vcc.YACLConfig;

@Environment(EnvType.CLIENT)
@Mixin(value = WakeColor.class, remap = false)
public abstract class WakeRendererMixin {

    @Shadow @Final public int a;
    @Shadow @Final public int r;
    @Shadow @Final public int g;
    @Shadow @Final public int b;

    @Unique
    private static double invertedLogisticCurve(float x) {
        float k = WakesConfig.shaderLightPassthrough;
        return WakesClient.areShadersEnabled() ? k * (4.0f * Math.pow((x - 0.5f), 3.0f) + 0.5f) : x;
    }

    @Inject(method = "blend", at = @At(value = "HEAD"), cancellable = true)
    private void modifyColor(WakeColor tint, int lightColor, float opacity, CallbackInfoReturnable<WakeColor> cir) {
        if (YACLConfig.mimicGlow && FabricLoader.getInstance().isModLoaded("wakes")) {
            Level world = Minecraft.getInstance().level;
            assert Minecraft.getInstance().player != null;
            Player player = Minecraft.getInstance().player;
            BlockPos pos = player.getOnPos();
            assert world != null;
            if (world.isDarkOutside() && world.getBiome(pos).is(Biomes.WARM_OCEAN)){
                float fade = Math.min(0.3f, (world.getDayTime() % 40) / 40f);
                float value = Math.min(0.3f, fade / 15f);
                float hue = 210f / 360f;
                float sat = 0.3f;
                WakeColor color = new WakeColor(hue, sat, value, 1.0f);
                final int foamLight = 15728880;

                double scrA = Math.pow((float) this.a / 255.0f, WakesConfig.blendStrength * 10f);
                int r = (int) ((double) this.r * scrA + (double) color.r * ((double) 1.0f - scrA));
                int g = (int) ((double) this.g * scrA + (double) color.g * ((double) 1.0f - scrA));
                int b = (int) ((double) this.b * scrA + (double) color.b * ((double) 1.0f - scrA));
                r = (int) ((double) r * invertedLogisticCurve((float) (foamLight >> 8 & 255) / 255.0f));
                g = (int) ((double) g * invertedLogisticCurve((float) (foamLight >> 8 & 255) / 255.0f));
                b = (int) ((double) b * invertedLogisticCurve((float) (foamLight >> 16 & 255) / 255.0f));
                WakeColor newColor = new WakeColor(r, g, b, (int) ((float) this.a * opacity));
                cir.setReturnValue(newColor);
            }
        }
    }
}


