package vesper.vcc.mixin.client.accessors.jei;

import mezz.jei.library.recipes.RecipeTransferManager;
import net.minecraft.world.inventory.AbstractContainerMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Set;

@Mixin(value = RecipeTransferManager.class, remap = false)
public interface RecipeTransferManagerAccessor {
    @Accessor("unsupportedContainers")
    Set<AbstractContainerMenu> getUnsupportedContainer();
}
