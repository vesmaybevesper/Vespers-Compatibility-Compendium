package vesper.vcc.effectivewakes;

import com.goby56.wakes.simulation.WakeNode;
import vesper.vcc.mixin.client.effectivewakes.WakeNodeAccessor;

public class WakesHelper {
    public static WakeNode createNode(int x, int y, int z, int floodLevel) {
        return WakeNodeAccessor.create(x, y, z, floodLevel);
    }
}
