package dev.vesper.vcc.mixin.effectivexparticlerain;

import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import dev.vesper.eveningstarlib.EveningStarLib;
import dev.vesper.vcc.Config;
import dev.vesper.vcc.util.MixinDummy;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.ParticleOptions;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

//? <=1.21.1{
/*import pigcart.particlerain.ParticleRain;
import net.minecraft.client.particle.TextureSheetParticle;
*///?}

//? 1.20.1 && fabric{
/*import org.ladysnake.effective.core.particle.DropletParticle;
*///?} 1.21.1 && fabric{
/*import org.ladysnake.effective.particle.DropletParticle;
*///?}


//? <=1.21.1 && fabric{
/*@Mixin(DropletParticle.class)
*///?} >=1.21.11 || !fabric{
@Mixin(MixinDummy.class)
//?}
@MixinEnvironment(type = MixinEnvironment.Env.CLIENT)
public abstract class DropletParticleMixin /*? <=1.21.1{ *//*extends TextureSheetParticle *//*?} */ {

	//? if <=1.21.1 {
	/*protected DropletParticleMixin(ClientLevel p_108323_, double p_108324_, double p_108325_, double p_108326_) {
		super(p_108323_, p_108324_, p_108325_, p_108326_);
	}
	*///?}

	//? <=1.21.1 && fabric{
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
