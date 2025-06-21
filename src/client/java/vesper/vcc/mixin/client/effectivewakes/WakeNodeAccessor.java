package vesper.vcc.mixin.client.effectivewakes;

import com.goby56.wakes.simulation.WakeNode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(WakeNode.class)
public interface WakeNodeAccessor {
    @Invoker("<init>")
    static WakeNode create(int x, int y, int z, int floodLevel){
        throw new AssertionError();
    }
}
