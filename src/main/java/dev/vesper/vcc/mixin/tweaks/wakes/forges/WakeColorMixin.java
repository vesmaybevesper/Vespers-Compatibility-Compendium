package dev.vesper.vcc.mixin.tweaks.wakes.forges;

//? if !fabric
//import com.leclowndu93150.wakes.render.WakeColor;
import dev.vesper.eveningstarlib.EveningStarLib;
import dev.vesper.vcc.Config;
import dev.vesper.vcc.util.MixinDummy;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//~ if fabric 'WakeColor' -> 'MixinDummy'
@Mixin(MixinDummy.class)
public class WakeColorMixin {

	//? if !fabric {
	/*@Shadow
	private static double[] cachedSrcA;

	@Shadow
	@Final
	public int g;

	//? if <=1.21.1 {
	/^@Shadow
	private static double invertedLogisticCurve(float x) {
		throw new UnsupportedOperationException("Implemented via mixin");
	}
	^///?} 26.2{
	@Shadow
	private static double lightFactor(float x){
		throw new UnsupportedOperationException("Implemented via mixin");
	}
	//?}

	@Shadow
	private static double[] cachedInvSrcA;

	@Inject(method = "blendFast", at = @At("HEAD"), cancellable = true)
	private static void blendFast(WakeColor color, int tintR, int tintG, int tintB, int lightColor, float opacity, CallbackInfoReturnable<Integer> cir) {
		if (Config.glowingWakes && EveningStarLib.isModLoaded("wakes")){
			Level level = Minecraft.getInstance().level;

			Player player = Minecraft.getInstance().player;
			assert player != null;
			BlockPos pos = player.blockPosition();

			assert level != null;
			//~ if 26.2 'isNight' -> 'isDarkOutside'
			if (level.isDarkOutside() && level.getBiome(pos).is(Biomes.WARM_OCEAN)){
				float fade = Math.min(0.3F, (float)(level.getGameTime() % 40L) / 40.0F);
				float value = Math.min(0.3F, fade / 15.0F);

				WakeColor glow = new WakeColor(0.5833333F, 0.3F, value, 1.0F);

				double scrA = cachedSrcA[color.a];
				double invSrcA = cachedInvSrcA[color.a]
						;
				int r = (int)((double)color.r * scrA + (double)glow.r * invSrcA);
				int g = (int)((double)color.g * scrA + (double)glow.g * invSrcA);
				int b = (int)((double)color.b * scrA + (double)glow.b * invSrcA);
				//~ if 26.2 'invertedLogisticCurve' -> 'lightFactor' {
				r = (int)((double) r * lightFactor((float) (lightColor & 255) / 255.0F));
				g = (int)((double) g * lightFactor((float) (lightColor >> 8 & 255) / 255.0F));
				b = (int)((double) b * lightFactor((float) (lightColor >> 16 & 255) / 255.0F));
				//~}
				int a = (int) (color.a * opacity);

				cir.setReturnValue(a << 24 | b << 16 | g << 8 | r);
			}
		}
	}
	*///?}
}
