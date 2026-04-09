package vesper.vcc;

import net.fabricmc.api.ClientModInitializer;
import vesper.vcc.leaks.etf.UpdatePlayerTextureMap;
import vesper.vcc.leaks.jade.ClearAccessor;
import vesper.vcc.leaks.jei.ClearCachedInventories;
import vesper.vcc.leaks.jei.ClearMenu;

public class VCCClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
        YACLConfig.CONFIG.load();
        UpdatePlayerTextureMap.init();
        ClearMenu.onRespawn();
        ClearAccessor.init();
        ClearCachedInventories.init();
	}
}