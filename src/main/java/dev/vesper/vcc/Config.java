package dev.vesper.vcc;

//? if neoforge || fabric
import net.neoforged.neoforge.common.ModConfigSpec;
//? if forge
//import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class Config {

	public static final ClientConfig CLIENT;
	//~ if forge 'ModConfigSpec' -> 'ForgeConfigSpec'
	public static final ModConfigSpec CONFIG_SPEC;

	static {
		//~ if forge 'ModConfigSpec' -> 'ForgeConfigSpec'
		final Pair<ClientConfig, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(ClientConfig::new);
		CLIENT = specPair.getLeft();
		CONFIG_SPEC = specPair.getRight();
	}

	public static class ClientConfig {
		//~ if forge 'ModConfigSpec' -> 'ForgeConfigSpec' {
		private final ModConfigSpec.BooleanValue oarSplash;
		private final ModConfigSpec.BooleanValue glowingWakes;
		private final ModConfigSpec.BooleanValue useEffectiveBubbleBreath;
		private final ModConfigSpec.BooleanValue replaceEffectualChestBubble;
		private final ModConfigSpec.BooleanValue replaceEffectualPots;
		private final ModConfigSpec.BooleanValue effectualGlowDrip;
		private final ModConfigSpec.BooleanValue breathSteam;
		private final ModConfigSpec.BooleanValue replaceRipple;
		public static final float shaderLightPassthrough = 0.5f;

		ClientConfig(ModConfigSpec.Builder builder) {
		//~}
			//builder.comment("Vesper's Compatibility Compendium").push("general");

			builder.comment("Compatibility & Integration").push("compatibility");
			oarSplash = builder.comment("Enable Oar Splash").define("oarSplash", true);
			useEffectiveBubbleBreath = builder.comment("effetive bubble").define("useEffectiveBubbleBreath", true);
			replaceEffectualChestBubble = builder.comment("Enable Oar Splash").define("replaceEffectualChestBubble", true);
			replaceEffectualPots = builder.comment("Enable Oar Splash").define("replaceEffectualPots", true);
			effectualGlowDrip = builder.comment("Enable Oar Splash").define("effectualGlowDrip", true);
			breathSteam = builder.comment("Enable Oar Splash").define("breathSteam", true);
			replaceRipple = builder.comment("Enable Oar Splash").define("replaceRipple", true);
			builder.pop();

			builder.comment("Tweaks").push("tweaks");
			glowingWakes = builder.comment("Make wakes glow").define("glowingWakes", true);
			builder.pop();
		}
	}

	public static boolean oarSplash() {return CLIENT.oarSplash.get();}
	public static boolean glowingWakes() {return CLIENT.glowingWakes.get();}
	public static boolean useEffectiveBubbleBreath() {return CLIENT.useEffectiveBubbleBreath.get();}
	public static boolean replaceEffectualChestBubble() {return CLIENT.replaceEffectualChestBubble.get();}
	public static boolean replaceEffectualPots() {return CLIENT.replaceEffectualPots.get();}
	public static boolean effectualGlowDrip() {return CLIENT.effectualGlowDrip.get();}
	public static boolean breathSteam() {return CLIENT.breathSteam.get();}
	public static boolean replaceRipple() {return CLIENT.replaceRipple.get();}
}
