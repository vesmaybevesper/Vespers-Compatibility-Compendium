package dev.vesper.vcc.mixin.fixes.leaks.minecraft;

import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.entity.EntityTickList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(EntityTickList.class)
@MixinEnvironment(type = MixinEnvironment.Env.SERVER)
public class EntityTickListMixin {
	//? 1.21.1{

	/*@Shadow
	private Int2ObjectMap<Entity> passive;

	@Inject(method = "forEach", at = @At(value = "FIELD", target = "Lnet/minecraft/world/level/entity/EntityTickList;iterated:Lit/unimi/dsi/fastutil/ints/Int2ObjectMap;", shift = At.Shift.AFTER, ordinal = 2))
	private void vcc$clearPassive$feild(Consumer<Entity> consumer, CallbackInfo ci){
		this.passive.clear();
	}
	*///?}
}
