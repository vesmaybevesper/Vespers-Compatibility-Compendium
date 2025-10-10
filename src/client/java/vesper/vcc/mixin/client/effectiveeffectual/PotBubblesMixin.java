package vesper.vcc.mixin.client.effectiveeffectual;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import org.ladysnake.effective.index.EffectiveParticles;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vesper.vcc.YACLConfig;

@Mixin(com.imeetake.effectual.effects.Bubbles.BubblePotsEffect.class)
public class PotBubblesMixin {
    @Inject(method = "register", at = @At(value = "HEAD"), cancellable = true, remap = false)
    private static void overrideRegister(CallbackInfo ci){
        final RandomSource RANDOM = RandomSource.create();
        if (FabricLoader.getInstance().isModLoaded("effective") && FabricLoader.getInstance().isModLoaded("effectual")) {
            if (YACLConfig.bubblePot){
                ClientTickEvents.END_CLIENT_TICK.register((ClientTickEvents.EndTick)(client) -> {
                    if (com.imeetake.effectual.EffectualClient.CONFIG.bubblePots()) {
                        if (client.level != null) {
                            ClientLevel world = client.level;
                            assert client.player != null;
                            BlockPos playerPos = client.player.getOnPos();
                            int radius = 5;

                            for(BlockPos pos : BlockPos.betweenClosed(playerPos.offset(-radius, -radius, -radius), playerPos.offset(radius, radius, radius))) {
                                if (world.getBlockState(pos).is(Blocks.DECORATED_POT)) {
                                    world.getBlockEntity(pos);
                                    if (world.getFluidState(pos).isSource() && RANDOM.nextInt(15) == 0) {
                                        world.addParticle(EffectiveParticles.BUBBLE, (double)pos.getX() + (double)0.5F, (double)pos.getY() + 1.3, (double)pos.getZ() + (double)0.5F, 0.0F, 0.1, 0.0F);
                                        /*WorldParticleBuilder.create(EffectiveParticles.BUBBLE).enableForcedSpawn().setScaleData(GenericParticleData.create(0.05f + world.random.nextFloat() * 0.05f).build())
                                                .setTransparencyData(GenericParticleData.create(1f).build())
                                                .enableNoClip().setLifetime(60)
                                                .addTickActor(new LinearForcedMotionImpl(new Vector3f(0f, 0.35f, 0f), new Vector3f(0, 0.1f, 0), 5))
                                                .setRenderType(ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT).spawn(world, pos.getX() + 0.5f, pos.getY() + 1.3, pos.getZ() + 0.5f);*/
                                        // world.addParticle(data, (double)pos.getX() + (double)0.5F, (double)pos.getY() + 1.3, (double)pos.getZ() + (double)0.5F, (double)0.0F, 0.1, (double)0.0F);
                                    }
                                }
                            }

                        }
                    }
                });
                ci.cancel();
            }
        }
    }
}
