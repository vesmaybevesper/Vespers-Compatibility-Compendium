package vesper.vcc;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ClientModInitializer;
import vesper.vcc.leaks.emi.EMI;
import vesper.vcc.utils.Util;

public class VCCClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		MidnightConfig.init("vcc", Config.class);
		Util.log("Vesper's Compatibility Compendium is loaded");
		// EffectiveParticleRainDisableConflicts.init();
		// DashKeyBind.init();
        EMI.initialize();
	}
}