package dev.vesper.vcc.mixin.effectualxeffective;

import com.imeetake.effectual.effects.MouthSteam.MouthSteamEffect;
import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import dev.vesper.eveningstarlib.EveningStarLib;
import dev.vesper.vcc.Config;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//? 1.20.1{
/*import org.ladysnake.effective.core.Effective;
*///?} 1.21.1{
/*import org.ladysnake.effective.index.EffectiveParticles;
*///?}

@Mixin(MouthSteamEffect.class)
@MixinEnvironment(type = MixinEnvironment.Env.CLIENT)
public class MouthSteamEffectMixin {

	//? <=1.21.1{
	/*@Unique
	private static final RandomSource RANDOM = RandomSource.create();

	@Inject(method = "spawnBreath", at = @At("HEAD"), cancellable = true)
	private static void vcc$register$head(Player player, CallbackInfo ci){
		if (Config.breathSteam && EveningStarLib.isModLoaded("effectual") && EveningStarLib.isModLoaded("effective")) {
			int count = 3 + RANDOM.nextInt(3);

			for(int i = 0; i < count; ++i) {
				player.level().addParticle((ParticleOptions) /^? 1.20.1 {^//^Effective.WATERFALL_CLOUD^//^?} 1.21.1 { ^/ /^EffectiveParticles.CASCADE ^//^?} ^/, player.getX(), player.getEyeY(), player.getZ(), (double)player.getId(), (double)0.0F, (double)0.0F);
			}
		}
	}
	*///?}
}
