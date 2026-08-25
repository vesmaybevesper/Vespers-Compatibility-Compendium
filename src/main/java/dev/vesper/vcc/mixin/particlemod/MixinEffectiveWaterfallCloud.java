package dev.vesper.vcc.mixin.particlemod;

import dev.vesper.vcc.util.MixinDummy;
import dev.vesper.vcc.util.ParticleModContext;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.SingleQuadParticle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//? 1.20.1 && fabric{
/*import org.ladysnake.effective.core.particle.WaterfallCloudParticle;
import net.fabricmc.fabric.impl.client.particle.FabricSpriteProviderImpl;
import team.lodestar.lodestone.systems.particle.world.options.WorldParticleOptions;
*///?}

//? 1.20.1 && fabric{
/*@Mixin(WaterfallCloudParticle.class)
*///?} else {
@Mixin(MixinDummy.class)
//?}
public class MixinEffectiveWaterfallCloud {

	//? 1.20.1 && fabric{
	/*@Inject(method = "<init>", at = @At("TAIL"))
	private void conditionallyChangeAttributes(ClientLevel world, WorldParticleOptions data, FabricSpriteProviderImpl spriteSet, double x, double y, double z, double xd, double yd, double zd, CallbackInfo ci) {
		if (ParticleModContext.fixCascadeForBreath) {
			((SingleQuadParticle) (Object) this).quadSize = 0.035F + world.random.nextFloat() * 0.05F;
		}
	}
	*///?}
}
