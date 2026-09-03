package dev.vesper.vcc.mixin.integrations.effectivexwakes;

//? if fabric {
import com.goby56.wakes.utils.WakesUtils;
import com.llamalad7.mixinextras.sugar.Local;
import com.moulberry.mixinconstraints.annotations.IfModLoaded;import dev.vesper.eveningstarlib.EveningStarLib;
import dev.vesper.vcc.Config;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//?}
import dev.vesper.vcc.util.MixinDummy;
import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixin;

//? 1.20.1 && fabric{
/*import org.ladysnake.effective.core.utils.EffectiveUtils;
import net.minecraft.world.entity.vehicle.Boat;
*///?} 1.21.1 && fabric{
/*import org.ladysnake.effective.utils.EffectiveUtils;
import net.minecraft.world.entity.vehicle.Boat;
*///?}

@IfModLoaded(value = "wakes")
//~ if !fabric 'WakesUtils' -> 'MixinDummy'
@Mixin(WakesUtils.class)
@MixinEnvironment(type =  MixinEnvironment.Env.CLIENT)
public class WakesUtilsMixin {
	//? <=1.21.1 && fabric{
	/*@Inject(method = {"spawnPaddleSplashCloudParticle"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"))
	private static void VCC$spawnPaddleSplashCloudParticle$invoke(
			Level world, Boat boat, CallbackInfo ci, @Local(ordinal = 1) Vec3 pos) {
		if (Config.oarSplash() && EveningStarLib.isModLoaded("effective") && EveningStarLib.isModLoaded("wakes")) {
			RandomSource random = world.random;

			EffectiveUtils.spawnWaterEffect(world, new Vec3(pos.x, pos.y, pos.z), random.nextGaussian() / 20.0f, random.nextFloat() / 4.0f, random.nextGaussian() / 20.0f, EffectiveUtils.WaterEffectType.DROPLET);
		}
	}
	*///?}
}
