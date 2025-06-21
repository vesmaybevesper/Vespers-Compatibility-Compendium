package vesper.vcc.mixin.client.effectiveparticlerain;


import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Blocks;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import org.ladysnake.effective.core.particle.DropletParticle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pigcart.particlerain.ParticleRainClient;
import vesper.vcc.Config;

@Mixin(DropletParticle.class)
public abstract class DropletParticleMixin extends SpriteBillboardParticle {

    protected DropletParticleMixin(ClientWorld clientWorld, double d, double e, double f) {
        super(clientWorld, d, e, f);
    }

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void replaceRipple(CallbackInfo ci){
        if (Config.EffectiveXParticleRain && Config.dropletRipple && FabricLoader.getInstance().isModLoaded("effective") && FabricLoader.getInstance().isModLoaded("particlerain")) {
            this.prevPosX = this.x;
            this.prevPosY = this.y;
            this.prevPosZ = this.z;
            if (this.age++ >= this.maxAge) {
                this.markDead();
            }

            if (this.onGround || this.age > 5 && this.world.getBlockState(BlockPos.ofFloored(this.x, this.y + this.velocityY, this.z)).getBlock() == Blocks.WATER) {
                this.markDead();
            }

            if (this.world.getBlockState(BlockPos.ofFloored(this.x, this.y + this.velocityY, this.z)).getBlock() == Blocks.WATER && this.world.getBlockState(BlockPos.ofFloored(this.x, this.y, this.z)).isAir()) {
                for(int i = 0; i > -10; --i) {
                    BlockPos pos = BlockPos.ofFloored(this.x, (double)(Math.round(this.y) + (long)i), this.z);
                    if (this.world.getBlockState(pos).getBlock() == Blocks.WATER && this.world.getBlockState(BlockPos.ofFloored(this.x, (double)(Math.round(this.y) + (long)i), this.z)).getFluidState().isStill() && this.world.getBlockState(BlockPos.ofFloored(this.x, (double)(Math.round(this.y) + (long)i + 1L), this.z)).isAir()) {
                        this.world.addParticle(ParticleRainClient.RIPPLE, this.x, (double)((float)(Math.round(this.y) + (long)i) + 0.9F), this.z, (double)0.0F, (double)0.0F, (double)0.0F);
                        break;
                    }
                }

                this.markDead();
            }
            this.velocityX *= (double)0.99F;
            this.velocityY -= (double)0.05F;
            this.velocityZ *= (double)0.99F;
            this.move(this.velocityX, this.velocityY, this.velocityZ);

            ci.cancel();
        }
    }
}
