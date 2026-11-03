package dev.vesper.vcc;

//? fabric && >=1.21.1{

//? if 1.20.1 {
/*import fuzs.forgeconfigscreens.client.gui.screens.ConfigScreen;
import net.minecraft.resources.Identifier;
*///?}
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.minecraft.network.chat.Component;
//? if >=1.21.1
import net.neoforged.neoforge.client.gui.ConfigurationScreen;

public class ModMenuInit implements ModMenuApi {
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		//~ if 1.20.1 'new ConfigurationScreen(VCC.MOD_ID, parent)' -> 'new ConfigScreen(parent, Component.literal("VCC Config"), IModConfig, ResourceLocation.tryParse(""))'
		return parent -> null;
	}
}

//?}
