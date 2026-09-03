package dev.vesper.vcc.platform.fabric;

//? fabric {

import dev.vesper.vcc.Config;
import dev.vesper.vcc.VCC;
import dev.kikugie.fletching_table.annotation.fabric.Entrypoint;
import fuzs.forgeconfigapiport.fabric.api.v5.ConfigRegistry;
import net.fabricmc.api.ModInitializer;
//~ if 1.20.1 'import net.neoforged.fml.config.ModConfig;' -> 'import net.minecraftforge.fml.config.ModConfig;'
import net.neoforged.fml.config.ModConfig;

@Entrypoint("main")
public class FabricEntrypoint implements ModInitializer {

	@Override
	public void onInitialize() {
		VCC.onInitialize();
		ConfigRegistry.INSTANCE.register(VCC.MOD_ID, ModConfig.Type.CLIENT, Config.CONFIG_SPEC);
	}
}
//?}
