package vesper.vcc.mixin.client.effectiveeffectual;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.EnderChestBlockEntity;
import org.ladysnake.effective.index.EffectiveParticles;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vesper.vcc.YACLConfig;

@Mixin(com.imeetake.effectual.effects.Bubbles.BubbleChestEffect.class)
public class EffectualBubblesMixin {
    @Inject(method = "register", at = @At("HEAD"), cancellable = true, remap = false)
    private static void overrideRegister(CallbackInfo ci){
        final RandomSource RANDOM = RandomSource.create();
        if (FabricLoader.getInstance().isModLoaded("effective") && FabricLoader.getInstance().isModLoaded("effectual")) {
            if (YACLConfig.bubbleChest){
                ClientTickEvents.END_CLIENT_TICK.register((client) -> {
                    if (com.imeetake.effectual.EffectualClient.CONFIG.bubbleChests()) {
                        if (client.level != null) {
                            ClientLevel world = client.level;
                            assert client.player != null;
                            BlockPos playerPos = client.player.getOnPos();
                            int radius = 5;

                            for(BlockPos pos : BlockPos.betweenClosed(playerPos.offset(-radius, -radius, -radius), playerPos.offset(radius, radius, radius))) {
                                if (world.getBlockState(pos).is(Blocks.CHEST) || world.getBlockState(pos).is(Blocks.ENDER_CHEST)) {
                                    BlockEntity blockEntity = world.getBlockEntity(pos);
                                    boolean isOpen = false;
                                    if (blockEntity instanceof ChestBlockEntity chestEntity) {
                                        isOpen = chestEntity.getOpenNess(0.0F) > 0.0F;
                                    } else if (blockEntity instanceof EnderChestBlockEntity enderChestEntity) {
                                        isOpen = enderChestEntity.getOpenNess(0.0F) > 0.0F;
                                    }

                                    if (isOpen && world.getFluidState(pos).isSource() && RANDOM.nextInt(10) == 0) {
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


