/*package vesper.vcc.mixin.client.effectivewakes;


import com.goby56.wakes.utils.WakesUtils;
import com.llamalad7.mixinextras.sugar.Local;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.ladysnake.effective.utils.EffectiveUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import vesper.vcc.YACLConfig;

@Environment(EnvType.CLIENT)
@Mixin(WakesUtils.class)
public class WakesUtilMixin {
    @Redirect(method = "spawnPaddleSplashCloudParticle", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"))
    private static void effectiveSplash(Level world, ParticleOptions particleEffect, double x, double y, double z, double velX, double velY, double velZ, @Local(argsOnly = true) Boat boat){
        if (YACLConfig.oarSplash && FabricLoader.getInstance().isModLoaded("wakes") && FabricLoader.getInstance().isModLoaded("effective")){
            RandomSource random =  world.getRandom();
            int count = random.nextIntBetweenInclusive(5, 8);

            for (int i = 0; i < count; i++){
                org.ladysnake.effective.utils.EffectiveUtils.spawnWaterEffect(world, new Vec3(x,y,z), random.nextGaussian() / 20f, random.nextFloat() / 4f, random.nextGaussian() / 20f, EffectiveUtils.WaterEffectType.DROPLET);
            }
        }
    }
}*/
