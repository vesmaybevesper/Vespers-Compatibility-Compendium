package vesper.vcc.mixin.client.effectiveeffectual;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biomes;
import org.ladysnake.effective.EffectiveConfig;
import org.ladysnake.effective.index.EffectiveParticles;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vesper.vcc.YACLConfig;

@Mixin(com.imeetake.effectual.effects.WaterDrip.WaterDripEffect.class)
public class WaterDripEffectMixin {
    @Unique
    private static final RandomSource RANDOM = RandomSource.create();

    @Inject(method = "spawnWaterDripParticles", at = @At("HEAD"), cancellable = true)
    private static void redirectParticle(Player player, CallbackInfo ci){
        if (FabricLoader.getInstance().isModLoaded("effective") && FabricLoader.getInstance().isModLoaded("effectual")) {
            if (YACLConfig.effectiveDroplet) {
                ClientLevel world = (ClientLevel) player.level();
                if (EffectiveConfig.glowingPlankton && world.isNight() && world.getBiome(player.getOnPos()).is(Biomes.WARM_OCEAN)){
                if (RANDOM.nextInt(5) == 0) {
                    double offsetX = (double)RANDOM.nextFloat() * 0.4 - (double)0.25F;
                    double offsetY = (double)RANDOM.nextFloat() * 0.8 + (double)1.0F;
                    double offsetZ = (double)RANDOM.nextFloat() * 0.4 - (double)0.25F;
                        world.addParticle(EffectiveParticles.GLOW_DROPLET, player.getX() + offsetX, player.getY() + offsetY, player.getZ() + offsetZ, 0.0F, -0.02, 0.0F);
                    }
                    ci.cancel();
                }
            }
        }
    }
}
