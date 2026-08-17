package dev.vesper.vcc.mixin.effectualxeffective;

import com.imeetake.effectual.effects.Bubbles.BubbleBreathEffect;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import dev.vesper.eveningstarlib.EveningStarLib;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.RandomSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import static dev.vesper.vcc.Config.useEffectiveBubbleBreath;

//? <=1.21.1{
/*import team.lodestar.lodestone.systems.particle.builder.WorldParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
*///?}
//? 1.20.1{
/*import org.ladysnake.effective.core.Effective;
import org.ladysnake.effective.core.utils.LinearForcedMotionImpl;
*///?} 1.21.1{
/*import org.ladysnake.effective.index.EffectiveParticles;
*///?}

@Mixin(BubbleBreathEffect.class)
@MixinEnvironment(type = MixinEnvironment.Env.CLIENT)
public class BubbleBreathMixin {

	//? <=1.21.1{
	/*@Unique
	private final static RandomSource RANDOM = RandomSource.create();

	@WrapOperation(method = "processBreathTick", at = @At(value = "INVOKE", target = "Lcom/imeetake/effectual/EffectualClientParticles;spawnVanilla(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"))
	private static void vcc$processBreathTick$head(ParticleOptions options, double x, double y, double z, double dx, double dy, double dz, Operation<Void> original){
		if (useEffectiveBubbleBreath && EveningStarLib.isModLoaded("effectual") && EveningStarLib.isModLoaded("effective")){
				//? 1.20.1{
			/^assert Minecraft.getInstance().level != null;
			Minecraft.getInstance().level.addParticle((ParticleOptions) Effective.BUBBLE, x, y, z, dx, dy, dz);

				/^¹WorldParticleBuilder.create(Effective.BUBBLE).enableForcedSpawn()
						.setScaleData(GenericParticleData.create(0.05f + player.level().random.nextFloat() * 0.05f)
								.build())
						.setTransparencyData(GenericParticleData.create(1.0f)
								.build())
						.enableNoClip()
						.setLifetime(60 + player.level().random.nextInt(60))
						.addTickActor(new LinearForcedMotionImpl(new Vector3f((float) velocityX, (float) velocityY, (float) velocityZ), new Vector3f(0, 0, 0), 10f))
						.setRenderType(ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT)
						.spawn(player.level(), originX, originY, originZ);¹^/
				^///?} 1.21.1{
			/^assert Minecraft.getInstance().level != null;
			Minecraft.getInstance().level.addParticle(EffectiveParticles.BUBBLE, x, y, z, dx, dy, dz);
				^///?}
		} else {
			original.call(options, x, y, z, dx, dy, dz);
		}
	}
	*///?}
}
