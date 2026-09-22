package dev.vesper.vcc.util;

/**
 * Simple flags for setting if the next instance of a particle should be modified
 * in some way, has the potential to cause other effects that spawn at the same
 * time to look a little weird. But what can ya do?
 */
public class ParticleModContext {
	public static boolean fixGlowDropForPlayerDrip = false;
	public static boolean fixCascadeForBreath = false;
}
