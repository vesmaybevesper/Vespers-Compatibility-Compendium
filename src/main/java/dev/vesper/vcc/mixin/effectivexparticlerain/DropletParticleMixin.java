package dev.vesper.vcc.mixin.effectivexparticlerain;

import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import dev.vesper.eveningstarlib.EveningStarLib;
import dev.vesper.vcc.Config;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.level.block.Blocks;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//? <26.2{
/*import pigcart.particlerain.ParticleRain;
*///?}

//? 1.20.1{
/*import org.ladysnake.effective.core.particle.DropletParticle;
import net.minecraft.client.particle.TextureSheetParticle;
*///?} 1.21.1{
/*import org.ladysnake.effective.particle.DropletParticle;
import net.minecraft.client.particle.TextureSheetParticle;
*///?}


//? <=1.21.1{
/*@Mixin(DropletParticle.class)
*///?} >=1.21.11{
// I need this to still be a mixin to avoid problems launching so I mixin to something random and do nothing :)
@Mixin(Config.class)
//?}
@MixinEnvironment(type = MixinEnvironment.Env.CLIENT)
public abstract class DropletParticleMixin /*? <=1.21.1{ *//*extends TextureSheetParticle *//*?} */ {

	//? <=1.21.1{
	/*protected DropletParticleMixin(ClientLevel clientLevel, double d, double e, double f) {
		super(clientLevel, d, e, f);
	}

	@Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientLevel;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"))
	private void tick(ClientLevel instance, ParticleOptions particle, double x, double y, double z, double xd, double yd, double zd){
		if (Config.replaceRipple && EveningStarLib.isModLoaded("effective") && EveningStarLib.isModLoaded("particle-rain")) {
			instance.addParticle(ParticleRain.RIPPLE, x, y, z, xd, yd, zd);
		}
	}
	*///?}
}
