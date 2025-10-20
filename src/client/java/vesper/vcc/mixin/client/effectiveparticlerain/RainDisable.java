package vesper.vcc.mixin.client.effectiveparticlerain;


import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.WaterFluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = WaterFluid.class, priority = 1500)
public abstract class RainDisable {
    /*@TargetHandler(mixin = "org.ladysnake.effective.mixin.water.RippleAndFlowingWaterSplashesSpawner", name = "effective$splashAndRainRipples")
    @Redirect(method = "@MixinSquared:Handler", at = @At(value = "INVOKE", target = "Lorg/ladysnake/effective/mixin/water/RippleAndFlowingWaterSplashesSpawner;shouldRipple(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)Z"))
    private boolean cancelEffectiveRain(Level level, BlockPos pos){
        if (FabricLoader.getInstance().isModLoaded("effective") && FabricLoader.getInstance().isModLoaded("particlerain")) return false;
        else {
            FluidState fluidState = level.getFluidState(pos);
            return fluidState.isSource() && level.isRaining() && level.getBlockState(pos.offset(0, 1, 0)).isAir();
        }
    }*/

}

