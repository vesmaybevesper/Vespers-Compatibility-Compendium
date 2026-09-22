package dev.vesper.vcc.mixin.tweaks.wakes.fabric;

//? if fabric
import com.goby56.wakes.particle.custom.SplashCloudParticle;
import com.moulberry.mixinconstraints.annotations.IfModLoaded;
import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import dev.vesper.vcc.Config;
import dev.vesper.vcc.VCC;
import dev.vesper.vcc.util.MiscMethods;
import dev.vesper.vcc.util.MixinDummy;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
//? if <=1.21.1
//import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LightLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@IfModLoaded(value = "wakes")
//~ if !fabric 'SplashCloudParticle' -> 'MixinDummy'
@Mixin(SplashCloudParticle.class)
@MixinEnvironment(type = MixinEnvironment.Env.CLIENT)
//~ if <=1.21.1 'SingleQuadParticle' -> 'TextureSheetParticle'
public abstract class SplashCloudMixin /*? fabric {*/extends SingleQuadParticle/*?}*/ {
	//? if fabric {
	@Unique
	private float colorEffect;

	protected SplashCloudMixin(ClientLevel level, double x, double y, double z, TextureAtlasSprite sprite) {
		//~ if <=1.21.1 'level, x, y, z, sprite' -> 'level, x, y, z'
		super(level, x, y, z, sprite);
	}

	@Inject(method = "<init>", at = @At("RETURN"))
	//~ if <=1.21.1 'ClientLevel world, Entity owner, double x, double y, double z, SpriteSet sprites, double velocityX, double velocityY, double velocityZ, CallbackInfo ci' -> 'ClientLevel world, double x, double y, double z, SpriteSet sprites, double velocityX, double velocityY, double velocityZ, CallbackInfo ci'
	public void vcc$init$return(ClientLevel world, Entity owner, double x, double y, double z, SpriteSet sprites, double velocityX, double velocityY, double velocityZ, CallbackInfo ci){
		this.colorEffect = world.getRandom().nextFloat();
	}

	@Inject(method = "tick", at = @At("HEAD"))
	private void vcc$tick$head(CallbackInfo ci) {
		if (Config.glowSplashPlane() && MiscMethods.shouldGlow()) {
			this.vcc$updateColor(this.level.getBrightness(LightLayer.BLOCK, new BlockPos(Mth.floor(this.x), Mth.floor(this.y), Mth.floor(this.z))));
		}
	}

	@Unique
	private void vcc$updateColor(float light) {
		if (MiscMethods.shouldGlow()) {
			// this is written instead of a single value cause i want to read some of these from variables in the future
			float redGreen = (float) (0.5f * (4.0f * Math.pow(0.9411765f - 0.5f, 3.0f) + 0.5f));
			this.setColor(redGreen, redGreen, 1.0f);
		} else {
			this.setColor(1.0f, 1.0f, 1.0f);
		}
	}
	//?}
}
