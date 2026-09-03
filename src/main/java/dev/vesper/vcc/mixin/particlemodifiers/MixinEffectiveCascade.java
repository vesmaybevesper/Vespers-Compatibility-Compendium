package dev.vesper.vcc.mixin.particlemodifiers;

import com.moulberry.mixinconstraints.annotations.IfModLoaded;
import dev.vesper.vcc.util.MixinDummy;

import dev.vesper.vcc.util.ParticleModContext;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import org.spongepowered.asm.mixin.Mixin;
//? 1.21.1 && fabric{
/*import org.ladysnake.effective.particle.CascadeParticle;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
*///?}

@IfModLoaded(value = "effective")
//~ if !1.21.1 || !fabric 'CascadeParticle' -> 'MixinDummy'
@Mixin(MixinDummy.class)
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
