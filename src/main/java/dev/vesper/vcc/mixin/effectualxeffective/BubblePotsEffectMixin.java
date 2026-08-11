package dev.vesper.vcc.mixin.effectualxeffective;

import com.imeetake.effectual.EffectualConfig;
import com.imeetake.effectual.effects.Bubbles.BubblePotsEffect;
import dev.architectury.event.events.client.ClientTickEvent;
import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import dev.vesper.eveningstarlib.EveningStarLib;
import dev.vesper.vcc.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.level.block.Blocks;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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

@Mixin(BubblePotsEffect.class)
@MixinEnvironment(type = MixinEnvironment.Env.CLIENT)
public class BubblePotsEffectMixin {

	//? <=1.21.1{
	/*@Shadow
	private static void resetScanPosition(){}

	@Shadow
	private static void advanceScanPosition(){}

	@Shadow
	private static ClientLevel lastLevel;

	@Shadow
	private static BlockPos lastPlayerPos;

	@Shadow
	private static int scanX;
	@Shadow
	private static int scanY;
	@Shadow
	private static int scanZ;

	@Unique
	private static final BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

	@Inject(method = "register", at = @At("HEAD"), cancellable = true)
	private static void vcc$register$head(CallbackInfo ci){
		if (Config.replaceEffectualPots && EveningStarLib.isModLoaded("effectual") && EveningStarLib.isModLoaded("effective")) {
			ClientTickEvent.CLIENT_POST.register((ClientTickEvent.Client)(client) -> {
				if (EffectualConfig.get().bubblePots && !client.isPaused()) {
					if (client.level == null) {
						resetScanPosition();
						lastLevel = null;
						lastPlayerPos = null;
					} else if (client.player != null) {
						ClientLevel level = client.level;
						if (lastLevel != level) {
							resetScanPosition();
							lastLevel = level;
							lastPlayerPos = null;
						}

						BlockPos center = client.player.blockPosition();
						if (lastPlayerPos != null && center.distSqr(lastPlayerPos) > (double)100.0F) {
							resetScanPosition();
						}

						lastPlayerPos = center;

						for(int i = 0; i < 48; ++i) {
							pos.set(center.getX() + scanX, center.getY() + scanY, center.getZ() + scanZ);
							if (level.getBlockState(pos).is(Blocks.DECORATED_POT) && level.getFluidState(pos).isSource()) {
								//? 1.20.1{
								/^level.addParticle((ParticleOptions) Effective.BUBBLE, pos.getX() + 0.5f, pos.getY() + 1.1, pos.getZ() + 0.5, 0, 0.1, 0);
								// some of these numbers might be in the wrong spot idk
								/^¹WorldParticleBuilder.create(Effective.BUBBLE).enableForcedSpawn()
										.setScaleData(GenericParticleData.create(0.05f + Minecraft.getInstance().level.random.nextFloat() * 0.05f)
												.build())
										.setTransparencyData(GenericParticleData.create(1.0f)
												.build())
										.enableNoClip()
										.setLifetime(60 + Minecraft.getInstance().level.random.nextInt(60))
										.addTickActor(new LinearForcedMotionImpl(new Vector3f((float) 0, 0.1F, (float) 0), new Vector3f(0, 0, 0), 10f))
										.setRenderType(ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT)
										.spawn(Minecraft.getInstance().level, pos.getX() + 0.5f, pos.getY() + 1.1, pos.getZ() + 0.5);¹^/
								^///?} 1.21.1{
								/^level.addParticle(EffectiveParticles.BUBBLE, pos.getX() + 0.5f, pos.getY() + 1.1, pos.getZ() + 0.5, 0, 0.1, 0);
								^///?}
							}

							advanceScanPosition();
						}

					}
				}
			});
			ci.cancel();
		}
	}
	*///?}
}
