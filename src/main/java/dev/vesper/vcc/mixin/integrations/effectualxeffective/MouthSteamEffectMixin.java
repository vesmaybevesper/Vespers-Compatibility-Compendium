package dev.vesper.vcc.mixin.integrations.effectualxeffective;

import com.imeetake.effectual.effects.MouthSteam.MouthSteamEffect;
//? if fabric {
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
//?}
import com.moulberry.mixinconstraints.annotations.IfModLoaded;
import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import dev.vesper.eveningstarlib.EveningStarLib;
import dev.vesper.vcc.Config;
import dev.vesper.vcc.util.ParticleModContext;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

//? 1.20.1 && fabric{
/*import org.ladysnake.effective.core.Effective;
import org.spongepowered.asm.mixin.injection.Coerce;
*///?} 1.21.1 && fabric{
/*import org.ladysnake.effective.index.EffectiveParticles;
*///?}

@IfModLoaded(value = "effectual")
@Mixin(MouthSteamEffect.class)
@MixinEnvironment(type = MixinEnvironment.Env.CLIENT)
public class MouthSteamEffectMixin {

	//? <=1.21.1 && fabric{
	/*@WrapOperation(method = "spawnBreath", at = @At(value = "INVOKE", target = "Lcom/imeetake/effectual/effects/MouthSteam/MouthSteamParticleFactory;spawn(DDDDDDLcom/imeetake/effectual/effects/MouthSteam/BreathAnchor;)V"))
	private static void vcc$register$invoke(double x, double y, double z, double dx, double dy, double dz, @Coerce Object anchor, Operation<Void> original){
		if (Config.breathSteam() && EveningStarLib.isModLoaded("effectual") && EveningStarLib.isModLoaded("effective")) {
			assert Minecraft.getInstance().level != null;
			// the pos needs help to go in front of where the player is looking, particle moves down really quickly
			ParticleModContext.fixCascadeForBreath = true;
			Minecraft.getInstance().level.addParticle((ParticleOptions) /^? 1.20.1 {^//^Effective.WATERFALL_CLOUD^//^?} 1.21.1 { ^/ /^EffectiveParticles.CASCADE ^//^?} ^/, x, y, z, 0, 0.001, 0);
			ParticleModContext.fixCascadeForBreath = true;
		} else {
			original.call(x, y, z, dx, dy, dz, anchor);
		}
	}
	*///?}
}
