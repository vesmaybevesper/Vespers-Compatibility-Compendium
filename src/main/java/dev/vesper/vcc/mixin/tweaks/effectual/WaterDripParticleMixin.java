package dev.vesper.vcc.mixin.tweaks.effectual;

import com.imeetake.effectual.effects.WaterDrip.WaterDripParticle;
import com.moulberry.mixinconstraints.annotations.IfModLoaded;
import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import dev.vesper.vcc.Config;
import dev.vesper.vcc.util.MiscMethods;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@IfModLoaded("effectual")
@Mixin(value = WaterDripParticle.class, remap = false)
@MixinEnvironment(type = MixinEnvironment.Env.CLIENT)
public abstract class WaterDripParticleMixin extends TextureSheetParticle {

	protected WaterDripParticleMixin(ClientLevel clientLevel, double d, double e, double f) {
		super(clientLevel, d, e, f);
	}

	@Inject(method = "<init>", at = @At("TAIL"))
	private void vcc$modifyColor(ClientLevel level, Player player, double localOffsetX, double localOffsetY, double localOffsetZ, SpriteSet spriteSet, CallbackInfo ci){
		if (Config.effectualGlowDrip()) {
			if (MiscMethods.shouldGlow()){
				this.setColor(this.random.nextFloat() / 5.0f, this.random.nextFloat() / 5.0f, 1.0f);
			}
		}
	}
}
