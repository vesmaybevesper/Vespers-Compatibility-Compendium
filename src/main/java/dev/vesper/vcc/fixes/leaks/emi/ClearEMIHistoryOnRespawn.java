package dev.vesper.vcc.fixes.leaks.emi;

//? if fabric {
import dev.vesper.vcc.VCC;
import net.fabricmc.loader.api.FabricLoader;
import dev.vesper.eveningstarlib.platform.fabric.events.ClientRespawnEventCallback;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClearEMIHistoryOnRespawn {
	public static void init(){
		if (!FabricLoader.getInstance().isModLoaded("emi")) return;

		try {
			Class<?> emiHistory = Class.forName("dev.emi.emi.runtime.EmiHistory");
			Method clearMethod = emiHistory.getDeclaredMethod("clear");

			ClientRespawnEventCallback.EVENT.register(((gameMode, oldPlayer, newPlayer, clientConnection) -> {
				try {
					clearMethod.invoke(null);
				} catch (InvocationTargetException | IllegalAccessException e) {
					VCC.LOGGER.error("Error while clearing emi history", e);
				}
			}));
		} catch (ClassNotFoundException | NoSuchMethodException e) {
			VCC.LOGGER.warn("EMI Class not found, skipping EMI fix");
		}
	}
}
//?}
