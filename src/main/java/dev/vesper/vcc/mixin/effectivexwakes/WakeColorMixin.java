package dev.vesper.vcc.mixin.effectivexwakes;

import com.goby56.wakes.WakesClient;
import com.goby56.wakes.config.WakesConfig;
import com.goby56.wakes.render.WakeColor;
import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import dev.vesper.vcc.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.ladysnake.effective.core.utils.EffectiveUtils;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WakeColor.class)
@MixinEnvironment(type = MixinEnvironment.Env.CLIENT)
public class WakeColorMixin {

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
	private static double invertedLogisticCurve(float x) {
		float k = WakesConfig.shaderLightPassthrough;
		return WakesClient.areShadersEnabled() ? k * (4.0f * Math.pow(x - 0.5f, 3.0f) + 0.5f) : x;
	}

	@Inject(method = "blend", at = @At("HEAD"), cancellable = true)
	private void VCC$blend$head(WakeColor tint, int lightColor, float opacity, CallbackInfoReturnable<WakeColor> cir){
		// I need to add the checks which require both making the config and updating ESL then adding it as a dep
		if (Config.glowingWakes && ){
			Level level = Minecraft.getInstance().level;

			Player player = Minecraft.getInstance().player;
			assert player != null;
			BlockPos pos = player.blockPosition();

			//? <=1.21.1{
			/*if (EffectiveUtils.isGlowingWater(level, pos)){
			*///?} >=1.21.11{
			if () {
			//?}
				float fade = Math.min(0.3F, (float)(level.getGameTime() % 40L) / 40.0F);
				float value = Math.min(0.3F, fade / 15.0F);
				float hue = 0.5833333F;
				float sat = 0.3F;
				WakeColor color = new WakeColor(hue, sat, value, 1.0F);
				int foamLight = 15728880;
				double scrA = Math.pow((double)((float)this.a / 255.0F), (double)(WakesConfig.blendStrength * 10.0F));
				int r = (int)((double)this.r * scrA + (double)color.r * ((double)1.0F - scrA));
				int g = (int)((double)this.g * scrA + (double)color.g * ((double)1.0F - scrA));
				int b = (int)((double)this.b * scrA + (double)color.b * ((double)1.0F - scrA));
				r = (int)((double)r * invertedLogisticCurve(0.0F));
				g = (int)((double)g * invertedLogisticCurve(0.0F));
				b = (int)((double)b * invertedLogisticCurve(0.9411765F));
				WakeColor newColor = new WakeColor(r, g, b, (int)((float)this.a * opacity));
				cir.setReturnValue(newColor);
			}
		}
	}
}
