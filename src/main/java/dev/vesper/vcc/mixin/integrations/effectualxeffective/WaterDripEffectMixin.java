package dev.vesper.vcc.mixin.integrations.effectualxeffective;

import com.imeetake.effectual.effects.WaterDrip.WaterDripEffect;
//? if fabric {
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
//?}
import dev.architectury.registry.registries.RegistrySupplier;
import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import dev.vesper.eveningstarlib.EveningStarLib;
import dev.vesper.vcc.Config;
import dev.vesper.vcc.util.ParticleModContext;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.SimpleParticleType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

//? 1.20.1 && fabric{
/*import org.ladysnake.effective.core.Effective;
import org.ladysnake.effective.core.utils.EffectiveUtils;
*///?} 1.21.1 && fabric{
/*import org.ladysnake.effective.utils.EffectiveUtils;
import org.ladysnake.effective.index.EffectiveParticles;
*///?}

@Mixin(WaterDripEffect.class)
@MixinEnvironment(type = MixinEnvironment.Env.CLIENT)
public class WaterDripEffectMixin {

	//? <=1.21.1 && fabric{
	/*@WrapOperation(method = "spawnWaterDripParticles", at = @At(value = "INVOKE", target = "Lcom/imeetake/effectual/EffectualClientParticles;spawn(Ldev/architectury/registry/registries/RegistrySupplier;DDDDDD)V"))
	private static void vcc$spawnWaterDripParticle$head(RegistrySupplier<? extends SimpleParticleType> type, double x, double y, double z, double dx, double dy, double dz, Operation<Void> original, @Local(name = "lx") double lx, @Local(name = "lz") double lz){
		if (Config.effectualGlowDrip && EveningStarLib.isModLoaded("effectual") && EveningStarLib.isModLoaded("effective")){
			assert Minecraft.getInstance().player != null;
			if (EffectiveUtils.isGlowingWater(Minecraft.getInstance().level, Minecraft.getInstance().player.blockPosition())) {
				assert Minecraft.getInstance().level != null;
				// I fully expect this to accidentally modify the droplet particles of other effects, but we can just call that cosmetic variation and move on
				// Deltas are the best this effect is going to get without taking over Glow Droplet in a way that will negatively impact the appearance of other effects
				// All that being said I have an idea for a better way to do this, will add in 0.4.1 after i port to the forges
				ParticleModContext.fixGlowDropForPlayerDrip = true;
				Minecraft.getInstance().level.addParticle(/^? 1.20.1 {^//^Effective.GLOW_DROPLET^//^?} 1.21.1 { ^/ /^EffectiveParticles.GLOW_DROPLET ^//^?} ^/, x, y, z, lx/7, -0.05, lz/7);
				ParticleModContext.fixGlowDropForPlayerDrip = false;
			} else {
				original.call(type, x, y, z, dx, dy, dz);
			}
		} else {
			original.call(type, x, y, z, dx, dy, dz);
		}
	}
	*///?}
}
