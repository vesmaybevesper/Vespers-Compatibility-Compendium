package dev.vesper.vcc;

//? if neoforge || fabric && >=1.21.1
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.common.ModConfigSpec;
//? if forge || fabric && 1.20.1
//import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class Config {

	public static final ClientConfig CLIENT;
	//~ if forge || fabric && 1.20.1 'ModConfigSpec' -> 'ForgeConfigSpec'
	public static final ModConfigSpec CONFIG_SPEC;

	static {
		//~ if forge || fabric && 1.20.1 'ModConfigSpec' -> 'ForgeConfigSpec'
		final Pair<ClientConfig, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(ClientConfig::new);
		CLIENT = specPair.getLeft();
		CONFIG_SPEC = specPair.getRight();
	}

	public static class ClientConfig {
		//~ if forge || fabric && 1.20.1 'ModConfigSpec' -> 'ForgeConfigSpec' {
		private final ModConfigSpec.BooleanValue glowingWakes;
		private final ModConfigSpec.BooleanValue glowSplashPlane;
		private final ModConfigSpec.BooleanValue effectualGlowDrip;
		//? if <=1.21.1 {
		/*private final ModConfigSpec.BooleanValue oarSplash;
		private final ModConfigSpec.BooleanValue useEffectiveBubbleBreath;
		private final ModConfigSpec.BooleanValue replaceEffectualChestBubble;
		private final ModConfigSpec.BooleanValue replaceEffectualPots;
		private final ModConfigSpec.BooleanValue breathSteam;
		private final ModConfigSpec.BooleanValue replaceRipple;
		*///?}
		public static final float shaderLightPassthrough = 0.5f;

		ClientConfig(ModConfigSpec.Builder builder) {
		//~}
			//? if <=1.21.1 {
			/*builder.comment("Integrations").push("integration");
			builder.comment("Effective & Wakes").push("effwakes");
			oarSplash = builder.comment("Enable Oar Splash").define("oarSplash", true);
			builder.pop();
			builder.comment("Effective & Effectual").push("effsqr");
			useEffectiveBubbleBreath = builder.comment("Effective bubble").define("useEffectiveBubbleBreath", true);
			replaceEffectualChestBubble = builder.comment("Enable Oar Splash").define("replaceEffectualChestBubble", true);
			replaceEffectualPots = builder.comment("Enable Oar Splash").define("replaceEffectualPots", true);
			breathSteam = builder.comment("Enable Oar Splash").define("breathSteam", false);
			builder.pop();
			builder.comment("Effective & Particle Rain").push("effrain");
			replaceRipple = builder.comment("Enable Oar Splash").define("replaceRipple", true);
			builder.pop();
			builder.pop();
			*///?}

			builder.comment(Component.translatable("config.tweaks.comment").getString()).push("tweaks");
			builder.comment(Component.translatable("config.wakes.comment").getString()).push("wakes");
			glowingWakes = builder.comment(Component.translatable("config.glowwakes.comment").getString()).define("glowingWakes", true);
			glowSplashPlane = builder.comment(Component.translatable("config.glowclouds.comment").getString()).define("glowSplashPlane", true);
			builder.pop();
			builder.comment(Component.translatable("config.effectual.comment").getString()).push("effectual");
			effectualGlowDrip = builder.comment(Component.translatable("config.glowdrip.comment").getString()).define("effectualGlowDrip", true);
			builder.pop();
			builder.pop();
		}
	}

	public static boolean glowingWakes() {return CLIENT.glowingWakes.get();}
	public static boolean glowSplashPlane() {return CLIENT.glowSplashPlane.get();}
	public static boolean effectualGlowDrip() {return CLIENT.effectualGlowDrip.get();}
	//? if <=1.21.1 {
	/*public static boolean oarSplash() {return CLIENT.oarSplash.get();}
	public static boolean useEffectiveBubbleBreath() {return CLIENT.useEffectiveBubbleBreath.get();}
	public static boolean replaceEffectualChestBubble() {return CLIENT.replaceEffectualChestBubble.get();}
	public static boolean replaceEffectualPots() {return CLIENT.replaceEffectualPots.get();}
	public static boolean breathSteam() {return CLIENT.breathSteam.get();}
	public static boolean replaceRipple() {return CLIENT.replaceRipple.get();}
	*///?}
}
