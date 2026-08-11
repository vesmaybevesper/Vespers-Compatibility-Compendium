package dev.vesper.vcc.mixin.effectualxeffective;

import com.imeetake.effectual.effects.WaterDrip.WaterDripEffect;
import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import dev.vesper.eveningstarlib.EveningStarLib;
import dev.vesper.vcc.Config;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//? 1.20.1{
/*import org.ladysnake.effective.core.Effective;
import org.ladysnake.effective.core.utils.EffectiveUtils;
*///?} 1.21.1{
/*import org.ladysnake.effective.utils.EffectiveUtils;
import org.ladysnake.effective.index.EffectiveParticles;
*///?}

@Mixin(WaterDripEffect.class)
@MixinEnvironment(type = MixinEnvironment.Env.CLIENT)
public class WaterDripEffectMixin {

	//? <=1.21.1{
	/*@Unique
	private static final RandomSource RANDOM = RandomSource.create();

	@Inject(method = "spawnWaterDripParticles", at = @At("HEAD"), cancellable = true)
	private static void vcc$spawnWaterDripParticle$head(Player player, CallbackInfo ci){
		if (Config.effectualGlowDrip && EveningStarLib.isModLoaded("effectual") && EveningStarLib.isModLoaded("effective")){
			if (EffectiveUtils.isGlowingWater(player.level(), player.blockPosition())) {
				int count = 1 + (RANDOM.nextFloat() < 0.12F ? 1 : 0);
				float yaw = player.getYRot();
				double ry = Math.toRadians((double)(-yaw));

				for(int i = 0; i < count; ++i) {
					double ring = 0.32 + RANDOM.nextDouble() * 0.08;
					double ang = RANDOM.nextDouble() * Math.PI * (double)2.0F;
					double lx = Math.cos(ang) * ring;
					double lz = Math.sin(ang) * ring;
					double ly = 0.95 + RANDOM.nextDouble() * 0.7;
					double rx = lx * Math.cos(ry) - lz * Math.sin(ry);
					double rz = lx * Math.sin(ry) + lz * Math.cos(ry);
					double x = player.getX() + rx;
					double y = player.getY() + ly;
					double z = player.getZ() + rz;
					player.level().addParticle(/^? 1.20.1 {^//^Effective.GLOW_DROPLET^//^?} 1.21.1 { ^/ /^EffectiveParticles.GLOW_DROPLET ^//^?} ^/, x, y, z, (double)player.getId(), lx, lz);
				}
				ci.cancel();
			}
		}
	}
	*///?}
}
