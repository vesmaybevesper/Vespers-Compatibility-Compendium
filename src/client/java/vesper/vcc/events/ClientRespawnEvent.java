package vesper.vcc.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.Connection;

public interface ClientRespawnEvent {
    Event<ClientRespawnEvent> EVENT = EventFactory.createArrayBacked(ClientRespawnEvent.class, callbacks -> (pc, oldPlayer, newPlayer, networkManager) -> {
        for (ClientRespawnEvent e : callbacks)
            e.onPlayerRespawn(pc, oldPlayer, newPlayer, networkManager);
    });

    void onPlayerRespawn(MultiPlayerGameMode gameMode, LocalPlayer oldPlayer, LocalPlayer newPlayer, Connection clientConnection);
}
