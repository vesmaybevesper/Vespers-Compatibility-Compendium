package dev.vesper.vcc.mixin.particlemodifiers;

import com.moulberry.mixinconstraints.annotations.IfModLoaded;
import dev.vesper.vcc.util.MixinDummy;
import dev.vesper.vcc.util.ParticleModContext;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//? 1.20.1 && fabric{
/*import org.ladysnake.effective.core.particle.GlowDropletParticle;
*///?} 1.21.1 && fabric{
/*import org.ladysnake.effective.particle.GlowDropletParticle;
*///?}

@IfModLoaded(value = "effective")
//~ if >1.2.1 || !fabric 'GlowDropletParticle' -> 'MixinDummy'
@Mixin(MixinDummy.class)
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
