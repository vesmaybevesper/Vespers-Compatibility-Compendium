package dev.vesper.vcc.platform.fabric;

//? fabric {

import dev.vesper.vcc.VCC;
import dev.kikugie.fletching_table.annotation.fabric.Entrypoint;
import dev.vesper.vcc.fixes.leaks.emi.ClearEMIHistoryOnRespawn;
import dev.vesper.vcc.fixes.leaks.etf.UpdateETFEntityOnRespawn;
import net.fabricmc.api.ClientModInitializer;

@Entrypoint("client")
public class FabricClientEntrypoint implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		VCC.onInitializeClient();

		// Calls to all the fixes, versioned by highest applicable version, additional versioning may happen within the methods
		//? <= 1.21.1{
		/*ClearEMIHistoryOnRespawn.init();
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
	}

}
//?}
