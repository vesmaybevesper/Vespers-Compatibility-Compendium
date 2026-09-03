package dev.vesper.vcc.platform.neoforge;

//? neoforge {

/*import dev.vesper.vcc.Config;
import dev.vesper.vcc.VCC;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(VCC.MOD_ID)
public class NeoforgeEntrypoint {

	public NeoforgeEntrypoint(ModContainer modContainer) {
		VCC.onInitialize();
		modContainer.registerConfig(ModConfig.Type.CLIENT, Config.CONFIG_SPEC);

		//~ if 1.21.1 'FMLEnvironment.getDist()' -> 'FMLEnvironment.dist'
		if (FMLEnvironment.getDist() == Dist.CLIENT) {
			modContainer.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
		}
	}
}
*///?}
