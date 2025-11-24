/*
package vesper.vcc.mixin.client.effectiveeffectual;


import com.imeetake.effectual.effects.Bubbles.BubbleBreathEffect;
import dev.vesper.eveningstarlib.common.ESLPosUtils;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
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
    @Inject(method = "spawnBubbleParticles", at = @At("HEAD"), cancellable = true)
    private static void replaceParticles(Player player, CallbackInfo ci){
        if (FabricLoader.getInstance().isModLoaded("effective") && FabricLoader.getInstance().isModLoaded("effectual")) {
            if (YACLConfig.bubbleBreath) {
                ClientLevel world = (ClientLevel) player.level();
                double yawRad = Math.toRadians(player.getYRot());
                double x = player.getX() - Math.sin(yawRad) * 0.3;
                double y = player.getEyeY() - 0.2;
                double z = player.getZ() + Math.cos(yawRad) * 0.3;
                int i = 0;

                for (int count = 2 * RANDOM.nextInt(4); i < count; i++) {
                    double xOffset = ESLPosUtils.offsetWithNegative(.000000000001);
                    double zOffset = ESLPosUtils.offsetWithNegative(.000000000001);
                    world.addParticle(EffectiveParticles.BUBBLE, x + (xOffset / 2), y, z + (zOffset / 2), 0, 0.1, 0);
                }
            }
                ci.cancel();
        }
    }
}

*/
