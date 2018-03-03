package org.team639.lib.led.patterns;

import org.team639.lib.led.LEDColor;
import org.team639.lib.led.LEDPattern;

public class CrossFadePattern extends LEDPattern {

    private LEDPattern patternA;
    private LEDPattern patternB;
    private int fadeTicks;

    private int cTicks = 0;

    public CrossFadePattern(LEDPattern a, LEDPattern b, int fadeTicks) {
        patternA = a;
        patternB = b;
        this.fadeTicks = fadeTicks;
    }

    private LEDColor[] mix(LEDColor[] a, LEDColor[] b, float percent) {
        if(a.length != b.length) return a;
        LEDColor[] o = new LEDColor[a.length];
        for(int i = 0; i < o.length; i++) {

        }
        return o;
    }

    /**
     * Called whenever the pattern becomes active.
     *
     * @return Initial values to be written to the led strip.
     */
    @Override
    public LEDColor[] start() {
        cTicks = 0;

        return new LEDColor[0];
    }

    /**
     * Called whenever the pattern becomes inactive.
     */
    @Override
    public void stop() {

    }

    /**
     * Returns an array of colors to be written to the LEDs.
     *
     * @return An array of colors to be written to the LEDs.
     */
    @Override
    public LEDColor[] nextPortion() {
        return new LEDColor[0];
    }
}
