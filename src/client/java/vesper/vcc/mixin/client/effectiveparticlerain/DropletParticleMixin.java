/*
package vesper.vcc.mixin.client.effectiveparticlerain;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.level.block.Blocks;
import org.ladysnake.effective.particle.DropletParticle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vesper.vcc.YACLConfig;

@Mixin(DropletParticle.class)
public abstract class DropletParticleMixin extends TextureSheetParticle {

    protected DropletParticleMixin(ClientLevel clientWorld, double d, double e, double f) {
        super(clientWorld, d, e, f);
    }

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void replaceRipple(CallbackInfo ci){
        if (FabricLoader.getInstance().isModLoaded("effective") && FabricLoader.getInstance().isModLoaded("particlerain")) {
            if (YACLConfig.dropletRipple) {
                this.xo = this.x;
                this.yo = this.y;
                this.zo = this.z;
                if (this.age++ >= this.lifetime) {
                    this.remove();
                }

                if (this.onGround || this.age > 5 && this.level.getBlockState(BlockPos.containing(this.x, this.y + this.yd, this.z)).getBlock() == Blocks.WATER) {
                    this.remove();
                }

                if (this.level.getBlockState(BlockPos.containing(this.x, this.y + this.yd, this.z)).getBlock() == Blocks.WATER && this.level.getBlockState(BlockPos.containing(this.x, this.y, this.z)).isAir()) {
                        for(int i = 0; i > -10; --i) {
                            BlockPos pos = BlockPos.containing(this.x, (double)(Math.round(this.y) + (long)i), this.z);
                            if (this.level.getBlockState(pos).getBlock() == Blocks.WATER && this.level.getBlockState(BlockPos.containing(this.x, (double)(Math.round(this.y) + (long)i), this.z)).getFluidState().isSource() && this.level.getBlockState(BlockPos.containing(this.x, (double)(Math.round(this.y) + (long)i + 1L), this.z)).isAir()) {
                                try {
                                    Class<?> particleRainClass = Class.forName("pigcart.particlerain.ParticleRain");
                                    Object rippleField = particleRainClass.getField("RIPPLE").get(null);
                                    this.level.addParticle((ParticleOptions) rippleField, this.x, (float)(Math.round(this.y) + (long)i) + 0.9F, this.z, 0.0F, 0.0F, 0.0F);
                                } catch (NoSuchFieldException | ClassNotFoundException | IllegalAccessException ignored) {}
                                break;
                            }
                        }

                    this.remove();
                }
                this.xd *= 0.99F;
                this.yd -= 0.05F;
                this.zd *= 0.99F;
                this.move(this.xd, this.yd, this.zd);

                ci.cancel();
            }
        }
    }
}*/
