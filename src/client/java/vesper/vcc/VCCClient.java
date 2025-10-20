package vesper.vcc;

import net.fabricmc.api.ClientModInitializer;
import vesper.vcc.leaks.emf.ClearContext;
import vesper.vcc.leaks.emi.EMI;
import vesper.vcc.leaks.etf.ClearCachedEntity;
import vesper.vcc.leaks.etf.UpdateEntity;
import vesper.vcc.leaks.iceberg.ClearEntities;
import vesper.vcc.leaks.iceberg.ClearMaps;
import vesper.vcc.utils.Util;

public class VCCClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
        YACLConfig.CONFIG.load();
		// EffectiveParticleRainDisableConflicts.init();
		// DashKeyBind.init();
        EMI.initialize();
        //ClearTextureMap.init();
        UpdateEntity.init();
        ClearContext.init();
        ClearCachedEntity.init();
        ClearMaps.init();
        ClearEntities.init();
	}
}