package dev.vesper.vcc.mixin.tweaks.wakes.fabric;

//? if fabric {
import com.goby56.wakes.WakesClient;
import com.goby56.wakes.config.WakesConfig;
import com.goby56.wakes.render.WakeColor;
//?}
import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import dev.vesper.eveningstarlib.EveningStarLib;
import dev.vesper.vcc.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import dev.vesper.vcc.util.MixinDummy;
//? 1.20.1 && fabric{
/*import org.ladysnake.effective.core.utils.EffectiveUtils;
*///?} 1.21.1 && fabric{
/*import org.ladysnake.effective.utils.EffectiveUtils;
*///?}

//~ if !fabric 'WakeColor' -> 'MixinDummy'
@Mixin(WakeColor.class)
@MixinEnvironment(type = MixinEnvironment.Env.CLIENT)
public class WakeColorMixin {

	//? if fabric {
	@Shadow
	@Final
	public int a;

	@Shadow
	@Final
	public int r;

	@Shadow
	@Final
	public int g;

	@Shadow
	@Final
	public int b;

	@Unique
	private static double VCC$invertedLogisticCurve(float x) {
		//? <=1.21.11{
		/*float k = WakesConfig.shaderLightPassthrough;
		*///?} >=26.2{
		//temp measure till i get familar with the new wakes changes
		float k = Config.ClientConfig.shaderLightPassthrough;
		//?}
		return WakesClient.areShadersEnabled() ? k * (4.0f * Math.pow(x - 0.5f, 3.0f) + 0.5f) : x;
	}

	// I've been really thinking trying to find a cleaner way to do this and I can't come up with anything so...
	@Inject(method = "blend", at = @At("HEAD"), cancellable = true)
	//? <=1.21.11{
	/*private void VCC$blend$head(WakeColor tint, int lightColor, float opacity, CallbackInfoReturnable<WakeColor> cir){
	*///?} >=26.2{
	private void VCC$blend$head(WakeColor tint, float opacity, CallbackInfoReturnable<WakeColor> cir){
	//?}
		//? <=1.21.1{
		/*if (Config.glowingWakes() && EveningStarLib.isModLoaded("effective") && EveningStarLib.isModLoaded("wakes")){
			Level level = Minecraft.getInstance().level;

			Player player = Minecraft.getInstance().player;
			assert player != null;
			BlockPos pos = player.blockPosition();

			if (EffectiveUtils.isGlowingWater(level, pos)){
				float fade = Math.min(0.3F, (float)(level.getGameTime() % 40L) / 40.0F);
				float value = Math.min(0.3F, fade / 15.0F);
				float hue = 0.5833333F;
				float sat = 0.3F;
				WakeColor color = new WakeColor(hue, sat, value, 1.0F);
				int foamLight = 15728880;
				double scrA = Math.pow((float)this.a / 255.0F, WakesConfig.blendStrength * 10.0F);
				int r = (int)((double)this.r * scrA + (double)color.r * ((double)1.0F - scrA));
				int g = (int)((double)this.g * scrA + (double)color.g * ((double)1.0F - scrA));
				int b = (int)((double)this.b * scrA + (double)color.b * ((double)1.0F - scrA));
				r = (int)((double)r * VCC$invertedLogisticCurve(0.0F));
				g = (int)((double)g * VCC$invertedLogisticCurve(0.0F));
				b = (int)((double)b * VCC$invertedLogisticCurve(0.9411765F));
				WakeColor newColor = new WakeColor(r, g, b, (int)((float)this.a * opacity));
				cir.setReturnValue(newColor);
			}
		}
		*///?} >=1.21.11{
		if (Config.glowingWakes() && EveningStarLib.isModLoaded("wakes")) {
			Level level = Minecraft.getInstance().level;

			Player player = Minecraft.getInstance().player;
			assert player != null;
			BlockPos pos = player.blockPosition();
			assert level != null;
			if (level.isDarkOutside() && level.getBiome(pos).is(Biomes.WARM_OCEAN)) {
				float fade = Math.min(0.3F, (float) (level.getGameTime() % 40L) / 40.0F);
				float value = Math.min(0.3F, fade / 15.0F);
				float hue = 0.5833333F;
				float sat = 0.3F;
				WakeColor color = new WakeColor(hue, sat, value, 1.0F);
				int foamLight = 15728880;
				double scrA = Math.pow((double) ((float) this.a / 255.0F), (double) (WakesConfig.blendStrength * 10.0F));
				int r = (int) ((double) this.r * scrA + (double) color.r * ((double) 1.0F - scrA));
				int g = (int) ((double) this.g * scrA + (double) color.g * ((double) 1.0F - scrA));
				int b = (int) ((double) this.b * scrA + (double) color.b * ((double) 1.0F - scrA));
				r = (int) ((double) r * VCC$invertedLogisticCurve(0.0F));
				g = (int) ((double) g * VCC$invertedLogisticCurve(0.0F));
				b = (int) ((double) b * VCC$invertedLogisticCurve(0.9411765F));
				WakeColor newColor = new WakeColor(r, g, b, (int) ((float) this.a * opacity));
				cir.setReturnValue(newColor);
			}
		}
		//?}
	}
	//?}
}
