package dev.vesper.vcc.platform.fabric;

//? fabric {
import dev.vesper.vcc.Config;import dev.vesper.vcc.VCC;
import dev.kikugie.fletching_table.annotation.fabric.Entrypoint;
import dev.vesper.vcc.fixes.leaks.emi.ClearEMIHistoryOnRespawn;
import dev.vesper.vcc.fixes.leaks.etf.UpdateETFEntityOnRespawn;
import dev.vesper.vcc.fixes.leaks.iceberg.ClearEntitiesOnUnload;
import dev.vesper.vcc.fixes.leaks.iceberg.ClearMapsOnUnload;
import dev.vesper.vcc.fixes.leaks.jade.ClearAccessorOnUnload;
import dev.vesper.vcc.fixes.leaks.jei.ClearMenuOnRespawn;
import fuzs.forgeconfigapiport.fabric.api.v5.ConfigRegistry;
import net.fabricmc.api.ClientModInitializer;
//~ if 1.20.1 'import net.neoforged.fml.config.ModConfig;' -> 'import net.minecraftforge.fml.config.ModConfig;'
import net.neoforged.fml.config.ModConfig;

@Entrypoint("client")
public class FabricClientEntrypoint implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		VCC.onInitializeClient();
		ConfigRegistry.INSTANCE.register(VCC.MOD_ID, ModConfig.Type.CLIENT, Config.CONFIG_SPEC);
		// Calls to all the fixes, versioned by highest applicable version, additional versioning may happen within the methods
		//? <= 1.21.1{
		/*ClearEMIHistoryOnRespawn.init();
		ClearAccessorOnUnload.init();
		*///?} <=1.21.11{

		//?}

		// Calls to fixes that are only applicable to one version
		//? 1.20.1 {

		//?} 1.21.1 {

		//?} 1.21.11 {

		//?} 26.2{

		//?}

		// All remaining fixes that apply to all versions
		UpdateETFEntityOnRespawn.init();
		ClearEntitiesOnUnload.init();
		ClearMapsOnUnload.init();
		ClearMenuOnRespawn.init();
	}

}
//?}
