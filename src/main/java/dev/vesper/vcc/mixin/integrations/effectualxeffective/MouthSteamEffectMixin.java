package dev.vesper.vcc.mixin.integrations.effectualxeffective;

import com.imeetake.effectual.effects.MouthSteam.MouthSteamEffect;
//? if fabric {
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
//?}
import dev.architectury.registry.registries.RegistrySupplier;
import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import dev.vesper.eveningstarlib.EveningStarLib;
import dev.vesper.vcc.Config;
import dev.vesper.vcc.util.ParticleModContext;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.SimpleParticleType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

//? 1.20.1 && fabric{
/*import org.ladysnake.effective.core.Effective;
*///?} 1.21.1 && fabric{
/*import org.ladysnake.effective.index.EffectiveParticles;
*///?}

@Mixin(MouthSteamEffect.class)
@MixinEnvironment(type = MixinEnvironment.Env.CLIENT)
public class MouthSteamEffectMixin {

	//? <=1.21.1 && fabric{
	/*@WrapOperation(method = "spawnBreath", at = @At(value = "INVOKE", target = "Lcom/imeetake/effectual/EffectualClientParticles;spawn(Ldev/architectury/registry/registries/RegistrySupplier;DDDDDD)V"))
	private static void vcc$register$invoke(RegistrySupplier<? extends SimpleParticleType> type, double x, double y, double z, double dx, double dy, double dz, Operation<Void> original){
		if (Config.breathSteam && EveningStarLib.isModLoaded("effectual") && EveningStarLib.isModLoaded("effective")) {
			assert Minecraft.getInstance().level != null;
			// the pos needs help to go in front of where the player is looking, particle is too large and moves down and really quickly
			ParticleModContext.fixCascadeForBreath = true;
			Minecraft.getInstance().level.addParticle((ParticleOptions) /^? 1.20.1 {^//^Effective.WATERFALL_CLOUD^//^?} 1.21.1 { ^/ /^EffectiveParticles.CASCADE ^//^?} ^/, x, y, z, 0, 0.001, 0);
			ParticleModContext.fixCascadeForBreath = true;
		} else {
			original.call(type, x, y, z, dx, dy, dz);
		}
	}
	*///?}
}
