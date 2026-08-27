package dev.vesper.vcc.mixin.integrations.effectualxeffective;

import com.imeetake.effectual.effects.Bubbles.BubbleBreathEffect;
//? if fabric {
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
//?}
import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import dev.vesper.eveningstarlib.EveningStarLib;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

//? <=1.21.1{
/*import static dev.vesper.vcc.Config.useEffectiveBubbleBreath;
*///?}
//? 1.20.1 && fabric{
/*import org.ladysnake.effective.core.Effective;
import org.ladysnake.effective.core.utils.LinearForcedMotionImpl;
*///?} 1.21.1 && fabric{
/*import org.ladysnake.effective.index.EffectiveParticles;
*///?}

@Mixin(BubbleBreathEffect.class)
@MixinEnvironment(type = MixinEnvironment.Env.CLIENT)
public class BubbleBreathMixin {

	//? <=1.21.1 && fabric{
	/*@WrapOperation(method = "processBreathTick", at = @At(value = "INVOKE", target = "Lcom/imeetake/effectual/EffectualClientParticles;spawnVanilla(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"))
	private static void vcc$processBreathTick$invoke(ParticleOptions options, double x, double y, double z, double dx, double dy, double dz, Operation<Void> original){
		if (useEffectiveBubbleBreath && EveningStarLib.isModLoaded("effectual") && EveningStarLib.isModLoaded("effective")){
				//? 1.20.1{
			/^assert Minecraft.getInstance().level != null;
			Minecraft.getInstance().level.addParticle((ParticleOptions) Effective.BUBBLE, x, y, z, dx, dy, dz);
				^///?} 1.21.1{
			/^assert Minecraft.getInstance().level != null;
			Minecraft.getInstance().level.addParticle(EffectiveParticles.BUBBLE, x, y, z, dx, dy, dz);
				^///?}
		} else {
			original.call(options, x, y, z, dx, dy, dz);
		}
	}
	*///?}
}
