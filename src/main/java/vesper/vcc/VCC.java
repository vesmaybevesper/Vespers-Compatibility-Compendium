package vesper.vcc;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.RandomSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VCC implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("VCC");
    public static final String[] launchMsg =
            {"I just hit things with hammers until they work", "Oh look! A penny!", "Would you be moved in with by me?",
                    "God has cursed me for my hubris, and my work is never finished", "Don't ask me to do this again",
                    "Toss a coin to your Vesper"};

    @Override
    public void onInitialize() {
        int randomMsg = RandomSource.create().nextIntBetweenInclusive(0, launchMsg.length - 1);
        LOGGER.info("[VCC]: {}", launchMsg[randomMsg]);
        //ClearRemaining.init();
    }
}
