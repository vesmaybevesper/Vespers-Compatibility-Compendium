package dev.vesper.vcc;

import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.autogen.AutoGen;
import dev.isxander.yacl3.config.v2.api.autogen.Boolean;
import dev.isxander.yacl3.config.v2.api.autogen.EnumCycler;
import dev.isxander.yacl3.platform.YACLPlatform;
import dev.vesper.eveningstarlib.common.serializers.fastjson.FastJsonConfigSerializerBuilder;
import net.minecraft.client.gui.screens.Screen;
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

	public static Screen config(Screen parent){
		return HANDLER.generateGui().generateScreen(parent);
	}

	public enum RippleType {WAKES, EFFECTIVE, PARTICLE_RAIN}

	public enum OverlapFavor {EFFECTIVE, EFFECTUAL}

	//@AutoGen(category = "general")
	@Boolean(formatter = Boolean.Formatter.TRUE_FALSE)
	@SerialEntry
	public static boolean globalRipple = false;

	//@AutoGen(category = "general")
	@EnumCycler
	@SerialEntry
	public static RippleType rippleType = RippleType.WAKES;

	//@AutoGen(category = "general")
	@Boolean(formatter = Boolean.Formatter.TRUE_FALSE)
	@SerialEntry
	public static boolean disableOverlap = false;

	//@AutoGen(category = "general")
	@EnumCycler
	@SerialEntry
	public static OverlapFavor overlapFavorPrimary = OverlapFavor.EFFECTIVE;

	//@AutoGen(category = "general")
	@EnumCycler
	@SerialEntry
	public static OverlapFavor overlapFavorSecondary = OverlapFavor.EFFECTIVE;

	//? <=1.21.1{
	/*@AutoGen(category = "effxwakes")
	@Boolean(formatter = Boolean.Formatter.ON_OFF, colored = true)
	@SerialEntry
	public static boolean oarSplash = true;
	*///?}

	//? <=1.21.1{
	/*@AutoGen(category = "effxwakes")
	*///?} >=1.21.11 || !fabric{
	@AutoGen(category = "tweaks")
	//?}
	@Boolean(formatter = Boolean.Formatter.ON_OFF, colored = true)
	@SerialEntry
	public static boolean glowingWakes = true;

	public static float shaderLightPassthrough = 0.5f;

	//? <=1.21.1{
	/*@AutoGen(category = "effxeff")
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
	public static boolean effectualGlowDrip = false;

	@AutoGen(category = "effxeff")
	@Boolean(formatter = Boolean.Formatter.ON_OFF, colored = true)
	@SerialEntry
	public static boolean breathSteam = false;

	@AutoGen(category = "effxparticlerain")
	@Boolean(formatter = Boolean.Formatter.ON_OFF, colored = true)
	@SerialEntry
	public static boolean replaceRipple = true;
	*///?}
}
