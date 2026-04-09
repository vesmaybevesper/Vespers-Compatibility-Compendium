package vesper.vcc.leaks.etf;

import dev.vesper.eveningstarlib.fabric.events.ClientRespawnEventCallback;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import traben.entity_texture_features.features.ETFManager;
import traben.entity_texture_features.features.player.ETFPlayerEntity;
import traben.entity_texture_features.features.player.ETFPlayerTexture;
import vesper.vcc.utils.Util;
import java.lang.invoke.VarHandle;

public class UpdatePlayerTextureMap {
    private static final Logger LOGGER = LoggerFactory.getLogger("VCC/ETF");

    public static void init() {
        VarHandle ETF$PLAYERTEXTURE = Util.ReflectionHelper.getFieldFromClass(AvatarRenderer.class, "etf$ETFPlayerTexture", ETFPlayerTexture.class, false);

        ClientRespawnEventCallback.EVENT.register((gameMode, oldPlayer, newPlayer, networkManager) -> {
            try {
                var etfTexture = ETFManager.getInstance().PLAYER_TEXTURE_MAP.get(oldPlayer.getUUID());
                if (etfTexture != null) {
                    if (newPlayer instanceof ETFPlayerEntity playerEntity) {
                        etfTexture.player =  playerEntity;
                    }
                }
            } catch (IllegalArgumentException e) {
                Util.warn("Failed to update ETF player texture");
            }
        });
    }
}
