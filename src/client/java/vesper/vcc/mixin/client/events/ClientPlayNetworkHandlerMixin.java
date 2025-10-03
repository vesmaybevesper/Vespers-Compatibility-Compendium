package vesper.vcc.mixin.client.events;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientCommonPacketListenerImpl;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.CommonListenerCookie;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.Connection;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import vesper.vcc.events.ClientRespawnEvent;

@Mixin(ClientPacketListener.class)
public abstract class ClientPlayNetworkHandlerMixin extends ClientCommonPacketListenerImpl {
    protected ClientPlayNetworkHandlerMixin(Minecraft minecraft, Connection connection, CommonListenerCookie commonListenerCookie) {
        super(minecraft, connection, commonListenerCookie);
    }

    @ModifyArg(method = "handleRespawn", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientLevel;addEntity(Lnet/minecraft/world/entity/Entity;)V"))
    private Entity onClientPlayerRespawn(Entity entity, @Local(ordinal = 0) LocalPlayer clientPlayer){
        LocalPlayer player = (LocalPlayer) entity;
        ClientRespawnEvent.EVENT.invoker().onPlayerRespawn(this.minecraft.gameMode, clientPlayer, player, this.connection);
        return entity;
    }
}
