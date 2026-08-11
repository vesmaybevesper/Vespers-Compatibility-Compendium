package dev.vesper.vcc;

import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.autogen.AutoGen;
import dev.isxander.yacl3.config.v2.api.autogen.Boolean;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import dev.isxander.yacl3.platform.YACLPlatform;
import dev.vesper.eveningstarlib.common.serializers.fastjson.FastJsonConfigSerializerBuilder;
import net.minecraft.resources.Identifier;

public class Config {
	//? 1.20.1{
	/*public static ConfigClassHandler<Config> HANDLER = ConfigClassHandler.createBuilder(Config.class)
			.id(Identifier.tryBuild(VCC.MOD_ID, "config"))
			.serializer(config -> FastJsonConfigSerializerBuilder.create(config)
					.setPath(YACLPlatform.getConfigDir().resolve("vcc.json"))
					.build())
			.build();
	*///?} >=1.21.1{
	public static ConfigClassHandler<Config> HANDLER = ConfigClassHandler.createBuilder(Config.class)
			.id(Identifier.fromNamespaceAndPath(VCC.MOD_ID, "config"))
			.serializer(config -> FastJsonConfigSerializerBuilder.create(config)
					.setPath(YACLPlatform.getConfigDir().resolve("vcc.json"))
					.build())
			.build();
	//?}

	@AutoGen(category = "effxwakes")
	@Boolean(formatter = Boolean.Formatter.ON_OFF, colored = true)
	@SerialEntry
	public static boolean oarSplash = true;

	@AutoGen(category = "effxwakes")
	@Boolean(formatter = Boolean.Formatter.ON_OFF, colored = true)
	@SerialEntry
	public static boolean glowingWakes = true;

	public static float shaderLightPassthrough = 0.5f;

	@AutoGen(category = "effxeff")
	@Boolean(formatter = Boolean.Formatter.ON_OFF, colored = true)
	@SerialEntry
	public static boolean useEffectiveBubbleBreath = true;

	@AutoGen(category = "effxeff")
	@Boolean(formatter = Boolean.Formatter.ON_OFF, colored = true)
	@SerialEntry
	public static boolean replaceEffectualChestBubble = true;

	@AutoGen(category = "effxeff")
	@Boolean(formatter = Boolean.Formatter.ON_OFF, colored = true)
	@SerialEntry
	public static boolean replaceEffectualPots = true;

	@AutoGen(category = "effxeff")
	@Boolean(formatter = Boolean.Formatter.ON_OFF, colored = true)
	@SerialEntry
	public static boolean effectualGlowDrip = true;

	@AutoGen(category = "effxeff")
	@Boolean(formatter = Boolean.Formatter.ON_OFF, colored = true)
	@SerialEntry
	public static boolean breathSteam = false;

	@AutoGen(category = "effxparticlerain")
	@Boolean(formatter = Boolean.Formatter.ON_OFF, colored = true)
	@SerialEntry
	public static boolean replaceRipple = true;
}
