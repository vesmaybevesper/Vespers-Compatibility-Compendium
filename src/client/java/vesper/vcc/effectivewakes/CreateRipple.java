package vesper.vcc.effectivewakes;

import com.goby56.wakes.simulation.WakeHandler;
import com.goby56.wakes.simulation.WakeNode;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;

import java.util.Set;

public class CreateRipple {
    public static void createWakeSplash(Level world, Vec3 pos, Boat boat) {
        BlockPos blockPos = BlockPos.containing(pos);
        FluidState fluidState = world.getFluidState(blockPos);

        if (fluidState.getType() == Fluids.WATER){
            Vec3 boatPos = boat.position();

            Set<WakeNode> nodes = WakeNode.Factory.splashNodes(boat, (int) boatPos.y());

            WakeHandler.getInstance().ifPresent(wakeHandler -> {
                for (WakeNode node : nodes){
                    wakeHandler.insert(node);
                }
                WakeNode center = WakesHelper.createNode(blockPos.getX(), blockPos.getY(), blockPos.getZ(), 3);
                 float boatSpeed = (float) boat.getDeltaMovement().horizontalDistance();
                 center.simulationNode.initialValues[1][1] = boatSpeed * 15f;
                 wakeHandler.insert(center);
            });
        }
    }
}
