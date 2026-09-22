package dev.vesper.vcc;

//? fabric{

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
//? if >=1.21.1
import net.neoforged.neoforge.client.gui.ConfigurationScreen;

public class ModMenuInit implements ModMenuApi {
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		// not sure what to put here for this rn will fix :)
		//~ if 1.20.1 'new ConfigurationScreen(VCC.MOD_ID, parent)' -> 'null'
		return parent -> new ConfigurationScreen(VCC.MOD_ID, parent);
	}
}

//?}
