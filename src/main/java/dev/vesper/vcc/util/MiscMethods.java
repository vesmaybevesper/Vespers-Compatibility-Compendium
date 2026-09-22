package dev.vesper.vcc.util;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;

public class MiscMethods {
	public static boolean shouldGlow(){
		Minecraft mc = Minecraft.getInstance();
		Level level = mc.level;
		Player player = mc.player;
		assert level != null;
		assert player != null;
		return isNight(level) && level.getBiome(player.blockPosition()).is(Biomes.WARM_OCEAN);
	}

	private static boolean isNight(Level level){
		//~ if <=1.21.11 'getOverworldClockTime' -> 'getDayTime'
		long time = level.getOverworldClockTime() % 24000;
		return time >= 12000 && time <= 23000;
	}
}
