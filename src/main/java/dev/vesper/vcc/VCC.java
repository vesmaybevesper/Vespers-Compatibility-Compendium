package dev.vesper.vcc;

import dev.vesper.vcc.fixes.leaks.supplementaries.ClearCachesOnServerStop;
import dev.vesper.vcc.platform.Platform;

import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//? fabric {
import dev.vesper.vcc.platform.fabric.FabricPlatform;
//?} neoforge {
/*import dev.vesper.vcc.platform.neoforge.NeoforgePlatform;
 *///?} forge {
/*import dev.vesper.vcc.platform.forge.ForgePlatform;
 *///?}

@SuppressWarnings("LoggingSimilarMessage")
public class VCC {

	public static final String MOD_ID = /*$ mod_id*/ "vcc";
	public static final String MOD_VERSION = /*$ mod_version*/ "0.4.0";
	public static final String MOD_FRIENDLY_NAME = /*$ mod_name*/ "Vesper's Compatibility Compendium";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private static final Platform PLATFORM = createPlatformInstance();

	public static void onInitialize() {
		LOGGER.info("Initializing {} on {}", MOD_ID, VCC.xplat().loader());
		LOGGER.debug("{}: { version: {}; friendly_name: {} }", MOD_ID, MOD_VERSION, MOD_FRIENDLY_NAME);
		Config.HANDLER.load();

		//? <=1.21.1{
		/*ClearCachesOnServerStop.init();
		*///?}
	}

	public static void onInitializeClient() {
		LOGGER.info("Initializing {} Client on {}", MOD_ID, VCC.xplat().loader());
		LOGGER.debug("{}: { version: {}; friendly_name: {} }", MOD_ID, MOD_VERSION, MOD_FRIENDLY_NAME);
	}

	static Platform xplat() {
		return PLATFORM;
	}

	private static Platform createPlatformInstance() {
		//? fabric {
		return new FabricPlatform();
		//?} neoforge {
		/*return new NeoforgePlatform();
		 *///?} forge {
		/*return new ForgePlatform();
		 *///?}
	}

	private static Identifier id(String path) {
		//? > 1.19.2 {
		return Identifier.tryBuild(MOD_ID, path);
		 //?} <= 1.19.2 {
		/*return new Identifier(MOD_ID, path);
		*///?}
	}

	private static Identifier id(String namespace, String path) {
		//? > 1.19.2 {
		return Identifier.tryBuild(namespace, path);
		 //?} <= 1.19.2 {
		/*return new Identifier(namespace, path);
		*///?}
	}
}
