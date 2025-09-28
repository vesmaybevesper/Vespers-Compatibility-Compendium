package vesper.vcc.effectivewakes;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeKeys;
import org.jetbrains.annotations.Nullable;
import org.ladysnake.effective.Effective;
import org.ladysnake.effective.EffectiveConfig;
import org.ladysnake.effective.index.EffectiveParticles;


public class EffectiveWakesUtil {
    public static void spawnSplashAccurate(World world, Vec3d pos, double velX, double velY, double velZ, @Nullable org.ladysnake.effective.particle.contracts.SplashParticleInitialData data){
        org.ladysnake.effective.particle.types.SplashParticleType splash = EffectiveParticles.SPLASH;
        BlockPos blockPos = BlockPos.ofFloored(pos.getX(),pos.getY(),pos.getZ());
        if (EffectiveConfig.glowingPlankton && world.isNight() && world.getBiome(blockPos).matchesKey(BiomeKeys.WARM_OCEAN)) {
            splash = EffectiveParticles.GLOW_SPLASH;
        }
        world.addParticle(splash.setData(data), pos.getX(), pos.getY() + 9, pos.getZ(), velX, velY, velZ);
    }
}
