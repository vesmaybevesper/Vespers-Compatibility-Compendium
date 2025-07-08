package vesper.vcc.effectiveparticlerain;

import net.fabricmc.loader.api.FabricLoader;
import org.ladysnake.effective.core.EffectiveConfig;
import pigcart.particlerain.config.ModConfig;
import vesper.vcc.Config;
import vesper.vcc.Util;

public class EffectiveParticleRainDisableConflicts {

    public static void init(){
        if (FabricLoader.getInstance().isModLoaded("wakes") && FabricLoader.getInstance().isModLoaded("effective")){
            if (Config.EPRDisableOverlap) {
                Util.log("Disabling Effective x Particle Rain overlapping features");
                if (EffectiveConfig.rainRippleDensity >= 1 && ModConfig.CONFIG.ripple.opacity >= 0.000000001){
                    EffectiveConfig.rainRippleDensity = 0;
                }
            }
            // More can be added starting here
        }
    }
}
