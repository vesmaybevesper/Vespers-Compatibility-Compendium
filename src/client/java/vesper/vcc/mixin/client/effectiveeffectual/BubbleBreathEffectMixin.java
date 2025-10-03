package vesper.vcc.mixin.client.effectiveeffectual;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.ladysnake.effective.index.EffectiveParticles;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vesper.vcc.Config;

@Mixin(com.imeetake.effectual.effects.Bubbles.BubbleBreathEffect.class)
public class BubbleBreathEffectMixin {
    @Unique
    private static final RandomSource RANDOM = RandomSource.create();
    @Inject(method = "spawnBubbleParticles", at = @At("HEAD"), cancellable = true)
    private static void replaceParticles(Player player, CallbackInfo ci){
        if (FabricLoader.getInstance().isModLoaded("effective") && FabricLoader.getInstance().isModLoaded("effectual")) {
            if (Config.EffectiveXEffectual && Config.useEffectiveBubbleBreath) {
                Level world = player.level();
                double x = player.getX();
                double y = player.getEyeY() - 0.2;
                double z = player.getZ();
                float yaw = player.getYRot() * ((float)Math.PI / 180F);
                double offsetForward = 0.3;
                x += -Math.sin(yaw) * offsetForward;
                z += Math.cos(yaw) * offsetForward;

                for(int i = 0; i < 3; ++i) {
                    double velocityX = (RANDOM.nextDouble() - (double)0.5F) * 0.02;
                    double velocityY = 0.1 + RANDOM.nextDouble() * 0.05;
                    double velocityZ = (RANDOM.nextDouble() - (double)0.5F) * 0.02;

                    if (world != null) {
                        world.addParticle(EffectiveParticles.BUBBLE,  x, y, z, velocityX, velocityY, velocityZ);
                    }
                    /*WorldParticleBuilder.create(EffectiveParticles.BUBBLE).enableForcedSpawn().setScaleData(GenericParticleData.create(0.05f + world.random.nextFloat() * 0.05f).build())
                            .setTransparencyData(GenericParticleData.create(1F).build())
                            .enableNoClip().setLifetime(60 + world.random.nextInt(60))
                            .addTickActor(new LinearForcedMotionImpl(new Vector3f((float) velocityX, (float) velocityY, (float) velocityZ), new Vector3f(0, 0, 0), 10f))
                            .setRenderType(ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT).spawn(world, x, y, z);*/
                    //client.world.addParticle(effect, x, y, z, velocityX, velocityY, velocityZ);
                }
                ci.cancel();
            }
        }
    }
}
