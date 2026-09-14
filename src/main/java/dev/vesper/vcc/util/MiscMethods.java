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
		//~ if <=1.21.1 'isDarkOutside' -> 'isNight'
		return level.isDarkOutside() && level.getBiome(player.blockPosition()).is(Biomes.WARM_OCEAN);
	}
}
