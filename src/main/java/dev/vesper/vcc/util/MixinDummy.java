package dev.vesper.vcc.util;

/**
 * Some Mixins are only needed on certain versions, but removing the @Mixin causes errors
 * with the class not being found. To get around this I've created a blank Class that can
 * be mixed-into without interacting with anything that actually matters allowing the mod to compile and run.
 */
public class MixinDummy {
}
