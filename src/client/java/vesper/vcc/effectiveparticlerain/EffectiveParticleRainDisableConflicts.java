package vesper.vcc.effectiveparticlerain;

import net.fabricmc.loader.api.FabricLoader;
import pigcart.particlerain.ParticleRainClient;
import vesper.vcc.Config;
import vesper.vcc.Util;

public class EffectiveParticleRainDisableConflicts {

    public static void init(){
        if (FabricLoader.getInstance().isModLoaded("wakes") && FabricLoader.getInstance().isModLoaded("effective")){
            if (Config.EPRDisableOverlap) {
                Util.log("Disabling Effective x Particle Rain overlapping features");
                if (org.ladysnake.effective.core.EffectiveConfig.rainRippleDensity >= 1 && ParticleRainClient.config.doRippleParticles){
                    org.ladysnake.effective.core.EffectiveConfig.rainRippleDensity = 0;
                }
            }
            // More can be added starting here

        }
    }
}
