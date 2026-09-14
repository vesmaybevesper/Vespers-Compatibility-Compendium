package dev.vesper.vcc.mixin.tweaks.effectual;

import com.imeetake.effectual.effects.WaterDrip.WaterDripParticle;
import com.moulberry.mixinconstraints.annotations.IfModLoaded;
import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import dev.vesper.vcc.Config;
import dev.vesper.vcc.util.MiscMethods;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.SingleQuadParticle;
//? if <=1.21.1
//import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@IfModLoaded("effectual")
@Mixin(value = WaterDripParticle.class, remap = false)
@MixinEnvironment(type = MixinEnvironment.Env.CLIENT)
public abstract class WaterDripParticleMixin extends SingleQuadParticle {

	//~ if <=1.21.1 'ClientLevel level, double x, double y, double z, TextureAtlasSprite sprite' -> 'ClientLevel level, double x, double y, double z'
	protected WaterDripParticleMixin(ClientLevel level, double x, double y, double z, TextureAtlasSprite sprite) {
		//~ if <=1.21.1 'level, x, y, z, sprite' -> 'level, x, y, z'
		super(level, x, y, z, sprite);
	}

	@Inject(method = "<init>", at = @At("TAIL"))
	//~ if <=1.21.1 'ClientLevel level, Player player, double localOffsetX, double localOffsetY, double localOffsetZ, TextureAtlasSprite sprite, CallbackInfo ci' -> 'ClientLevel level, Player player, double localOffsetX, double localOffsetY, double localOffsetZ, SpriteSet spriteSet, CallbackInfo ci'
	private void vcc$modifyColor(ClientLevel level, Player player, double localOffsetX, double localOffsetY, double localOffsetZ, TextureAtlasSprite sprite, CallbackInfo ci){
		if (Config.effectualGlowDrip()) {
			if (MiscMethods.shouldGlow()){
				this.setColor(this.random.nextFloat() / 5.0f, this.random.nextFloat() / 5.0f, 1.0f);
			}
		}
	}
}
