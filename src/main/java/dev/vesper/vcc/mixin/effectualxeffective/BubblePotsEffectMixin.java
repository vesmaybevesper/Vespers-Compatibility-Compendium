package dev.vesper.vcc.mixin.effectualxeffective;

import com.imeetake.effectual.effects.Bubbles.BubblePotsEffect;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import dev.vesper.eveningstarlib.EveningStarLib;
import dev.vesper.vcc.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

//? 1.20.1{
/*import org.ladysnake.effective.core.Effective;
*///?} 1.21.1{
/*import org.ladysnake.effective.index.EffectiveParticles;
*///?}

@Mixin(BubblePotsEffect.class)
@MixinEnvironment(type = MixinEnvironment.Env.CLIENT)
public class BubblePotsEffectMixin {

	//? <=1.21.1{
	/*@WrapOperation(method = "lambda$register$0", at = @At(value = "INVOKE", target = "Lcom/imeetake/effectual/EffectualClientParticles;spawnVanilla(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"))
	private static void vcc$register$head(ParticleOptions options, double x, double y, double z, double dx, double dy, double dz, Operation<Void> original){
		if (Config.replaceEffectualPots && EveningStarLib.isModLoaded("effectual") && EveningStarLib.isModLoaded("effective")) {
			assert Minecraft.getInstance().level != null;
		//? 1.20.1{
			/^Minecraft.getInstance().level.addParticle((ParticleOptions) Effective.BUBBLE, x, y, z, dx, dy, dz);
		^///?} 1.21.1{
			/^Minecraft.getInstance().level.addParticle(EffectiveParticles.BUBBLE, x, y, z, dx, dy, dz);
		^///?}
		} else {
			original.call(options, x, y, z, dx, dy, dz);
		}
	}
	*///?}
}
