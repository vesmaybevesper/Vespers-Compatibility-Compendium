package vesper.vcc;

import eu.midnightdust.lib.config.MidnightConfig;

public class Config extends MidnightConfig {
    public static final String EFFECTIVEWAKES = "Effective x Wakes";
    public static final String EFFECTIVEEFFECTUAL = "Effective x Effectual";
    public static final String EFFECTIVEPARTICLERAIN = "Effective x Particle Rain";

    @Entry(category = EFFECTIVEWAKES) public static boolean EffectiveXWakes = true;
    @Entry(category = EFFECTIVEWAKES) public static boolean oarSplash = true;
    @Entry(category = EFFECTIVEWAKES) public static boolean WakeRenderMixin = true;
    // @Entry(category = EFFECTIVEWAKES) public static boolean DisableEffectiveSplash = true;


    @Entry(category = EFFECTIVEEFFECTUAL) public static boolean EffectiveXEffectual = true;
    @Entry(category = EFFECTIVEEFFECTUAL) public static boolean useEffectiveBubbleChest = true;
    @Entry(category = EFFECTIVEEFFECTUAL) public static boolean useEffectiveBubbleBreath = true;
    @Entry(category = EFFECTIVEEFFECTUAL) public static boolean useEffectiveBubblePot = true;
    @Entry(category = EFFECTIVEEFFECTUAL) public static boolean useEffectiveDroplet = true;

    @Entry(category = EFFECTIVEPARTICLERAIN) public static boolean EffectiveXParticleRain = true;
    @Entry(category = EFFECTIVEPARTICLERAIN) public static boolean EPRDisableOverlap = true;
    @Entry(category = EFFECTIVEPARTICLERAIN) public static boolean dropletRipple = true;


}