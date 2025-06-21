package vesper.vcc.effectivewakes;

import com.goby56.wakes.simulation.WakeHandler;
import com.goby56.wakes.simulation.WakeNode;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Set;

public class CreateRipple {
    public static void createWakeSplash(World world, Vec3d pos, BoatEntity boat) {
        BlockPos blockPos = BlockPos.ofFloored(pos);
        FluidState fluidState = world.getFluidState(blockPos);

        if (fluidState.getFluid() == Fluids.WATER){
            Vec3d boatPos = boat.getPos();

            Set<WakeNode> nodes = WakeNode.Factory.splashNodes(boat, (int) boatPos.getY());

            WakeHandler.getInstance().ifPresent(wakeHandler -> {
                for (WakeNode node : nodes){
                    wakeHandler.insert(node);
                }
                WakeNode center = WakesHelper.createNode(blockPos.getX(), blockPos.getY(), blockPos.getZ(), 3);
                 float boatSpeed = (float) boat.getVelocity().horizontalLength();
                 center.simulationNode.initialValues[1][1] = boatSpeed * 15f;
                 wakeHandler.insert(center);
            });
        }
    }
}
