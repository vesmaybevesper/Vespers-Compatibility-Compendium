package dev.vesper.vcc.platform.fabric;

//? fabric {

import dev.vesper.vcc.Config;
import dev.vesper.vcc.VCC;
import dev.kikugie.fletching_table.annotation.fabric.Entrypoint;
//? if >=1.21.1 {
//~ if 1.21.1 'fuzs.forgeconfigapiport.fabric.api.v5.ConfigRegistry' -> 'fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeConfigRegistry'
import fuzs.forgeconfigapiport.fabric.api.v5.ConfigRegistry;
//?} else {
/*import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
*///?}
import net.fabricmc.api.ModInitializer;
//~ if 1.20.1 'import net.neoforged.fml.config.ModConfig;' -> 'import net.minecraftforge.fml.config.ModConfig;'
import net.neoforged.fml.config.ModConfig;

@Entrypoint("main")
public class FabricEntrypoint implements ModInitializer {

	@Override
	public void onInitialize() {
		VCC.onInitialize();
		//? if >= 1.21.1 {
		//~ if 1.21.1 'ConfigRegistry' -> 'NeoForgeConfigRegistry'
		ConfigRegistry.INSTANCE.register(VCC.MOD_ID, ModConfig.Type.CLIENT, Config.CONFIG_SPEC);
		//?} else {
		/*ForgeConfigRegistry.INSTANCE.register(VCC.MOD_ID, ModConfig.Type.CLIENT, Config.CONFIG_SPEC);
		*///?}
	}
}
//?}
