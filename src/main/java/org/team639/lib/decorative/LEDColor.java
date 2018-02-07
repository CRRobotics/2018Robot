package org.team639.lib.decorative;

public class LEDColor {
    private int brightness;
    private int red;
    private int green;
    private int blue;

    public LEDColor(int red, int green, int blue) {
        this(0xff, red, green, blue);
    }

    public LEDColor(int brightness, int red, int green, int blue) {
        this.brightness = brightness;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public byte[] toByteArray() {
        byte[] arr = {(byte)brightness, (byte)blue, (byte)green, (byte)red};
        return arr;
    }
}
