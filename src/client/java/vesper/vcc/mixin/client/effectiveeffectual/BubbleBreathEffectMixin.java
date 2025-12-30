package vesper.vcc.mixin.client.effectiveeffectual;

import com.imeetake.effectual.effects.Bubbles.BubbleBreathEffect;
import dev.vesper.eveningstarlib.common.ESLPosUtils;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.ladysnake.effective.index.EffectiveParticles;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vesper.vcc.YACLConfig;

@Mixin(BubbleBreathEffect.class)
public class BubbleBreathEffectMixin {
    @Unique
    private static final RandomSource RANDOM = RandomSource.create();
    @Inject(method = "processBreathTick", at = @At("HEAD"), cancellable = true)
    private static void replaceParticles(Player player, CallbackInfo ci){
        if (FabricLoader.getInstance().isModLoaded("effective") && FabricLoader.getInstance().isModLoaded("effectual")) {
            if (YACLConfig.bubbleBreath) {
                ClientLevel world = (ClientLevel) player.level();
                float xRot = player.getXRot();
                float yRot = player.getYRot();
                float xRotRad = xRot * ((float) Math.PI / 180F);
                float yRotRad = -yRot * ((float) Math.PI / 180F);
                double lookX = (double) (Mth.sin(yRotRad) * Mth.cos(xRotRad));
                double lookY = (double) (-Mth.sin(xRotRad));
                double lookZ = (double) (Mth.cos(yRotRad) * Mth.cos(xRotRad));
                double mouthOffsetForward = (double) 0.25F;
                double mouthOffsetDown = 0.15;
                double originX = player.getX() + lookX * mouthOffsetForward;
                double originY = player.getEyeY() - mouthOffsetDown + lookY * mouthOffsetForward;
                double originZ = player.getZ() + lookZ * mouthOffsetForward;
                Vec3 playerVel = player.getDeltaMovement();
                if (RANDOM.nextBoolean()) {
                    double velocityX = lookX * 0.1 + playerVel.x * 0.8;
                    double velocityY = lookY * 0.1 + playerVel.y * 0.8 + 0.05;
                    double velocityZ = lookZ * 0.1 + playerVel.z * 0.8;
                    double xOffset = ESLPosUtils.offsetWithNegative(.000000000001);
                    double zOffset = ESLPosUtils.offsetWithNegative(.000000000001);
                    world.addParticle(EffectiveParticles.BUBBLE, originX + (xOffset / 2), originY, originZ + (zOffset / 2), velocityX, velocityY, velocityZ);
                }
            }
                ci.cancel();
        }
    }
}