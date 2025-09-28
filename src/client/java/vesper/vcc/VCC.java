package vesper.vcc;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import vesper.vcc.tweaks.enchancement.DashKeyBind;

@Environment(EnvType.CLIENT)
public class VCC implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("vcc");

    @Override
    public void onInitialize() {
        //DashKeyBind.init();
    }
}
