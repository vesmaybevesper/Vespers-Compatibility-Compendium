package dev.vesper.vcc.mixin.particlemodifiers;

import dev.vesper.vcc.util.MixinDummy;
import org.spongepowered.asm.mixin.Mixin;

//? 1.20.1 && fabric{
/*import org.ladysnake.effective.core.particle.GlowDropletParticle;
*///?} 1.21.1 && fabric{
/*import org.ladysnake.effective.particle.GlowDropletParticle;
*///?}

//? <=1.21.1 && fabric{
/*@Mixin(GlowDropletParticle.class)
*///?} >1.21.1 || !fabric{
@Mixin(MixinDummy.class)
//?}
public class MixinEffectiveGlowDroplet {

	//? <=1.21.1 && fabric{
	/*@Inject(method = "<init>", at = @At("TAIL"))
	private void conditionallyChangeAttributes(ClientLevel world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteSet spriteProvider, CallbackInfo ci){
		if (ParticleModContext.fixGlowDropForPlayerDrip) {
			((SingleQuadParticle) (Object) this).quadSize = 0.016f + world.random.nextFloat() * 0.012f;
			((SingleQuadParticle) (Object) this).alpha = 0.45F + world.random.nextFloat() * 0.2F;
		}
	}
	*///?}
}
