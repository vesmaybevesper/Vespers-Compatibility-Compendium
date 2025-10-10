package vesper.vcc.mixin.client.effectiveeffectual;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.ladysnake.effective.index.EffectiveParticles;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vesper.vcc.YACLConfig;

@Mixin(com.imeetake.effectual.effects.MouthSteam.MouthSteamEffect.class)
public class MouthSteamEffectMixin {
    @Inject(method = "spawn", at = @At("HEAD"), cancellable = true, remap = false)
    private static void override(Minecraft client, Player player, CallbackInfo ci){
        if (FabricLoader.getInstance().isModLoaded("effective") && FabricLoader.getInstance().isModLoaded("effectual")) {
            if (YACLConfig.mouthSteam) {
                Level world = player.level();
                double x = player.getX();
                double y = player.getEyeY() - 0.1;
                double z = player.getZ();
                float yaw = player.getYRot() * ((float)Math.PI / 180F);
                double offsetForward = 0.3;
                x += -Math.sin(yaw) * offsetForward;
                z += Math.cos(yaw) * offsetForward;
                world.addParticle(EffectiveParticles.CASCADE, x, y, z, 0.0F, 0.001, 0.0F);
               /* WorldParticleBuilder.create(EffectiveParticles.CASCADE).setScaleData(GenericParticleData.create(0.05f + world.random.nextFloat() * 0.05f).build())
                        .setTransparencyData(GenericParticleData.create(0.5f).build())
                        //.enableNoClip()
                        .setLifetime(60 + world.random.nextInt(60))
                        .addTickActor(new LinearForcedMotionImpl(new Vector3f(0, -1, 0.0f), new Vector3f(0, 0, 0), 1))
                        .setRenderType(ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT)
                        .spawn(world, x, y, z);*/
                ci.cancel();
            }
        }
    }
}
