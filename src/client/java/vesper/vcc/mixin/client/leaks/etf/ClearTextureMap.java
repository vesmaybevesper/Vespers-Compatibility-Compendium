package vesper.vcc.mixin.client.leaks.etf;

import net.fabricmc.loader.api.FabricLoader;

public class ClearTextureMap {

    private void clear(){
        if (FabricLoader.getInstance().isModLoaded("entity_texture_features")){

        }
    }
}
