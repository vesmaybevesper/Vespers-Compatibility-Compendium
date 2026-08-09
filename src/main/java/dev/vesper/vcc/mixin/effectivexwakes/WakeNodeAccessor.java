package dev.vesper.vcc.mixin.effectivexwakes;

import com.goby56.wakes.simulation.WakeNode;
import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(WakeNode.class)
@MixinEnvironment(type = MixinEnvironment.Env.CLIENT)
public interface WakeNodeAccessor {
	@Invoker("<init>")
	static WakeNode create(int x, int y, int z, int floodLevel){
		throw new AssertionError();
	}
}
