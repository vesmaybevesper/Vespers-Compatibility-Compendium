package vesper.vcc;

import com.google.gson.GsonBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.autogen.AutoGen;
import dev.isxander.yacl3.config.v2.api.autogen.Boolean;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.resources.Identifier;

public class YACLConfig {
    public static ConfigClassHandler<YACLConfig> CONFIG = ConfigClassHandler.createBuilder(YACLConfig.class)
            .id(Identifier.fromNamespaceAndPath("vcc", "vcc_config"))
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(FabricLoader.getInstance().getConfigDir().resolve("vcc_config.json5"))
                    .appendGsonBuilder(GsonBuilder::setPrettyPrinting)
                    .setJson5(true)
                    .build())
            .build();

    public static Screen Config(Screen parent){
        return CONFIG.generateGui().generateScreen(parent);
    }

    @AutoGen(category = "effWakes")
    @Boolean(formatter = Boolean.Formatter.ON_OFF, colored = true)
    @SerialEntry
    public static boolean oarSplash = true;

    @AutoGen(category = "effWakes")
    @Boolean(formatter = Boolean.Formatter.ON_OFF, colored = true)
    @SerialEntry
    public static boolean mimicGlow = true;

    @AutoGen(category = "eff^2")
    @Boolean(formatter = Boolean.Formatter.ON_OFF, colored = true)
    @SerialEntry
    public static boolean bubbleChest = true;

    @AutoGen(category = "eff^2")
    @Boolean(formatter = Boolean.Formatter.ON_OFF, colored = true)
    @SerialEntry
    public static boolean bubbleBreath = true;

    @AutoGen(category = "eff^2")
    @Boolean(formatter = Boolean.Formatter.ON_OFF, colored = true)
    @SerialEntry
    public static boolean bubblePot = true;

    @AutoGen(category = "eff^2")
    @Boolean(formatter = Boolean.Formatter.ON_OFF, colored = true)
    @SerialEntry
    public static boolean effectiveDroplet = true;

    @AutoGen(category = "eff^2")
    @Boolean(formatter = Boolean.Formatter.ON_OFF, colored = true)
    @SerialEntry
    public static boolean mouthSteam = true;

    @AutoGen(category = "effPR")
    @Boolean(formatter = Boolean.Formatter.ON_OFF, colored = true)
    @SerialEntry
    public static boolean dropletRipple = true;
}
