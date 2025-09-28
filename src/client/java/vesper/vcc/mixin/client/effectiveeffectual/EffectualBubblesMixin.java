package vesper.vcc.mixin.client.effectiveeffectual;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.entity.EnderChestBlockEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.ladysnake.effective.index.EffectiveParticles;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vesper.vcc.Config;

@Mixin(com.imeetake.effectual.effects.Bubbles.BubbleChestEffect.class)
public class EffectualBubblesMixin {
    @Inject(method = "register", at = @At("HEAD"), cancellable = true, remap = false)
    private static void overrideRegister(CallbackInfo ci){
        final Random RANDOM = Random.create();
        if (FabricLoader.getInstance().isModLoaded("effective") && FabricLoader.getInstance().isModLoaded("effectual")) {
            if (Config.EffectiveXEffectual && Config.useEffectiveBubbleChest){
                ClientTickEvents.END_CLIENT_TICK.register((client) -> {
                    if (com.imeetake.effectual.EffectualClient.CONFIG.bubbleChests()) {
                        if (client.world != null) {
                            ClientWorld world = client.world;
                            assert client.player != null;
                            BlockPos playerPos = client.player.getBlockPos();
                            int radius = 5;

                            for(BlockPos pos : BlockPos.iterate(playerPos.add(-radius, -radius, -radius), playerPos.add(radius, radius, radius))) {
                                if (world.getBlockState(pos).isOf(Blocks.CHEST) || world.getBlockState(pos).isOf(Blocks.ENDER_CHEST)) {
                                    BlockEntity blockEntity = world.getBlockEntity(pos);
                                    boolean isOpen = false;
                                    if (blockEntity instanceof ChestBlockEntity chestEntity) {
                                        isOpen = chestEntity.getAnimationProgress(0.0F) > 0.0F;
                                    } else if (blockEntity instanceof EnderChestBlockEntity enderChestEntity) {
                                        isOpen = enderChestEntity.getAnimationProgress(0.0F) > 0.0F;
                                    }

                                    if (isOpen && world.getFluidState(pos).isStill() && RANDOM.nextInt(10) == 0) {
                                        world.addParticle(EffectiveParticles.BUBBLE, (double)pos.getX() + (double)0.5F, (double)pos.getY() + (double)1.0F, (double)pos.getZ() + (double)0.5F, (double)0.0F, 0.1, (double)0.0F);
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


