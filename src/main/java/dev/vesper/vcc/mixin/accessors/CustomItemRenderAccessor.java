package dev.vesper.vcc.mixin.accessors;

import com.anthonyhilyard.iceberg.renderer.CustomItemRenderer;
import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.decoration.ArmorStand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

//? <=1.21.1{
/*import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.horse.Horse;
*///?} >=1.21.11{
import net.minecraft.world.entity.animal.equine.Horse;
import net.minecraft.world.entity.animal.wolf.Wolf;
//?}

@Mixin(CustomItemRenderer.class)
@MixinEnvironment(type = MixinEnvironment.Env.CLIENT)
public interface CustomItemRenderAccessor {
	@Mutable
	//? <=1.21.1{
	/*@Accessor("entity")
	*///?} >=1.21.11{
	@Accessor("cachedSpawnEntity")
	//?}
	static void setEntity(Entity entity){}

	@Mutable
	@Accessor("horse")
	static void setHorse(Horse horse){}

	@Mutable
	@Accessor("armorStand")
	static void setArmorStand(ArmorStand armorStand){}

	@Mutable
	@Accessor("wolf")
	static void setWolf(Wolf wolf){}
}
