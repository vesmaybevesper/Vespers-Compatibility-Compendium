package dev.vesper.vcc;

import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.autogen.AutoGen;
import dev.isxander.yacl3.config.v2.api.autogen.Boolean;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import dev.isxander.yacl3.platform.YACLPlatform;
import net.minecraft.resources.Identifier;

public class Config {
	public static ConfigClassHandler<Config> HANDLER = ConfigClassHandler.createBuilder(Config.class).id(Identifier.tryBuild(VCC.MOD_ID, "config")).serializer(config -> GsonConfigSerializerBuilder.create(config).setPath(YACLPlatform.getConfigDir().resolve("vcc.json")).build()).build();

	@AutoGen(category = "effxwakes")
	@Boolean(formatter = Boolean.Formatter.ON_OFF, colored = true)
	@SerialEntry
	public static boolean oarSplash = true;

	@AutoGen(category = "effxwakes")
	@Boolean(formatter = Boolean.Formatter.ON_OFF, colored = true)
	@SerialEntry
	public static boolean glowingWakes = true;


}
