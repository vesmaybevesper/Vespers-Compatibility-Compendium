package dev.vesper.vcc.mixin.tweaks.wakes.fabric;

import com.goby56.wakes.particle.custom.SplashCloudParticle;
import com.moulberry.mixinconstraints.annotations.IfModLoaded;
import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import dev.vesper.vcc.Config;
import dev.vesper.vcc.util.MiscMethods;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
//? if <=1.21.1
//import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LightLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// straight port of the old effected wakes code, I expect this to not work as intended anymore

@IfModLoaded("Wakes")
@Mixin(SplashCloudParticle.class)
@MixinEnvironment(type = MixinEnvironment.Env.CLIENT)
//~ if <=1.21.1 'SingleQuadParticle' -> 'TextureSheetParticle'
public class SplashCloudMixin extends SingleQuadParticle {
	@Unique
	private float colorEffect;

	protected SplashCloudMixin(ClientLevel level, double x, double y, double z, TextureAtlasSprite sprite) {
		//~ if <=1.21.1 'level, x, y, z, sprite' -> 'level, x, y, z'
		super(level, x, y, z, sprite);
	}


	//? if >=1.21.11 {
	@Override
	protected Layer getLayer() {return Layer.TRANSLUCENT;}
	//?} else{
	/*@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
	}
	*///?}

	@Inject(method = "<init>", at = @At("RETURN"))
	//~ if <=1.21.1 'ClientLevel world, Entity owner, double x, double y, double z, SpriteSet sprites, double velocityX, double velocityY, double velocityZ, CallbackInfo ci' -> 'ClientLevel world, double x, double y, double z, SpriteSet sprites, double velocityX, double velocityY, double velocityZ, CallbackInfo ci'
	public void vcc$init$return(ClientLevel world, Entity owner, double x, double y, double z, SpriteSet sprites, double velocityX, double velocityY, double velocityZ, CallbackInfo ci){
		this.colorEffect = world.getRandom().nextFloat();
	}

	@Inject(method = "tick", at = @At("HEAD"))
	private void vcc$tick$head(CallbackInfo ci){
		if (Config.glowSplashPlane()) {
			this.vcc$updateColor(this.level.getBrightness(LightLayer.BLOCK, new BlockPos((int) this.x, (int) this.y, (int) this.z)));
		}
	}

	@Unique
	private void vcc$updateColor(float light){
		if (MiscMethods.shouldGlow()){
			float redGreen = Math.min(1.0F, this.colorEffect / 5.0F + light / 15F);
			this.setColor(redGreen, redGreen, 1.0F);
		} else {
			this.setColor(1.0F, 1.0F, 1.0F);
		}
	}

}
