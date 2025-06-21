package vesper.vcc;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ClientModInitializer;
import vesper.vcc.effectiveparticlerain.EffectiveParticleRainDisableConflicts;

public class VCCClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		MidnightConfig.init("vcc", Config.class);
		EffectiveParticleRainDisableConflicts.init();
		Util.log("Vesper's Compatibility Compendium is loaded");
	}
}