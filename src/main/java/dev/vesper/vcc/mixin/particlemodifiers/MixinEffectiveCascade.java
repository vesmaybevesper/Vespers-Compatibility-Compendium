package dev.vesper.vcc.mixin.particlemodifiers;

import dev.vesper.vcc.util.MixinDummy;

import org.spongepowered.asm.mixin.Mixin;
//? 1.21.1 && fabric{
/*import org.ladysnake.effective.particle.CascadeParticle;
*///?}

//? 1.21.1 && fabric{
/*@Mixin(CascadeParticle.class)
*///?} else {
@Mixin(MixinDummy.class)
//?}
public class MixinEffectiveCascade {

	//? 1.21.1 && fabric{
	/*@Inject(method = "<init>", at = @At("TAIL"))
	private void conditionallyChangeAttributes(ClientLevel world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteSet spriteProvider, CallbackInfo ci){
		if (ParticleModContext.fixCascadeForBreath) {
			((SingleQuadParticle) (Object) this).quadSize =  0.035F + world.random.nextFloat() * 0.05F;
		}
	}
	*///?}
}
