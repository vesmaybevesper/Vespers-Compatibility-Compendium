package dev.vesper.vcc.mixin.effectualxeffective;

import com.imeetake.effectual.EffectualClientParticles;
import com.imeetake.effectual.EffectualConfig;
import com.imeetake.effectual.effects.Bubbles.BubbleChestEffect;
import dev.architectury.event.events.client.ClientTickEvent;
import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import dev.vesper.eveningstarlib.EveningStarLib;
import dev.vesper.vcc.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.EnderChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
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

@Mixin(BubbleChestEffect.class)
@MixinEnvironment(type = MixinEnvironment.Env.CLIENT)
public class BubbleChestEffectMixin {

	//? <=1.21.1{
	/*@Shadow
	private static void resetScanPosition(){}

	@Shadow
	private static void advanceScanPosition(){}

	@Shadow
	private static ClientLevel lastLevel;

	@Shadow
	private static BlockPos lastPlayerPos;

	@Unique
	private static final BlockPos.MutableBlockPos posM = new BlockPos.MutableBlockPos();

	@Unique
	private static final RandomSource RAND = RandomSource.create();

	@Shadow
	private static int scanX;
	@Shadow
	private static int scanY;
	@Shadow
	private static int scanZ;

	@Inject(method = "register", at = @At("HEAD"), cancellable = true)
	private static void vcc$register$head(CallbackInfo ci){
		if (Config.replaceEffectualChestBubble && EveningStarLib.isModLoaded("effectual") && EveningStarLib.isModLoaded("effective")) {
			ClientTickEvent.CLIENT_POST.register((ClientTickEvent.Client)(client) -> {
				if (EffectualConfig.get().bubbleChests && !client.isPaused()) {
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
							posM.set(center.getX() + scanX, center.getY() + scanY, center.getZ() + scanZ);
							BlockState state = level.getBlockState(posM);
							if ((state.is(Blocks.CHEST) || state.is(Blocks.ENDER_CHEST)) && level.getFluidState(posM).isSource()) {
								BlockEntity be = level.getBlockEntity(posM);
								boolean open = false;
								if (be instanceof ChestBlockEntity) {
									ChestBlockEntity chest = (ChestBlockEntity)be;
									open = chest.getOpenNess(0.0F) > 0.0F;
								} else if (be instanceof EnderChestBlockEntity) {
									EnderChestBlockEntity ender = (EnderChestBlockEntity)be;
									open = ender.getOpenNess(0.0F) > 0.0F;
								}

								if (open) {
									double px = (double)posM.getX() + 0.4 + RAND.nextDouble() * 0.2;
									double py = (double)posM.getY() + 0.8;
									double pz = (double)posM.getZ() + 0.4 + RAND.nextDouble() * 0.2;
									//? 1.20.1{
									/^level.addParticle((ParticleOptions) Effective.BUBBLE, px, py, pz, 0.0D, 0.1D, 0.0D);
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
											.spawn(Minecraft.getInstance().level, px, py, pz);¹^/
									^///?} 1.21.1{
									/^level.addParticle(EffectiveParticles.BUBBLE, px, py, pz, 0.0D, 0.1D, 0.0D);
									^///?}
								}
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
