package dev.vesper.vcc.mixin.effectualxeffective;

import com.imeetake.effectual.effects.Bubbles.BubbleBreathEffect;
import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import dev.vesper.eveningstarlib.EveningStarLib;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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

	@Inject(method = "processBreathTick", at = @At("HEAD"), cancellable = true)
	private static void vcc$processBreathTick$head(Player player, CallbackInfo ci){
		if (useEffectiveBubbleBreath && EveningStarLib.isModLoaded("effectual") && EveningStarLib.isModLoaded("effective")){
			float xRot = player.getXRot();
			float yRot = player.getYRot();
			float xRotRad = xRot * ((float)Math.PI / 180F);
			float yRotRad = -yRot * ((float)Math.PI / 180F);
			double lookX = (double)(Mth.sin(yRotRad) * Mth.cos(xRotRad));
			double lookY = (double)(-Mth.sin(xRotRad));
			double lookZ = (double)(Mth.cos(yRotRad) * Mth.cos(xRotRad));
			double mouthOffsetForward = (double)0.25F;
			double mouthOffsetDown = 0.15;
			double originX = player.getX() + lookX * mouthOffsetForward;
			double originY = player.getEyeY() - mouthOffsetDown + lookY * mouthOffsetForward;
			double originZ = player.getZ() + lookZ * mouthOffsetForward;
			Vec3 playerVel = player.getDeltaMovement();
			if (RANDOM.nextBoolean()) {
				double velocityX = lookX * 0.1 + playerVel.x * 0.8;
				double velocityY = lookY * 0.1 + playerVel.y * 0.8 + 0.05;
				double velocityZ = lookZ * 0.1 + playerVel.z * 0.8;
				//? 1.20.1{
				/^player.level().addParticle((ParticleOptions) Effective.BUBBLE, originX, originY, originZ, velocityX, velocityY, velocityZ);

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
				/^player.level().addParticle(EffectiveParticles.BUBBLE, originX, originY, originZ, velocityX, velocityY, velocityZ);
				^///?}
			}

			ci.cancel();
		}
	}
	*///?}
}
