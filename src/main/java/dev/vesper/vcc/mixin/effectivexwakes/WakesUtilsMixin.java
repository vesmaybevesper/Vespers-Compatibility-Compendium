package dev.vesper.vcc.mixin.effectivexwakes;

import com.goby56.wakes.utils.WakesUtils;
import com.llamalad7.mixinextras.sugar.Local;
import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import dev.vesper.vcc.Config;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.ladysnake.effective.core.utils.EffectiveUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(WakesUtils.class)
@MixinEnvironment(type =  MixinEnvironment.Env.CLIENT)
public class WakesUtilsMixin {
	//? <=1.21.1{
	/*@Redirect(method = {"spawnPaddleSplashCloudParticle"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"))
	private static void VCC$spawnPaddleSplashCloudParticle$invoke(Level instance, ParticleOptions particle, double x, double y, double z, double xd, double yd, double zd, @Local(argsOnly = true, name = "boat") Boat boat) {
		// I need to add the checks which require both making the config and updating ESL then adding it as a dep
		if (Config.oarSplash && ) {
			RandomSource random = instance.random;
			int count = random.nextIntBetweenInclusive(5, 8);

			for (int i = 0; i < count; i++){
				EffectiveUtils.spawnWaterEffect(instance, new Vec3(x, y, z), random.nextGaussian() / 20.0f, random.nextFloat() / 4.0f, random.nextGaussian() / 20.0f, EffectiveUtils.WaterEffectType.DROPLET);
			}
		}
	}
	*///?}
}
