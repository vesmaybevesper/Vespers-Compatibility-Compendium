package dev.vesper.vcc;

//? fabric{

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;

public class ModMenuInit implements ModMenuApi {
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return parent -> new ConfigurationScreen(VCC.MOD_ID, parent);
	}
}

//?}
