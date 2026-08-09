package dev.vesper.vcc.mixin.effectivexwakes;

import com.goby56.wakes.render.WakeRenderer;
import com.goby56.wakes.render.WakeTexture;
import com.goby56.wakes.simulation.Brick;
import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import net.minecraft.client.Camera;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(WakeRenderer.class)
@MixinEnvironment(type = MixinEnvironment.Env.CLIENT)
public interface WakeRendererAccessor {
	@Invoker("render")
	void invokeRenderer(Matrix4f matrix, Camera camera, Brick brick, WakeTexture texture);

	@Invoker("initTextures")
	void invokeRenderTextures();
}
