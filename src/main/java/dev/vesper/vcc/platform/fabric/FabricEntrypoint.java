package dev.vesper.vcc.platform.fabric;

//? fabric {

import dev.vesper.vcc.VCC;
import dev.kikugie.fletching_table.annotation.fabric.Entrypoint;
import net.fabricmc.api.ModInitializer;

@Entrypoint("main")
public class FabricEntrypoint implements ModInitializer {

	@Override
	public void onInitialize() {
		VCC.onInitialize();
	}
}
//?}
