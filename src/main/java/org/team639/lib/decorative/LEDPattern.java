package org.team639.lib.decorative;

/**
 * An abstract class representing an LED pattern
 */
public abstract class LEDPattern {

    /**
     * Called whenever the pattern becomes active.
     */
    public abstract void start();

    /**
     * Called whenever the pattern becomes inactive.
     */
    public abstract void stop();

    /**
     * Returns an array of colors to be written to the LEDs.
     * @return An array of colors to be written to the LEDs.
     */
    public abstract LEDColor[] nextPortion();
}
