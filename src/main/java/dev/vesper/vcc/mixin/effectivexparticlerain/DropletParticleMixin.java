package dev.vesper.vcc.mixin.effectivexparticlerain;


import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import dev.vesper.eveningstarlib.EveningStarLib;
import dev.vesper.vcc.Config;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//? <26.2{
/*import pigcart.particlerain.ParticleRain;
*///?}

//? 1.20.1{
/*import org.ladysnake.effective.core.particle.DropletParticle;
import net.minecraft.client.particle.TextureSheetParticle;
*///?} 1.21.1{
/*import org.ladysnake.effective.particle.DropletParticle;
import net.minecraft.client.particle.TextureSheetParticle;
*///?}


//? <=1.21.1{
/*@Mixin(DropletParticle.class)
*///?} >=1.21.11{
// I need this to still be a mixin to avoid problems launching so I mixin to something random and do nothing :)
@Mixin(Config.class)
//?}
@MixinEnvironment(type = MixinEnvironment.Env.CLIENT)
public abstract class DropletParticleMixin /*? <=1.21.1{ *//*extends TextureSheetParticle *//*?} */ {

	//? <=1.21.1{
	/*protected DropletParticleMixin(ClientLevel clientLevel, double d, double e, double f) {
		super(clientLevel, d, e, f);
	}

	@Inject(method = "tick", at = @At("HEAD"), cancellable = true)
	private void vcc$tick$head(CallbackInfo ci){
		if (Config.replaceRipple && EveningStarLib.isModLoaded("particlerain") && EveningStarLib.isModLoaded("effective")) {
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
						this.level.addParticle(ParticleRain.RIPPLE, this.x, (double)((float)(Math.round(this.y) + (long)i) + 0.9F), this.z, (double)0.0F, (double)0.0F, (double)0.0F);
						break;
					}
				}

				this.remove();
			}

			this.xd *= (double)0.99F;
			this.yd -= (double)0.05F;
			this.zd *= (double)0.99F;
			this.move(this.xd, this.yd, this.zd);

			ci.cancel();
		}
	}
	*///?}
}
